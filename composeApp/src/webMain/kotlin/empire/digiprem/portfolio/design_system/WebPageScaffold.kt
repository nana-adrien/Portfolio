package empire.digiprem.portfolio.design_system

import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.ScrollbarStyle
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.scrollableArea
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WebPageScaffold(
    header: @Composable ColumnScope.() -> Unit,
    footer: @Composable (() -> Unit)?=null,
    socialMedia: @Composable (() -> Unit)?=null,
    scrollState: ScrollState,
    modifier: Modifier= Modifier,
    content: @Composable () -> Unit,
) {

        Box(
            modifier = Modifier.fillMaxSize(),
        ){
            Column(modifier = Modifier.fillMaxSize().then(modifier)) {
                content()
                footer?.invoke()
            }
            Column(modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter)) {
                header()
            }
            socialMedia?.let{
                Box(modifier = Modifier.wrapContentSize().align(Alignment.BottomStart)) {
                    it.invoke()
                }
            }

            VerticalScrollbar(
                modifier = Modifier.fillMaxHeight().align(Alignment.TopEnd).background(Color.Transparent),
                adapter = rememberScrollbarAdapter(scrollState),
                style = LocalScrollbarStyle.current.copy(
                    unhoverColor = MaterialTheme.colorScheme.primaryContainer,
                    hoverColor =MaterialTheme.colorScheme.primary,
                    hoverDurationMillis = 200
                )
            )
        }
        /*Box(modifier= Modifier.fillMaxHeight().width(4.dp).scrollableArea(
            state= scrollState,
            orientation = Orientation.Vertical
        ))*/


}