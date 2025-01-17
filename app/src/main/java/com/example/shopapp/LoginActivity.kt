package com.example.shopapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    private lateinit var goMainPage : AppCompatButton
    private lateinit var goRegPage : AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        goMainPage = findViewById(R.id.loginButton)
        goRegPage = findViewById(R.id.regButton)

        goMainPage.setOnClickListener {
            var login = findViewById<EditText>(R.id.loginField).text.toString().trim()
            var password = findViewById<EditText>(R.id.passField).text.toString().trim()

            if (login == "") {
                Toast.makeText(this, "Введите логин пользователя!", Toast.LENGTH_LONG).show()
            }
            else if (password.equals("")) {
                Toast.makeText(this, "Введите пароль!", Toast.LENGTH_LONG).show()
            }

            val db = DbHelper(this, null)
            programData.db = db
            val isAuth = db.getUser(login, password)
            if (isAuth) {

                programData.userName = login
                programData.adminFlag = db.isAdmin(login)

                var intent : Intent
                if (programData.adminFlag) {
                    intent = Intent(this@LoginActivity, MainWindowActivityAdmin :: class.java)
                }
                else {
                    intent = Intent(this@LoginActivity, MainWindowActivityNotAdmin :: class.java)
                }
                //var intent = Intent(this@LoginActivity, MainWindowActivityAdmin :: class.java)
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(this, "Проверьте корректность данных!", Toast.LENGTH_LONG).show()
            }
        }

        goRegPage.setOnClickListener {
            var intent = Intent(this@LoginActivity, RegistrationActivity :: class.java)
            startActivity(intent)
            finish()
        }
    }
}