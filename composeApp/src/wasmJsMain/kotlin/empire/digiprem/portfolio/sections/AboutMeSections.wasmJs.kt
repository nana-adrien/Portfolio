package empire.digiprem.portfolio.sections

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.dp
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.skia.Color
import org.jetbrains.skia.Paint
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLParagraphElement
import org.w3c.dom.HTMLSpanElement
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
        Text("Download image \uD83D\uDCE5")
    }

}