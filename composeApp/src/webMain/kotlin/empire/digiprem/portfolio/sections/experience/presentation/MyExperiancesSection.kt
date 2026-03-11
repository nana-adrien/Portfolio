package empire.digiprem.portfolio.sections.experience.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.design_system.PortfolioButton
import empire.digiprem.portfolio.design_system.PortfolioTabBar
import empire.digiprem.portfolio.design_system.PortfolioTabItem
import empire.digiprem.portfolio.design_system.layout.SectionLayout
import empire.digiprem.portfolio.theme.labelXSmall

private val selectedItems = listOf(
    PortfolioTabItem(
        id = "1",
        title = "Experience",
    ),
    PortfolioTabItem(
        id = "2",
        title = "Education",
    ),
)

@Composable
fun MyExperiencesSection(
    modifier: Modifier = Modifier,
) {
    var selectPortfolioTabItems by remember { mutableStateOf(selectedItems.first()) }

    SectionLayout(
        title = "Experience",
        modifier = modifier,
    ) {
        PortfolioTabBar(
            selectedPortfolioTabItem = selectPortfolioTabItems,
            tabItems = selectedItems,
            onSelectItem = { selectedItem ->
                selectPortfolioTabItems = selectedItem
            }
        )
        ExperienceITem()
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            PortfolioButton(
                text = "View All",
                onClick = {}
            )
        }
    }
}


@Composable
fun ExperienceITem(
    number: Int = 4,
) {

    Box(
        modifier = Modifier.wrapContentSize(),
    ) {
        Column(modifier = Modifier.wrapContentSize()) {
            for(num in 0..number) {
                if (num % 2== 0) {
                    ExperienceStepITem(
                        num
                    ){
                        Column(modifier = Modifier.wrapContentHeight().fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(7.dp),) {
                            Text(
                                text = "Zeal College Of Engineering And Research | SPPU",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            )
                            Text(
                                text = "B.E. Information Technology | 2020 - 2024",
                                style = MaterialTheme.typography.labelXSmall.copy(fontWeight = FontWeight.SemiBold),
                            )
                            Text(
                                text = """
                              * Pursuing IT Engineering course from Savitribai Phule Pune University (SPPU)
                              * Worked on web application development using HTML, CSS, and JavaScript, designing a database system using SQL 
                              * Familiar with software development methodologies and project management practices, including Agile and Waterfall methodologies.  
                            """.trimIndent(),
                                style = MaterialTheme.typography.labelXSmall.copy(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)),
                                textAlign = TextAlign.Justify,
                            )
                        }
                    }
                }
                else{
                    ExperienceStepITem(
                        num
                    ){
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Zeal College Of Engineering And Research | SPPU",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            )
                            Text(
                                text = "B.E. Information Technology | 2020 - 2024",
                                style = MaterialTheme.typography.labelXSmall.copy(fontWeight = FontWeight.SemiBold),
                            )
                        }
                    }
                }

            }
        }
    }

}

@Composable
private fun ExperienceStepITem(
    id: Int,
    content: @Composable () -> Unit
) {
    val isMobileDevice by remember { mutableStateOf(true) }
    val density = LocalDensity.current
    var boxHeight by remember { mutableStateOf<Dp>(150.dp) }
    Box(
        modifier = Modifier.width(800.dp).wrapContentHeight(),
        contentAlignment = if (isMobileDevice) Alignment.CenterStart else Alignment.Center
    )
    {
        Box(
            modifier =
                Modifier
                    .heightIn(min = 100.dp)
                    .onSizeChanged { size ->
                        boxHeight = with(density) {
                            size.height.toDp()
                        }
                    }
                    .width(if (isMobileDevice) 400.dp else 300.dp)
                    .align(if (isMobileDevice) Alignment.CenterStart else if (id % 2 == 0) Alignment.CenterStart else Alignment.CenterEnd)
                    .padding(vertical = 20.dp)
                    .padding(start = if (isMobileDevice) 50.dp else 20.dp, end = if (isMobileDevice) 0.dp else 20.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shadow(elevation = 10.dp, shape = RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .padding(15.dp),
            content = { content() }
        )
        Column(
            modifier = Modifier.height(boxHeight).width(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalDivider(Modifier.fillMaxHeight(0.4f))
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(5.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Default.ShoppingBag,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null,
                )
            }
            VerticalDivider()
        }

    }
}