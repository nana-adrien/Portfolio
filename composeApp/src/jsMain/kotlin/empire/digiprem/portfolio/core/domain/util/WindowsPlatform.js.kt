package empire.digiprem.portfolio.core.domain.util

import empire.digiprem.portfolio.core.domain.enums.OpenLinkTarget
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLAnchorElement

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
    actual fun downloadField(fileFinallyName: String, fileUrl: String) {

        val imagePath = "composeResources/portfolionanaadrien.composeapp.generated.resources/$fileUrl"
        val extension = imagePath.substringAfterLast('.', "")
        // 2. Créer un élément 'a' invisible
        val link = document.createElement("a") as HTMLAnchorElement
        link.href = imagePath
        link.download = "$fileFinallyName.$extension" // Nom du fichier final pour l'utilisateur

        // 3. Ajouter au document, cliquer, puis supprimer
        document.body?.appendChild(link)
        link.click()
        document.body?.removeChild(link)
    }
}