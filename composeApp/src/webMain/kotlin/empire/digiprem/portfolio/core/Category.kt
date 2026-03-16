package empire.digiprem.portfolio.core

import empire.digiprem.portfolio.core.design_system.PortfolioTabItem

data class Category<T>(
    val details: PortfolioTabItem,
    val groups: List<T>,
)
