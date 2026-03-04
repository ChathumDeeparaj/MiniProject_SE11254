package com.chathum.assignment03miniproject.data

import android.content.Context
import android.content.SharedPreferences
import com.chathum.assignment03miniproject.model.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * TaskAdapter is a RecyclerView Adapter responsible for binding a list of [Task] objects
 * to individual item views displayed in the RecyclerView on the main screen.
 *
 * It uses the ViewHolder pattern to efficiently recycle views and avoid
 * unnecessary calls to findViewById(), improving scroll performance.
 *
 * @param tasks The initial list of tasks to display.
 * @param onTaskDelete A callback lambda invoked when the user taps the delete button on a task.
 * @param onTaskToggle A callback lambda invoked when the user checks/unchecks a task's completion checkbox.
 */

class TaskRepository(context: Context) {

    // We use Context.MODE_PRIVATE to ensure that our SharedPreferences file
    // is strictly accessible only by our application. This prevents other
    // applications on the device from reading or tampering with the user's personal notes.
    private val prefs: SharedPreferences = context.getSharedPreferences("tasks_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun getTasks(): List<Task> {
        val tasksJson = prefs.getString("tasks_list", null) ?: return emptyList()
        val type = object : TypeToken<List<Task>>() {}.type
        return gson.fromJson(tasksJson, type)
    }

    fun saveTasks(tasks: List<Task>) {
        val tasksJson = gson.toJson(tasks)
        prefs.edit().putString("tasks_list", tasksJson).apply()
    }
}