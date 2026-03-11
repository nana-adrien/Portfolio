package empire.digiprem.portfolio.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class SocialMedia(
    val icon: ImageVector,
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
            Box(modifier = Modifier.size(30.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .clickable {

                }
                .padding(7.dp),
                contentAlignment = Alignment.Center){
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = it.icon,
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        }
    }
}