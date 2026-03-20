package empire.digiprem.portfolio.core.design_system.components.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.core.domain.services.TranslationService

@Composable
fun FormErrorContainer(errorMessage: String?) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.errorContainer)
            .padding(vertical = 7.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            TranslationService.getString(errorMessage!!),
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
        )
    }
}