package empire.digiprem.portfolio.design_system.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.design_system.currentDeviceConfigure

@Composable
fun AdaptativeContainerLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val deviceConfiguration=currentDeviceConfigure()
    Box(
        modifier = modifier.fillMaxWidth( if (deviceConfiguration.isMobileDevice())1f else 0.8f)/*.padding(horizontal = 16.dp)*/,
        content = {
            content()
        }
    )
}