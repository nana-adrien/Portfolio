package empire.digiprem.portfolio.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.LinkedCamera
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.app.components.Header
import empire.digiprem.portfolio.app.components.MenuItem
import empire.digiprem.portfolio.app.components.SocialMedia
import empire.digiprem.portfolio.app.components.SocialMediaLink
import empire.digiprem.portfolio.design_system.PortfolioLogo
import empire.digiprem.portfolio.design_system.WebPageScaffold
import empire.digiprem.portfolio.sections.AboutMeSections
import empire.digiprem.portfolio.sections.ContactSection
import empire.digiprem.portfolio.sections.HomeSections
import empire.digiprem.portfolio.sections.experience.presentation.MyExperiencesSection
import empire.digiprem.portfolio.sections.project.presentation.MyProjectSection
import empire.digiprem.portfolio.sections.project.presentation.components.ExperienceItem
import empire.digiprem.portfolio.sections.tech_stack.TechStackSection
import empire.digiprem.portfolio.theme.PortfolioTheme
import org.jetbrains.compose.resources.painterResource

import portfolionanaadrien.composeapp.generated.resources.Res
import portfolionanaadrien.composeapp.generated.resources.plan_de_travail_de_k_n_a

@Composable
fun App() {
    PortfolioTheme(
        darkTheme = false
    ) {
        var showContent by remember { mutableStateOf(false) }
        val scrollState = rememberScrollState()
        var selectedMenu by remember { mutableStateOf<MenuItem?>(null) }
        WebPageScaffold(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
                .safeContentPadding()
                .fillMaxSize()
                .verticalScroll(scrollState),
            header = {
                Header(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = 0.2f))
                        ,
                    logo = { PortfolioLogo() },
                    selectedMenu = selectedMenu,
                    menuItems = listOf(
                        MenuItem(
                            id = 1L,
                            title = "Home",
                            link = ""
                        ),
                        MenuItem(
                            id = 2L,
                            title = "About Me",
                            link = ""
                        ),
                        MenuItem(
                            id = 3L,
                            title = "Projects",
                            link = ""
                        ),
                        MenuItem(
                            id = 4L,
                            title = "Experiences",
                            link = ""
                        ),
                        MenuItem(
                            id = 5L,
                            title = "Contact",
                            link = ""
                        ),
                    )
                ) {
                    selectedMenu = it
                }
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
            },
            footer = {
                Surface(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                ) {

                }
            },
            socialMedia = {
                SocialMediaLink(
                    modifier = Modifier,
                    socialMedias = listOf(
                        SocialMedia(
                            icon = Icons.Default.Whatsapp,
                            link = "WhatsApp",
                        ),
                        SocialMedia(
                            icon = Icons.Default.Facebook,
                            link = "Facebook",
                        ),
                        SocialMedia(
                            icon = Icons.Default.LinkedCamera,
                            link = "Facebook",
                        ),
                    )
                )
            }

        ) {
            HomeSections(modifier = Modifier.heightIn(min = 500.dp).fillMaxWidth().padding(vertical = 20.dp))
            AboutMeSections(
                modifier = Modifier.heightIn(min = 500.dp).fillMaxWidth().padding(vertical = 20.dp),
            )
            TechStackSection(modifier = Modifier.heightIn(min = 500.dp).fillMaxWidth().padding(vertical = 20.dp))
            MyProjectSection(modifier = Modifier.heightIn(min = 500.dp).fillMaxWidth().padding(vertical = 20.dp))
            MyExperiencesSection(modifier = Modifier.heightIn(min = 500.dp).fillMaxWidth().padding(vertical = 20.dp))
            ContactSection(modifier = Modifier.heightIn(min = 500.dp).fillMaxWidth().padding(vertical = 20.dp))

        }
        /*    Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondary)
                    .safeContentPadding()
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    ,
                horizontalAlignment = Alignment.CenterHorizontally,
            )
            {


                *//*Button(onClick = { showContent = !showContent }) {
                Text("Click a gains!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }*//*
        }*/
    }
}