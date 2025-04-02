package com.unieventos.model

import android.location.Address

class User(
    var id: String,
    var name: String,
    var email: String,
    var password: String,
    var role: Role,
    var phoneNumber: String,
    var address: Address
){
}
