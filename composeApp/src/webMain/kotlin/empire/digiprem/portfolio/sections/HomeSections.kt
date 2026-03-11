package empire.digiprem.portfolio.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import empire.digiprem.portfolio.design_system.layout.SectionLayout
import org.jetbrains.compose.resources.painterResource
import portfolionanaadrien.composeapp.generated.resources.Res
import portfolionanaadrien.composeapp.generated.resources.plan_de_travail_de_k_n_a

@Composable
fun HomeSections(
    modifier: Modifier = Modifier,
) {
    SectionLayout(
        modifier = modifier,
    ){
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(Res.drawable.plan_de_travail_de_k_n_a),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}
