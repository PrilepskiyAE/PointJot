package com.prilepskiy.common

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

// Title styles
object TitleTextStyles {
    val H1 = TextStyle(
        fontSize = 28.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.Medium,
        color = Color(0xFF4A4A4A) // gray_700
    )

    val H2 = TextStyle(
        fontSize = 24.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Medium,
        color = Color(0xFF4A4A4A) // gray_700
    )

    val H3 = TextStyle(
        fontSize = 20.sp,
        lineHeight = 26.sp,
        fontWeight = FontWeight.Medium, // 500
        color = Color(0xFF4A4A4A) // gray_700
    )

    val H3W700 = TextStyle(
        fontSize = 20.sp,
        lineHeight = 26.sp,
        fontWeight = FontWeight.Bold, // 700
        color = Color(0xFF4A4A4A) // gray_700
    )

    val H4 = TextStyle(
        fontSize = 18.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.SemiBold, // 600
        color = Color.Black
    )

    val H4W700 = TextStyle(
        fontSize = 18.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Bold, // 700
        color = Color.Black
    )

    val H5 = TextStyle(
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.SemiBold, // 600
        color = Color(0xFF4A4A4A) // gray_700
    )

    val H6 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.SemiBold, // 600
        color = Color(0xFF4A4A4A) // gray_700
    )

    val H6W700 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Bold, // 700
        letterSpacing = 0.03.em
    )

    val H5_TOOLBAR = TextStyle(
        fontSize = 20.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.SemiBold, // 600
        color = Color(0xFF4A4A4A) // gray_700
    )
}

// Body styles
object BodyTextStyles {
    val Large = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        color = Color(0xFF6B6B6B) // gray_500
    )

    val LargeW400 = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Normal, // 400
        color = Color(0xFF6B6B6B) // gray_500
    )

    val Medium = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = Color.Black
    )

    val MediumW400 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Normal, // 400
    )

    val Small = TextStyle(
        fontSize = 12.sp,
        lineHeight = 18.sp,
        color = Color(0xFFBDBDBD) // gray_300
    )

    val SmallGray400 = TextStyle(
        fontSize = 12.sp,
        lineHeight = 18.sp,
        color = Color(0xFF9E9E9E) // gray_400
    )

    val SmallW400 = TextStyle(
        fontSize = 12.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Normal, // 400
        color = Color(0xFF4A4A4A) // gray_700
    )

    val SmallW400Height14 = TextStyle(
        fontSize = 10.sp,
        lineHeight = 14.sp,
        fontWeight = FontWeight.Normal, // 400
        color = Color(0xFF9E9E9E) // gray_400
    )

    val SmallW400Height8 = TextStyle(
        fontSize = 10.sp,
        lineHeight = 8.sp,
        fontWeight = FontWeight.Normal, // 400
        color = Color(0xFF9E9E9E) // gray_400
    )

    val SmallGr700 = TextStyle(
        fontSize = 12.sp,
        lineHeight = 18.sp,
        color = Color(0xFF4A4A4A) // gray_700
    )

    val SmallGr500 = TextStyle(
        fontSize = 12.sp,
        lineHeight = 18.sp,
        color = Color(0xFF4B4F58)
    )

    val SmallGr600 = TextStyle(
        fontSize = 12.sp,
        lineHeight = 18.sp,
        color = Color(0xFF2F3137)
    )

    val SmallW600 = TextStyle(
        fontSize = 12.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.SemiBold, // 600
        color = Color(0xFF6B6B6B) // gray_500
    )

    val ExtraSmall = TextStyle(
        fontSize = 10.sp,
        lineHeight = 14.sp,
        color = Color(0xFF9E9E9E) // gray_400
    )
}

