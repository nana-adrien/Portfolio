package empire.digiprem.portfolio

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.renderComposeScene
import androidx.compose.ui.window.ComposeViewport
import androidx.compose.ui.window.ComposeViewportConfiguration
import androidx.navigation.ExperimentalBrowserHistoryApi
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.bindToBrowserNavigation
import androidx.navigation.toRoute
import coil3.util.Logger
import empire.digiprem.portfolio.app.App
import empire.digiprem.portfolio.app.NavigationGraph
import empire.digiprem.portfolio.app.Section
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.serialization.builtins.serializer



@OptIn(ExperimentalComposeUiApi::class)
actual fun PlatformComposeViewport(
    configure: ComposeViewportConfiguration.() -> Unit,
    content: @Composable (() -> Unit)
){
    val body=document.body?:return
    // On cherche l'élément où Compose s'affiche (souvent d'ID "root")
    val root = document.getElementById("root")

    // On force l'autorisation du clic droit sur cet élément
    root?.addEventListener("contextmenu", { event ->
        // On NE FAIT PAS event.preventDefault() ici.
        // On laisse l'événement remonter au navigateur.
        event.stopPropagation()
    }, true)

    ComposeViewport (
        viewportContainerId = "root",
        configure=configure,
        content=content
    )
}


fun setupGlobalErrorHandler() {

}
@OptIn(ExperimentalBrowserHistoryApi::class)
actual suspend fun onNavHostReady(navController: NavController) {

    val initRoute = window.location.hash.takeIf { it.isNotEmpty() }
        ?: window.location.pathname.substringAfter("/", "")
    print(initRoute)
    when {
        initRoute.isEmpty() /*|| initRoute == "/"*/ || initRoute.startsWith("#home") -> {
            val section = initRoute.substringAfter("?section=")
            val normalSection= Section.entries.firstOrNull{it.name==section}?.name
            if (normalSection == null && initRoute.isNotEmpty()) {
                navController.navigate(NavigationGraph.Error404(path = initRoute.removePrefix("#")))
            }else{
                navController.navigate(NavigationGraph.HomeScreen(normalSection?:Section.home.name))
            }
        }
        else -> {
            navController.navigate(NavigationGraph.Error404(path = initRoute.removePrefix("#")))
        }
    }

    navController.bindToBrowserNavigation() { entry ->
        val route = entry.destination.route.orEmpty()
        when {
            // Identifies the route using its serial descriptor
            route.startsWith(NavigationGraph.HomeScreen.serializer().descriptor.serialName) -> {
                // Sets the corresponding URL fragment to "#start"
                // instead of "#org.example.app.StartScreen"
                //
                // This string must always start with the `#` character to keep
                // the processing at the front end
                val section= entry.toRoute<NavigationGraph.HomeScreen>().section
                "#home?section=$section"
            }
            // Route 404 (Si vous avez une classe dédiée)
            route.contains(NavigationGraph.Error404.serializer().descriptor.serialName) -> {
                // Ici, on récupère l'URL erronée qui a été stockée dans la route 404
                val invalidPath = entry.toRoute<NavigationGraph.Error404>().path
                "#$invalidPath"
            }
            else -> "#not-found" // Fallback par défaut
        }
    }
}

