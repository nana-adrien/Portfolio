package empire.digiprem.portfolio.sections.project.data

import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.project.presentation.MyProject



val iosProjectsCategory =  Category(
    details =  PortfolioTabItem(id = "2", title = "iOS"),
    groups = listOf(
        MyProject(
            title = "Barcode Scanner",
            description = "The project presented here is the result of a SWUIFT UI fundamentals training course; it is the third in a series of four. ",
            githubLink = "https://github.com/nana-adrien/BarcodeScanner"
        )
    )
)