package com.blez.bleachfandom.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.blez.bleachfandom.ui.theme.SMALL_PADDING
import com.blez.bleachfandom.ui.theme.titleColor

@Composable
fun OrderedList(
    title : String,
    items : List<String> ?= listOf("Unknown"),
    textColor : Color
) {
    Column {
        Text(modifier = Modifier.padding(bottom = SMALL_PADDING),
            text = title,
            color = textColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        if (items != null) {
            items.forEachIndexed { index, item ->
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = "${index + 1}.$item",
                    color = textColor,
                    fontSize = MaterialTheme.typography.body1.fontSize,
                )
            }
        } else {
            var items = listOf("Unknown")
            items.forEachIndexed { index, item ->
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = "${index + 1}.$item",
                    color = textColor,
                    fontSize = MaterialTheme.typography.body1.fontSize,
                )
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrderedList() {

    OrderedList(title = "Family", textColor = MaterialTheme.colors.titleColor )
}
