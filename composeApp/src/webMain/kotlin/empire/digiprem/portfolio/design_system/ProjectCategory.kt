package empire.digiprem.portfolio.design_system

import empire.digiprem.portfolio.sections.project.presentation.MyProject

// Catégorie avec sa liste de projets
data class ProjectCategory(
    val tab: PortfolioTabItem,
    val projects: List<MyProject>
)