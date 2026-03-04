package com.chathum.assignment03miniproject.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.chathum.assignment03miniproject.R
import com.chathum.assignment03miniproject.model.Task
import com.chathum.assignment03miniproject.viewmodel.TaskViewModel

class AddEditTaskActivity : AppCompatActivity() {

    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_task)

        // STATE MANAGEMENT NOTE: Because these EditText views have android:id tags in the XML,
        // the Android OS automatically preserves any unsaved text typed into them
        // if the user rotates the screen while drafting a task.
        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etDesc = findViewById<EditText>(R.id.etDescription)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val desc = etDesc.text.toString().trim()

            // SECURE CODING
            // Input Validation: I validate that the user inputs are not empty before
            // processing and saving. This prevents the application from storing malformed
            // data, which could lead to NullPointerExceptions or UI rendering bugs later.
            if (title.isEmpty()) {
                Toast.makeText(this, "Title cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create model and save via ViewModel
            val newTask = Task(title = title, description = desc)
            taskViewModel.addTask(newTask)

            Toast.makeText(this, "Task Saved", Toast.LENGTH_SHORT).show()
            finish() // Closes this screen and returns to the Main list
        }
    }
}