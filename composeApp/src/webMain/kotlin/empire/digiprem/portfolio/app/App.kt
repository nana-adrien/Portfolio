package empire.digiprem.portfolio.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LinkedCamera
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import empire.digiprem.portfolio.app.components.Footer
import empire.digiprem.portfolio.app.components.Header
import empire.digiprem.portfolio.app.components.MenuItem
import empire.digiprem.portfolio.app.components.SocialMedia
import empire.digiprem.portfolio.app.components.SocialMediaLink
import empire.digiprem.portfolio.core.design_system.PortfolioButton
import empire.digiprem.portfolio.core.design_system.PortfolioIconButton
import empire.digiprem.portfolio.core.design_system.PortfolioLogoText
import empire.digiprem.portfolio.core.design_system.WebPageScaffold
import empire.digiprem.portfolio.core.design_system.currentDeviceConfigure
import empire.digiprem.portfolio.core.domain.Language
import empire.digiprem.portfolio.core.domain.TranslationManager
import empire.digiprem.portfolio.core.domain.TranslationManager.currentLanguage
import empire.digiprem.portfolio.pages.Error404Page
import empire.digiprem.portfolio.sections.AboutMeSections
import empire.digiprem.portfolio.sections.ContactSection
import empire.digiprem.portfolio.sections.HomeSections
import empire.digiprem.portfolio.sections.PortfolioTextField
import empire.digiprem.portfolio.sections.experience.presentation.MyExperiencesSection
import empire.digiprem.portfolio.sections.getBaseUrl
import empire.digiprem.portfolio.sections.openLink
import empire.digiprem.portfolio.sections.project.presentation.MyProjectSection
import empire.digiprem.portfolio.sections.sendDataToService
import empire.digiprem.portfolio.sections.tech_stack.presentation.TechStackSection
import empire.digiprem.portfolio.theme.PortfolioTheme
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource
import portfolionanaadrien.composeapp.generated.resources.Res
import portfolionanaadrien.composeapp.generated.resources.nav_about
import portfolionanaadrien.composeapp.generated.resources.nav_contact
import portfolionanaadrien.composeapp.generated.resources.nav_experience
import portfolionanaadrien.composeapp.generated.resources.nav_home
import portfolionanaadrien.composeapp.generated.resources.nav_project
import portfolionanaadrien.composeapp.generated.resources.nav_tech

@Composable
fun App(
    darkTheme: Boolean = isSystemInDarkTheme(),
    onNavHostReady: suspend (NavController) -> Unit = {}
) {
    var enabledDarkTheme by rememberSaveable { mutableStateOf(darkTheme) }


    SelectionContainer {
        PortfolioTheme(
            darkTheme = enabledDarkTheme
        ) {
            Navigator(
                enabledDarkTheme = enabledDarkTheme,
                onNavHostReady = onNavHostReady
            ) {
                enabledDarkTheme = !enabledDarkTheme
            }
        }

    }
}

@Composable
fun Navigator(
    enabledDarkTheme: Boolean,
    onNavHostReady: suspend (NavController) -> Unit = {},
    onDarkThemeChanged: () -> Unit,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationGraph.HomeScreen()
    ) {
        composable<NavigationGraph.HomeScreen> {
            HomePage(
                targetId = it.toRoute<NavigationGraph.HomeScreen>().section,
                isDarkTheme = enabledDarkTheme,
                onDarkThemeChanged = onDarkThemeChanged,
            )
        }
        composable<NavigationGraph.Error404> {
            Error404Page(
                enabledDarkTheme = enabledDarkTheme,
                onDarkThemeChanged = onDarkThemeChanged,
            )
        }
    }

    LaunchedEffect(navController) {
        onNavHostReady(navController)
    }
}

sealed interface NavigationGraph {
    @Serializable
    //@SerialName("/")
    data class HomeScreen(val section: String = Section.home.name) : NavigationGraph

    @Serializable
    data object Error404 : NavigationGraph

}


@Composable
fun FadeSlideInOnScroll(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    var visible by remember { mutableStateOf(false) }

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(700)
    )

    val offsetY by animateDpAsState(
        targetValue = if (visible) 0.dp else (40).dp,
        animationSpec = tween(700)
    )

    Box(
        modifier = modifier
            .offset(y = offsetY)
            .graphicsLayer { this.alpha = alpha }
            .onPlaced {
                visible = true
            }
    ) {
        content()
    }
}

