package empire.digiprem.portfolio

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.skia.Image
import org.jetbrains.skia.Surface

import portfolionanaadrien.composeapp.generated.resources.Res
import portfolionanaadrien.composeapp.generated.resources.capture
import portfolionanaadrien.composeapp.generated.resources.compose_multiplatform
import portfolionanaadrien.composeapp.generated.resources.plan_de_travail_de_k_n_a

@Composable
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .safeContentPadding()
                .fillMaxSize()
                .verticalScroll(scrollState)
                ,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .padding(16.dp)) {

            }
            Box(
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth().background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black,
                            )
                        )

                    )
                ,

            ){
                Image(
                    modifier = Modifier.fillMaxSize()
                        ,
                    painter = painterResource(Res.drawable.plan_de_travail_de_k_n_a),
                    contentDescription = null,
                    contentScale = ContentScale.Crop

                )
            }
            Box(
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Red.copy(alpha = 0.5f),
                                Color.Red,
                            )
                        )

                    )
                ,

            )
            Box(
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Blue.copy(alpha = 0.5f),
                                Color.Blue,
                            )
                        )

                    )
                ,

            )


            /*Button(onClick = { showContent = !showContent }) {
                Text("Click a gains!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }*/
        }
    }
}