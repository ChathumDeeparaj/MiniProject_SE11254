package com.chathum.assignment03miniproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chathum.assignment03miniproject.data.TaskRepository
import com.chathum.assignment03miniproject.model.Task

// STATE MANAGEMENT & ARCHITECTURE:
// The ViewModel separates data logic from UI logic (MVVM).
// Crucially, it survives configuration changes like screen rotations,
// ensuring our task list isn't lost or re-fetched unnecessarily when rotating the device.

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    // LiveData is used so the UI can observe state changes.
    // Encapsulation: We expose an immutable LiveData (_tasks is private)
    private val repository = TaskRepository(application)

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> get() = _tasks

    init {
        loadTasks()
    }
    // Fetches data from local storage and updates the LiveData state
    public fun loadTasks() {
        _tasks.value = repository.getTasks()
    }

    // Adds a task, saves it to SharedPreferences, and updates the UI state
    fun addTask(task: Task) {
        val currentTasks = _tasks.value?.toMutableList() ?: mutableListOf()
        currentTasks.add(task)
        repository.saveTasks(currentTasks)
        _tasks.value = currentTasks
    }

    // Removes a task, saves the updated list, and updates the UI state
    fun deleteTask(task: Task) {
        val currentTasks = _tasks.value?.toMutableList() ?: mutableListOf()
        currentTasks.remove(task)
        repository.saveTasks(currentTasks)
        _tasks.value = currentTasks
    }

    // Toggles the completion status of a specific task and saves the new state
    fun toggleComplete(task: Task) {
        val currentTasks = _tasks.value?.toMutableList() ?: mutableListOf()
        val index = currentTasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            currentTasks[index] = task.copy(isCompleted = !task.isCompleted)
            repository.saveTasks(currentTasks)
            _tasks.value = currentTasks
        }
    }
}