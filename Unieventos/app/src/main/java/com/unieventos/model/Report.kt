package com.unieventos.model

import java.time.LocalDateTime
import java.util.Date

class Report(
    var id: String = "",
    var title: String = "",
    var category: String = "",
    var description: String = "",
    var state: ReportState = ReportState.PENDING,
    var images: List<String> = listOf(),
    var comments: List<Comment> = listOf(),
    var location: Location = Location(),
    var isSave: Boolean = false,
    var date: Date = Date(),
    var idUser: String = ""
    ) {
}
