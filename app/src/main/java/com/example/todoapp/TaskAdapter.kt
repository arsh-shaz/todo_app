package com.example.todoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private var tasks: List<Task>, context: Context) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val priorityTextView: TextView = itemView.findViewById(R.id.priorityChipView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateChipView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.titleTextView.text = task.title
        holder.contentTextView.text = task.content
        holder.priorityTextView.text = task.priority
        holder.dateTextView.text = task.deadline
    }

    fun refreshData(newTasks: List<Task>){
        tasks = newTasks
        notifyDataSetChanged()
    }
}