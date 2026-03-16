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
import empire.digiprem.portfolio.sections.OpenLinkTarget
import empire.digiprem.portfolio.sections.openLink

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
                model =  it.url?: it.icon,
                onClick = { openLink(url = it.link, openLinkTarget = OpenLinkTarget.NEW_ONGLET) },
            )
           /* Box(
                modifier = Modifier.size(35.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .clickable {
                    openLink(url = it.link, openLinkTarget = OpenLinkTarget.NEW_ONGLET)
                }
                .padding(8.dp),
                contentAlignment = Alignment.Center) {
                if (it.url != null) {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = it.url,
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        colorFilter = ColorFilter.tint(Color.White) ,
                    )
                } else {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = it.icon,
                        contentDescription = null,
                        tint = Color.White,
                    )
                }

            }*/
        }
    }
}