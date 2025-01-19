package com.napzak.market.core.common.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.DecimalFormat

fun priceSeparatorTransformation(): VisualTransformation {
    return VisualTransformation { text ->
        val originalText = text.text
        val formattedText = originalText.toLongOrNull()?.let {
            DecimalFormat("#,###").format(it)
        } ?: originalText

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return if (offset == 0) 0 else formattedText.length
            }

            override fun transformedToOriginal(offset: Int): Int {
                return text.length
            }
        }
        TransformedText(AnnotatedString(formattedText), offsetMapping)
    }
}