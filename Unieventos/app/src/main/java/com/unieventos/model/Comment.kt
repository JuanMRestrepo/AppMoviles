package com.unieventos.model

import java.util.Date

class Comment(
    var id: String = "",
    var content: String = "",
    var userId: String = "",
    var reportId: String = "",
    var creationDate: Date = Date()
){
}
