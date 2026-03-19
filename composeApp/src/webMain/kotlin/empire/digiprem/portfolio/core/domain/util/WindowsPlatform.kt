package empire.digiprem.portfolio.core.domain.util

import empire.digiprem.portfolio.core.domain.enums.OpenLinkTarget

expect object WindowsPlatform {
     fun openLink(url: String, openLinkTarget: OpenLinkTarget = OpenLinkTarget.SAME_ONGLET)
     fun getBaseUrl(): String
     fun getNavigatorLanguage():String
}