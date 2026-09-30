package empire.digiprem.portfolio.sections.experience.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.filled.WorkspacePremium
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
import androidx.compose.ui.graphics.vector.ImageVector
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
import empire.digiprem.portfolio.core.domain.enums.OpenLinkTarget
import empire.digiprem.portfolio.core.domain.services.TranslationService
import empire.digiprem.portfolio.core.domain.util.WindowsPlatform
import empire.digiprem.portfolio.sections.experience.data.categories.professionalExperienceCategory
import empire.digiprem.portfolio.sections.experience.data.experiences
import empire.digiprem.portfolio.sections.experience.domain.Certification
import empire.digiprem.portfolio.sections.experience.domain.Education
import empire.digiprem.portfolio.sections.experience.domain.ProfessionalExperience
import empire.digiprem.portfolio.sections.experience.domain.TimelineItem


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
        title = TranslationService.getString("experience"),
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
                    text = TranslationService.getString(if (isReduceForm) "view_all" else "Reduce" )  ,
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
    val icon: ImageVector = when (item) {
        is Education -> Icons.Default.School
        is Certification -> Icons.Default.WorkspacePremium
        else -> Icons.Default.Work
    }
    ExperienceStepITem(
        id=id,
        icon = icon,
    ) {
        Column(
            modifier = Modifier.wrapContentHeight().fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(7.dp),
        ) {
            Text(
                text = TranslationService.getString( item.title) +
                    when {
                        item is ProfessionalExperience -> " | ${item.location}"
                        item is Certification && item.location != null -> " | ${TranslationService.getString(item.location)}"
                        else -> ""
                    },
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground
            )
            if (item is Education) {
                val yearRange = if (item.startYear != null && item.endYear != null) " | ${item.startYear} - ${item.endYear}" else ""
                Text(
                    text = "${TranslationService.getString( item.degree)}$yearRange",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colorScheme.onBackground
                )
            } else if (item is ProfessionalExperience) {
                val yearRange = buildString {
                    item.startYear?.let { append(it) }
                    when {
                        item.isCurrent -> {
                            if (isNotEmpty()) append(" - ")
                            append(TranslationService.getString("present_label"))
                        }
                        item.endYear != null -> {
                            if (isNotEmpty()) append(" - ")
                            append(item.endYear)
                        }
                    }
                }
                val positionLabel = item.position?.let { TranslationService.getString(it) } ?: TranslationService.getString("internship")
                Text(
                    text = positionLabel + (if (yearRange.isNotEmpty()) " | $yearRange" else ""),
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colorScheme.onBackground
                )
            } else if (item is Certification && item.startYear != null) {
                Text(
                    text = "${item.startYear}",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            item.description?.let {description->
                Text(
                    text = TranslationService.getString( description) ,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    ),
                    textAlign = TextAlign.Justify
                )
            }
            if (item is Certification && item.certificateLink != null) {
                Row(
                    modifier = Modifier.wrapContentSize()
                        .clickable { WindowsPlatform.openLink(url = item.certificateLink, openLinkTarget = OpenLinkTarget.NEW_ONGLET) },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    PortfolioIcon(
                        modifier = Modifier.size(16.dp),
                        model = Icons.Default.OpenInNew,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                    Text(
                        text = TranslationService.getString("view_certificate"),
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

        }
    }
}

@Composable
 fun ExperienceStepITem(
    id: Int,
    icon: ImageVector,
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
                    model = icon,
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
            VerticalDivider()
        }

    }
}