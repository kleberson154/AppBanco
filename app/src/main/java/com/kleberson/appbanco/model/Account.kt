package com.kleberson.appbanco.model

class Account(val email: String, val password: String, val firstName: String, val lastName: String, val phone: String) {
    override fun toString(): String {
        return "Account(email='$email', password='$password', firstName='$firstName', lastName='$lastName', phone='$phone')"
    }
}