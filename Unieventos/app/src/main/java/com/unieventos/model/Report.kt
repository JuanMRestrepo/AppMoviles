package com.unieventos.model

import java.time.LocalDateTime

class Report(
    var id: String,
    var title: String,
    var description: String,
    var state: ReportState,
    var images: List<String>,
    var location: Location,
    var fecha: LocalDateTime,
    ) {
}
