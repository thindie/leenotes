package com.example.thindie.leenotes.common.design_system.theme

import android.graphics.fonts.FontFamily
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.unit.sp
import com.example.thindie.leenotes.R

val font = Font(R.font.jakartaregular)
val family = font.toFontFamily()

val Typography = Typography(
    displayLarge =TextStyle(fontFamily = family, fontSize = 48.sp),
    displayMedium =TextStyle(fontFamily = family, fontSize = 44.sp),
    displaySmall =TextStyle(fontFamily = family, fontSize = 42.sp),
    headlineLarge =TextStyle(fontFamily = family, fontSize = 38.sp),
    headlineMedium =TextStyle(fontFamily = family, fontSize = 36.sp),
    headlineSmall =TextStyle(fontFamily = family, fontSize = 32.sp),
    titleLarge =TextStyle(fontFamily = family, fontSize = 28.sp),
    titleMedium = TextStyle(fontFamily = family, fontSize = 26.sp),
    titleSmall =TextStyle(fontFamily = family, fontSize = 24.sp),
    bodyLarge =TextStyle(fontFamily = family, fontSize = 18.sp),
    bodyMedium =TextStyle(fontFamily = family, fontSize = 16.sp),
    bodySmall =TextStyle(fontFamily = family, fontSize = 14.sp, fontWeight = FontWeight.W700),
    labelLarge =TextStyle(fontFamily = family, fontSize = 14.sp, fontWeight = FontWeight.W300),
    labelMedium =TextStyle(fontFamily = family, fontSize = 14.sp, fontWeight = FontWeight.W200),
    labelSmall =TextStyle(fontFamily = family, fontSize = 14.sp, fontWeight = FontWeight.W100)

)