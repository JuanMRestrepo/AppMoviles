package com.unieventos.model

import java.time.LocalDateTime

class Comment(
    var id: String,
    var content: String,
    var userId: String,
    var reportId: String,
    var fecha: LocalDateTime
){
}
