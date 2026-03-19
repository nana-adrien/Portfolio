package empire.digiprem.portfolio.app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.core.design_system.PortfolioIconButton
import empire.digiprem.portfolio.core.domain.enums.OpenLinkTarget
import empire.digiprem.portfolio.core.domain.util.WindowsPlatform

data class SocialMedia(
    val icon: ImageVector,
    val url: String? = null,
    val link: String,
)


@Composable
fun SocialMediaLink(
    modifier: Modifier = Modifier,
    socialMedias: List<SocialMedia>,
) {
    Column(
        modifier = modifier.width(60.dp).padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        socialMedias.forEach {
            PortfolioIconButton(
                model = it.url ?: it.icon,
                onClick = { WindowsPlatform.openLink(url = it.link, openLinkTarget = OpenLinkTarget.NEW_ONGLET) },
            )
        }
    }
}