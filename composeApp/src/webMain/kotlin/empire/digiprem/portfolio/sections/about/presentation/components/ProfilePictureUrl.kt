package empire.digiprem.portfolio.sections.about.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.core.design_system.PortfolioImage
import empire.digiprem.portfolio.core.design_system.animation.ShimmerSkeleton
import empire.digiprem.portfolio.core.design_system.currentDeviceConfigure
import portfolionanaadrien.composeapp.generated.resources.Res

@Composable
 fun ProfilePictureUrl(){
    val isMobileDevice = currentDeviceConfigure().isMobileDevice()
    val interactionSource = remember { MutableInteractionSource() }
    val isHover by interactionSource.collectIsHoveredAsState()
    val imageFilter by animateFloatAsState(targetValue = if (isHover) 1f else 0f)
    val translation by animateFloatAsState(targetValue = if (isHover) -10f else 0f)

    Column(
        Modifier
            .graphicsLayer {
                rotationZ = if (!isMobileDevice) -5f else 0f
                translationY = translation
            }
            .widthIn(max = if (isMobileDevice) 350.dp else 280.dp)
            .heightIn(max = 350.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.background).padding(10.dp)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .hoverable(interactionSource = interactionSource)
                .padding(bottom = 30.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            PortfolioImage(
                modifier = Modifier.fillMaxSize(),
                image = Res.getUri("drawable/nana_adrien.jpg"),
                onLoading = { ShimmerSkeleton(Modifier.fillMaxSize()) },
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.colorMatrix(
                    ColorMatrix().apply { setToSaturation(imageFilter) } // 0f = grayscale
                )
            )
        }

    }
}
