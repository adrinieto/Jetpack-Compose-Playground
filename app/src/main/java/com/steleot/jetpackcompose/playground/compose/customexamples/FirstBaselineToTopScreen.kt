package com.steleot.jetpackcompose.playground.compose.customexamples

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.steleot.jetpackcompose.playground.CustomExamplesNavRoutes
import com.steleot.jetpackcompose.playground.compose.reusable.DefaultScaffold

private const val Url = "customexamples/FirstBaselineToTopScreen.kt"

@Composable
fun FirstBaselineToTopScreen() {
    val paddingTop = 24.dp
    DefaultScaffold(
        title = CustomExamplesNavRoutes.FirstBaselineToTop,
        link = Url,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.border(1.dp, Color.Green)) {
                Text(
                    text = "Text with padding on First Baseline",
                    modifier = Modifier.firstBaselineToTop(paddingTop)
                )
            }
            Box(modifier = Modifier.border(1.dp, Color.Red)) {
                Text(
                    text = "Text with normal padding top",
                    modifier = Modifier.padding(top = paddingTop)
                )
            }
        }
    }
}

@Suppress("unused")
fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = Modifier.layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseline = placeable[FirstBaseline]

    val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
    val height = placeable.height + placeableY
    layout(placeable.width, height) {
        placeable.place(0, placeableY)
    }
}