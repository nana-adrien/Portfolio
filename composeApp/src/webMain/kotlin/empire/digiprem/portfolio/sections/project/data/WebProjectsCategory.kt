package empire.digiprem.portfolio.sections.project.data

import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.project.presentation.MyProject



val webProjectsCategory =  Category(
    details =  PortfolioTabItem(id = "5", title = "web"),
    groups = listOf(
        MyProject(
            title = "My Portfolio",
            description = "A modern developer portfolio built with Kotlin and WebAssembly using Compose Multiplatform.  \n" +
                    "The project showcases my skills, experience, and projects through a responsive and interactive web application fully written in Kotlin.",
            image = "https://github.com/nana-adrien/Portfolio/blob/master/composeApp/production/capture/portfolio_preview.png",
            githubLink = "https://github.com/nana-adrien/Portfolio",
        ),
    )
)