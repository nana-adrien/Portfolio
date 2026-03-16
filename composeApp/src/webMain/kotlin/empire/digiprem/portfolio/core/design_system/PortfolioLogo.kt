package empire.digiprem.portfolio.core.design_system

import androidx.compose.foundation.Image
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
import empire.digiprem.portfolio.sections.openLink
import org.jetbrains.compose.resources.vectorResource
import portfolionanaadrien.composeapp.generated.resources.Res
import portfolionanaadrien.composeapp.generated.resources.logo_dark
import portfolionanaadrien.composeapp.generated.resources.logo_light

@Composable
fun PortfolioLogoText(
    isDarkTheme:Boolean,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
){
    Row(
        modifier=modifier.padding(vertical = 7.dp).clickable { openLink("/") },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ){
        PortfolioLogo(
            isDarkTheme = !isDarkTheme,
            reverseBackground = true,
            modifier = Modifier.size(40.dp),
        )
        Text(
            modifier = modifier
                .clip(shape = RoundedCornerShape(4.dp))

            ,
            text = "Nana Adrien",
            style = MaterialTheme
                .typography
                .labelMedium.copy(color = color,fontWeight = FontWeight.Bold),
        )
    }


}
@Composable
fun PortfolioLogo(
    isDarkTheme:Boolean,
    reverseBackground:Boolean=false,
    modifier: Modifier = Modifier,
){
    Box(
        modifier = modifier.clip(CircleShape).shadow(elevation = 10.dp, CircleShape).background( if (reverseBackground)MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.surface)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            imageVector = if (isDarkTheme) vectorResource(Res.drawable.logo_dark) else  vectorResource(Res.drawable.logo_light) ,
            contentDescription = null,
            contentScale = ContentScale.Inside
        )
    }
}