package com.unieventos.model

import java.time.LocalDateTime

class Report(
    var id: String,
    var title: String,
    var description: String,
    var state: ReportState,
    var images: List<String>,
    var location: Location,
    var isSave: Boolean,
    var fecha: LocalDateTime,
    var idUser: String
    ) {
}
