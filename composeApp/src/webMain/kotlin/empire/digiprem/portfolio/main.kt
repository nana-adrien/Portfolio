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
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.serializer

@OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalBrowserHistoryApi::class,
    ExperimentalSerializationApi::class
)
fun main() {
   PlatformComposeViewport {
        App(
            onNavHostReady = { onNavHostReady(it)}
        )
    }
}
@OptIn(ExperimentalComposeUiApi::class)
expect fun PlatformComposeViewport(
    configure: ComposeViewportConfiguration.() -> Unit = {},
    content: @Composable (() -> Unit) = { }
)

expect suspend fun onNavHostReady(navController: NavController)