package empire.digiprem.portfolio.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import portfolionanaadrien.composeapp.generated.resources.Res
import portfolionanaadrien.composeapp.generated.resources.noto_glyf_colr
import portfolionanaadrien.composeapp.generated.resources.noto_sans_bold
import portfolionanaadrien.composeapp.generated.resources.noto_sans_light
import portfolionanaadrien.composeapp.generated.resources.noto_sans_medium
import portfolionanaadrien.composeapp.generated.resources.noto_sans_regular
import portfolionanaadrien.composeapp.generated.resources.noto_sans_semibold

val PlusJakartaSans
    @Composable get() = FontFamily(
        Font(
            resource = Res.font.noto_sans_medium,
            weight = FontWeight.Medium
        ),
        Font(
            resource = Res.font.noto_sans_bold,
            weight = FontWeight.Bold
        ),
        Font(
            resource = Res.font.noto_sans_semibold,
            weight = FontWeight.SemiBold
        ),
        Font(
            resource = Res.font.noto_sans_light,
            weight = FontWeight.Light
        ),
        Font(
            resource = Res.font.noto_glyf_colr,
        ),
        Font(
            resource = Res.font.noto_sans_regular,
            weight = FontWeight.Normal
        ),
    )

val typography
    @Composable get() = Typography(
        titleLarge = TextStyle(
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.15.sp,
            fontSize = 30.sp,
            lineHeight = 36.sp
        ),
        titleMedium = TextStyle(
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            letterSpacing = 0.15.sp,
            lineHeight = 28.sp
        ),
        titleSmall = TextStyle(
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            letterSpacing = 0.15.sp,
            lineHeight = 24.sp
        ),

        labelMedium = TextStyle(
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.15.sp,
            fontSize = 16.sp,
            lineHeight = 24.sp
        ),
        labelSmall = TextStyle(
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.15.sp,
            fontSize = 14.sp,
            lineHeight = 20.sp
        ),

        bodyLarge = TextStyle(
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.15.sp,
            fontSize = 18.sp,
            lineHeight = 26.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.15.sp,
            fontSize = 16.sp,
            lineHeight = 24.sp
        ),
        bodySmall = TextStyle(
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.15.sp,
            fontSize = 14.sp,
            lineHeight = 20.sp
        ),


        headlineSmall = TextStyle(
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.15.sp,
            fontSize = 14.sp,
            lineHeight = 18.sp
        ),
        displaySmall = TextStyle(
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.15.sp,
            fontSize = 11.sp,
            lineHeight = 14.sp
        ),

        )

val Typography.labelXSmall: TextStyle
    @Composable get() = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.25.sp,
        fontSize = 11.sp,
        lineHeight = 14.sp
    )
val Typography.titleXSmall: TextStyle
    @Composable get() = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.15.sp,
        fontSize = 14.sp,
        lineHeight = 18.sp
    )
