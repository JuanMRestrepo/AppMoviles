package com.unieventos.ui.clientes.componentsClient

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.unieventos.model.Location
import com.unieventos.model.Report
import com.unieventos.model.ReportState
import java.time.LocalDateTime

@Composable
fun GetTestReports(): List<Report> {
    return remember {
        listOf(
            Report(
                "1",
                "Report 1",
                "Armenia",
                ReportState.ACCEPTED,
                listOf("https://i1.sndcdn.com/artworks-qJ5IFyKat8H70Vkz-tYUbnQ-t500x500.jpg"),
                Location(1.0, 2.0),
                LocalDateTime.now()
            ),
            Report(
                "2",
                "Report 2",
                "Pereira",
                ReportState.ACCEPTED,
                listOf("https://i1.sndcdn.com/artworks-qJ5IFyKat8H70Vkz-tYUbnQ-t500x500.jpg"),
                Location(1.0, 2.0),
                LocalDateTime.now()
            ),
            Report(
                "3",
                "Report 3",
                "Manizales",
                ReportState.ACCEPTED,
                listOf("https://i1.sndcdn.com/artworks-qJ5IFyKat8H70Vkz-tYUbnQ-t500x500.jpg"),
                Location(1.0, 2.0),
                LocalDateTime.now()
            )
        )
    }
}