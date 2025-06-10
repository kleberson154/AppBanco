package com.kleberson.appbanco.util

class FormatBalance(
    private val balance: Double
) {
    fun format(): String {
        return String.format("%.2f", balance).replace('.', ',')
    }

}