package empire.digiprem.portfolio.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.design_system.layout.AdaptativeContainerLayout
import org.jetbrains.compose.resources.stringResource

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
                text = "Power ❤\uFE0F by Nana Adrien",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),

                )
        }
        Box(modifier = Modifier.wrapContentWidth().fillMaxHeight().padding(horizontal = 10.dp), contentAlignment = Alignment.Center) {

            Text(
                text = "Kotlin And Compose Web",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            )
        }
        Box(modifier = Modifier.wrapContentWidth().fillMaxHeight().padding(horizontal = 10.dp), contentAlignment = Alignment.Center) {

            Text(
                text = "Design by https://jigarsable.vercel.app/ ",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            )
        }
    }


}