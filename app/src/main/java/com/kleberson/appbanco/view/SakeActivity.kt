package com.kleberson.appbanco.view

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.kleberson.appbanco.R
import com.kleberson.appbanco.controller.AccountController
import com.kleberson.appbanco.database.Database
import com.kleberson.appbanco.exception.InsufficientBalanceException
import com.kleberson.appbanco.util.FormatBalance

class SakeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.sake_activity)
        val formatBalance = FormatBalance()
        val email = intent.getStringExtra("email") ?: ""
        val password = intent.getStringExtra("password") ?: ""
        val param = intent.getStringExtra("param")

        val balanceTextView = findViewById<TextView>(R.id.textViewBalanceSake)
        val balanceCreditTextView = findViewById<TextView>(R.id.textViewBalanceCreditSake)
        val buttonNextSake = findViewById<android.widget.Button>(R.id.buttonNextSake)
        val inputSake = findViewById<EditText>(R.id.editTextInputSake)
        val accountController = AccountController(this)
        val profile = accountController.login(email, password)

        if (profile != null) {
            balanceTextView.text = formatBalance.format(profile.balance)
            balanceCreditTextView.text = formatBalance.format(profile.limitCredit)
        }

        buttonNextSake.setOnClickListener {
            if (profile != null) {
                try {
                    if ((profile.balance + profile.limitCredit) >= inputSake.text.toString().toDouble()) {
                        accountController.sake(email, inputSake.text.toString().toDouble())
                        val intent = Intent(this, SuccessActivity::class.java).apply {
                            putExtra("email", email)
                            putExtra("password", password)
                            putExtra("param", param)}
                        startActivity(intent)
                    }else{
                        throw InsufficientBalanceException()
                    }
                }catch (e: InsufficientBalanceException){
                    Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}