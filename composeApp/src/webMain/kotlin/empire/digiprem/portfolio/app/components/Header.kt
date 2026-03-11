package empire.digiprem.portfolio.app.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Light
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key.Companion.P
import androidx.compose.ui.platform.PlatformContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import empire.digiprem.portfolio.design_system.layout.AdaptativeContainerLayout
import kotlin.repeat

data class MenuItem(
    val id: Long,
    val title: String,
    val link: String,
)
@Composable
expect fun BlurComponent()

@Composable
expect fun PlatformText()
@Composable
fun Header(
    modifier: Modifier = Modifier,
    logo: @Composable () -> Unit,
    selectedMenu: MenuItem?,
    menuItems: List<MenuItem>,
    selectMenuItem: (MenuItem) -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
       // BlurComponent()

        AdaptativeContainerLayout(
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                logo()
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    //PlatformText()
                    menuItems.forEachIndexed { index, item ->
                     // PlatformText {
                          Text(
                              modifier = Modifier
                                  .clip(RoundedCornerShape(4.dp))
                                  .hoverable(interactionSource = MutableInteractionSource(), enabled = false)
                                  .clickable {
                                      selectMenuItem(item)
                                  }.padding(5.dp),
                              text = item.title,
                              style = MaterialTheme
                                  .typography
                                  .labelSmall
                                  .let {
                                      it.copy(
                                          fontWeight = FontWeight.SemiBold,
                                          color = if (selectedMenu?.id == item.id) {
                                              MaterialTheme.colorScheme.primary
                                          } else it.color,
                                          textDecoration = if (selectedMenu?.id == item.id) {
                                              TextDecoration.Underline
                                          } else it.textDecoration,

                                          )
                                  },
                          )
                     // }
                    }
                    IconButton(
                        onClick = { },
                    ) {
                        Icon(imageVector = Icons.Default.Light, contentDescription = "Light")
                    }
                }
            }
        }
    }
}