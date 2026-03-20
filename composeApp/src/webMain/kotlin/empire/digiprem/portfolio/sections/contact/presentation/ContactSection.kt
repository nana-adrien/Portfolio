package empire.digiprem.portfolio.sections.contact.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import empire.digiprem.portfolio.core.design_system.PortfolioButton
import empire.digiprem.portfolio.core.design_system.components.form.FormErrorContainer
import empire.digiprem.portfolio.core.design_system.components.form.PortfolioTextField
import empire.digiprem.portfolio.core.design_system.currentDeviceConfigure
import empire.digiprem.portfolio.core.design_system.layout.SectionLayout
import empire.digiprem.portfolio.core.domain.services.TranslationService

@Composable
fun ContactSection(
    modifier: Modifier = Modifier,
    viewModel: ContactViewModel = viewModel { ContactViewModel() },
) {
    val state by viewModel.state.collectAsState()
    val isMobileDevice = currentDeviceConfigure().isMobileDevice()

    SectionLayout(
        title = TranslationService.getString("contact_me"),
        modifier = modifier,
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        FlowRow(
            modifier = Modifier.width(if (isMobileDevice) 400.dp else 950.dp)
                .wrapContentHeight()
                .heightIn(max =if (isMobileDevice) 800.dp else 500.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                            MaterialTheme.colorScheme.primaryContainer,
                            MaterialTheme.colorScheme.primary,
                        )
                    )
                ).padding(40.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically),
            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally)
        ) {
            Column(
                modifier = Modifier.width(400.dp).padding(bottom = 20.dp).align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.spacedBy(7.dp, alignment = Alignment.CenterVertically),
            ) {
                Text(
                    text = TranslationService.getString("get_in_touch"),
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = TranslationService.getString(
                        "inbox_open",
                        listOf("\uD83D\uDC8C", "\uD83D\uDC42", "\uD83C\uDF89")
                    ),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Column(
                modifier = Modifier.width(400.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically),
            ) {
                if (state.errorMessage != null) {
                    FormErrorContainer(state.errorMessage)
                }

                PortfolioTextField(
                    state = state.nameTextFieldState,
                    isError = !state.isNameValid,
                    errorMessage = TranslationService.getString("form_name_error", listOf("3", "20")),
                    placeholder = TranslationService.getString("contact_form_name_placeholder"),
                    modifier = Modifier.fillMaxWidth().height(40.dp),
                )
                PortfolioTextField(
                    state = state.emailTextFieldState,
                    isError = !state.isEmailValid,
                    errorMessage = TranslationService.getString("form_email_placeholder"),
                    placeholder = TranslationService.getString("contact_form_email_placeholder"),
                    modifier = Modifier.fillMaxWidth().height(40.dp),
                )
                PortfolioTextField(
                    state = state.messageTextFieldState,
                    isError = !state.isMessageValid,
                    errorMessage = TranslationService.getString("form_name_error", listOf("100", "1000")),
                    placeholder = TranslationService.getString("contact_form_message_placeholder"),
                    lineLimits = TextFieldLineLimits.Default,
                    modifier = Modifier.fillMaxWidth().height(100.dp),
                )
                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    PortfolioButton(
                        isLoading = state.isLoading,
                        enabled = state.canSend && state.isLoading.not(),
                        text = TranslationService.getString("contact_form_send_button", listOf("\uD83D\uDC4B")),
                        onClick = {
                            viewModel.onAction(ContactAction.OnSendButonClick)
                        }
                    )
                }
            }
        }
        if (state.showSuccessDialog) {
            AlertDialog(
                onDismissRequest = {
                    viewModel.onAction(ContactAction.OnCleanForm)
                    viewModel.onAction(ContactAction.OnDismissSuccessDialog)
                },
                title = {
                    Text(
                        TranslationService.getString("alert_success_send_form_title"),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                text = {
                    Text(
                        TranslationService.getString("alert_success_send_form_desc"),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    )
                },
                confirmButton = {
                    PortfolioButton(
                        text = TranslationService.getString("close"),
                    ) {
                        viewModel.onAction(ContactAction.OnCleanForm)
                        viewModel.onAction(ContactAction.OnDismissSuccessDialog)
                    }
                }
            )
        }
    }
}


