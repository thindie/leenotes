package com.example.thindie.leenotes.common.design_system.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.unit.sp
import com.example.thindie.leenotes.R

val labelFont = Font(R.font.comorantregular)
val labelFamily = labelFont.toFontFamily()

val bodyFont = Font(R.font.philosopher)
val bodyFamily = bodyFont.toFontFamily()

val displayFont = Font(R.font.rubikdoodle)
val displayFamily = displayFont.toFontFamily()

val titleFont = Font(R.font.stix)
val titleFamily = titleFont.toFontFamily()

val headLineFont = Font(R.font.cormorantgaramond)
val headLineFamily = headLineFont.toFontFamily()


val Typography = Typography(
    displayLarge = TextStyle(fontFamily = displayFamily, fontSize = 48.sp),
    displayMedium = TextStyle(fontFamily = displayFamily, fontSize = 44.sp),
    displaySmall = TextStyle(fontFamily = displayFamily, fontSize = 42.sp),
    headlineLarge = TextStyle(fontFamily = headLineFamily, fontSize = 38.sp),
    headlineMedium = TextStyle(fontFamily = headLineFamily, fontSize = 36.sp),
    headlineSmall = TextStyle(fontFamily = headLineFamily, fontSize = 32.sp),
    titleLarge = TextStyle(fontFamily = titleFamily, fontSize = 28.sp),
    titleMedium = TextStyle(fontFamily = titleFamily, fontSize = 26.sp),
    titleSmall = TextStyle(fontFamily = titleFamily, fontSize = 24.sp),
    bodyLarge = TextStyle(fontFamily = bodyFamily, fontSize = 18.sp),
    bodyMedium = TextStyle(fontFamily = bodyFamily, fontSize = 16.sp),
    bodySmall = TextStyle(fontFamily = bodyFamily, fontSize = 20.sp, fontWeight = FontWeight.W700),
    labelLarge = TextStyle(fontFamily = labelFamily, fontSize = 20.sp, fontWeight = FontWeight.W300),
    labelMedium = TextStyle(fontFamily = labelFamily, fontSize = 20.sp, fontWeight = FontWeight.W200),
    labelSmall = TextStyle(fontFamily = labelFamily, fontSize = 20.sp, fontWeight = FontWeight.W100)

)