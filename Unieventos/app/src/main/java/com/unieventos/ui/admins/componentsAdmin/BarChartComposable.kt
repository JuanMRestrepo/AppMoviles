package com.unieventos.ui.admins.componentsAdmin

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unieventos.R
import com.unieventos.model.ChartData
import kotlin.math.ceil

@Composable
fun BarChartComposable(
    chartData: List<ChartData>,
    modifier: Modifier = Modifier
) {
    if (chartData.isEmpty()) {
        Box(modifier = modifier) {
            Text(
                text = stringResource(id = R.string.noDataAvailableLbl2),
                modifier = Modifier.align(Alignment.Center)
            )
        }
        return
    }
    val maxValue = chartData.maxOf { maxOf(it.acceptedCount, it.pendingCount) }
    val adjustedMax = if (maxValue == 0) 1 else maxValue
    val yStep = if (adjustedMax <= 5) 1 else ceil(adjustedMax / 5f).toInt()
    val numLines = (adjustedMax / yStep).coerceAtLeast(1)

    Box(
        modifier = modifier
            .background(Color(0xFFF5F5F5), RoundedCornerShape(4.dp))
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(4.dp))
            .padding(
                top = 16.dp,
                bottom = 32.dp,
                start = 32.dp,
                end = 16.dp
            )
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val barWidth = canvasWidth / (chartData.size * 3)

            for (i in 0..numLines) {
                val yValue = i * yStep
                val yPosition = canvasHeight * (1 - (yValue.toFloat() / adjustedMax))

                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, yPosition),
                    end = Offset(canvasWidth, yPosition),
                    strokeWidth = 1f
                )

                drawContext.canvas.nativeCanvas.drawText(
                    yValue.toString(),
                    -28f,
                    yPosition - 5,
                    android.graphics.Paint().apply {
                        color = android.graphics.Color.GRAY
                        textSize = 10.sp.toPx()
                        textAlign = android.graphics.Paint.Align.RIGHT
                    }
                )
            }

            chartData.forEachIndexed { index, data ->
                val x = index * (3 * barWidth)

                val acceptedHeight = (data.acceptedCount.toFloat() / maxValue) * canvasHeight
                drawRect(
                    color = Color.Blue,
                    topLeft = Offset(x, canvasHeight - acceptedHeight),
                    size = Size(barWidth, acceptedHeight)
                )

                val pendingHeight = (data.pendingCount.toFloat() / maxValue) * canvasHeight
                drawRect(
                    color = Color(0xFF90CAF9),
                    topLeft = Offset(x + barWidth + 4, canvasHeight - pendingHeight),
                    size = Size(barWidth, pendingHeight)
                )

                drawContext.canvas.nativeCanvas.drawText(
                    data.label,
                    x + barWidth / 2,
                    canvasHeight + 20,
                    android.graphics.Paint().apply {
                        color = android.graphics.Color.BLACK
                        textSize = 10.sp.toPx()
                        textAlign = android.graphics.Paint.Align.CENTER
                    }
                )
            }
        }
    }
}