package empire.digiprem.portfolio.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


@Composable
fun PortfolioTheme(
    darkTheme: Boolean= isSystemInDarkTheme(),
    content: @Composable ()-> Unit
) {
    val colorScheme=if (darkTheme) DarkColorScheme else LightColorScheme
    val extendedScheme=if (darkTheme) DarkExtendedColors else LightExtendedColors

    CompositionLocalProvider(LocalExtendedColors provides extendedScheme){
        MaterialTheme(
            colorScheme=colorScheme,
            typography = typography,
            content = content
        )
    }

}