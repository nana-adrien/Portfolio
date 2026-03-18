package empire.digiprem.portfolio.sections.project.data

import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.project.domain.MyProject
import org.jetbrains.compose.resources.getString
import portfolionanaadrien.composeapp.generated.resources.Res
import portfolionanaadrien.composeapp.generated.resources.project_ios_barcode_title
import portfolionanaadrien.composeapp.generated.resources.project_multiplatform_chirp_desc
import portfolionanaadrien.composeapp.generated.resources.project_multiplatform_chirp_title
import portfolionanaadrien.composeapp.generated.resources.project_multiplatform_immobi_desc
import portfolionanaadrien.composeapp.generated.resources.project_multiplatform_immobi_title
import portfolionanaadrien.composeapp.generated.resources.project_multiplatform_nativeios_desc
import portfolionanaadrien.composeapp.generated.resources.project_multiplatform_nativeios_title


suspend fun multiplatformProjectsCategory() = Category(
    details = PortfolioTabItem(id = "6", title = "Multiplatform"),
    groups = listOf(
        MyProject(
            title = "project_multiplatform_nativeios_title",
            description = "project_multiplatform_nativeios_desc",
            previewLink = "https://github.com/nana-adrien/Native-Ios-in-Compose-Multiplatforme/tree/main/capture",
            githubLink = "https://github.com/nana-adrien/Native-Ios-in-Compose-Multiplatforme"
        ),

        MyProject(
            title = "project_multiplatform_chirp_title",
            description = "project_multiplatform_chirp_desc",
            githubLink = "https://github.com/nana-adrien/Chirp"
        ),

        MyProject(
            title = "project_multiplatform_immobi_title",
            description = "project_multiplatform_immobi_desc",
            image = "https://raw.githubusercontent.com/nana-adrien/Immobi-Market/refs/heads/master/captures/immobi_market_preview.png",
            previewLink = "https://github.com/nana-adrien/Immobi-Market/blob/master/captures/immobi_market_preview.png?raw=true",
            githubLink = "https://github.com/nana-adrien/Immobi-Market"
        ),
    )
)