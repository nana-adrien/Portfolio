package empire.digiprem.portfolio.sections

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import empire.digiprem.portfolio.core.design_system.ButtonType
import empire.digiprem.portfolio.core.design_system.PortfolioButton
import empire.digiprem.portfolio.core.design_system.PortfolioImage
import empire.digiprem.portfolio.core.design_system.animation.ShimmerSkeleton
import empire.digiprem.portfolio.core.design_system.currentDeviceConfigure
import empire.digiprem.portfolio.core.design_system.layout.SectionLayout
import empire.digiprem.portfolio.core.domain.TranslationManager
import empire.digiprem.portfolio.theme.labelXSmall
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Parameters
import io.ktor.http.isSuccess
import kotlinx.coroutines.launch
import portfolionanaadrien.composeapp.generated.resources.Res

@Composable
fun AboutMeSections(
    modifier: Modifier = Modifier,
) {
    var email = remember { TextFieldState("") }
    var name = remember { TextFieldState("") }
    var reason by remember { mutableStateOf("") } // Option par défaut
    val reasons =
        listOf("form_reason_recruitment", "form_reason_partnership", "form_reason_curiosity", "form_reason_other")
    var showDialog by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val isMobileDevice = currentDeviceConfigure().isMobileDevice()
    val interactionSource = remember { MutableInteractionSource() }
    val isHover by interactionSource.collectIsHoveredAsState()
    val imageFilter by animateFloatAsState(targetValue = if (isHover) 1f else 0f)
    val translation by animateFloatAsState(targetValue = if (isHover) -10f else 0f)
    val BoxContent = @Composable {
        Column(
            Modifier
                .graphicsLayer {
                    rotationZ = if (!isMobileDevice) -5f else 0f
                    translationY = translation
                }
                .widthIn(max = if (isMobileDevice) 350.dp else 280.dp)
                .heightIn(max = 350.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.background).padding(10.dp)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .hoverable(interactionSource = interactionSource)
                    .padding(bottom = 30.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                PortfolioImage(
                    modifier = Modifier.fillMaxSize(),
                    image = Res.getUri("drawable/plan_de_travail_de_k_n_a.jpeg"),
                    onLoading = { ShimmerSkeleton(Modifier.fillMaxSize()) },
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.colorMatrix(
                        ColorMatrix().apply { setToSaturation(imageFilter) } // 0f = grayscale
                    )
                )
            }

        }
    }

    SectionLayout(
        title = TranslationManager.getString("about_me"),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 30.dp),
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
                                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 5.dp), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = TranslationManager.getString("profile_tagline"),
                            style = MaterialTheme.typography.labelXSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = TranslationManager.getString("profile_about_desc"),
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
                        /*PortfolioButton(
                            type = ButtonType.SECONDARY,
                            text = TranslationManager.getString("download_cv"),
                            model = Icons.Default.Download,
                        ) { showDialog = true }*/
                    }
                    if (showDialog) {
                        DisableSelection {
                            AlertDialog(
                                onDismissRequest = { showDialog = false },
                                confirmButton = {

                                    PortfolioButton(
                                        text = TranslationManager.getString("download"),
                                        enabled = name.text.isNotBlank() && reason.isNotBlank() && if(reason!="form_reason_other") email.text.isNotBlank() else true,
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
                                        }
                                    )
                                },
                                title = {
                                    Text(
                                        TranslationManager.getString("download_cv"),
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                },
                                text = {
                                    Column (
                                        verticalArrangement = Arrangement.spacedBy(20.dp),
                                    ){
                                        PortfolioTextField(
                                            placeholder = TranslationManager.getString("form_name"),
                                            state = name,
                                            modifier = Modifier.fillMaxWidth().height(50.dp),
                                        )
                                        if (reason!="form_reason_other" && reason.isNotBlank()) {
                                            Text(TranslationManager.getString("abaout_form_email_desc"), style = MaterialTheme.typography.labelMedium,color = MaterialTheme.colorScheme.onBackground)
                                            PortfolioTextField(
                                                placeholder = TranslationManager.getString("contact_form_email_placeholder"),
                                                state = email,
                                                modifier = Modifier.fillMaxWidth().height(50.dp),
                                            )
                                        }
                                        Text(TranslationManager.getString("form_reason"), fontWeight = FontWeight.Bold,color = MaterialTheme.colorScheme.onBackground)
                                        Column  (
                                            verticalArrangement = Arrangement.spacedBy(7.dp),
                                        ){
                                            reasons.forEach { option ->
                                                Row(
                                                    modifier = Modifier.fillMaxWidth().clickable { reason = option },
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    RadioButton(
                                                        selected = (reason == option),
                                                        onClick = { reason = option })
                                                    Text(
                                                        TranslationManager.getString(option),
                                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                                                    )
                                                }
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

suspend fun sendDataToService(name: String, reason: String, onSuccusses: () -> Unit, onFailure: () -> Unit) {
    val client = HttpClient()
    try {
        val result = client.post("https://formspree.io/f/xjgaodye") {
            setBody(FormDataContent(Parameters.build {
                append("name", name)
                append("reason", reason)
            }))
        }

        if (result.status.isSuccess()) {
            onSuccusses()
        } else {
            onFailure()
        }
    } catch (e: Exception) {
        println("Erreur d'envoi")
        onFailure()
    }
}


@Composable
expect fun PortfolioButtonDownload()