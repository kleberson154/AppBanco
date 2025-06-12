package com.kleberson.appbanco.util

class FormatBalance {
    fun format(balance: Double): String {
        return String.format("%.2f", balance).replace('.', ',')
    }
}