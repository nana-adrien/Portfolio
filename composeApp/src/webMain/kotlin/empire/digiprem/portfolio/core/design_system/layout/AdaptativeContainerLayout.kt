package empire.digiprem.portfolio.core.design_system.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import empire.digiprem.portfolio.core.design_system.currentDeviceConfigure

@Composable
fun AdaptativeContainerLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val deviceConfiguration=currentDeviceConfigure()
    Box(
        modifier = modifier.fillMaxWidth( if (deviceConfiguration.isMobileDevice() ||deviceConfiguration.isTabletDevice() )0.97f else 0.85f)/*.padding(horizontal = 16.dp)*/,
        content = {
            content()
        }
    )
}