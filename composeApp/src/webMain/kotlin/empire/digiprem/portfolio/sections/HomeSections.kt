package empire.digiprem.portfolio.sections

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.design_system.PortfolioButton
import empire.digiprem.portfolio.design_system.currentDeviceConfigure
import empire.digiprem.portfolio.design_system.layout.AdaptativeContainerLayout
import empire.digiprem.portfolio.design_system.layout.SectionLayout
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.skia.paragraph.TextStyle
import portfolionanaadrien.composeapp.generated.resources.Res
import portfolionanaadrien.composeapp.generated.resources.plan_de_travail_de_k_n_a

@Composable
fun HomeSections(
    modifier: Modifier = Modifier,
) {
    val isMobileDevice = currentDeviceConfigure().isMobileDevice()

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(Res.drawable.plan_de_travail_de_k_n_a),
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
                modifier = Modifier.width(if (isMobileDevice) 300.dp else 400.dp).fillMaxHeight(if (isMobileDevice) 0.5f else  0.6f),
                contentAlignment = Alignment.Center
            )
            {
                Box(
                    modifier = Modifier.padding(top = 70.dp).size(if (isMobileDevice) 35.dp else  50.dp).clip(CircleShape)
                        .shadow(elevation = 10.dp, CircleShape).background(Color.White)
                        .align(Alignment.TopStart)
                )
                Box(
                    modifier = Modifier.size(if (isMobileDevice) 45.dp else 60.dp).clip(CircleShape).background(Color.White)
                        .align(Alignment.TopEnd)
                )
                Box(
                    modifier = Modifier.size(if (isMobileDevice) 35.dp else 50.dp).clip(CircleShape).background(Color.White)
                        .align(Alignment.CenterEnd)
                )
                Box(
                    modifier = Modifier.padding(end = 150.dp, top = 20.dp).size(if (isMobileDevice) 35.dp else 40.dp).align(Alignment.BottomEnd)
                        .clip(CircleShape).background(Color.White)
                )
                Box(modifier = Modifier.size(if (isMobileDevice) 150.dp else 200.dp).clip(CircleShape).background(Color.Red)) {

                }
            }
        }

        SectionLayout {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp,Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally) {
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
                        modifier = Modifier.width(500.dp).fillMaxHeight().padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterVertically),
                    )
                    {
                        Text(
                            "👋 Hé",
                            style = MaterialTheme.typography.labelMedium, color = Color.White,
                        )
                        Text(
                            "Je suis Nana Adrien",
                            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                            color = Color.White
                        )
                        TypeWriterText(
                            text = "Je suis passionné par le ",
                            additionalTexts = listOf(
                                "Dev Mobile",
                                "Dev Backend",
                                "Dev Multiplatform"
                            ),
                            textAnimateColor = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelMedium.copy(color = Color.White),
                        )
                        Text(
                            "Je me concentre sur le développement d'applications web conviviales qui répondent aux exigences du client, en accordant " +
                                    "une attention particulière aux détails, à l'évolutivité et aux performances. ",
                            style = MaterialTheme.typography.labelSmall,
                            textAlign = TextAlign.Justify, color = Color.White
                        )
                        PortfolioButton(
                            text = "sur moi",
                            onClick = {

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