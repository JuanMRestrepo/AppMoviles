package com.unieventos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.unieventos.model.Comment
import com.unieventos.model.Report
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

    fun toggleLike(reportId: String, userId: String) {
        viewModelScope.launch {
            val oldReports = _reports.value.toMutableList()
            val index = oldReports.indexOfFirst { it.id == reportId }
            if (index != -1) {
                val report = oldReports[index]

                val updatedReport = Report(
                    id = report.id,
                    title = report.title,
                    category = report.category,
                    description = report.description,
                    state = report.state,
                    images = report.images,
                    comments = report.comments,
                    location = report.location,
                    date = report.date,
                    idUser = report.idUser,
                    likeCount = if (report.likedUsers.contains(userId)) report.likeCount - 1 else report.likeCount + 1,
                    likedUsers = if (report.likedUsers.contains(userId)) {
                        report.likedUsers.toMutableList().apply { remove(userId) }
                    } else {
                        report.likedUsers.toMutableList().apply { add(userId) }
                    }
                )

                db.collection("reports")
                    .document(reportId)
                    .set(updatedReport)
                    .await()

                val newReports = oldReports.toMutableList().apply {
                    set(index, updatedReport)
                }

                _reports.value = newReports
            }
        }
    }

    fun addComment(reportId: String, comment: Comment) {
        viewModelScope.launch {
            try {
                val report = _reports.value.find { it.id == reportId } ?: return@launch

                val newComments = ArrayList(report.comments).apply {
                    add(comment)
                }

                val updatedReport = Report(
                    id = report.id,
                    title = report.title,
                    category = report.category,
                    description = report.description,
                    state = report.state,
                    images = report.images,
                    comments = newComments,
                    location = report.location,
                    date = report.date,
                    idUser = report.idUser,
                    likeCount = report.likeCount,
                    likedUsers = ArrayList(report.likedUsers)
                )

                db.collection("reports")
                    .document(reportId)
                    .update("comments", newComments)
                    .await()

                val updatedList = _reports.value.toMutableList().apply {
                    set(indexOfFirst { it.id == reportId }, updatedReport)
                }
                _reports.value = updatedList

            } catch (e: Exception) {
                _reportResult.value = RequestResult.Failure("Error al comentar")
            }
        }
    }

    fun getCommentsForReport(reportId: String): List<Comment> {
        return _reports.value.find { it.id == reportId }?.comments ?: emptyList()
    }
}
