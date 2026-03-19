package empire.digiprem.portfolio.core.domain.util

import empire.digiprem.portfolio.core.domain.enums.OpenLinkTarget
import kotlinx.browser.window

actual object WindowsPlatform {
    actual fun openLink(url: String,openLinkTarget: OpenLinkTarget) {
        window.open(url, openLinkTarget.target) // _blank ouvre dans un nouvel onglet
    }

    actual fun getBaseUrl(): String {
        val baseUrl = "${window.location.protocol}//${window.location.host}"
        return baseUrl
    }
    actual fun getNavigatorLanguage():String=window.navigator
        .language.substringBefore("-").lowercase()
}