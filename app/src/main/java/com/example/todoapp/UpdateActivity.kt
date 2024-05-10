package com.example.todoapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.databinding.ActivityUpdateBinding
import java.util.*

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: TaskDBHelper
    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDBHelper(this)

        taskId = intent.getIntExtra("task_id", -1)
        if (taskId == -1) {
            finish()
            return
        }

        val task = db.getTaskByID(taskId)
        binding.updateTitleEditText.setText(task.title)
        binding.updateContentEditText.setText(task.content)
        binding.updatePriorityEditText.setText(task.priority)
        binding.updateDateEditText.setText(task.deadline)

        // Set OnClickListener for the date EditText to open the date picker dialog
        binding.updateDatePickerButton.setOnClickListener {
            showDatePicker()
        }

        binding.updateSaveButton.setOnClickListener {
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            val newPriority = binding.updatePriorityEditText.text.toString()
            val newDeadline = binding.updateDateEditText.text.toString()

            val updatedTask = Task(taskId, newTitle, newContent, newPriority, newDeadline)

            db.updateTask(updatedTask)
            finish()
            Toast.makeText(this, "Task Updated", Toast.LENGTH_SHORT).show()
        }
    }

    // Method to show the date picker dialog
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                // Update the selected date
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                // Update the EditText with the selected date
                binding.updateDateEditText.setText(selectedDate)
            },
            year,
            month,
            dayOfMonth
        )
        datePickerDialog.show()
    }
}
