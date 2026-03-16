package empire.digiprem.portfolio.sections

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil3.compose.AsyncImage
import empire.digiprem.portfolio.core.design_system.PortfolioButton
import empire.digiprem.portfolio.core.design_system.PortfolioIcon
import empire.digiprem.portfolio.core.design_system.PortfolioLogo
import empire.digiprem.portfolio.core.design_system.animation.FloatingBox
import empire.digiprem.portfolio.core.design_system.currentDeviceConfigure
import empire.digiprem.portfolio.core.design_system.layout.SectionLayout
import empire.digiprem.portfolio.theme.PlusJakartaSans
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import portfolionanaadrien.composeapp.generated.resources.Res
import portfolionanaadrien.composeapp.generated.resources.focus_desc
import portfolionanaadrien.composeapp.generated.resources.hey
import portfolionanaadrien.composeapp.generated.resources.nana_intro
import portfolionanaadrien.composeapp.generated.resources.nav_project
import portfolionanaadrien.composeapp.generated.resources.on_me
import portfolionanaadrien.composeapp.generated.resources.passion_intro


@Composable
fun HomeSections(
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
    onAboutButtonClick: () -> Unit,
) {
    val isMobileDevice = currentDeviceConfigure().isMobileDevice()

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {

        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = Res.getUri("drawable/plan_de_travail_de_k_n_a.jpeg"),// painterResource(Res.drawable.plan_de_travail_de_k_n_a),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier.fillMaxSize()
                .fillMaxWidth(0.4f)
                .align(Alignment.CenterStart)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color.Black,
                            Color.Black.copy(alpha = 0.5f),
                            Color.Black.copy(alpha = 0.3f),
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent,
                        )
                    )
                )
        )
        Box(
            modifier = Modifier.fillMaxSize()
                .fillMaxWidth(0.4f)
                .align(Alignment.CenterEnd)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.3f),
                            Color.Black.copy(alpha = 0.5f),
                            Color.Black.copy(alpha = 0.7f),
                            Color.Black,

                            )
                    )
                )
        )

        val mutableContent = @Composable {
            Box(
                modifier = Modifier
                    .width(if (isMobileDevice) 350.dp else 500.dp)
                    .fillMaxHeight(if (isMobileDevice) 0.5f else 0.6f),
                contentAlignment = Alignment.Center
            )
            {
                FloatingBox(
                    modifier = Modifier.align(Alignment.TopStart).padding(top = 70.dp)
                ) {
                    Box(
                        modifier = Modifier.size(if (isMobileDevice) 50.dp else 65.dp)
                            .clip(CircleShape)
                            .shadow(elevation = 10.dp, CircleShape).background(MaterialTheme.colorScheme.surface)
                            .padding(if (isMobileDevice) 7.dp else 10.dp)
                    ) {
                        PortfolioIcon(
                            modifier = Modifier.fillMaxSize(),
                            model = "https://cdn.simpleicons.org/jetpackcompose",
                            tint = null
                        )
                    }

                }

                val animatedFloat by animateFloatAsState(if (isMobileDevice) 100f else 0f)

                FloatingBox(
                    modifier = Modifier.align(Alignment.TopEnd)
                        .graphicsLayer {
                            translationY = animatedFloat
                        },
                ) {
                    Box(
                        modifier = Modifier
                            .size(if (isMobileDevice) 55.dp else 75.dp).clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(if (isMobileDevice) 15.dp else 18.dp)
                    ) {
                        PortfolioIcon(
                            modifier = Modifier.fillMaxSize(),
                            model = "https://cdn.simpleicons.org/kotlin",
                            tint = null
                        )
                    }
                }

                FloatingBox(
                    modifier = Modifier.align(Alignment.CenterEnd)
                        .padding(start = 50.dp)
                        .graphicsLayer {
                            translationY = animatedFloat
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .size(if (isMobileDevice) 45.dp else 65.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(if (isMobileDevice) 7.dp else 10.dp)
                    ) {

                        PortfolioIcon(
                            modifier = Modifier.fillMaxSize(),
                            model = "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/intellij/intellij-original.svg",
                            tint = null
                        )
                    }
                }
                FloatingBox(
                    modifier = Modifier.align(Alignment.BottomEnd).padding(end = 70.dp, top = 20.dp)
                ) {
                    Box(
                        modifier = Modifier.size(if (isMobileDevice) 50.dp else 65.dp)

                            .clip(CircleShape).background(MaterialTheme.colorScheme.surface)
                            .padding(if (isMobileDevice) 7.dp else 10.dp)
                    ) {
                        PortfolioIcon(
                            modifier = Modifier.fillMaxSize(),
                            model = "https://cdn.simpleicons.org/postman",
                            tint = null
                        )
                    }
                }

                PortfolioLogo(
                    isDarkTheme = isDarkTheme,
                    modifier = Modifier.padding(end = 50.dp).size(250.dp)
                )
            }
        }

        SectionLayout(

        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isMobileDevice) {
                    mutableContent()
                }
                Row(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight().align(Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    Column(
                        modifier = Modifier.fillMaxWidth(if (isMobileDevice) 1f else 0.5f).fillMaxHeight()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterVertically),
                    )
                    {
                        val infiniteTransition = rememberInfiniteTransition()

// On anime l'angle de rotation de -10 à 30 degrés
                        val rotation by infiniteTransition.animateFloat(
                            initialValue = -10f,
                            targetValue = 30f,
                            animationSpec = infiniteRepeatable(
                                animation = tween(600, easing = LinearEasing),
                                repeatMode = RepeatMode.Reverse
                            )
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(7.dp),
                        ) {
                            Text(
                                text = "👋",
                                modifier = Modifier.graphicsLayer {
                                    rotationZ = rotation
                                    // On fixe le point de pivot en bas à droite pour simuler le poignet
                                    transformOrigin = TransformOrigin(1f, 1f)
                                },
                                fontFamily = PlusJakartaSans // Votre config avec NotoEmoji
                            )
                            Text(
                                stringResource(Res.string.hey),
                                style = MaterialTheme.typography.labelMedium, color = Color.White,
                            )
                        }


                        Text(
                            stringResource(Res.string.nana_intro),
                            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                            color = Color.White
                        )
                        TypeWriterText(
                            text = stringResource(Res.string.passion_intro),
                            additionalTexts = listOf(
                                "Dev Mobile",
                                "Dev Backend",
                                "Dev Multiplatform"
                            ),
                            textAnimateColor = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelMedium.copy(color = Color.White),
                        )
                        Text(
                            text = stringResource(Res.string.focus_desc),
                            style = MaterialTheme.typography.labelSmall,
                            textAlign = TextAlign.Justify, color = Color.White
                        )
                        PortfolioButton(
                            text = stringResource(Res.string.on_me),
                            model = Icons.Default.KeyboardArrowRight,
                            onClick = {
                                onAboutButtonClick()
                            }
                        )
                    }
                    if (!isMobileDevice) {
                        mutableContent()
                    }
                }
            }
        }
    }
}

