package empire.digiprem.portfolio.sections.experience.data.categories

import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.experience.domain.Education
import empire.digiprem.portfolio.sections.experience.domain.TimelineItem

val educationExperienceCategory = Category<TimelineItem>(
    details = PortfolioTabItem(
        id = "1",
        title = "Education",
    ),
    groups = listOf(
        Education(
            title = "Zeal College Of Engineering And Research | SPPU",
            degree = "B.E. Information Technology",
            startYear = 2020,
            endYear = 2024,
            description = """
            * Pursuing IT Engineering course from Savitribai Phule Pune University (SPPU)
            * Worked on web application development using HTML, CSS, and JavaScript, designing a database system using SQL 
            * Familiar with software development methodologies and project management practices, including Agile and Waterfall methodologies.
        """.trimIndent()
        )
    )
)