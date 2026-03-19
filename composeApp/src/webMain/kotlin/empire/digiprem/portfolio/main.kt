package empire.digiprem.portfolio

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewportConfiguration
import androidx.navigation.ExperimentalBrowserHistoryApi
import androidx.navigation.NavController
import empire.digiprem.portfolio.app.App
import empire.digiprem.portfolio.core.domain.Language
import empire.digiprem.portfolio.core.domain.services.TranslationService
import empire.digiprem.portfolio.core.domain.services.TranslationService.translationsLoaded
import empire.digiprem.portfolio.core.domain.services.UserSettingService
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalBrowserHistoryApi::class,
    ExperimentalSerializationApi::class
)
suspend fun main() {
    Language.entries.forEach { language ->
        TranslationService.load(language)
    }
    translationsLoaded=true


    PlatformComposeViewport {
        UserSettingService.initTheme(isSystemInDarkTheme())
        if (translationsLoaded){
            App(
                onNavHostReady = { onNavHostReady(it) }
            )
        }

    }
}

@OptIn(ExperimentalComposeUiApi::class)
expect fun PlatformComposeViewport(
    configure: ComposeViewportConfiguration.() -> Unit = {},
    content: @Composable (() -> Unit) = { }
)

expect suspend fun onNavHostReady(navController: NavController)