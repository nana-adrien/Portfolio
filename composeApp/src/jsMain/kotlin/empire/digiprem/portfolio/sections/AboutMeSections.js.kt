package empire.digiprem.portfolio.sections

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.browser.document
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.HTMLParagraphElement
import org.w3c.dom.MutationObserver
import org.w3c.dom.MutationObserverInit

@Composable
actual fun PortfolioButtonDownload() {
    Button(onClick = {
        // 1. Récupérer le chemin de l'image (doit correspondre au nom dans composeResources/drawable)
        val imagePath = "composeResources/portfolionanaadrien.composeapp.generated.resources/drawable/capture.png"

        // 2. Créer un élément 'a' invisible
        val link = document.createElement("a") as HTMLAnchorElement
        link.href = imagePath
        link.download = "mon_image_telechargee.png" // Nom du fichier final pour l'utilisateur

        // 3. Ajouter au document, cliquer, puis supprimer
        document.body?.appendChild(link)
        link.click()
        document.body?.removeChild(link)

        // eval("window.goatcounter.count({path: 'download-cv', title: 'Téléchargement CV', event: true})")
    }) {
        TranslatableText("Download image \uD83D\uDCE5")
    }

}

@OptIn(ExperimentalWasmJsInterop::class)
@Composable
fun TranslatableText(originalText: String) {
    var translatedText by remember { mutableStateOf(originalText) }

    // DIV pour traduction
    DisposableEffect(originalText) {
        val div = document.createElement("p") as HTMLParagraphElement
        div.textContent = originalText

// 1. Indiquer explicitement que c'est traductible
        div.setAttribute("translate", "yes")
        div.setAttribute("overflow", "hidden")

// 2. Le rendre "invisible" mais techniquement présent pour le traducteur
        div.style.apply {
            opacity
        }

        document.body?.appendChild(div)

      /*  val observer = MutationObserver { _, _ ->
            translatedText = div.textContent ?: originalText
        }

        val options = MutationObserverInit(
            attributes = true,
            subtree = true,
            attributeFilter = arrayOf("class", "style").unsafeCast<JsArray<String>>() // ✅ vrai JS array
        )


// L'astuce : on le passe en tant que "dynamic" pour que le bridge
// Wasm-JS ne vérifie pas les champs non-définis (comme attributeFilter)
        observer.observe(div, options)*/
        onDispose {
            //observer.disconnect()
            div.remove()
        }
    }

    // Affichage sur Canvas
    Text(translatedText)

}