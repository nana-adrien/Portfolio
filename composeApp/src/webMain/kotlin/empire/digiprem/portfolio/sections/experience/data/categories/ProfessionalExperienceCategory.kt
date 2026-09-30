package empire.digiprem.portfolio.sections.experience.data.categories

import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.experience.domain.ExperienceType
import empire.digiprem.portfolio.sections.experience.domain.ProfessionalExperience
import empire.digiprem.portfolio.sections.experience.domain.TimelineItem

 val  professionalExperienceCategory = Category<TimelineItem>(
    details = PortfolioTabItem(
        id = "2",
        title = "professional_title",
    ),
    groups = listOf(
        ProfessionalExperience(
            title = "professional_nextget_title",
            location = "Douala, Cameroun",
            type = ExperienceType.JOB,
            position = "professional_nextget_position",
            startYear = null,
            endYear = null,
            isCurrent = true,
            description = "professional_nextget_desc"
        )
    )
)
