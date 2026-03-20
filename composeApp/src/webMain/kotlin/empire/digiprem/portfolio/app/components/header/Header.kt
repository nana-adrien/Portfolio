package empire.digiprem.portfolio.app.components.header

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.core.design_system.PortfolioIconButton
import empire.digiprem.portfolio.core.design_system.currentDeviceConfigure
import empire.digiprem.portfolio.core.design_system.layout.AdaptativeContainerLayout
import empire.digiprem.portfolio.core.domain.services.TranslationService

data class MenuItem(
    val id: String,
    val title: String,
    val link: String,
)

@Composable
fun Header(
    modifier: Modifier = Modifier,
    logo: @Composable () -> Unit,
    action: @Composable (RowScope.() -> Unit)? = null,
    animateContentColor: Color = Color.Black,
    selectedMenu: MenuItem?,
    menuItems: List<MenuItem>,
    selectMenuItem: (MenuItem) -> Unit,
) {
    val isMobileDevice = currentDeviceConfigure().isMobileDevice()
    var enabledMenu by rememberSaveable { mutableStateOf(false) }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        AdaptativeContainerLayout() {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                logo()
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.End),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (!isMobileDevice) {
                        menuItems.forEachIndexed { index, item ->

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .hoverable(interactionSource = MutableInteractionSource(), enabled = false)
                                    .pointerHoverIcon(PointerIcon.Hand)
                                    .clickable {
                                        selectMenuItem(item)
                                    }.padding(7.dp),
                            ) {
                                Text(
                                    text = TranslationService.getString(item.title),
                                    style = MaterialTheme
                                        .typography
                                        .labelSmall
                                        .let {
                                            it.copy(
                                                fontWeight = FontWeight.SemiBold,
                                                color = if (selectedMenu?.id == item.id) {
                                                    MaterialTheme.colorScheme.primary
                                                } else animateContentColor,
                                                textDecoration = if (selectedMenu?.id == item.id) {
                                                    TextDecoration.Underline
                                                } else it.textDecoration,

                                                )
                                        },
                                )
                            }

                            // }
                        }
                    }
                    action?.invoke(this@Row)
                    AnimatedVisibility(isMobileDevice) {
                        PortfolioIconButton(
                            model = Icons.Default.Menu,
                            onClick = {
                                enabledMenu = true
                            },
                            tint = animateContentColor,
                        )
                    }
                }

            }
        }


        AnimatedVisibility(enabledMenu && isMobileDevice) {
            DropdownMenu(
                expanded = enabledMenu && isMobileDevice,
                modifier = Modifier.align(Alignment.TopEnd).fillMaxWidth(0.7f).wrapContentHeight(),
                onDismissRequest = { enabledMenu = false },
            ) {
                DisableSelection {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        menuItems.forEachIndexed { index, item ->
                            DropdownMenuItem(
                                modifier = Modifier.background(
                                    color = if (selectedMenu?.id == item.id) {
                                        MaterialTheme.colorScheme.primary
                                    } else Color.Transparent,
                                ),
                                colors = MenuDefaults.itemColors().let {
                                    it.copy(
                                        textColor = if (selectedMenu?.id == item.id) {
                                            MaterialTheme.colorScheme.background
                                        } else MaterialTheme.colorScheme.onBackground,
                                    )
                                },
                                text = {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(5.dp),
                                        text = TranslationService.getString(item.title),
                                        style = MaterialTheme
                                            .typography
                                            .labelSmall
                                            .let {
                                                it.copy(
                                                    fontWeight = FontWeight.SemiBold,
                                                    textAlign = TextAlign.Center,
                                                )
                                            },
                                    )
                                },
                                onClick = {
                                    enabledMenu = false
                                    selectMenuItem(item)
                                }
                            )
                        }
                        DropdownMenuItem(
                            modifier = Modifier.background(MaterialTheme.colorScheme.errorContainer),
                            text = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Close",
                                        tint = MaterialTheme.colorScheme.background
                                    )
                                    Text(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .padding(5.dp),
                                        text = TranslationService.getString("close"),
                                        style = MaterialTheme
                                            .typography
                                            .labelSmall
                                            .let {
                                                it.copy(
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = MaterialTheme.colorScheme.background
                                                )
                                            },
                                    )
                                }

                            },
                            onClick = {
                                enabledMenu = false
                            }
                        )
                    }
                }

            }
        }


    }
}