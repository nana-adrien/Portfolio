package empire.digiprem.portfolio.sections.project.data

import empire.digiprem.portfolio.design_system.PortfolioTabItem
import empire.digiprem.portfolio.design_system.ProjectCategory
import empire.digiprem.portfolio.sections.project.presentation.MyProject



val iosProjectsCategory = ProjectCategory(
    tab = PortfolioTabItem(id = "2", title = "iOS"),
    projects = listOf(
        MyProject(
            title = "NotyStack iOS",
            description = "Swift, SwiftUI, Firebase",
            githubLink = "https://github.com/example/ios1"
        )
    )
)