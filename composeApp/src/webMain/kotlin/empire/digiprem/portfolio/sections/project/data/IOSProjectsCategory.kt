package empire.digiprem.portfolio.sections.project.data

import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.project.presentation.MyProject



val iosProjectsCategory =  Category(
    details =  PortfolioTabItem(id = "2", title = "iOS"),
    groups = listOf(
        MyProject(
            title = "NotyStack iOS",
            description = "Swift, SwiftUI, Firebase",
            githubLink = "https://github.com/example/ios1"
        )
    )
)