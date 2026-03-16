package empire.digiprem.portfolio.core.design_system

import empire.digiprem.portfolio.sections.project.domain.MyProject

// Catégorie avec sa liste de projets
data class ProjectCategory(
    val tab: PortfolioTabItem,
    val projects: List<MyProject>
)