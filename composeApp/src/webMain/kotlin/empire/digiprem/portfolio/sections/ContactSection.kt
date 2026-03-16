package empire.digiprem.portfolio.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.core.design_system.PortfolioButton
import empire.digiprem.portfolio.core.design_system.currentDeviceConfigure
import empire.digiprem.portfolio.core.design_system.layout.SectionLayout
import empire.digiprem.portfolio.core.domain.TranslationManager

@Composable
fun ContactSection(
    modifier: Modifier = Modifier,
) {
    val isMobileDevice=currentDeviceConfigure().isMobileDevice()

    SectionLayout(
        title = TranslationManager.getString("contact_me"),
        modifier = modifier,
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        FlowRow(
         modifier = Modifier.width(if (isMobileDevice) 400.dp else 800.dp)
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
                    text =TranslationManager.getString("get_in_touch"),
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text =TranslationManager.getString("inbox_open"),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Column(
                modifier = Modifier.width(300.dp)
                    .height(350.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(7.dp),
            ) {
                val nameState = remember { TextFieldState("") }
                val emailState = remember { TextFieldState("") }
                val messageState = remember { TextFieldState("") }
                PortfolioTextField(
                    state = nameState,
                    placeholder = TranslationManager.getString("contact_form_name_placeholder"),
                    modifier = Modifier.fillMaxWidth().height(40.dp),
                )
                PortfolioTextField(
                    state =emailState,
                    placeholder = TranslationManager.getString("contact_form_email_placeholder"),
                    modifier = Modifier.fillMaxWidth().height(40.dp),
                )
                PortfolioTextField(
                    state = messageState,
                    placeholder = TranslationManager.getString("contact_form_message_placeholder"),
                    lineLimits = TextFieldLineLimits.Default,
                    modifier = Modifier.fillMaxWidth().height(100.dp),
                )
                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    PortfolioButton(
                        text = TranslationManager.getString("contact_form_send_button"),
                        onClick = {}
                    )
                }
            }
        }

    }
}

@Composable
fun PortfolioTextField(
    state: TextFieldState,
    placeholder: String?=null,
    enabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Unspecified,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.SingleLine,
    modifier: Modifier = Modifier,
) {

    BasicTextField(
        state = state,
        modifier = modifier,
        lineLimits=lineLimits,
      //  interactionSource = interactionSource,
        enabled = enabled,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        keyboardOptions = KeyboardOptions(
            keyboardType=keyboardType
        ),
        textStyle = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onBackground),
        decorator = {
            Box(Modifier.fillMaxWidth().fillMaxHeight()
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(8.dp),
                contentAlignment =if (lineLimits is TextFieldLineLimits.SingleLine )  Alignment.CenterStart else Alignment.TopStart,
            ) {
                if (placeholder!=null && state.text.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)),
                    )
                }
                it()
            }
        }
    )

}