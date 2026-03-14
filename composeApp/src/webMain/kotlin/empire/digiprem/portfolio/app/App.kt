package empire.digiprem.portfolio.app

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.Light
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LinkedCamera
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastJoinToString
import androidx.navigation.NavController
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import empire.digiprem.portfolio.app.components.Footer
import empire.digiprem.portfolio.app.components.Header
import empire.digiprem.portfolio.app.components.MenuItem
import empire.digiprem.portfolio.app.components.SocialMedia
import empire.digiprem.portfolio.app.components.SocialMediaLink
import empire.digiprem.portfolio.design_system.PortfolioIconButton
import empire.digiprem.portfolio.design_system.PortfolioLogo
import empire.digiprem.portfolio.design_system.WebPageScaffold
import empire.digiprem.portfolio.design_system.currentDeviceConfigure
import empire.digiprem.portfolio.sections.AboutMeSections
import empire.digiprem.portfolio.sections.ContactSection
import empire.digiprem.portfolio.sections.HomeSections
import empire.digiprem.portfolio.sections.experience.presentation.MyExperiencesSection
import empire.digiprem.portfolio.sections.getBaseUrl
import empire.digiprem.portfolio.sections.openLink
import empire.digiprem.portfolio.sections.project.presentation.MyProjectSection
import empire.digiprem.portfolio.sections.tech_stack.TechStackSection
import empire.digiprem.portfolio.theme.PortfolioTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource

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
    enabledDarkTheme: Boolean,
    onDarkThemeChanged: () -> Unit,
) {
    var currentSection by rememberSaveable { mutableStateOf(targetId) }
    var value by rememberSaveable { mutableStateOf(0) }
    var value2: String by rememberSaveable { mutableStateOf("") }
    var sectionRefs by remember { mutableStateOf<MutableMap<Section, MutableState<IntOffset>>>(mutableMapOf()) }

    val isMobileDevice = currentDeviceConfigure().isMobileDevice()
    val density = LocalDensity.current
    var showContent by remember { mutableStateOf(false) }
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val menuItems = Section.entries.mapIndexed { index, section ->
        MenuItem(
            id = section.name,
            title = section.name.split('_').fastJoinToString(" ").replaceFirstChar { it.uppercase() },
            link = getBaseUrl()+"#home?section=$section",
        )
    }
    var selectedMenu by remember { mutableStateOf(menuItems.first { it.id == currentSection }) }
    val animateHeaderContainerColor by animateColorAsState(
        targetValue = with(density) {
            if (scrollState.scrollIndicatorState?.scrollOffset?.toDp() ?: 0.dp < 750.dp) Color.Transparent else MaterialTheme.colorScheme.surface.copy(
                alpha = 0.7f
            )
        }
    )
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
                    .height(50.dp)
                    .fillMaxWidth()
                    .background(animateHeaderContainerColor)
                    .padding(horizontal = if (isMobileDevice) 16.dp else 0.dp),
                animateContentColor = animateContentColor,
                logo = { PortfolioLogo(color = animateContentColor) },
                action = {
                    PortfolioIconButton(
                        onClick = { onDarkThemeChanged() },
                    ) {
                        Icon(
                            imageVector = if (enabledDarkTheme) Icons.Default.DarkMode else Icons.Default.LightMode,
                            contentDescription = "Light",
                            tint = animateContentColor
                        )
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
        item {
            FadeSlideInOnScroll {
                HomeSections(
                    modifier = Modifier
                        .height(800.dp)
                        .fillMaxWidth()
                        .padding(bottom = 30.dp)
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
                        .heightIn(min = 500.dp)
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
                        .padding(vertical = 20.dp)
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
    LaunchedEffect(scrollState) {
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
    contact,
}