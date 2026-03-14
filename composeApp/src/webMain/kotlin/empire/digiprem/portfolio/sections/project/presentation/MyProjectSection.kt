package empire.digiprem.portfolio.sections.project.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.input.pointer.PointerType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import empire.digiprem.portfolio.design_system.PortfolioButton
import empire.digiprem.portfolio.design_system.PortfolioTabBar
import empire.digiprem.portfolio.design_system.PortfolioTabItem
import empire.digiprem.portfolio.design_system.currentDeviceConfigure
import empire.digiprem.portfolio.design_system.layout.SectionLayout
import empire.digiprem.portfolio.sections.tech_stack.TechStackItem
import empire.digiprem.portfolio.theme.extended
import org.jetbrains.compose.resources.painterResource
import portfolionanaadrien.composeapp.generated.resources.Res
import portfolionanaadrien.composeapp.generated.resources.capture

private val selectedItems = listOf(
    PortfolioTabItem(
        id = "1",
        title = "Android",
    ),
    PortfolioTabItem(
        id = "2",
        title = "Ios",
    ),
    PortfolioTabItem(
        id = "3",
        title = "Server",
    ),
    PortfolioTabItem(
        id = "4",
        title = "Desktop",
    ),
    PortfolioTabItem(
        id = "5",
        title = "Web",
    ),
    PortfolioTabItem(
        id = "6",
        title = "CrossPlatform",
    ),
)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ProjectItem(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    val isMobileDevice= currentDeviceConfigure().isMobileDevice()
    var enabledLinkBox by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val enabledLinkBox2 by interactionSource.collectIsHoveredAsState()

    Column(
        modifier = modifier
            .widthIn(min =if (isMobileDevice) 300.dp else  200.dp,max = if (isMobileDevice) 350.dp else  250.dp)
            .height( if (isMobileDevice) 270.dp else 270.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, MaterialTheme.colorScheme.background.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.background)
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .fillMaxWidth()
                .hoverable(
                    interactionSource = interactionSource,
                )
                .clip(RoundedCornerShape(8.dp)),
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(Res.drawable.capture),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
            this@Column.AnimatedVisibility(
                visible = enabledLinkBox2,
                enter = fadeIn() + expandHorizontally(),
                exit = fadeOut() + shrinkHorizontally(),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.7f)),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        repeat(3){
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Color.White)
                                    .padding( 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = Modifier.fillMaxSize(),
                                    imageVector = Icons.Default.Whatsapp,
                                    contentDescription = null,
                                    tint = Color.Black,
                                )
                            }
                        }

                    }
                }
            }

        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = MaterialTheme.typography.labelLarge.let{it.copy(fontWeight = FontWeight.Bold)},
            color = MaterialTheme.colorScheme.onBackground,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = description,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
            color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
            maxLines = 2,
        )

    }

}

@Composable
fun MyProjectSection(
    modifier: Modifier = Modifier,
) {
    val isMobileDevice= currentDeviceConfigure().isMobileDevice()
    var selectPortfolioTabItems by remember { mutableStateOf(selectedItems.first()) }
    var projectItems by rememberSaveable { mutableStateOf(10) }
    LaunchedEffect(selectPortfolioTabItems) {
        projectItems = when (selectPortfolioTabItems.id) {
            "1" -> 10
            "2" -> 15
            "4" -> 4
            "5" -> 6
            else -> 3
        }
    }
    SectionLayout(
        title = "Project",
        modifier = modifier,
    ) {
        PortfolioTabBar(
            selectedPortfolioTabItem = selectPortfolioTabItems,
            tabItems = selectedItems,
            onSelectItem = { selectedItem ->
                selectPortfolioTabItems = selectedItem
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
       /* LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.height(
                ((projectItems / 4) * 300.dp) + 400.dp
            ).animateContentSize(),
        ) {
            items(projectItems) {
                ProjectItem(
                    title = "NotyStack Android",
                    description = "Tech Stack: Android, Java, XML, Firebase, MVVM, RoomDB, SQLite",
                )
            }
        }*/
        FlowRow(
            modifier = Modifier.wrapContentSize().animateContentSize(),
            verticalArrangement = Arrangement.spacedBy( if (isMobileDevice) 10.dp else 20.dp),
            horizontalArrangement = Arrangement.spacedBy( if (isMobileDevice) 10.dp else 20.dp),
        ){
            repeat(projectItems) {
                ProjectItem(
                    title = "NotyStack Android",
                    description = "Tech Stack: Android, Java, XML, Firebase, MVVM, RoomDB, SQLite",
                )
            }
        }


        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            BouncingBox2{
                PortfolioButton(
                    text = "View All",
                    onClick = {}
                )
            }

        }
    }
}
@Composable
fun BouncingBox2(
    content: @Composable BoxScope.() -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition()

    // Animation verticale infinie
    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 20f, // hauteur du rebond
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 700, // temps d’une chute
                easing = EaseIn // accélération vers le bas
            ),
            repeatMode = RepeatMode.Reverse // rebond automatique
        )
    )

    Box(
        modifier = Modifier.wrapContentSize()
            .offset { IntOffset(0, offsetY.toInt()) } // translation verticale

    ){content()}
}