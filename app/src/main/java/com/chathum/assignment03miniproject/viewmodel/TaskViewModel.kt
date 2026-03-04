package com.chathum.assignment03miniproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chathum.assignment03miniproject.data.TaskRepository
import com.chathum.assignment03miniproject.model.Task

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TaskRepository(application)

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> get() = _tasks

    init {
        loadTasks()
    }

    public fun loadTasks() {
        _tasks.value = repository.getTasks()
    }

    fun addTask(task: Task) {
        val currentTasks = _tasks.value?.toMutableList() ?: mutableListOf()
        currentTasks.add(task)
        repository.saveTasks(currentTasks)
        _tasks.value = currentTasks
    }

    fun deleteTask(task: Task) {
        val currentTasks = _tasks.value?.toMutableList() ?: mutableListOf()
        currentTasks.remove(task)
        repository.saveTasks(currentTasks)
        _tasks.value = currentTasks
    }

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