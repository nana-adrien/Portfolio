package empire.digiprem.portfolio.app.components.footer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.app.components.Footer
import empire.digiprem.portfolio.core.design_system.currentDeviceConfigure

@Composable
fun CommonFooter(){
    val isMobileDevice = currentDeviceConfigure().isMobileDevice() || currentDeviceConfigure().isTabletDevice()
    Footer(
        modifier = Modifier
            .heightIn(min = 60.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = if (!isMobileDevice) 16.dp else 5.dp, vertical = 10.dp)
    )
}