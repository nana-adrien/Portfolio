package empire.digiprem.portfolio.app

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import empire.digiprem.portfolio.app.components.footer.CommonFooter
import empire.digiprem.portfolio.app.components.header.CommonHeader
import empire.digiprem.portfolio.app.components.header.MenuItem
import empire.digiprem.portfolio.app.components.social_media.CommonSocialMedia
import empire.digiprem.portfolio.core.design_system.PortfolioIconButton
import empire.digiprem.portfolio.core.design_system.WebPageScaffold
import empire.digiprem.portfolio.core.design_system.currentDeviceConfigure
import empire.digiprem.portfolio.core.domain.util.WindowsPlatform
import empire.digiprem.portfolio.pages.Error404Page
import empire.digiprem.portfolio.sections.about.presentation.AboutMeSections
import empire.digiprem.portfolio.sections.contact.presentation.ContactSection
import empire.digiprem.portfolio.sections.experience.presentation.MyExperiencesSection
import empire.digiprem.portfolio.sections.home.HomeSections
import empire.digiprem.portfolio.sections.project.presentation.MyProjectSection
import empire.digiprem.portfolio.sections.tech_stack.presentation.TechStackSection
import empire.digiprem.portfolio.theme.PortfolioTheme
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Composable
fun App(
    isSystemInDarkTheme: Boolean,
    onDarkThemeChanged: () -> Unit,
    onNavHostReady: suspend (NavController) -> Unit = {},
) {
    PortfolioTheme(
        darkTheme = isSystemInDarkTheme
    ) {
        Navigator(
            enabledDarkTheme = isSystemInDarkTheme,
            onNavHostReady = onNavHostReady
        ) {
            onDarkThemeChanged()
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
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface),
        startDestination = NavigationGraph.InitScreen
    ) {
        composable<NavigationGraph.InitScreen> {
            Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)) {

            }
        }
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
    data object InitScreen : NavigationGraph

    @Serializable
    //@SerialName("/")
    data class HomeScreen(val section: String = Section.home.name) : NavigationGraph

    @Serializable
    data class Error404(val path: String) : NavigationGraph

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
    val scrollState = rememberLazyListState()
    val menuItems = Section.entries.map { section ->
        MenuItem(
            id = section.name,
            title = section.getTitle(),
            link = WindowsPlatform.getBaseUrl() + "#home?section=$section",
        )
    }

    var selectedMenu by remember { mutableStateOf(menuItems.first { it.id == currentSection }) }
    val scope = rememberCoroutineScope()

    // Scroll automatique vers le hash au chargement

    WebPageScaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
            .safeContentPadding()
            .fillMaxSize(),
        scrollState = scrollState,
        header = {
            CommonHeader(
                isDarkTheme = isDarkTheme,
                scrollState = scrollState,
                selectedMenu = selectedMenu,
                menuItems = menuItems,
                selectMenuItem = {
                    currentSection = it.id
                    selectedMenu = it
                    WindowsPlatform.openLink(it.link)
                },
                onDarkThemeChanged = onDarkThemeChanged,
            )

        },
        footer = {
            CommonFooter()
        },
        socialMedia = {
            CommonSocialMedia(
                visible = scrollState.firstVisibleItemIndex > 0,
            )
        },
        floatingButtonButton = {
            AnimatedVisibility(
                modifier = Modifier.padding(end = 50.dp, bottom = 150.dp),
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
                        .height(900.dp)
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
                                item.offset + (item.size) / 2

                            kotlin.math.abs(itemCenter - viewportCenter)
                        }

                    currentItem?.let {
                        val index = if (it.index > menuItems.lastIndex) menuItems.lastIndex else it.index
                        selectedMenu = menuItems[index]
                        WindowsPlatform.openLink(selectedMenu.link)
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