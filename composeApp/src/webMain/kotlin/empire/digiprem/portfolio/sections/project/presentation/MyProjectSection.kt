package empire.digiprem.portfolio.sections.project.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.core.design_system.PortfolioButton
import empire.digiprem.portfolio.core.design_system.PortfolioIcon
import empire.digiprem.portfolio.core.design_system.PortfolioTabBar
import empire.digiprem.portfolio.core.design_system.components.BouncingBox
import empire.digiprem.portfolio.core.design_system.currentDeviceConfigure
import empire.digiprem.portfolio.core.design_system.layout.SectionLayout
import empire.digiprem.portfolio.sections.OpenLinkTarget
import empire.digiprem.portfolio.sections.openLink
import empire.digiprem.portfolio.sections.project.data.MyProjectDB.categories
import empire.digiprem.portfolio.sections.project.presentation.components.ProjectItem
import portfolionanaadrien.composeapp.generated.resources.Res


@Composable
fun MyProjectSection(
    modifier: Modifier = Modifier,
) {
    val isMobileDevice = currentDeviceConfigure().isMobileDevice()
    var selectPortfolioTabItems by remember { mutableStateOf(categories.first().details) }
    val selectedCategoryProjects = categories
        .firstOrNull { it.details.id == selectPortfolioTabItems.id }?.groups
        ?: emptyList()
    var isReduceForm by rememberSaveable { mutableStateOf(true) }

    SectionLayout(
        title = "Project",
        modifier = modifier,
    ) {
        PortfolioTabBar(
            selectedPortfolioTabItem = selectPortfolioTabItems,
            tabItems = categories
                .filter { it.groups.isNotEmpty() }
                .map { it.details },
            onSelectItem = { selectedItem ->
                selectPortfolioTabItems = selectedItem
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        FlowRow(
            modifier = Modifier.wrapContentSize().animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(if (isMobileDevice) 10.dp else 20.dp),
            horizontalArrangement = Arrangement.spacedBy(if (isMobileDevice) 10.dp else 20.dp),
        ) {
            selectedCategoryProjects.forEachIndexed { index, project ->
                if (isReduceForm && index >= 4) return@forEachIndexed // afficher que les 3 premiers
                ProjectItem(
                    isPrivate = project.isPrivate,
                    title = project.title,
                    image = project.image?: Res.getUri("drawable/capture.png"),
                    description = project.description,
                    demoLink = project.demoLink,
                    githubLink = project.githubLink,
                    previewLink = project.previewLink,
                ) {
                    openLink(url = it, OpenLinkTarget.NEW_ONGLET)
                }
            }
        }


        AnimatedVisibility(selectedCategoryProjects.size > 3) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                BouncingBox{
                    PortfolioButton(
                        text = if (isReduceForm) "View All" else "reduce",
                        onClick = {
                            isReduceForm = !isReduceForm
                        }
                    )
                }

            }
        }

    }
}




