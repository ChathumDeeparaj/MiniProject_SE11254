package com.chathum.assignment03miniproject.model
// ARCHITECTURE: This is the 'Model' in MVVM architecture.
// It represents the data structure for a single task.
data class Task(
    // Generates a unique ID for each task automatically
    val id: String = java.util.UUID.randomUUID().toString(),
    var title: String,
    var description: String,
    var isCompleted: Boolean = false
)