package empire.digiprem.portfolio.sections.about.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import empire.digiprem.portfolio.core.design_system.ButtonType
import empire.digiprem.portfolio.core.design_system.PortfolioButton
import empire.digiprem.portfolio.core.design_system.components.form.FormErrorContainer
import empire.digiprem.portfolio.core.design_system.components.form.PortfolioTextField
import empire.digiprem.portfolio.core.design_system.currentDeviceConfigure
import empire.digiprem.portfolio.core.design_system.layout.SectionLayout
import empire.digiprem.portfolio.core.domain.services.TranslationService
import empire.digiprem.portfolio.sections.about.presentation.components.ProfilePictureUrl
import empire.digiprem.portfolio.theme.labelXSmall
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*


enum class AboutFormReason(val value: String) {
    RECRUITMENT("form_reason_recruitment"),
    PARTNERSHIP("form_reason_partnership"),
    CURIOSITY("form_reason_curiosity"),
    OTHER("form_reason_other")
}
@Composable
fun AboutMeSections(
    viewModel: AboutMeViewModel= viewModel { AboutMeViewModel() },
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val isMobileDevice = currentDeviceConfigure().isMobileDevice()

    SectionLayout(
        title = TranslationService.getString("about_me"),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically)
        ) {
            if (isMobileDevice) {
                ProfilePictureUrl()
            }

            Row(
                modifier = Modifier.wrapContentHeight().fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(60.dp)
            ) {
                if (!isMobileDevice) {
                    ProfilePictureUrl()
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
                            text = TranslationService.getString("profile_tagline"),
                            style = MaterialTheme.typography.labelXSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = TranslationService.getString("profile_about_desc",listOf("\uD83D\uDC4B","📱","\uD83D\uDCBB","\uD83D\uDE80","\uD83C\uDF93")),
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
                            type = ButtonType.SECONDARY,
                            text = TranslationService.getString("download_cv"),
                            model = Icons.Default.Download,
                        ) { viewModel.onAction(AboutMeAction.OnShowDialog)  }
                    }
                    if (state.showDialog) {
                        DisableSelection {
                            AlertDialog(
                                onDismissRequest = { viewModel.onAction(AboutMeAction.OnShowDialog) },
                                dismissButton = {
                                    PortfolioButton(
                                        text = TranslationService.getString("cancel"),
                                        type = ButtonType.SECONDARY,
                                        onClick = {
                                            viewModel.onAction(AboutMeAction.OnCleanForm)
                                            viewModel.onAction(AboutMeAction.OnShowDialog)
                                        }
                                    )
                                },
                                confirmButton = {
                                    PortfolioButton(
                                        text = TranslationService.getString("download"),
                                        isLoading = state.isLoading,
                                        enabled =  state.canDownload && !state.isLoading ,
                                        onClick = {
                                           viewModel.onAction(AboutMeAction.OnDownloadButonClick)
                                        }
                                    )
                                },
                                title = {
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(10.dp),
                                    ) {
                                        if (state.errorMessage!=null) {
                                            FormErrorContainer(state.errorMessage)
                                        }
                                        Text(
                                            TranslationService.getString("download_cv"),
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                    }

                                },
                                text = {
                                    Column (
                                        modifier = Modifier.verticalScroll(rememberScrollState()),
                                        verticalArrangement = Arrangement.spacedBy(20.dp),
                                    ){
                                        PortfolioTextField(
                                            placeholder = TranslationService.getString("form_name"),
                                            state = state.nameTextFieldState,
                                            isError =  !state.isNameValid,
                                            errorMessage = TranslationService.getString("form_name_error",listOf("3","20")),
                                            modifier = Modifier.fillMaxWidth().height(50.dp),
                                        )
                                        if (state.reason!=AboutFormReason.OTHER && state.reason!=null) {
                                            Text(TranslationService.getString("abaout_form_email_desc"), style = MaterialTheme.typography.labelMedium,color = MaterialTheme.colorScheme.onBackground)
                                            PortfolioTextField(
                                                placeholder = TranslationService.getString("contact_form_email_placeholder"),
                                                state = state.emailTextFieldState,
                                                isError = !state.isEmailValid,
                                                keyboardType = KeyboardType.Email,
                                                errorMessage = TranslationService.getString("form_email_placeholder"),
                                                modifier = Modifier.fillMaxWidth().height(50.dp),
                                            )
                                        }
                                        Text(TranslationService.getString("form_reason"), fontWeight = FontWeight.Bold,color = MaterialTheme.colorScheme.onBackground)
                                        Column  (
                                            verticalArrangement = Arrangement.spacedBy(7.dp),
                                        ){
                                            AboutFormReason.entries.forEach { option ->
                                                Row(
                                                    modifier = Modifier.fillMaxWidth().clickable {
                                                        viewModel.onAction(AboutMeAction.OnSelectReason(option))
                                                    },
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    RadioButton(
                                                        selected = (state.reason == option),
                                                        onClick = { viewModel.onAction(AboutMeAction.OnSelectReason(option)) })
                                                    Text(
                                                        TranslationService.getString(option.value),
                                                        style = MaterialTheme.typography.labelMedium,
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
                    if (state.showSuccessDialog) {
                        AlertDialog(
                            onDismissRequest = {
                                viewModel.onAction(AboutMeAction.OnCleanForm)
                                viewModel.onAction(AboutMeAction.OnDismissSuccessDialog) },
                            title = {
                                Text(
                                    TranslationService.getString("alert_success_download_title"),
                                    style = MaterialTheme.typography.titleMedium
                                )
                            },
                            text = {
                                Text(
                                    TranslationService.getString("alert_success_download_message"),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                                )
                            },
                            confirmButton = {
                                PortfolioButton(
                                    text =TranslationService.getString("close"),
                                ){
                                    viewModel.onAction(AboutMeAction.OnCleanForm)
                                    viewModel.onAction(AboutMeAction.OnDismissSuccessDialog)
                                }
                            }
                        )
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


/*
@Composable
expect fun PortfolioButtonDownload()*/
