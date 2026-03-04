package com.chathum.assignment03miniproject.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chathum.assignment03miniproject.R
import com.chathum.assignment03miniproject.adapter.TaskAdapter
import com.chathum.assignment03miniproject.viewmodel.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val taskViewModel: TaskViewModel by viewModels()
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val fabAdd = findViewById<com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton>(R.id.fabAdd)

        adapter = TaskAdapter(
            tasks = emptyList(),
            onTaskDelete = { task -> taskViewModel.deleteTask(task) },
            onTaskToggle = { task -> taskViewModel.toggleComplete(task) }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        taskViewModel.tasks.observe(this) { tasks ->
            adapter.updateTasks(tasks)
        }

        fabAdd.setOnClickListener {
            startActivity(Intent(this, AddEditTaskActivity::class.java))
        }
    }


    override fun onResume() {
        super.onResume()
        // Force the ViewModel to fetch the latest data from SharedPreferences
        // every time this screen becomes visible again.
        taskViewModel.loadTasks()
    }
}