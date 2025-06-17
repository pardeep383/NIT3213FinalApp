package com.example.finalapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: ObjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameField = findViewById<EditText>(R.id.editUsername)
        val passwordField = findViewById<EditText>(R.id.editPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)

        val repository = ObjectRepository(RetrofitClient.apiService)
        val factory = ObjectViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[ObjectViewModel::class.java]

        loginButton.setOnClickListener {
            val username = usernameField.text.toString().trim()
            val password = passwordField.text.toString().trim()
            Log.d("LOGIN_UI", "Clicked login with $username / $password")

            if (username.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(username, password)
            } else {
                Toast.makeText(this, "Please enter both fields", Toast.LENGTH_SHORT).show()
            }
        }

        lifecycleScope.launch {
            viewModel.loginResult.collectLatest { result ->
                result?.onSuccess { keypass ->
                    val intent = Intent(this@LoginActivity, DashboardActivity::class.java).apply {
                        putExtra("username", usernameField.text.toString())
                        putExtra("password", passwordField.text.toString())
                        putExtra("keypass", keypass)
                    }
                    startActivity(intent)
                    finish()
                }?.onFailure {
                    Toast.makeText(this@LoginActivity, "Login failed: ${it.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
