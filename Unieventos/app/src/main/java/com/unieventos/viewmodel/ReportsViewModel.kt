package com.unieventos.viewmodel

import androidx.lifecycle.ViewModel
import com.unieventos.model.Location
import com.unieventos.model.Report
import com.unieventos.model.ReportState
import com.unieventos.model.Role
import com.unieventos.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime
import java.util.UUID

class ReportsViewModel: ViewModel()  {

    private val _reports = MutableStateFlow(emptyList<Report>())
    val reports: StateFlow<List<Report>> = _reports.asStateFlow()

    init {
        _reports.value = getReports()
    }

    fun createReport(report: Report) {
        _reports.value += report
    }

    fun deleteReport(reportId: String) {
        _reports.value = _reports.value.filter { it.id != reportId }
    }

    fun findById(reportId: String): Report? {
        return _reports.value.find { it.id == reportId }
    }

    fun findByUserId(userId: String): List<Report> {
        return _reports.value.filter { it.idUser == userId }
    }

    fun findByUser(userId: String): List<Report> {
        return _reports.value.filter { it.idUser == userId }
    }

    fun getReports(): List<Report> {
        return listOf(
            Report(
                "1",
                "Report 1",
                "Infrastructure",
                "Hay un hueco grande",
                ReportState.ACCEPTED,
                listOf("https://i1.sndcdn.com/artworks-qJ5IFyKat8H70Vkz-tYUbnQ-t500x500.jpg"),
                listOf(),
                Location(4.46, -75.64, "Armenia"),
                false,
                LocalDateTime.now(),
                "52535115"
            ),
            Report(
                "2",
                "Report 2",
                "Pets",
                "Se robaron a un perro",
                ReportState.ACCEPTED,
                listOf("https://i1.sndcdn.com/artworks-qJ5IFyKat8H70Vkz-tYUbnQ-t500x500.jpg"),
                listOf(),
                Location(4.537175, -75.670132, "Armenia"),
                false,
                LocalDateTime.now(),
                "1095550822"
            ),
            Report(
                "3",
                "Report 3",
                "Security",
                "Incendio fuerte",
                ReportState.ACCEPTED,
                listOf("https://i1.sndcdn.com/artworks-qJ5IFyKat8H70Vkz-tYUbnQ-t500x500.jpg"),
                listOf(),
                Location(4.540983, -75.666067, "Circasia"),
                false,
                LocalDateTime.now(),
                "52535115"
            ),
            Report(
                "4",
                "Report 4",
                "Security",
                "Mucha lluvia",
                ReportState.ACCEPTED,
                listOf("https://i1.sndcdn.com/artworks-qJ5IFyKat8H70Vkz-tYUbnQ-t500x500.jpg"),
                listOf(),
                Location(4.547962, -75.667697, "Pereira"),
                true,
                LocalDateTime.now(),
                "1095550822"
            ),
            Report(
                "5",
                "Report 5",
                "Community",
                "Accidente registrado",
                ReportState.PENDING,
                listOf("https://i1.sndcdn.com/artworks-qJ5IFyKat8H70Vkz-tYUbnQ-t500x500.jpg"),
                listOf(),
                Location(4.540273, -75.684445, "Manizales"),
                true,
                LocalDateTime.now(),
                "1095550822"
            )
        )
    }
}
