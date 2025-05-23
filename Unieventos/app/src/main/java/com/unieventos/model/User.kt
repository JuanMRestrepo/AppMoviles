package com.unieventos.model

class User(
    var id: String = "",
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var role: Role = Role.CLIENT,
    var phoneNumber: String = "",
    var address: String = "",
    var savedReportIds: MutableList<String> = mutableListOf()
){
}
