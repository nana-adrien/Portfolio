package empire.digiprem.portfolio.sections

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.design_system.PortfolioButton
import empire.digiprem.portfolio.design_system.currentDeviceConfigure
import empire.digiprem.portfolio.design_system.layout.AdaptativeContainerLayout
import empire.digiprem.portfolio.design_system.layout.SectionLayout
import org.jetbrains.compose.resources.painterResource
import portfolionanaadrien.composeapp.generated.resources.Res
import portfolionanaadrien.composeapp.generated.resources.plan_de_travail_de_k_n_a

@Composable
fun HomeSections(
    modifier: Modifier = Modifier,
) {
    val isMobileDevice=currentDeviceConfigure().isMobileDevice()

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

        SectionLayout{
            FlowRow(
                modifier =Modifier.width(900.dp).fillMaxHeight().align(Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.spacedBy(20.dp,Alignment.CenterVertically),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Column(
                    modifier = Modifier.width(500.dp).fillMaxHeight().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp,Alignment.CenterVertically),
                ) {
                    Text("👋 Hé", style = MaterialTheme.typography.labelMedium,color=Color.White)
                    Text(
                        "Je suis Nana Adrien",
                        style = MaterialTheme.typography.titleLarge,color=Color.White
                    )
                    Text(
                        "Je suis passionné par Backend Development|",
                        style = MaterialTheme.typography.labelMedium,color=Color.White
                    )
                    Text(
                        "Je me concentre sur le développement d'applications web conviviales qui répondent aux exigences du client, en accordant " +
                                "une attention particulière aux détails, à l'évolutivité et aux performances. ",
                        style = MaterialTheme.typography.labelSmall,
                        textAlign = TextAlign.Justify,color=Color.White
                    )
                    PortfolioButton(
                        text = "sur moi",
                        onClick = {

                        }
                    )
                }
                Box(
                    modifier = Modifier.width(300.dp).fillMaxHeight(0.7f),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier.size(50.dp).clip(CircleShape).background(Color.Blue)
                            .align(Alignment.TopStart)
                    )
                    Box(
                        modifier = Modifier.size(40.dp).clip(CircleShape).background(Color.Black).align(Alignment.TopEnd)
                    )
                    Box(
                        modifier = Modifier.size(40.dp).clip(CircleShape).background(Color.White).align(Alignment.CenterEnd)
                    )
                    Box(
                        modifier = Modifier.padding(end = 150.dp, top = 20.dp).size(40.dp).align(Alignment.BottomEnd).clip(CircleShape).background(Color.White)
                    )
                    Box(modifier = Modifier.size(200.dp).clip(CircleShape).background(Color.Red)) {

                    }
                }
            }
        }


    }
}
