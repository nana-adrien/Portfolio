package empire.digiprem.portfolio.sections.experience.data.categories

import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.experience.domain.Education
import empire.digiprem.portfolio.sections.experience.domain.TimelineItem

val educationExperienceCategory = Category<TimelineItem>(
    details = PortfolioTabItem(
        id = "1",
        title = "education_title",
    ),
    groups = listOf(
        Education(
            title = "education_zeal_title",
            degree = "education_zeal_degree",
            startYear = 2020,
            endYear = 2024,
            description ="education_zeal_desc"
        )
    )
)