// Label styles
object LabelTextStyles {
    val Small = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium // 500
    )

    val SmallGreen500 = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium, // 500
        color = Color(0xFF4CAF50) // green_500
    )

    val SmallGray700 = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium, // 500
        color = Color(0xFF4A4A4A) // gray_700
    )

    val SmallBlue500 = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium, // 500
        color = Color(0xFF2196F3) // blue_500
    )

    val ExtraSmall = TextStyle(
        fontSize = 10.sp,
        lineHeight = 14.sp,
        fontWeight = FontWeight.Medium // 500
    )

    val ExtraSmall400 = TextStyle(
        fontSize = 10.sp,
        lineHeight = 14.sp,
        fontWeight = FontWeight.Normal, // 400
        color = Color(0xFFBDBDBD) // gray_300
    )

    val ExtraSmall500 = TextStyle(
        fontSize = 10.sp,
        lineHeight = 14.sp,
        fontWeight = FontWeight.Medium, // 500
        color = Color(0xFFBDBDBD) // gray_300
    )

    val ExtraSmall600 = TextStyle(
        fontSize = 10.sp,
        lineHeight = 14.sp,
        fontWeight = FontWeight.SemiBold, // 600
        color = Color(0xFFBDBDBD) // gray_300
    )

    val ExtraLarge = TextStyle(
        fontSize = 28.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.Bold, // 700
        color = Color(0xFF4A4A4A) // gray_700
    )

    val Large = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Medium // 500
    )

    val Large500W = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Medium, // 500
        color = Color(0xFF6B6B6B) // gray_500
    )

    val Large600W = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.SemiBold, // 600
        color = Color(0xFF6B6B6B) // gray_500
    )

    val Medium = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium, // 500
        color = Color(0xFF9E9E9E) // gray_400
    )

    val MediumW500 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium, // 500
        color = Color(0xFF4A4A4A) // gray_700
    )

    val MediumW600 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.SemiBold, // 600
        color = Color(0xFF757575) // gray_600
    )

    val GraphInfoText1 = TextStyle(
        fontSize = 8.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Bold // 700
    )
}

// Match/Tennis specific styles
object MatchTextStyles {
    val TeamText = TextStyle(
        fontSize = 12.sp,
        lineHeight = 18.sp,
        color = Color.Black
    )

    val TeamTextWinner = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )

    val TeamTextWinnerLive = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF2196F3) // blue_500
    )

    val TeamTextLoser = TextStyle(
        fontSize = 12.sp,
        lineHeight = 18.sp,
        color = Color(0xFF676D79)
    )

    val TennisScore = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Bold, // 700
        color = Color(0xFF6B6B6B) // gray_500
    )
}

// Bold styles
object BoldTextStyles {
    val Default = TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.Bold // 700
    )

    val Small = TextStyle(
        fontSize = 20.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Bold // 700
    )
}

