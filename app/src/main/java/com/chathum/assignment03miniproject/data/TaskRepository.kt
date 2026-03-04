package com.chathum.assignment03miniproject.data

import android.content.Context
import android.content.SharedPreferences
import com.chathum.assignment03miniproject.model.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


// DATA PERSISTENCE: This class handles saving and loading data locally
// so that tasks remain even after the app is completely restarted.

class TaskRepository(context: Context) {

    // I use Context.MODE_PRIVATE to ensure that our SharedPreferences file
    // is strictly accessible only by this application. This prevents other
    // applications on the device from reading or tampering with the user's personal notes.
    private val prefs: SharedPreferences = context.getSharedPreferences("tasks_prefs", Context.MODE_PRIVATE)
    // I use Gson to serialize our List<Task> into a JSON string to store in SharedPreferences
    private val gson = Gson()

    // Retrieves the saved JSON string and converts it back into a List of Task objects
    fun getTasks(): List<Task> {
        val tasksJson = prefs.getString("tasks_list", null) ?: return emptyList()
        val type = object : TypeToken<List<Task>>() {}.type
        return gson.fromJson(tasksJson, type)
    }

    // Converts the current List of Tasks into a JSON string and saves it securely
    fun saveTasks(tasks: List<Task>) {
        val tasksJson = gson.toJson(tasks)
        prefs.edit().putString("tasks_list", tasksJson).apply()
    }
}