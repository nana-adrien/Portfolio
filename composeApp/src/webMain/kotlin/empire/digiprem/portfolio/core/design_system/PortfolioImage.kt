package empire.digiprem.portfolio.core.design_system

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import org.jetbrains.compose.resources.painterResource
import portfolionanaadrien.composeapp.generated.resources.Res
import portfolionanaadrien.composeapp.generated.resources.logo_dark

@Composable
fun PortfolioImage(
    modifier: Modifier = Modifier,
    image: String?,
    contentDescription: String? = null,
    onLoading: @Composable (() -> Unit)? = null,
    onFailure: @Composable (() -> Unit)? = {
        Image(
            painter = painterResource(Res.drawable.logo_dark),
            contentDescription = "Placeholder",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    },
    colorFilter: ColorFilter? = null,
    contentScale: ContentScale = ContentScale.Fit,
) {
    val painter = rememberAsyncImagePainter(model =image)

    val painterState by painter.state.collectAsState()
    var isVisible by rememberSaveable { mutableStateOf(false) }
    Box( contentAlignment = Alignment.Center) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(tween(700))
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                modifier =modifier,
                colorFilter=colorFilter,
                contentScale = contentScale
            )
       }
        when (painterState) {
            is AsyncImagePainter.State.Loading -> {
                onLoading?.let { it() }
            }
            is AsyncImagePainter.State.Error -> {
                onFailure?.let { it() }
            }
            is AsyncImagePainter.State.Success -> {
                isVisible=true
            }
            else -> {}
        }
    }
}
