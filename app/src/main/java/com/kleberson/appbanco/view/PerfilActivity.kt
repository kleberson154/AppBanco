package com.kleberson.appbanco.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kleberson.appbanco.R
import com.kleberson.appbanco.controller.AccountController
import com.kleberson.appbanco.util.FormatBalance

class PerfilActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perfil_activity)
        val emailLog = intent.getStringExtra("email") ?: ""
        val password = intent.getStringExtra("password") ?: ""
        val param = intent.getStringExtra("param") ?: ""

        val controller = AccountController(this)

        val email = findViewById<TextView>(R.id.textViewEmailPerfil)
        val fisrtName = findViewById<TextView>(R.id.textViewFIrstNamePerfil)
        val lastName = findViewById<TextView>(R.id.textViewLastNamePerfil)
        val phone = findViewById<TextView>(R.id.textViewPhonePerfil)
        val balance = findViewById<TextView>(R.id.textViewBalance)
        val balanceCredit = findViewById<TextView>(R.id.textViewBalanceCredit)
        val btnDelete = findViewById<Button>(R.id.buttonDeleteAccount)

        val user = controller.getInformationUser(emailLog, password)

        if(user != null){
            email.text = user.email
            fisrtName.text = user.firstName
            lastName.text = user.lastName
            phone.text = user.phone
            balance.text = formatBalance.format(user.balance)
            if (user.balance > 0){
                balanceCredit.text = formatBalance.format(user.limitCredit)
            } else {
                balanceCredit.text = formatBalance.format(user.limitCredit + user.balance)
            }
        }else{
            email.text = "Email not found"
            fisrtName.text = "First name not found"
            lastName.text = "Last name not found"
            phone.text = "Phone not found"
            balance.text = "Balance not found"
            balanceCredit.text = "Credit limit not found"
        }


        btnDelete.setOnClickListener {
            android.app.AlertDialog.Builder(this)
                .setTitle("Confirmação")
                .setMessage("Tem certeza que deseja excluir sua conta?")
                .setPositiveButton("Sim") { _, _ ->
                    controller.deleteAccount(emailLog)
                    startActivity(Intent(this, SuccessActivity::class.java).apply {
                        putExtra("param", param)
                    })
                    finish()
                }
                .setNegativeButton("Não", null)
                .show()
        }

    }
}