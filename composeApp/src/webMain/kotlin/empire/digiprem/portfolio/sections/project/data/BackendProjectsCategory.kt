package empire.digiprem.portfolio.sections.project.data

import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.project.domain.MyProject
import org.jetbrains.compose.resources.getString
import portfolionanaadrien.composeapp.generated.resources.Res
import portfolionanaadrien.composeapp.generated.resources.project_android_notifications_title
import portfolionanaadrien.composeapp.generated.resources.project_backend_chirpapi_desc
import portfolionanaadrien.composeapp.generated.resources.project_backend_chirpapi_title
import portfolionanaadrien.composeapp.generated.resources.project_backend_immobiapi_desc
import portfolionanaadrien.composeapp.generated.resources.project_backend_immobiapi_title


suspend fun backendProjectsCategory() = Category(
    details = PortfolioTabItem(id = "3", title = "Backend"),
    groups = listOf(
        MyProject(
            title ="project_backend_chirpapi_title",
            description = "project_backend_chirpapi_desc",
            githubLink = "https://github.com/nana-adrien/chirp-api"
        ),
        MyProject(
            title = "project_backend_immobiapi_title",
            description ="project_backend_immobiapi_desc",
            githubLink = "https://github.com/nana-adrien/Immobi-Market"
        ),
    )
)