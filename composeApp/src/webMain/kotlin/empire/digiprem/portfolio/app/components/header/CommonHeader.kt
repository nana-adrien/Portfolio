package empire.digiprem.portfolio.app.components.header

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.core.design_system.PortfolioIconButton
import empire.digiprem.portfolio.core.design_system.PortfolioLogoText
import empire.digiprem.portfolio.core.design_system.currentDeviceConfigure
import empire.digiprem.portfolio.core.domain.Language
import empire.digiprem.portfolio.core.domain.services.TranslationService
import empire.digiprem.portfolio.core.domain.services.TranslationService.currentLanguage
import empire.digiprem.portfolio.core.domain.util.WindowsPlatform

@Composable
fun ColumnScope.CommonHeader (
    isDarkTheme:Boolean,
    scrollState: LazyListState,
    selectedMenu: MenuItem?=null,
    menuItems: List<MenuItem> =emptyList(),
    selectMenuItem: (MenuItem) -> Unit={},
    onDarkThemeChanged: () -> Unit
){
    val density = LocalDensity.current

    val isMobileDevice = currentDeviceConfigure().isMobileDevice() || currentDeviceConfigure().isTabletDevice()
    var showLanguageDialog by rememberSaveable { mutableStateOf(false) }
    val animateHeaderContainerColor by animateColorAsState(
        targetValue = with(density) {
            if (scrollState.scrollIndicatorState?.scrollOffset?.toDp() ?: 0.dp < 750.dp) Color.Transparent else MaterialTheme.colorScheme.surface.copy(
                alpha = 0.7f
            )
        }
    )
    val animateContentColor by animateColorAsState(
        targetValue = with(density) { if (scrollState.scrollIndicatorState?.scrollOffset?.toDp() ?: 0.dp < 750.dp) Color.White else MaterialTheme.colorScheme.onBackground }
    )
    Header(
        modifier = Modifier
            .height(55.dp)
            .fillMaxWidth()
            .background(animateHeaderContainerColor)
            .padding(horizontal = if (isMobileDevice) 16.dp else 0.dp),
        animateContentColor = animateContentColor,
        logo = {
            PortfolioLogoText(
                isDarkTheme = isDarkTheme,
                color = animateContentColor
            )
        },
        action = {
            PortfolioIconButton(
                model = if (isDarkTheme) Icons.Default.DarkMode else Icons.Default.LightMode,
                onClick =onDarkThemeChanged ,
                tint = animateContentColor
            )
            Box {
                PortfolioIconButton(
                    model = Icons.Default.Language,
                    onClick = { showLanguageDialog = !showLanguageDialog },
                    tint = animateContentColor
                )
                DropdownMenu(
                    expanded = showLanguageDialog,
                    onDismissRequest = { showLanguageDialog = false },
                ) {
                    Language.entries.forEach { language ->
                        val isSelectedLanguage = currentLanguage == language
                        DisableSelection {
                            DropdownMenuItem(
                                modifier = Modifier
                                    .pointerHoverIcon(PointerIcon.Hand)
                                    .background(
                                        color = if (isSelectedLanguage) {
                                            MaterialTheme.colorScheme.primary
                                        } else Color.Transparent,
                                    ),
                                colors = MenuDefaults.itemColors().let {
                                    it.copy(
                                        textColor = if (isSelectedLanguage) {
                                            MaterialTheme.colorScheme.background
                                        } else MaterialTheme.colorScheme.onBackground,
                                    )
                                },
                                text = {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(5.dp),
                                        text = TranslationService.getString(language.value),
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
                                    TranslationService.setLanguage(language)
                                    showLanguageDialog = false
                                })
                        }
                    }
                }
            }

        },
        selectedMenu = selectedMenu,
        menuItems = menuItems,
        selectMenuItem = selectMenuItem
    )
    HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f))
}