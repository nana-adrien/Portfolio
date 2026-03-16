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
            title = "professional_digiprem_title",
            location = "Douala, Cameroon",
            type = ExperienceType.INTERNSHIP,
            position = null,
            startYear = 2022,
            endYear = 2022,
            description = "professional_digiprem_desc"
        ),
        ProfessionalExperience(
            title = "professional_netfx_title",
            location = "Douala, Cameroon",
            type = ExperienceType.JOB,
            position = "professional_netfx_position",
            startYear = 2023,
            endYear = 2025,
            description = "professional_netfx_desc"
        )
    )
)
