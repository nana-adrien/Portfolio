package empire.digiprem.portfolio.design_system

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp

@Composable
fun PortfolioTabBar(
    selectedPortfolioTabItem: PortfolioTabItem,
    tabItems: List<PortfolioTabItem>,
    modifier: Modifier = Modifier,
    onSelectItem: (PortfolioTabItem) -> Unit
){

    FlowRow(
        modifier = Modifier.clip(RoundedCornerShape(4.dp)).background(MaterialTheme.colorScheme.background).padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ){
        tabItems.forEach { item ->
            Box(
                modifier = Modifier
                    .pointerHoverIcon(PointerIcon.Hand)
                    .clip(RoundedCornerShape(4.dp))
                    .background(
                        if (selectedPortfolioTabItem.id == item.id) {
                            MaterialTheme.colorScheme.primary
                        } else MaterialTheme.colorScheme.background
                    )
                    .clickable(
                        onClick = { onSelectItem(item) }
                    )
                    .padding(horizontal = 20.dp,vertical = 7.dp)
            ){
                Text(
                    text = item.title,
                    style=MaterialTheme
                        .typography
                        .labelSmall
                        .let {
                            if (selectedPortfolioTabItem.id == item.id) {
                                it.copy(color = Color.White)
                            } else{
                                it.copy(color =  MaterialTheme.colorScheme.onBackground)

                            }
                        }

                    )
            }
        }
    }
}