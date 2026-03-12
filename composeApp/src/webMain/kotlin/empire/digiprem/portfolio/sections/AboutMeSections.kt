package empire.digiprem.portfolio.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import coil3.Image
import empire.digiprem.portfolio.design_system.ButtonType
import empire.digiprem.portfolio.design_system.PortfolioButton
import empire.digiprem.portfolio.design_system.currentDeviceConfigure
import empire.digiprem.portfolio.design_system.layout.SectionLayout
import empire.digiprem.portfolio.theme.labelXSmall
import kotlinx.coroutines.flow.flow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import portfolionanaadrien.composeapp.generated.resources.Res
import portfolionanaadrien.composeapp.generated.resources.compose_multiplatform

@Composable
fun AboutMeSections(
    modifier: Modifier = Modifier,
) {

    val isMobileDevice = currentDeviceConfigure().isMobileDevice()

    val BoxContent = @Composable {
        Column(Modifier
            .graphicsLayer {
                rotationZ = if (!isMobileDevice) -5f else 0f
            }
            .widthIn(max = if (isMobileDevice) 350.dp  else 280.dp )
            .heightIn(max=350.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White).padding(10.dp)
        ) {
            Box(Modifier.fillMaxWidth().fillMaxHeight().padding(bottom = 30.dp).clip(RoundedCornerShape(8.dp)).background(Color.Gray)) {
                Image(
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,

                    )
            }

        }
    }

    SectionLayout(
        title = "About Me",
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            if (isMobileDevice) {
                BoxContent()
            }

            Row(
                modifier = Modifier.wrapContentHeight().fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(60.dp)
            ) {
                if (!isMobileDevice) {
                    BoxContent()
                }
                Column(modifier = Modifier.widthIn(min=400.dp).padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "Nana Adrien",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )
                    Box(
                        modifier = Modifier.wrapContentSize().clip(RoundedCornerShape(4.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f))
                            .padding(horizontal = 10.dp, vertical = 5.dp), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "~Dev Multiplatform ~",
                            style = MaterialTheme.typography.labelXSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = """
                            Ahoy there! 🎉 I'm a seasoned Full-Stack developer based in Pune, India, proudly waving my Information Technology undergrad cape from SPPU! 🎓 I've delved into web development since my freshman days. 💻 My focus lies in crafting websites and applications that seamlessly blend functionality. Oh, and did I mention my love for building full-stack clones and side projects?💡It's kind of my thing! 🚀
                        """.trimIndent(),
                        style = MaterialTheme.typography.labelSmall,
                        textAlign = TextAlign.Justify,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                    )
                    Spacer(Modifier.height(10.dp))
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
                    ) {
                        PortfolioButton(
                            text = "Resume",
                        ) {}
                        PortfolioButton(
                            type = ButtonType.SECONDARY,
                            text = "Download My CV",
                        ) {}
                    }
                }

            }
        }


    }
}