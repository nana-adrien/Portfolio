package empire.digiprem.portfolio.pages

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LinkedCamera
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastJoinToString
import empire.digiprem.portfolio.app.Section
import empire.digiprem.portfolio.app.components.Footer
import empire.digiprem.portfolio.app.components.Header
import empire.digiprem.portfolio.app.components.MenuItem
import empire.digiprem.portfolio.app.components.SocialMedia
import empire.digiprem.portfolio.app.components.SocialMediaLink
import empire.digiprem.portfolio.core.design_system.PortfolioButton
import empire.digiprem.portfolio.core.design_system.PortfolioIconButton
import empire.digiprem.portfolio.core.design_system.PortfolioLogoText
import empire.digiprem.portfolio.core.design_system.WebPageScaffold
import empire.digiprem.portfolio.core.design_system.currentDeviceConfigure
import empire.digiprem.portfolio.core.design_system.layout.SectionLayout
import empire.digiprem.portfolio.core.domain.TranslationManager
import empire.digiprem.portfolio.sections.getBaseUrl
import empire.digiprem.portfolio.sections.openLink

@Composable
fun Error404Page(
    enabledDarkTheme: Boolean,
    onDarkThemeChanged: () -> Unit,) {
    val isMobileDevice = currentDeviceConfigure().isMobileDevice()
    val density = LocalDensity.current
    val scrollState = rememberLazyListState()

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

    WebPageScaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
            .safeContentPadding()
            .fillMaxSize(),
        scrollState = rememberLazyListState(),
        header = {
            Header(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .background(animateHeaderContainerColor)
                    .padding(horizontal = if (isMobileDevice) 16.dp else 0.dp),
                animateContentColor = animateContentColor,
                logo = { PortfolioLogoText(
                    isDarkTheme =enabledDarkTheme ,
                    color = animateContentColor) },
                action = {
                    PortfolioIconButton(
                        model = if (enabledDarkTheme) Icons.Default.DarkMode else Icons.Default.LightMode,
                        onClick = { onDarkThemeChanged() },
                        tint = animateContentColor
                    )
                },
                selectedMenu = null,
                menuItems = emptyList()
            ) {
                openLink(it.link)
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f))
        },
        footer = {
            Footer(
                modifier = Modifier
                    .heightIn(min = 60.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = if (!isMobileDevice) 16.dp else 5.dp, vertical = 10.dp)
            )

        },
        socialMedia = {
            SocialMediaLink(
                modifier = Modifier,
                socialMedias = listOf(
                    SocialMedia(
                        icon = Icons.Default.Whatsapp,
                        link = "WhatsApp",
                    ),
                    SocialMedia(
                        icon = Icons.Default.Facebook,
                        link = "Facebook",
                    ),
                    SocialMedia(
                        icon = Icons.Default.LinkedCamera,
                        link = "Facebook",
                    ),
                )
            )
        }
    ) {
        item {
            Box(
                modifier = Modifier
                    .padding(32.dp)
                    .fillParentMaxSize(0.85f),
                contentAlignment = Alignment.Center
            ) {
                SectionLayout(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "404",
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = TranslationManager.getString("page_not_found"),
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                            ),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        PortfolioButton(
                            onClick = { openLink("/") },
                            text = TranslationManager.getString("back_home"),
                        )
                    }
                }

            }
        }
    }
}