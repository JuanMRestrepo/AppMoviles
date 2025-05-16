package com.unieventos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.unieventos.model.Report
import com.unieventos.model.User
import com.unieventos.utils.RequestResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ReportsViewModel: ViewModel()  {

    private val db = Firebase.firestore

    private val _reports = MutableStateFlow(emptyList<Report>())
    val reports: StateFlow<List<Report>> = _reports.asStateFlow()

    private val _reportResult = MutableStateFlow<RequestResult?>(null)
    val reportResult: StateFlow<RequestResult?> = _reportResult.asStateFlow()

    private val _currentReport = MutableStateFlow<Report?>(null)
    val currentReport: StateFlow<Report?> = _currentReport.asStateFlow()

    init {
        getAllReports()
    }

    fun getAllReports(){
        viewModelScope.launch {
            _reports.value = findAllFirebaseReports()
        }
    }

    private suspend fun findAllFirebaseReports(): List<Report> {
        val querySnapshot = db.collection("reports")
            .get()
            .await()

        return querySnapshot.documents.mapNotNull {
            it.toObject(Report::class.java)?.apply {
                id = it.id
            }
        }
    }

    fun createReport(report: Report) {
        viewModelScope.launch {
            _reportResult.value = RequestResult.Loading
            _reportResult.value = runCatching {
                val documentRef = db.collection("reports")
                    .add(report)
                    .await()

                report.id = documentRef.id

                documentRef.update("id", documentRef.id).await()

                _reports.value = findAllFirebaseReports()
            }.fold(
                onSuccess = {
                    RequestResult.Success("Report created correctly")
                },
                onFailure = {
                    RequestResult.Failure(it.message ?: "Error creating report")
                }
            )
        }
    }

    /*
    fun createReport(report: Report) {
        viewModelScope.launch {
            _reportResult.value = RequestResult.Loading
            _reportResult.value = runCatching { createFirebaseReport(report) }
                .fold(
                    onSuccess = {
                        RequestResult.Success("Report created correctly")
                    },
                    onFailure = {
                        RequestResult.Failure(it.message ?: "Error creating report")
                    }
                )
        }
    }

     */

    private suspend fun createFirebaseReport(report: Report) {
        db.collection("reports")
            .add(report)
            .await()

        _reports.value = findAllFirebaseReports()
    }

    fun deleteReport(reportId: String) {
        viewModelScope.launch {
            _reportResult.value = RequestResult.Loading
            _reportResult.value = runCatching { deleteFirebaseReport(reportId) }
                .fold(
                    onSuccess = {
                        RequestResult.Success("Report eliminated correctly")
                    },
                    onFailure = {
                        RequestResult.Failure(it.message ?: "Error deleting report")
                    }
                )
        }
    }

    private suspend fun deleteFirebaseReport(reportId: String) {
        db.collection("reports")
            .document(reportId)
            .delete()
            .await()

        _reports.value = findAllFirebaseReports()
    }

    fun updateReport(report: Report) {
        viewModelScope.launch {
            _reportResult.value = RequestResult.Loading
            _reportResult.value = runCatching { updateFirebase(report) }
                .fold(
                    onSuccess = {
                        RequestResult.Success("Report updated correctly")
                    },
                    onFailure = {
                        RequestResult.Failure(it.message ?: "Error editing report")
                    }
                )
        }
    }

    private suspend fun updateFirebase(report: Report) {
        db.collection("reports")
            .document(report.id)
            .set(report)
            .await()

        _reports.value = findAllFirebaseReports()
    }

    fun findById(reportId: String): Report? {
        return _reports.value.find { it.id == reportId }
    }

    fun findByIdReport(reportId: String) {
        viewModelScope.launch {
            findByIdFirebase(reportId)
        }
    }

    private suspend fun findByIdFirebase(reportId: String) {
        val querySnapshot = db.collection("reports")
            .document(reportId)
            .get()
            .await()

        val report = querySnapshot.toObject(Report::class.java)?.apply {
            this.id = querySnapshot.id
        }
    }

    fun resetCurrentReport(){
        _currentReport.value = null
    }

    fun resetReportResult(){
        _reportResult.value = null
    }

    fun findByUserId(userId: String): List<Report> {
        return _reports.value.filter { it.idUser == userId }
    }






















    /*
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

     */
}
