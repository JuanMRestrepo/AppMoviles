package com.unieventos.model

import java.util.Date

class Report(
    var id: String = "",
    var title: String = "",
    var category: String = "",
    var description: String = "",
    var rejectionReason: String = "",
    var state: ReportState = ReportState.PENDING,
    var images: List<String> = listOf(),
    var comments: List<Comment> = listOf(),
    var location: Location = Location(),
    var date: Date = Date(),
    var idUser: String = "",
    var likeCount: Int = 0,
    var likedUsers: MutableList<String> = mutableListOf()
    ) {
}
