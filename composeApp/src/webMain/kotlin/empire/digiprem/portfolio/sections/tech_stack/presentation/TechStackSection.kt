package empire.digiprem.portfolio.sections.tech_stack.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.core.design_system.PortfolioIcon
import empire.digiprem.portfolio.core.design_system.PortfolioImage
import empire.digiprem.portfolio.core.design_system.PortfolioTabBar
import empire.digiprem.portfolio.core.design_system.layout.SectionLayout
import empire.digiprem.portfolio.core.domain.TranslationManager
import empire.digiprem.portfolio.sections.tech_stack.data.techStackCategories
import empire.digiprem.portfolio.sections.tech_stack.presentation.components.TechStackItem
import portfolionanaadrien.composeapp.generated.resources.Res


@Composable
fun TechStackSection(
    modifier: Modifier = Modifier,
) {
    var selectPortfolioTabItems by remember { mutableStateOf(techStackCategories.first().details) }

    val selectedCategoryProjects = techStackCategories
        .firstOrNull { it.details.id == selectPortfolioTabItems.id }?.groups
        ?: emptyList()

    SectionLayout(
        title = TranslationManager.getString("tech_stack"),
        modifier = modifier,
    ) {
        PortfolioTabBar(
            selectedPortfolioTabItem = selectPortfolioTabItems,
            tabItems = techStackCategories
                .filter { it.groups.isNotEmpty() }
                .map { it.details },
            onSelectItem = { selectedItem ->
                selectPortfolioTabItems = selectedItem
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        FlowRow(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .wrapContentHeight().animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically),
            horizontalArrangement = Arrangement.spacedBy(30.dp, alignment = Alignment.CenterHorizontally),
        ) {
            selectedCategoryProjects.forEach { techStack ->
                TechStackItem(
                    name = techStack.name,
                   color = techStack.backgroundColor,
                ) {
                    PortfolioIcon(
                        modifier = Modifier.fillMaxSize(),
                        model = techStack.iconLink,
                        tint = null,
                    )
                }
            }

        }

    }
}