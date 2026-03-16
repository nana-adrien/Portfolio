package empire.digiprem.portfolio.sections.project.data

import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.project.domain.MyProject
import org.jetbrains.compose.resources.getString
import portfolionanaadrien.composeapp.generated.resources.Res
import portfolionanaadrien.composeapp.generated.resources.project_backend_chirpapi_title
import portfolionanaadrien.composeapp.generated.resources.project_ios_barcode_desc
import portfolionanaadrien.composeapp.generated.resources.project_ios_barcode_title


suspend fun iosProjectsCategory() =  Category(
    details =  PortfolioTabItem(id = "2", title = "iOS"),
    groups = listOf(
        MyProject(
            title = "project_ios_barcode_title",
            description = "project_ios_barcode_desc",
            githubLink = "https://github.com/nana-adrien/BarcodeScanner"
        )
    )
)