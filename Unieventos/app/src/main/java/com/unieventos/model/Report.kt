package com.unieventos.model

import java.time.LocalDateTime

class Report(
    var id: String = "123",
    var title: String = "",
    var category: String = "",
    var description: String = "",
    var state: ReportState = ReportState.PENDING,
    var images: List<String> = listOf(),
    var comments: List<Comment> = listOf(),
    var location: Location = Location(),
    var isSave: Boolean = false,
    var date: LocalDateTime = LocalDateTime.now(),
    var idUser: String = ""
    ) {
}
