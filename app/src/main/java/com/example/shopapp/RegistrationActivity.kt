package com.example.shopapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegistrationActivity : AppCompatActivity() {
    private lateinit var goLoginPage : AppCompatButton
    private lateinit var goMainPage : AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        goLoginPage = findViewById(R.id.loginButton)
        goMainPage = findViewById(R.id.regButton)

        goMainPage.setOnClickListener {
            var login = findViewById<EditText>(R.id.loginField).text.toString().trim()
            var password = findViewById<EditText>(R.id.passField).text.toString().trim()
            var password2 = findViewById<EditText>(R.id.passRepeatField).text.toString().trim()

            if (password.equals(password2)) {
                var db = DbHelper(this, null)
                programData.db = db
                var isCreated = db.isCreated(login)
                if (isCreated) {
                    Toast.makeText(this, "Пользователь $login существует!", Toast.LENGTH_LONG).show()
                }
                else {
                    db.addUser(login, password)
                    Toast.makeText(this, "Пользователь $login создан!", Toast.LENGTH_LONG).show()

                    programData.userName = login

                    var intent = Intent(this@RegistrationActivity, MainWindowActivityNotAdmin::class.java)
                    startActivity(intent)
                    finish()

                    //db.reloadTables()
                }
            }
            else {
                println("pass: $password, pass2: $password2")
                Toast.makeText(this, "Пароли отличаются!", Toast.LENGTH_LONG).show()
            }

        }

        goLoginPage.setOnClickListener {
            var intent = Intent(this@RegistrationActivity, LoginActivity :: class.java)
            startActivity(intent)
            finish()
        }

    }
}