package empire.digiprem.portfolio.sections.experience.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.core.design_system.PortfolioButton
import empire.digiprem.portfolio.core.design_system.PortfolioIcon
import empire.digiprem.portfolio.core.design_system.PortfolioTabBar
import empire.digiprem.portfolio.core.design_system.currentDeviceConfigure
import empire.digiprem.portfolio.core.design_system.layout.SectionLayout
import empire.digiprem.portfolio.core.domain.TranslationManager
import empire.digiprem.portfolio.sections.experience.data.categories.professionalExperienceCategory
import empire.digiprem.portfolio.sections.experience.data.experiences
import empire.digiprem.portfolio.sections.experience.domain.Education
import empire.digiprem.portfolio.sections.experience.domain.ProfessionalExperience
import empire.digiprem.portfolio.sections.experience.domain.TimelineItem
import empire.digiprem.portfolio.theme.labelXSmall



@Composable
fun MyExperiencesSection(
    modifier: Modifier = Modifier,
) {
    val d=professionalExperienceCategory
    var selectPortfolioTabItems by remember { mutableStateOf(experiences.first().details)}
    val selectedCategoryExperiences = experiences
        .firstOrNull { it.details.id == selectPortfolioTabItems.id }?.groups
        ?: emptyList()
    var isReduceForm by rememberSaveable { mutableStateOf(true) }

    SectionLayout(
        title = TranslationManager.getString("experience"),
        modifier = modifier,
    ) {
        PortfolioTabBar(
            selectedPortfolioTabItem = selectPortfolioTabItems,
            tabItems = experiences
                .filter{it.groups.isNotEmpty()}
                .map { it.details },
            onSelectItem = { selectedItem ->
                selectPortfolioTabItems = selectedItem
            }
        )

        Box(
            modifier = Modifier.wrapContentSize(),
        ) {
            Column(modifier = Modifier.wrapContentSize()) {
                selectedCategoryExperiences.forEachIndexed { index, experiences ->
                    if (isReduceForm && index >= 4) return@forEachIndexed // afficher que les 3 premiers
                    ExperienceItem(id = index,
                        item = experiences)
                }
            }
        }
        AnimatedVisibility(selectedCategoryExperiences.size > 3) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                PortfolioButton(
                    text = TranslationManager.getString(if (isReduceForm) "view_all" else "Reduce" )  ,
                    onClick = {
                        isReduceForm = !isReduceForm
                    }
                )
            }
        }

    }
}



@Composable
 fun ExperienceItem(
    id: Int,
    item: TimelineItem
) {
    ExperienceStepITem(
        id=id,
        isEducation = item is Education,
    ) {
        Column(
            modifier = Modifier.wrapContentHeight().fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(7.dp),
        ) {
            Text(
                text = TranslationManager.getString( item.title) + (if (item is ProfessionalExperience) " | ${item.location}" else ""),
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground
            )
            if (item is Education) {
                Text(
                    text = "${TranslationManager.getString( item.degree)} | ${item.startYear} - ${item.endYear}",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colorScheme.onBackground
                )
            } else if (item is ProfessionalExperience) {
                Text(
                    text = "${item.position?.let { TranslationManager.getString(it ) } ?:TranslationManager.getString( "Internship")} | ${item.startYear} - ${item.endYear}",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            item.description?.let {description->
                Text(
                    text = TranslationManager.getString( description) ,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    ),
                    textAlign = TextAlign.Justify
                )
            }

        }
    }
}

@Composable
 fun ExperienceStepITem(
    id: Int,
    isEducation:Boolean,
    content: @Composable () -> Unit
) {
    val isMobileDevice=currentDeviceConfigure().isMobileDevice()

  //  val isMobileDevice by remember { mutableStateOf(true) }
    val density = LocalDensity.current
    var boxHeight by remember { mutableStateOf<Dp>(150.dp) }
    Box(
        modifier = Modifier.width(1000.dp).wrapContentHeight(),
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
                    .width( 400.dp )
                    .align(if (isMobileDevice) Alignment.CenterStart else if (id % 2 == 0) Alignment.CenterStart else Alignment.CenterEnd)
                    .padding(vertical = 20.dp)
                    .padding(start = if (isMobileDevice) 50.dp else 50.dp, end = if (isMobileDevice) 0.dp else 50.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shadow(elevation = 10.dp, shape = RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.background)
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
                    .background(MaterialTheme.colorScheme.background)
                    .padding(5.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                PortfolioIcon(
                    modifier = Modifier.fillMaxSize(),
                    model= if (isEducation) Icons.Default.School else Icons.Default.Work,
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
            VerticalDivider()
        }

    }
}