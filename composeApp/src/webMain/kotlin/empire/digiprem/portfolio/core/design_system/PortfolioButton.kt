package empire.digiprem.portfolio.core.design_system

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp

enum class ButtonType {
    PRIMARY,
    SECONDARY,
}


@Composable
fun PortfolioButton(
    text: String,
    model: Any? = null,
    enabled: Boolean = true,
    type: ButtonType = ButtonType.PRIMARY,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {

    Button(
        enabled = enabled,
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = if (type == ButtonType.PRIMARY) {
                4.dp
            } else 0.dp,
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (type == ButtonType.PRIMARY) {
                MaterialTheme.colorScheme.primary
            } else MaterialTheme.colorScheme.background,
            contentColor = if (type == ButtonType.PRIMARY) {
                Color.White
            } else MaterialTheme.colorScheme.primary,
        ),
        onClick = onClick,
        modifier = modifier.pointerHoverIcon(PointerIcon.Hand),
    ) {
        Text(
            text = text,
            maxLines = 1,
            style = MaterialTheme
                .typography
                .labelSmall
                .let {
                    if (type == ButtonType.PRIMARY) {
                        it.copy(color = Color.White)
                    } else{
                        it
                    }
                }
        )
        model?.let { model ->
            PortfolioIcon(
                modifier = Modifier.size(25.dp),
                model = model,
                tint = if (type == ButtonType.PRIMARY) {
                    Color.White
                } else MaterialTheme.colorScheme.primary,
            )
        }

    }

}