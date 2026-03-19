package empire.digiprem.portfolio.sections.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.core.design_system.PortfolioButton
import empire.digiprem.portfolio.core.design_system.PortfolioImage
import empire.digiprem.portfolio.core.design_system.animation.TypeWriterTextAnimation
import empire.digiprem.portfolio.core.design_system.animation.WavingEmojiAnimation
import empire.digiprem.portfolio.core.design_system.currentDeviceConfigure
import empire.digiprem.portfolio.core.design_system.layout.SectionLayout
import empire.digiprem.portfolio.core.domain.services.TranslationService
import empire.digiprem.portfolio.sections.home.components.HomeLogoSection
import portfolionanaadrien.composeapp.generated.resources.Res


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
        PortfolioImage(
            modifier = Modifier.fillMaxSize(),
            image = Res.getUri("drawable/plan_de_travail_de_k_n_a.jpeg"),
            contentScale = ContentScale.Crop,
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


        SectionLayout {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isMobileDevice) {
                    HomeLogoSection(
                       isMobileDevice=isMobileDevice,
                        isDarkTheme,
                        modifier = Modifier
                            .width(350.dp)
                            .fillMaxHeight( 0.4f )
                            .padding(top = 80.dp),
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight().align(Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(if (isMobileDevice) 1f else 0.5f).fillMaxHeight()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterVertically),
                    )
                    {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(7.dp),
                        ) {
                            WavingEmojiAnimation(
                                "👋",
                                style = MaterialTheme.typography.labelMedium.copy(color =Color.White)
                            )
                            Text(
                                text = TranslationService.getString("salutation"),
                                style = MaterialTheme.typography.labelMedium, color = Color.White,
                            )
                        }

                        Text(
                            TranslationService.getString("nana_intro"),
                            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                            color = Color.White
                        )
                        TypeWriterTextAnimation(
                            text = TranslationService.getString("passion_intro"),
                            additionalTexts = listOf(
                                "dev_mobile",
                                "dev_backend",
                                "dev_multiplatform"
                            ).map { TranslationService.getString(it) },
                            textAnimateColor = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelMedium.copy(color = Color.White),
                        )
                        Text(
                            text = TranslationService.getString("focus_desc", arg = listOf("\uD83D\uDE80","✨","\uD83D\uDCC8","⚡",)),
                            style = MaterialTheme.typography.labelSmall,
                            textAlign = TextAlign.Justify, color = Color.White
                        )
                        PortfolioButton(
                            text = TranslationService.getString("on_me"),
                            model = Icons.Default.KeyboardArrowRight,
                            onClick = {
                                onAboutButtonClick()
                            }
                        )
                    }
                    if (!isMobileDevice) {
                        HomeLogoSection(
                            isMobileDevice=isMobileDevice,
                            isDarkTheme,
                            modifier = Modifier
                                .width(500.dp)
                                .fillMaxHeight(0.6f)
                        )
                    }
                }
            }
        }
    }
}
