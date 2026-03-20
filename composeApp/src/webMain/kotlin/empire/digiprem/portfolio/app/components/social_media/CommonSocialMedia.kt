package empire.digiprem.portfolio.app.components.social_media

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LinkedCamera
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import portfolionanaadrien.composeapp.generated.resources.Res
import portfolionanaadrien.composeapp.generated.resources.github
import portfolionanaadrien.composeapp.generated.resources.linked_in

@Composable
fun CommonSocialMedia(
    visible: Boolean,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandHorizontally(),
        exit = fadeOut() + shrinkHorizontally(),
    ) {
        SocialMediaLink(
            modifier = Modifier,
            socialMedias = listOf(
                SocialMedia(
                    icon = Icons.Default.Whatsapp,
                    link = "https://wa.me/237655011738",
                ),
                SocialMedia(
                    icon = painterResource(Res.drawable.github),
                    link = "https://github.com/nana-adrien",
                ),
                SocialMedia(
                    icon =  painterResource(Res.drawable.linked_in),
                    link = "https://www.linkedin.com/in/adrien-nana-5b04aa262/",
                ),
            )
        )
    }

}