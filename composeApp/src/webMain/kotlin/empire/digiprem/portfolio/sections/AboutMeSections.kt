package empire.digiprem.portfolio.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import coil3.Image
import empire.digiprem.portfolio.design_system.ButtonType
import empire.digiprem.portfolio.design_system.PortfolioButton
import empire.digiprem.portfolio.design_system.currentDeviceConfigure
import empire.digiprem.portfolio.design_system.layout.SectionLayout
import empire.digiprem.portfolio.theme.labelXSmall
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Parameters
import io.ktor.http.isSuccess
import io.ktor.util.Platform
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import portfolionanaadrien.composeapp.generated.resources.Res
import portfolionanaadrien.composeapp.generated.resources.compose_multiplatform

@Composable
fun AboutMeSections(
    modifier: Modifier = Modifier,
) {
    var name = remember { TextFieldState("") }
    var reason by remember { mutableStateOf("Recrutement") } // Option par défaut
    val reasons = listOf("Recrutement", "Partenariat", "Curiosité", "Autre")
    var showDialog by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val isMobileDevice = currentDeviceConfigure().isMobileDevice()

    val BoxContent = @Composable {
        Column(
            Modifier
            .graphicsLayer {
                rotationZ = if (!isMobileDevice) -5f else 0f
            }
            .widthIn(max = if (isMobileDevice) 350.dp else 280.dp)
            .heightIn(max = 350.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.background).padding(10.dp)
        ) {
            Box(
                Modifier.fillMaxWidth().fillMaxHeight().padding(bottom = 30.dp).clip(RoundedCornerShape(8.dp))
                    .background(Color.Gray)
            ) {
                Image(
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,

                    )
            }

        }
    }

    SectionLayout(
        title = "About Me",
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically)
        ) {
            if (isMobileDevice) {
                BoxContent()
            }

            Row(
                modifier = Modifier.wrapContentHeight().fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(60.dp)
            ) {
                if (!isMobileDevice) {
                    BoxContent()
                }
                Column(
                    modifier = Modifier.widthIn(min = 400.dp).padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Nana Adrien",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    Box(
                        modifier = Modifier.wrapContentSize().clip(RoundedCornerShape(4.dp))
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 5.dp), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "~Dev Multiplatform ~",
                            style = MaterialTheme.typography.labelXSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = """
                            Ahoy there! 🎉 I'm a seasoned Full-Stack developer based in Pune, India, proudly waving my Information Technology undergrad cape from SPPU! 🎓 I've delved into web development since my freshman days. 💻 My focus lies in crafting websites and applications that seamlessly blend functionality. Oh, and did I mention my love for building full-stack clones and side projects?💡It's kind of my thing! 🚀
                        """.trimIndent(),
                        style = MaterialTheme.typography.labelSmall,
                        textAlign = TextAlign.Justify,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    )
                    Spacer(Modifier.height(10.dp))
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
                    ) {
                        PortfolioButton(
                            text = "Resume",
                        ) {}
                        PortfolioButtonDownload()
                        PortfolioButton(
                            type = ButtonType.SECONDARY,
                            text = "Download My CV",
                        ) { showDialog = true }
                    }
                    if (showDialog) {
                        DisableSelection {
                            AlertDialog(
                                onDismissRequest = { showDialog = false },
                                confirmButton = {

                                    PortfolioButton(
                                        text = "Télécharger",
                                        enabled = name.text.isNotBlank(),
                                        onClick = {
                                            scope.launch {
                                                sendDataToService(
                                                    name.text.toString(),
                                                    reason,
                                                    onSuccusses = {
                                                        showDialog = false
                                                    },
                                                    onFailure = {
                                                        showDialog = true
                                                    })
                                            }
                                            // Envoi des infos
                                            // downloadCV()                   // Lancement du téléchargement

                                        }
                                    )
                                },
                                title = { Text("Télécharger mon CV", style = MaterialTheme.typography.titleMedium) },
                                text = {
                                    Column {
                                        PortfolioTextField(
                                            placeholder = "Votre nom / Entreprise",
                                            state = name,
                                            modifier = Modifier.fillMaxWidth().height(40.dp),
                                        )
                                        Spacer(Modifier.height(16.dp))
                                        Text("Motif :", fontWeight = FontWeight.Bold)
                                        reasons.forEach { option ->
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                RadioButton(
                                                    selected = (reason == option),
                                                    onClick = { reason = option })
                                                Text(option)
                                            }
                                        }
                                    }
                                }
                            )
                        }
                    }
                }

            }
        }


    }
}

suspend fun sendDataToService(name: String, reason: String,onSuccusses: () -> Unit,onFailure: () -> Unit) {
    val client = HttpClient()
    try {
     val result=   client.post("https://formspree.io/f/xjgaodye") {
            setBody(FormDataContent(Parameters.build {
                append("name", name)
                append("reason", reason)
            }))
        }

        if (result.status.isSuccess()) {
            onSuccusses()
        }else{
            onFailure()
        }
    } catch (e: Exception) {
        println("Erreur d'envoi")
        onFailure()
    }
}


@Composable
expect fun PortfolioButtonDownload()