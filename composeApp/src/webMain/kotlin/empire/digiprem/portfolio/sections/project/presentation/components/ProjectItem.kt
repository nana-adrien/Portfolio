package empire.digiprem.portfolio.sections.project.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import empire.digiprem.portfolio.core.design_system.PortfolioIcon
import empire.digiprem.portfolio.core.design_system.currentDeviceConfigure
import org.jetbrains.compose.resources.painterResource
import portfolionanaadrien.composeapp.generated.resources.Res
import portfolionanaadrien.composeapp.generated.resources.capture

@OptIn(ExperimentalComposeUiApi::class)
@Composable
 fun ProjectItem(
    isPrivate: Boolean = false,
    title: String,
    description: String,
    image: String?,
    demoLink: String? = null,
    githubLink: String? = null,
    previewLink: String? = null,
    modifier: Modifier = Modifier,
    onLinkClick: (String) -> Unit,
) {
    val isMobileDevice = currentDeviceConfigure().isMobileDevice()
    val interactionSource = remember { MutableInteractionSource() }
    val enabledLinkBox2 by interactionSource.collectIsHoveredAsState()

    Column(
        modifier = modifier
            .widthIn(min = if (isMobileDevice) 300.dp else 200.dp, max = if (isMobileDevice) 350.dp else 300.dp)
            .height(if (isMobileDevice) 270.dp else 310.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, MaterialTheme.colorScheme.background.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.background)
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    )
    {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .fillMaxWidth()
                .hoverable(
                    interactionSource = interactionSource,
                )
                .clip(RoundedCornerShape(8.dp)),
        ) {

            image?.let {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = image,
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                )
            }
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
                        if (isPrivate) {
                            ProjectLinkButton(
                                model = Icons.Default.Security,
                            ) {

                            }
                        } else {
                            previewLink?.let {
                                ProjectLinkButton(
                                    model = Icons.Default.Preview,
                                ) {
                                    onLinkClick(it)
                                }
                            }
                            githubLink?.let {
                                ProjectLinkButton(
                                    model = Res.getUri("drawable/github.png")
                                ) {
                                    onLinkClick(it)
                                }
                            }
                            demoLink?.let {
                                ProjectLinkButton(
                                    model = Icons.Default.Videocam,
                                ) {
                                    onLinkClick(it)

                                }
                            }
                        }

                    }
                }
            }

        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = MaterialTheme.typography.titleSmall.let { it.copy(fontWeight = FontWeight.Bold) },
            color = MaterialTheme.colorScheme.onBackground,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = description,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
            maxLines = 2,
        )

    }

}