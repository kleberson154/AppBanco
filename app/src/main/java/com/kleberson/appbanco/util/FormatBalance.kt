package com.kleberson.appbanco.util

import android.annotation.SuppressLint

class FormatBalance {
    @SuppressLint("DefaultLocale")
    fun format(balance: Double): String {
        return String.format("%.2f", balance).replace('.', ',')
    }
}