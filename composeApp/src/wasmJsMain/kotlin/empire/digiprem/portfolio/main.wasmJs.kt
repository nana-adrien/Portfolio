package empire.digiprem.portfolio

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.compose.ui.window.ComposeViewportConfiguration
import androidx.navigation.ExperimentalBrowserHistoryApi
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.bindToBrowserNavigation
import androidx.navigation.toRoute
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
    ComposeViewport (
        body,
        configure=configure,
        content=content
    )
}

@OptIn(ExperimentalBrowserHistoryApi::class)
actual suspend fun onNavHostReady(navController: NavController) {

    val initRoute = window.location.hash.takeIf { it.isNotEmpty() }
        ?: window.location.pathname.substringAfter("/", "")
    when {
        initRoute.isEmpty() || initRoute == "/" || initRoute.startsWith("home") -> {
            val section = initRoute.substringAfter("?section=")
            val normalSection= Section.entries.firstOrNull{it.name==section}?.name?:Section.home.name
            navController.navigate(NavigationGraph.HomeScreen(normalSection))
        }
        else -> {
            navController.navigate(NavigationGraph.Error404)
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
            else -> "#not-found"
        }
    }
}