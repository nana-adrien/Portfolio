package empire.digiprem.portfolio.sections.project.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import empire.digiprem.portfolio.core.design_system.PortfolioIcon

@Composable
fun ProjectLinkButton(
    model: Any,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val color by animateColorAsState(
        targetValue = if (isHovered) Color.Black else Color.White
    )
    val colorIcon by animateColorAsState(
        targetValue = if (isHovered) Color.White else Color.Black
    )
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .hoverable(interactionSource = interactionSource)
            .clickable { onClick() }
            .background(color)
            .padding(7.dp),
        contentAlignment = Alignment.Center
    ) {
        PortfolioIcon(
            model = model,
            modifier = Modifier.fillMaxSize(),
            tint = colorIcon
        )
    }
}
