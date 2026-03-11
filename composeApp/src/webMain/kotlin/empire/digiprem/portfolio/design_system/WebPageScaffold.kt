package empire.digiprem.portfolio.design_system

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun WebPageScaffold(
    header: @Composable ColumnScope.() -> Unit,
    footer: @Composable (() -> Unit)?=null,
    socialMedia: @Composable (() -> Unit)?=null,
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

    }
}