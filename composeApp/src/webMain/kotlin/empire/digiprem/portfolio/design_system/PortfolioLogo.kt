package empire.digiprem.portfolio.design_system

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import empire.digiprem.portfolio.theme.PortfolioTheme

@Composable
fun PortfolioLogo(
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    onclick:() -> Unit={},
){
    Text(
        modifier = modifier
            .clip(shape = RoundedCornerShape(4.dp))
            .clickable(onClick = onclick)
        ,
        text = "Nana Adrien",
        style = MaterialTheme
            .typography
            .labelMedium.copy(color = color,fontWeight = FontWeight.Bold),
    )

}