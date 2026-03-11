package empire.digiprem.portfolio.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.design_system.PortfolioButton
import empire.digiprem.portfolio.design_system.currentDeviceConfigure
import empire.digiprem.portfolio.design_system.layout.SectionLayout

@Composable
fun ContactSection(
    modifier: Modifier = Modifier,
) {
    val isMobileDevice=currentDeviceConfigure().isMobileDevice()

    SectionLayout(
        title = "Contact Me",
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
                    text = "Get in touch",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = "My inbox is always open! \uD83D\uDC8C Whether you've got a burning question or want to drop a friendly \"hello\", I'm all ears!\uD83D\uDC42 Let's chat! \uD83C\uDF89",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Column(
                modifier = Modifier.width(300.dp)
                    .height(350.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(7.dp),
            ) {
                PortfolioTextField(
                    state = TextFieldState(""),
                    placeholder = "Name",
                    modifier = Modifier.fillMaxWidth().height(40.dp),
                )
                PortfolioTextField(
                    state = TextFieldState(""),
                    placeholder = "Email",
                    modifier = Modifier.fillMaxWidth().height(40.dp),
                )
                PortfolioTextField(
                    state = TextFieldState(""),
                    placeholder = "Message",
                    lineLimits = TextFieldLineLimits.Default,
                    modifier = Modifier.fillMaxWidth().height(100.dp),
                )
                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    PortfolioButton(
                        text = "Say Hello 👋",
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
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    BasicTextField(
        state = state,
        modifier = modifier,
        lineLimits=lineLimits,
        interactionSource = interactionSource,
        enabled = enabled,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        keyboardOptions = KeyboardOptions(
            keyboardType=keyboardType
        ),
        decorator = {
            Box(Modifier.fillMaxWidth().fillMaxHeight()
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(8.dp),
                contentAlignment =if (lineLimits is TextFieldLineLimits.SingleLine )  Alignment.CenterStart else Alignment.TopStart,
            ) {
                if (placeholder!=null && state.text.isEmpty() && !isFocused) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurface),
                    )
                } else{
                    it()
                }
            }
        }
    )

}