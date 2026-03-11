package empire.digiprem.portfolio.app.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgba
import org.jetbrains.compose.web.dom.Div

@Composable
actual fun BlurComponent() {
    Div({
        style {
            property("backdrop-filter", "blur(20px)")
            backgroundColor(rgba(255,255,255,0.2))
            borderRadius(20.px)
            padding(20.px)
        }
    }) {
        Text("Glass Card")
    }
}