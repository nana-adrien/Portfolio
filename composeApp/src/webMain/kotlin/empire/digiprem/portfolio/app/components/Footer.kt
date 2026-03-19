package empire.digiprem.portfolio.app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.core.domain.services.TranslationService

@Composable
fun Footer(
    modifier: Modifier = Modifier,
) {

    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalArrangement = Arrangement.spacedBy(20.dp,Alignment.CenterVertically),
    ) {

        Box(modifier = Modifier.wrapContentWidth().fillMaxHeight().padding(horizontal = 10.dp), contentAlignment = Alignment.Center) {
            Text(
                text = TranslationService.getString("powered_by"),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),

                )
        }
        Box(modifier = Modifier.wrapContentWidth().fillMaxHeight().padding(horizontal = 10.dp), contentAlignment = Alignment.Center) {

            Text(
                text =TranslationService.getString("kotlin_compose"),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            )
        }
        Box(modifier = Modifier.wrapContentWidth().fillMaxHeight().padding(horizontal = 10.dp), contentAlignment = Alignment.Center) {
            Text(
                text = TranslationService.getString("design_by"),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            )
        }
    }


}