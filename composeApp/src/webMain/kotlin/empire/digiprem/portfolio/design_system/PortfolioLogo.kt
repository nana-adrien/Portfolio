package empire.digiprem.portfolio.design_system

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PortfolioLogo(
    modifier: Modifier = Modifier,
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
            .labelMedium
    )

}