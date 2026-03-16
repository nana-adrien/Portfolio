package empire.digiprem.portfolio.core.design_system.animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun FloatingBox(
    modifier: Modifier = Modifier,
    seed: Int = (0..10000).random(),
    content: @Composable () -> Unit
) {
    val random = remember(seed) { kotlin.random.Random(seed) }
    val infiniteTransition = rememberInfiniteTransition(label = "floating")

    val offsetX by infiniteTransition.animateFloat(
        initialValue = random.nextFloat() * -20f,
        targetValue = random.nextFloat() * 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = random.nextInt(3000, 6000),
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "x"
    )

    val offsetY by infiniteTransition.animateFloat(
        initialValue = random.nextFloat() * -15f,
        targetValue = random.nextFloat() * 15f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = random.nextInt(3000, 6000),
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "y"
    )

    val rotation by infiniteTransition.animateFloat(
        initialValue = random.nextFloat() * -5f,
        targetValue = random.nextFloat() * 5f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = random.nextInt(4000, 7000),
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "rotation"
    )
    Box(
        modifier = modifier
            .wrapContentSize()
            .graphicsLayer {
                translationX = offsetX
                translationY = offsetY
                rotationZ = rotation
            },
        content = { content() }
    )
}