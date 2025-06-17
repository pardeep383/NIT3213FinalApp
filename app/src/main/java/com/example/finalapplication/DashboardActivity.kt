package com.example.finalapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DashboardActivity : AppCompatActivity() {

    private lateinit var viewModel: ObjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val username = intent.getStringExtra("username") ?: return
        val password = intent.getStringExtra("password") ?: return
        val keypass = intent.getStringExtra("keypass") ?: return

        val repository = ObjectRepository(RetrofitClient.apiService)
        val factory = ObjectViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[ObjectViewModel::class.java]

        viewModel.fetchDashboard(keypass, username, password)

        val listView = findViewById<ListView>(R.id.recyclerView)

        lifecycleScope.launch {
            delay(200)
            viewModel.entities.collectLatest { list ->
                if (list.isEmpty()) {
                    Toast.makeText(this@DashboardActivity, "No data found", Toast.LENGTH_SHORT).show()
                    return@collectLatest
                }

                val data = list.map { entity ->
                    // Pick the first non-null, non-empty string value
                    val displayName = entity.entries.firstOrNull { it.value != null && it.value.toString().isNotBlank() }?.value.toString()
                    mapOf("summary" to displayName, "full" to entity.entries.joinToString("\n") { "${it.key}: ${it.value}" })
                }

                val adapter = SimpleAdapter(
                    this@DashboardActivity,
                    data,
                    android.R.layout.simple_list_item_1,
                    arrayOf("summary"),
                    intArrayOf(android.R.id.text1)
                )

                listView.adapter = adapter

                listView.setOnItemClickListener { _, _, position, _ ->
                    val details = data[position]["full"]
                    val intent = Intent(this@DashboardActivity, DetailsActivity::class.java)
                    intent.putExtra("details", details)
                    startActivity(intent)
                }
            }
        }

        findViewById<Button>(R.id.btnBackToLogin).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
