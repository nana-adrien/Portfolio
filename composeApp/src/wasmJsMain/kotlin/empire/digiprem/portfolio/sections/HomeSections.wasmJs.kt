package empire.digiprem.portfolio.sections

import kotlinx.browser.window

actual fun openLink(url: String,openLinkTarget: OpenLinkTarget) {
    window.open(url, openLinkTarget.target) // _blank ouvre dans un nouvel onglet
}