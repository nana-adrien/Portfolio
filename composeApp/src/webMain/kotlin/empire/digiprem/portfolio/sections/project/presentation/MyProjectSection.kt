package empire.digiprem.portfolio.sections.project.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioButton
import empire.digiprem.portfolio.core.design_system.PortfolioImage
import empire.digiprem.portfolio.core.design_system.PortfolioTabBar
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.core.design_system.components.BouncingBox
import empire.digiprem.portfolio.core.design_system.currentDeviceConfigure
import empire.digiprem.portfolio.core.design_system.layout.SectionLayout
import empire.digiprem.portfolio.core.domain.enums.OpenLinkTarget
import empire.digiprem.portfolio.core.domain.services.TranslationService
import empire.digiprem.portfolio.core.domain.util.WindowsPlatform
import empire.digiprem.portfolio.sections.project.data.MyProjectDB.categories
import empire.digiprem.portfolio.sections.project.domain.MyProject
import empire.digiprem.portfolio.sections.project.presentation.components.ProjectItem
import empire.digiprem.portfolio.sections.project.presentation.components.ProjectLinkButton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import portfolionanaadrien.composeapp.generated.resources.Res


data class MyProjectSectionState(

    val categories: List<Category<MyProject>> = emptyList(),
    val selectPortfolioTabItems: PortfolioTabItem? = null,
    val isReduceForm:Boolean = false,
    val tabItem: List<PortfolioTabItem> = emptyList(),
    val selectedProject: MyProject? = null,
)

sealed interface MyProjectSectionAction {
    data class OnSelectTab(val tab: PortfolioTabItem) : MyProjectSectionAction
    data  object OnReduceItemClick : MyProjectSectionAction
    data class OnProjectClick(val project: MyProject) : MyProjectSectionAction
    data object OnDismissProjectDetails : MyProjectSectionAction
}

class MyProjectSectionViewModel : ViewModel() {

    private val _state = MutableStateFlow(MyProjectSectionState())
    val state = _state.asStateFlow()

    fun onAction(action: MyProjectSectionAction) {
        when (action) {
            is MyProjectSectionAction.OnSelectTab -> {
                _state.update {
                    it.copy(
                        selectPortfolioTabItems = action.tab,
                    )
                }
            }

            MyProjectSectionAction.OnReduceItemClick -> {
                _state.update {
                    it.copy(
                        isReduceForm = !it.isReduceForm,
                    )
                }
            }

            is MyProjectSectionAction.OnProjectClick -> {
                _state.update {
                    it.copy(selectedProject = action.project)
                }
            }

            MyProjectSectionAction.OnDismissProjectDetails -> {
                _state.update {
                    it.copy(selectedProject = null)
                }
            }
        }
    }


    init {
        viewModelScope.launch {
            val categories = categories()
            _state.update {
                it.copy(
                    selectPortfolioTabItems = categories.first().details,
                    categories = categories()
                )
            }
        }
    }
}

@Composable
fun MyProjectSection(
    modifier: Modifier = Modifier,
) {
    val viewModel: MyProjectSectionViewModel =viewModel { MyProjectSectionViewModel()}
    /*viewModel(
        factory = viewModelFactory {
            initializer { MyProjectSectionViewModel() }
        }
    )*/
    val state = viewModel.state.collectAsState().value
    val isMobileDevice = currentDeviceConfigure().isMobileDevice()
    var selectedCategorySize by rememberSaveable { mutableStateOf(0) }
    SectionLayout(
        title = TranslationService.getString("my_projects") ,
        modifier = modifier,
    ) {
        state.selectPortfolioTabItems?.let {
            PortfolioTabBar(
                selectedPortfolioTabItem = state.selectPortfolioTabItems,
                tabItems = state.categories.filter { it.groups.isNotEmpty() }.map { it.details },
                onSelectItem = { selectedItem ->
                    viewModel.onAction(MyProjectSectionAction.OnSelectTab(selectedItem))
                }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        FlowRow(
            modifier = Modifier.wrapContentSize().animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(if (isMobileDevice) 10.dp else 20.dp),
            horizontalArrangement = Arrangement.spacedBy(if (isMobileDevice) 10.dp else 20.dp),
        ) {
            state.categories
                .firstOrNull {
                    it.details.id == state.selectPortfolioTabItems!!.id
                }?.groups?.let {
                    selectedCategorySize=it.size
                    it.forEachIndexed { index, project ->
                        if (state.isReduceForm && index >= 4) return@forEachIndexed // afficher que les 3 premiers
                        ProjectItem(
                            isPrivate = project.isPrivate,
                            title = project.title,
                            image = project.image ?: Res.getUri("drawable/capture.png"),
                            description = project.description,
                            tags = project.tags,
                            demoLink = project.demoLink,
                            githubLink = project.githubLink,
                            previewLink = project.previewLink,
                            onLinkClick = {
                                WindowsPlatform.openLink(url = it, OpenLinkTarget.NEW_ONGLET)
                            },
                            onClick = {
                                viewModel.onAction(MyProjectSectionAction.OnProjectClick(project))
                            },
                        )
                    }
                }

        }


        AnimatedVisibility(selectedCategorySize > 4) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                BouncingBox {
                    PortfolioButton(
                        text = TranslationService.getString(if (state.isReduceForm) "view_all" else "Reduce" )  ,
                        onClick = {
                           viewModel.onAction(MyProjectSectionAction.OnReduceItemClick)
                        }
                    )
                }

            }
        }

    }

    state.selectedProject?.let { project ->
        ProjectDetailsDialog(
            project = project,
            onDismiss = { viewModel.onAction(MyProjectSectionAction.OnDismissProjectDetails) },
            onLinkClick = { WindowsPlatform.openLink(url = it, OpenLinkTarget.NEW_ONGLET) },
        )
    }
}

@Composable
private fun ProjectDetailsDialog(
    project: MyProject,
    onDismiss: () -> Unit,
    onLinkClick: (String) -> Unit,
) {
    DisableSelection {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    text = TranslationService.getString(project.title),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
            },
            text = {
                Column(
                    modifier = Modifier.widthIn(max = 500.dp).verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    PortfolioImage(
                        modifier = Modifier.fillMaxWidth().height(180.dp).clip(RoundedCornerShape(8.dp)),
                        image = project.image ?: Res.getUri("drawable/capture.png"),
                    )
                    if (project.tags.isNotEmpty()) {
                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                        ) {
                            project.tags.forEach { tag ->
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.15f))
                                        .padding(horizontal = 8.dp, vertical = 3.dp),
                                ) {
                                    Text(
                                        text = tag,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.primary,
                                    )
                                }
                            }
                        }
                    }
                    Text(
                        text = TranslationService.getString(project.fullDescription ?: project.description),
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Justify,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                    )
                    if (!project.isPrivate) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            project.githubLink?.let {
                                ProjectLinkButton(model = Res.getUri("drawable/github.png")) { onLinkClick(it) }
                            }
                            project.previewLink?.let {
                                ProjectLinkButton(model = Icons.Default.Preview) { onLinkClick(it) }
                            }
                            project.demoLink?.let {
                                ProjectLinkButton(model = Icons.Default.Videocam) { onLinkClick(it) }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                PortfolioButton(
                    text = TranslationService.getString("close"),
                    onClick = onDismiss,
                )
            }
        )
    }
}
