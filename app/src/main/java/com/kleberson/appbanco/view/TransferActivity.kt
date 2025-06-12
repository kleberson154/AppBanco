package com.kleberson.appbanco.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.kleberson.appbanco.R
import com.kleberson.appbanco.controller.AccountController
import com.kleberson.appbanco.database.Database
import com.kleberson.appbanco.exception.InsufficientBalanceException
import com.kleberson.appbanco.exception.UserNotFoundException
import com.kleberson.appbanco.util.FormatBalance

class TransferActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.transfer_activity)
        val formatBalance = FormatBalance()
        val email = intent.getStringExtra("email") ?: ""
        val password = intent.getStringExtra("password") ?: ""
        val param = intent.getStringExtra("param")

        val balanceTextView = findViewById<TextView>(R.id.textViewBalanceTransfer)
        val buttonNextTransfer = findViewById<Button>(R.id.buttonNextTransfer)
        val inputTransfer = findViewById<EditText>(R.id.editTextInputTransfer)
        val inputEmail = findViewById<EditText>(R.id.editTextEmailTransfer)
        val accountController = AccountController(this)
        val profile = accountController.login(email, password)
        val db = Database(this)

        if (profile != null) {
            balanceTextView.text = formatBalance.format(profile.balance)
        }

        buttonNextTransfer.setOnClickListener {
            if (profile != null) {
                try {
                    if (profile.balance >= inputTransfer.text.toString().toDouble()) {
                        if (db.searchUser(inputEmail.text.toString())) {
                            accountController.transfer(email, inputTransfer.text.toString().toDouble(), inputEmail.text.toString())
                            val intent = Intent(this, SuccessActivity::class.java).apply {
                                putExtra("email", email)
                                putExtra("password", password)
                                putExtra("param", param)}
                            startActivity(intent)
                        }else{
                            throw UserNotFoundException()
                        }
                    }else{
                        throw InsufficientBalanceException()
                    }
                }catch (e: InsufficientBalanceException){
                    Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                }catch (e: UserNotFoundException){
                    Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}