object Spaces {
    val space0 = 0.dp
    val space1 = 1.dp
    val space2 = 2.dp
    val space3 = 3.dp
    val space4 = 4.dp
    val space5 = 5.dp
    val space6 = 6.dp
    val space7 = 7.dp
    val space8 = 8.dp
    val space9 = 9.dp
    val space10 = 10.dp
    val space11 = 11.dp
    val space12 = 12.dp
    val space13 = 13.dp
    val space14 = 14.dp
    val space15 = 15.dp
    val space16 = 16.dp
    val space17 = 17.dp
    val space18 = 18.dp
    val space20 = 20.dp
    val space22 = 22.dp
    val space23 = 23.dp
    val space24 = 24.dp
    val space26 = 26.dp
    val space27 = 27.dp
    val space28 = 28.dp
    val space30 = 30.dp
    val space31 = 31.dp
    val space32 = 32.dp
    val space34 = 34.dp
    val space35 = 35.dp
    val space36 = 36.dp
    val space38 = 38.dp
    val space39 = 39.dp
    val space40 = 40.dp
    val space42 = 42.dp
    val space44 = 44.dp
    val space45 = 45.dp
    val space46 = 46.dp
    val space48 = 48.dp
    val space49 = 49.dp
    val space50 = 50.dp
    val space51 = 51.dp
    val space52 = 52.dp
    val space54 = 54.dp
    val space56 = 56.dp
    val space58 = 58.dp
    val space59 = 59.dp
    val space60 = 60.dp
    val space64 = 64.dp
    val space68 = 68.dp
    val space69 = 70.dp
    val space70 = 70.dp
    val space72 = 72.dp
    val space74 = 74.dp
    val space76 = 76.dp
    val space78 = 78.dp
    val space80 = 80.dp
    val space82 = 82.dp
    val space84 = 84.dp
    val space86 = 86.dp
    val space88 = 88.dp
    val space90 = 90.dp
    val space94 = 92.dp
    val space96 = 96.dp
    val space99 = 99.dp
    val space100 = 100.dp
    val space102 = 102.dp
    val space104 = 104.dp
    val space110 = 110.dp
    val space112 = 112.dp
    val space116 = 116.dp
    val space120 = 120.dp
    val space124 = 124.dp
    val space125 = 125.dp
    val space130 = 130.dp
    val space138 = 138.dp
    val space150 = 150.dp
    val space156 = 156.dp
    val space160 = 160.dp
    val space164 = 164.dp
    val space165 = 165.dp
    val space167 = 167.dp
    val space168 = 168.dp
    val space170 = 170.dp
    val space175 = 175.dp
    val space184 = 184.dp
    val space186 = 186.dp
    val space187 = 187.dp
    val space190 = 190.dp
    val space191 = 191.dp
    val space192 = 192.dp
    val space196 = 196.dp
    val space198 = 198.dp
    val space200 = 200.dp
    val space202 = 202.dp
    val space205 = 205.dp
    val space206 = 206.dp
    val space210 = 210.dp
    val space212 = 212.dp
    val space216 = 216.dp
    val space220 = 220.dp
    val space222 = 222.dp
    val space224 = 224.dp
    val space241 = 241.dp
    val space250 = 250.dp
    val space259 = 259.dp
    val space264 = 264.dp
    val space276 = 276.dp
    val space277 = 277.dp
    val space282 = 282.dp
    val space284 = 284.dp
    val space290 = 290.dp
    val space298 = 298.dp
    val space300 = 300.dp
    val space301 = 301.dp
    val space312 = 312.dp
    val space328 = 328.dp
    val space336 = 336.dp
    val space350 = 350.dp
    val space480 = 480.dp

    // Negative spaces
    val space2Neg = (-2).dp
    val space4Neg = (-4).dp
    val space16Neg = (-16).dp
    val space148Neg = (-148).dp

    // Decimal spaces
    val space8_5 = 8.5.dp
    val space16_5 = 16.5.dp
}

// Sizes (sp)
object Sizes {
    val size0 = 0.sp
    val size2 = 2.sp
    val size3 = 3.sp
    val size5 = 5.sp
    val size6 = 6.sp
    val size8 = 8.sp
    val size10 = 10.sp
    val size11 = 11.sp
    val size12 = 12.sp
    val size13 = 13.sp
    val size14 = 14.sp
    val size15 = 15.sp
    val size16 = 16.sp
    val size18 = 18.sp
    val size20 = 20.sp
    val size24 = 24.sp
    val size26 = 26.sp
    val size28 = 28.sp
    val size32 = 32.sp
    val size48 = 48.sp
    val size60 = 60.sp
}

// Margins (aliasing common spaces for backward compatibility)
object Margins {
    val margin_xm = 18.dp // margin_xm
    val margin_m = 14.dp // margin_m
    val margin_e = 20.dp // margin_e
    val margin_l = 24.dp // margin_l
    val margin_xl = 32.dp // margin_xl
}

// Specific component dimensions
object ComponentDimensions {
    // Input code
    val inputCodeTextSize = 24.sp
    val inputCodeBoxWidth = 72.dp
    val inputCodeBoxHeight = 60.dp
    val inputCodeFor6BoxWidth = 44.dp
    val inputCodeFor6BoxHeight = 60.dp
    val inputCodeFor6TextSize = 36.dp

    // Input text
    val inputTextBoxHeight = 42.dp
    val inputTextBoxPaddingHorizontal = 20.dp
    val inputTextBoxPaddingHorizontal15 = 15.dp

    // Comments
    val commentsProgressHeight = 96.dp

    // Table
    val tableItemVerticalPadding = 8.dp
    val tableItemHorizontalPadding = 12.dp
    val tableItemMinWidth = 100.dp
    val tableItemMaxWidth = 130.dp
}