package empire.digiprem.portfolio.sections.home.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.core.design_system.PortfolioIcon
import empire.digiprem.portfolio.core.design_system.PortfolioLogo
import empire.digiprem.portfolio.core.design_system.animation.FloatingBox


@Composable
fun HomeLogoSection(
    isMobileDevice: Boolean, isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        FloatingBox(
            modifier = Modifier.align(Alignment.TopStart).padding(top = if (isMobileDevice) 0.dp else 70.dp)
        ) {
            Box(
                modifier = Modifier.size(if (isMobileDevice) 50.dp else 65.dp)
                    .clip(CircleShape)
                    .shadow(elevation = 10.dp, CircleShape).background(MaterialTheme.colorScheme.surface)
                    .padding(if (isMobileDevice) 7.dp else 10.dp)
            ) {
                PortfolioIcon(
                    modifier = Modifier.fillMaxSize(),
                    model = "https://cdn.simpleicons.org/jetpackcompose",
                    tint = null
                )
            }

        }

        val animatedFloat by animateFloatAsState(if (isMobileDevice) 100f else 0f)

        FloatingBox(
            modifier = Modifier.align(Alignment.TopEnd)
                .graphicsLayer {
                    translationY = animatedFloat
                },
        ) {
            Box(
                modifier = Modifier
                    .size(if (isMobileDevice) 55.dp else 75.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(if (isMobileDevice) 15.dp else 18.dp)
            ) {
                PortfolioIcon(
                    modifier = Modifier.fillMaxSize(),
                    model = "https://cdn.simpleicons.org/kotlin",
                    tint = null
                )
            }
        }

        FloatingBox(
            modifier = Modifier.align(Alignment.CenterEnd)
                .padding(start = 50.dp)
                .graphicsLayer {
                    translationY = animatedFloat
                }
        ) {
            Box(
                modifier = Modifier
                    .size(if (isMobileDevice) 45.dp else 65.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(if (isMobileDevice) 7.dp else 10.dp)
            ) {

                PortfolioIcon(
                    modifier = Modifier.fillMaxSize(),
                    model = "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/intellij/intellij-original.svg",
                    tint = null
                )
            }
        }
        FloatingBox(
            modifier = Modifier.align(Alignment.BottomEnd)
                .padding(end = if (isMobileDevice) 30.dp else 70.dp, top = 40.dp)
        ) {
            Box(
                modifier = Modifier.size(if (isMobileDevice) 50.dp else 65.dp)

                    .clip(CircleShape).background(MaterialTheme.colorScheme.surface)
                    .padding(if (isMobileDevice) 7.dp else 10.dp)
            ) {
                PortfolioIcon(
                    modifier = Modifier.fillMaxSize(),
                    model = "https://cdn.simpleicons.org/postman",
                    tint = null
                )
            }
        }

        PortfolioLogo(
            isDarkTheme = isDarkTheme,
            modifier = Modifier.padding(end = 50.dp).size(250.dp)
        )
    }
}
