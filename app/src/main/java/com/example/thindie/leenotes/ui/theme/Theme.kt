package com.example.thindie.leenotes.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF1A0909),
    onPrimaryContainer = Color(0xFF704F4F),
    secondary = Color(0xFF411818),
    onSecondary = Color(0xFF704F4F),
    onSecondaryContainer = Color(0xFFA37C7C),
    tertiary = Color(0xFF542828),
    onTertiary = Color(0xFFCC6114),
    primaryContainer = Color(0xFF155F89)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFFFFFFF),
    onPrimaryContainer = Color(0xFF704F4F),
    secondary = Color(0xFFFCE6E6),
    onSecondary = Color(0xFFEBC9C9),
    onSecondaryContainer = Color(0xFFA37C7C),
    tertiary = Color(0xFFE7FFF9),
    onTertiary = Color(0xFF1474CC),
    primaryContainer = Color(0xFF389CD4),
)

@Composable
fun LeenotesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

val colors
    @Composable get() = MaterialTheme.colorScheme

val typo
    @Composable get() = MaterialTheme.typography