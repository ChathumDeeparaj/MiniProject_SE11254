package com.chathum.assignment03miniproject.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chathum.assignment03miniproject.R
import com.chathum.assignment03miniproject.model.Task

class TaskAdapter(
    private var tasks: List<Task>,
    private val onTaskDelete: (Task) -> Unit,
    private val onTaskToggle: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    fun updateTasks(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int = tasks.size

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleText: TextView = itemView.findViewById(R.id.tvTaskTitle)
        private val descText: TextView = itemView.findViewById(R.id.tvTaskDesc)
        private val checkBox: CheckBox = itemView.findViewById(R.id.cbCompleted)

        // This is the fix! Changed Button to ImageButton
        private val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)

        fun bind(task: Task) {
            titleText.text = task.title
            descText.text = task.description

            checkBox.setOnCheckedChangeListener(null)
            checkBox.isChecked = task.isCompleted

            if (task.isCompleted) {
                titleText.paintFlags = titleText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                titleText.paintFlags = titleText.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }

            checkBox.setOnCheckedChangeListener { _, _ -> onTaskToggle(task) }
            btnDelete.setOnClickListener { onTaskDelete(task) }
        }
    }
}