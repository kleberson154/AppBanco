package com.kleberson.appbanco.exception

class InsufficientBalanceException(
    message: String = "Saldo insuficiente. Verifique seu saldo e tente novamente."
) : Exception(message) {
    override val message: String?
        get() = "Erro: ${super.message}"
}