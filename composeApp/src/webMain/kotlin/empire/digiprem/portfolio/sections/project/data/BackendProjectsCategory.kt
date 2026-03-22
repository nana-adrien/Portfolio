package empire.digiprem.portfolio.sections.project.data

import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.project.domain.MyProject


suspend fun backendProjectsCategory() = Category(
    details = PortfolioTabItem(id = "3", title = "Backend"),
    groups = listOf(
        MyProject(
            title ="project_backend_chirpapi_title",
            image = "https://cdn.jsdelivr.net/gh/nana-adrien/Immobi-Market@master/captures/immobi_market_api.png",
            description = "project_backend_chirpapi_desc",
            githubLink = "https://github.com/nana-adrien/chirp-api"
        ),
        MyProject(
            title = "project_backend_immobiapi_title",
            image = "https://cdn.jsdelivr.net/gh/nana-adrien/Immobi-Market@master/captures/immobi_market_api.png",
            description ="project_backend_immobiapi_desc",
            githubLink = "https://github.com/nana-adrien/Immobi-Market"
        ),
    )
)