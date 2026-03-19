package empire.digiprem.portfolio.core.design_system

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.core.domain.util.WindowsPlatform
import portfolionanaadrien.composeapp.generated.resources.Res

@Composable
fun PortfolioLogoText(
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
) {
    Row(
        modifier = modifier.padding(vertical = 7.dp).clickable { WindowsPlatform.openLink("/") },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        PortfolioLogo(
            isDarkTheme = !isDarkTheme,
            reverseBackground = true,
            modifier = Modifier.size(40.dp),
        )
        Text(
            modifier = modifier
                .clip(shape = RoundedCornerShape(4.dp)),
            text = "Nana Adrien",
            style = MaterialTheme
                .typography
                .labelMedium.copy(color = color, fontWeight = FontWeight.Bold),
        )
    }


}

@Composable
fun PortfolioLogo(
    isDarkTheme: Boolean,
    reverseBackground: Boolean = false,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .shadow(elevation = 10.dp, CircleShape)
            .background(
                if (reverseBackground) MaterialTheme.colorScheme.onSurface
                else MaterialTheme.colorScheme.surface
            )


    ) {
        PortfolioImage(
            modifier = Modifier.fillMaxSize(),
            image = if (isDarkTheme) Res.getUri("drawable/logo_dark_svg.svg") else Res.getUri("drawable/logo_light_svg.svg"),
            onFailure = null,
            contentScale = ContentScale.Fit
        )
    }
}