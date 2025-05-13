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
                "Infrastructure",
                "Hay un hueco grande",
                ReportState.ACCEPTED,
                listOf("https://i1.sndcdn.com/artworks-qJ5IFyKat8H70Vkz-tYUbnQ-t500x500.jpg"),
                listOf(),
                Location(1.0, 2.0, "Armenia"),
                false,
                LocalDateTime.now(),
                "1"
            ),
            Report(
                "2",
                "Report 2",
                "Pets",
                "Se robaron a un perro",
                ReportState.ACCEPTED,
                listOf("https://i1.sndcdn.com/artworks-qJ5IFyKat8H70Vkz-tYUbnQ-t500x500.jpg"),
                listOf(),
                Location(1.0, 2.0, "Armenia"),
                false,
                LocalDateTime.now(),
                "1"
            ),
            Report(
                "3",
                "Report 3",
                "Security",
                "Incendio fuerte",
                ReportState.ACCEPTED,
                listOf("https://i1.sndcdn.com/artworks-qJ5IFyKat8H70Vkz-tYUbnQ-t500x500.jpg"),
                listOf(),
                Location(1.0, 2.0, "Circasia"),
                false,
                LocalDateTime.now(),
                "2"
            ),
            Report(
                "4",
                "Report 4",
                "Security",
                "Mucha lluvia",
                ReportState.ACCEPTED,
                listOf("https://i1.sndcdn.com/artworks-qJ5IFyKat8H70Vkz-tYUbnQ-t500x500.jpg"),
                listOf(),
                Location(3.0, 6.0, "Pereira"),
                true,
                LocalDateTime.now(),
                "3"
            ),
            Report(
                "5",
                "Report 5",
                "Community",
                "Accidente registrado",
                ReportState.PENDING,
                listOf("https://i1.sndcdn.com/artworks-qJ5IFyKat8H70Vkz-tYUbnQ-t500x500.jpg"),
                listOf(),
                Location(3.0, 6.0, "Manizales"),
                true,
                LocalDateTime.now(),
                "3"
            )
        )
    }
}