@Composable
private fun HomePage(
    targetId: String,
    isDarkTheme: Boolean,
    onDarkThemeChanged: () -> Unit,
) {
    var currentSection by rememberSaveable { mutableStateOf(targetId) }
    val currentDeviceConfiguration = currentDeviceConfigure()
    val isMobileDevice = currentDeviceConfiguration.isMobileDevice() || currentDeviceConfiguration.isTabletDevice()
    val density = LocalDensity.current
    val scrollState = rememberLazyListState()
    val menuItems = Section.entries.map { section ->
        MenuItem(
            id = section.name,
            title = section.getTitle(),
            link = getBaseUrl() + "#home?section=$section",
        )
    }

    var selectedMenu by remember { mutableStateOf(menuItems.first { it.id == currentSection }) }
    val scope = rememberCoroutineScope()
    val animateHeaderContainerColor by animateColorAsState(
        targetValue = with(density) {
            if (scrollState.scrollIndicatorState?.scrollOffset?.toDp() ?: 0.dp < 750.dp) Color.Transparent else MaterialTheme.colorScheme.surface.copy(
                alpha = 0.7f
            )
        }
    )
    var showLanguageDialog by rememberSaveable { mutableStateOf(false) }
    val animateContentColor by animateColorAsState(
        targetValue = with(density) { if (scrollState.scrollIndicatorState?.scrollOffset?.toDp() ?: 0.dp < 750.dp) Color.White else MaterialTheme.colorScheme.onBackground }
    )
    // Scroll automatique vers le hash au chargement

    WebPageScaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
            .safeContentPadding()
            .fillMaxSize(),
        scrollState = scrollState,
        header = {
            Header(
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .background(animateHeaderContainerColor)
                    .padding(horizontal = if (isMobileDevice) 16.dp else 0.dp),
                animateContentColor = animateContentColor,
                logo = {
                    PortfolioLogoText(
                        isDarkTheme = isDarkTheme,
                        color = animateContentColor
                    )
                },
                action = {
                    PortfolioIconButton(
                        model = if (isDarkTheme) Icons.Default.DarkMode else Icons.Default.LightMode,
                        onClick = { onDarkThemeChanged() },
                        tint = animateContentColor
                    )
                    Box{
                        PortfolioIconButton(
                            model = Icons.Default.Language,
                            onClick = { showLanguageDialog = !showLanguageDialog },
                            tint = animateContentColor
                        )
                        DropdownMenu(
                            expanded = showLanguageDialog,

                            onDismissRequest = { showLanguageDialog = false },
                        ) {
                            Language.entries.forEach { language ->
                                val isSelectedLanguage = currentLanguage == language
                                DisableSelection {
                                    DropdownMenuItem(
                                        modifier = Modifier
                                            .pointerHoverIcon(PointerIcon.Hand)
                                            .background(
                                                color = if (isSelectedLanguage) {
                                                    MaterialTheme.colorScheme.primary
                                                } else Color.Transparent,
                                            ),
                                        colors = MenuDefaults.itemColors().let {
                                            it.copy(
                                                textColor = if (isSelectedLanguage) {
                                                    MaterialTheme.colorScheme.background
                                                } else MaterialTheme.colorScheme.onBackground,
                                            )
                                        },
                                        text = {
                                            Text(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(5.dp),
                                                text = TranslationManager.getString(language.value),
                                                style = MaterialTheme
                                                    .typography
                                                    .labelSmall
                                                    .let {
                                                        it.copy(
                                                            fontWeight = FontWeight.SemiBold,
                                                            textAlign = TextAlign.Center,
                                                        )
                                                    },
                                            )
                                        },
                                        onClick = {
                                            TranslationManager.setLanguage(language)
                                            showLanguageDialog = false
                                        })
                                }
                            }
                        }
                    }

                },
                selectedMenu = selectedMenu,
                menuItems = menuItems
            ) {
                currentSection = it.id
                selectedMenu = it
                openLink(it.link)
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f))
        },
        footer = {
            Footer(
                modifier = Modifier
                    .heightIn(min = 60.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = if (!isMobileDevice) 16.dp else 5.dp, vertical = 10.dp)
            )

        },
        socialMedia = {
            AnimatedVisibility(
                visible = scrollState.firstVisibleItemIndex > 0,
                enter = fadeIn() + expandHorizontally(),
                exit = fadeOut() + shrinkHorizontally(),
            ) {
                SocialMediaLink(
                    modifier = Modifier,
                    socialMedias = listOf(
                        SocialMedia(
                            icon = Icons.Default.Whatsapp,
                            link = "https://wa.me/237655011738",
                        ),
                        SocialMedia(
                            icon = Icons.Default.Pin,
                            url = Res.getUri("drawable/github.png"),
                            link = "https://github.com/nana-adrien",
                        ),
                        SocialMedia(
                            icon = Icons.Default.LinkedCamera,
                            url = Res.getUri("drawable/linked_in.png"),
                            link = "https://www.linkedin.com/in/adrien-nana-5b04aa262/",
                        ),
                    )
                )
            }


        },
        floatingButtonButton = {
            AnimatedVisibility(
                modifier = Modifier.padding(end = 50.dp, bottom = 70.dp),
                visible = scrollState.firstVisibleItemIndex > 0,
                enter = fadeIn() + expandHorizontally(),
                exit = fadeOut() + shrinkHorizontally(),
            ) {
                PortfolioIconButton(
                    modifier = Modifier.size(50.dp),
                    model = Icons.Default.ArrowUpward,
                    onClick = {
                        scope.launch {
                            scrollState.animateScrollToItem(0)
                        }
                    },
                )
            }
        }

    ) {
        item {
            FadeSlideInOnScroll {
                HomeSections(
                    isDarkTheme = isDarkTheme,
                    modifier = Modifier
                        .height(800.dp)
                        .fillMaxWidth()
                        .padding(bottom = 30.dp),
                    onAboutButtonClick = {
                        scope.launch {
                            scrollState.animateScrollToItem(1)
                        }
                    }
                )
            }
        }
        item {
            FadeSlideInOnScroll {
                AboutMeSections(
                    modifier = Modifier
                        .heightIn(min = 500.dp)
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                )
            }
        }
        item {
            FadeSlideInOnScroll {
                TechStackSection(
                    modifier = Modifier
                        .heightIn(min = 300.dp)
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                )
            }
        }
        item {
            FadeSlideInOnScroll {
                MyProjectSection(
                    modifier = Modifier
                        .heightIn(min = 500.dp)
                        .fillMaxWidth()
                        .padding(vertical = 30.dp)
                )
            }
        }
        item {
            FadeSlideInOnScroll {
                MyExperiencesSection(
                    modifier = Modifier
                        .heightIn(min = 500.dp)
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                )
            }
        }
        item {
            FadeSlideInOnScroll {
                ContactSection(
                    modifier = Modifier
                        .heightIn(min = 600.dp)
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                )
            }
        }
    }
    LaunchedEffect(scrollState.isScrollInProgress) {
        if (scrollState.isScrollInProgress) {
            snapshotFlow { scrollState.layoutInfo }
                .collect { layout ->
                    val viewportCenter =
                        (layout.viewportStartOffset + layout.viewportEndOffset) / 2
                    val currentItem =
                        layout.visibleItemsInfo.minByOrNull { item ->

                            val itemCenter =
                                item.offset + item.size / 2

                            kotlin.math.abs(itemCenter - viewportCenter)
                        }

                    currentItem?.let {
                        val index = it.index
                        selectedMenu = menuItems[index]
                        openLink(selectedMenu.link)
                    }
                }
        }

    }
    LaunchedEffect(currentSection) {
        when {
            currentSection == Section.home.name -> scrollState.animateScrollToItem(0)
            currentSection == Section.about.name -> scrollState.animateScrollToItem(1)
            currentSection == Section.tech_stack.name -> scrollState.animateScrollToItem(2)
            currentSection == Section.project.name -> scrollState.animateScrollToItem(
                3
            )

            currentSection == Section.experience.name -> scrollState.animateScrollToItem(4)
            currentSection == Section.contact.name -> scrollState.animateScrollToItem(
                5
            )
        }
        /* sectionRefs[targetId]?.value?.let { offset ->
             scrollState.animateScrollTo(offset.y)
         }*/
    }
}

enum class Section {
    home,
    about,
    tech_stack,
    project,
    experience,
    contact;

    fun getTitle() = "nav_${this.name.lowercase()}"
}