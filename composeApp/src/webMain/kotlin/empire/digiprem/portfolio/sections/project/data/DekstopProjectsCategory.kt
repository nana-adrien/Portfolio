package empire.digiprem.portfolio.sections.project.data

import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.project.domain.MyProject



suspend fun desktopProjectsCategory() =  Category(
    details =  PortfolioTabItem(id = "4", title = "Desktop"),
    groups = emptyList<MyProject>(),
)