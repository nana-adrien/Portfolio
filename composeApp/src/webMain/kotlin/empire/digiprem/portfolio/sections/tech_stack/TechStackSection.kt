package empire.digiprem.portfolio.sections.tech_stack

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material.icons.filled.Window
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.design_system.PortfolioTabBar
import empire.digiprem.portfolio.design_system.PortfolioTabItem
import empire.digiprem.portfolio.design_system.layout.SectionLayout
import org.jetbrains.compose.resources.vectorResource
import kotlin.random.Random

private val selectedItems = listOf(
    PortfolioTabItem(
        id = "1",
        title = "Frontend",
    ),
    PortfolioTabItem(
        id = "2",
        title = "Backend",
    ),
    PortfolioTabItem(
        id = "3",
        title = "other",
    ),
)

@Composable
internal fun TechStackItem(
    name: String,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(7.dp),
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .border(1.dp, Color.LightGray, CircleShape)
                .background(
                    color =
                        Color(
                            red = Random.nextInt(),
                            green = Random.nextInt(),
                            blue = Random.nextInt()
                        ).copy(alpha = 0.2f)
                )
                .padding(10.dp)
        ) {
            icon()
        }
        Text(
            text = name,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
        )
    }

}

@Composable
fun TechStackSection(
    modifier: Modifier = Modifier,
) {
    var selectPortfolioTabItems by remember { mutableStateOf(selectedItems.first()) }
    var techStackItems by rememberSaveable { mutableStateOf(10) }
    LaunchedEffect(selectPortfolioTabItems) {
        techStackItems = when (selectPortfolioTabItems.id) {
            "1" -> 10
            "2" -> 15
            else -> 3
        }
    }
    SectionLayout(
        title = "Tech Stack",
        modifier = modifier,
    ) {
        PortfolioTabBar(
            selectedPortfolioTabItem = selectPortfolioTabItems,
            tabItems = selectedItems,
            onSelectItem = { selectedItem ->
                selectPortfolioTabItems = selectedItem
            }
        )
        Spacer(modifier = Modifier.height(10.dp))

       /* LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxWidth(0.8f).height(600.dp).animateContentSize(),
        ) {
            items(techStackItems) {
                TechStackItem(
                    name = "Whatsapp"
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Default.Whatsapp,
                        contentDescription = null,
                    )
                }

            }
        }*/

        FlowRow(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .wrapContentHeight().animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(30.dp,Alignment.CenterVertically),
            horizontalArrangement = Arrangement.spacedBy(30.dp, alignment = Alignment.CenterHorizontally),
        ){
            repeat(techStackItems) {
                TechStackItem(
                    name = "Whatsapp"
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Default.Whatsapp,
                        contentDescription = null,
                    )
                }
            }
        }

    }
}