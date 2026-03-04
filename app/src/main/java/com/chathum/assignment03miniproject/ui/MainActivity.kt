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

    // STATE MANAGEMENT: Connects to our ViewModel. The 'by viewModels()' delegate
    // ensures this ViewModel survives screen rotations.
    private val taskViewModel: TaskViewModel by viewModels()
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val fabAdd = findViewById<com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton>(R.id.fabAdd)

        // Initialize adapter and pass lambda functions to handle UI clicks
        adapter = TaskAdapter(
            tasks = emptyList(),
            onTaskDelete = { task -> taskViewModel.deleteTask(task) },
            onTaskToggle = { task -> taskViewModel.toggleComplete(task) }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // STATE MANAGEMENT: Observe the LiveData from the ViewModel.
        // Whenever the data changes (item added/deleted/toggled), the UI updates automatically.
        taskViewModel.tasks.observe(this) { tasks ->
            adapter.updateTasks(tasks)
        }

        // Navigate to the Add/Edit screen
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