@Composable
fun TypeWriterText(
    text: String,
    style: androidx.compose.ui.text.TextStyle = LocalTextStyle.current,
    textAnimateColor: Color = MaterialTheme.colorScheme.primary,
    additionalTexts: List<String> = listOf(),
) {

    var textIndex by remember { mutableStateOf(0) }
    var charIndex by remember { mutableStateOf(0) }
    var deleting by remember { mutableStateOf(false) }

    val currentText = additionalTexts[textIndex]

    LaunchedEffect(charIndex, deleting, textIndex) {

        delay(if (deleting) 40 else 80)

        if (!deleting) {
            if (charIndex < currentText.length) {
                charIndex++
            } else {
                delay(1200)
                deleting = true
            }
        } else {
            if (charIndex > 0) {
                charIndex--
            } else {
                deleting = false
                textIndex = (textIndex + 1) % additionalTexts.size
            }
        }
    }

    Row {
        Text(
            text = text,
            style = style
        )
        Text(
            currentText.substring(0, charIndex),
            style = style.copy(fontWeight = FontWeight.Bold),
            color = textAnimateColor,
        )
        BlinkingCursor(style = style, cursorColor = textAnimateColor)
    }
}

@Composable
fun BlinkingCursor(
    style: androidx.compose.ui.text.TextStyle,
    cursorColor: Color = MaterialTheme.colorScheme.primary,
) {

    val infiniteTransition = rememberInfiniteTransition()

    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(500),
            repeatMode = RepeatMode.Reverse
        )
    )

    Text(
        "|",
        modifier = Modifier.alpha(alpha),
        style = style.copy(fontWeight = FontWeight.Bold),
        color = cursorColor,
    )
}


enum class OpenLinkTarget(val target: String) {
    NEW_ONGLET("_blank"),
    SAME_ONGLET("_self")
}

expect fun openLink(url: String, openLinkTarget: OpenLinkTarget = OpenLinkTarget.SAME_ONGLET)
expect fun getBaseUrl(): String