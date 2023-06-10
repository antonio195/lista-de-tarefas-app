package com.antoniocostadossantos.listadetarefasapp.view.newTaskActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.antoniocostadossantos.listadetarefasapp.databinding.ActivityNewTaskBinding
import com.antoniocostadossantos.listadetarefasapp.model.Task
import com.antoniocostadossantos.util.TASK_ID
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewTaskBinding
    private val newTaskViewModel: NewTaskViewModel by viewModel()
    var isUpdate = false
    private lateinit var task: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkIfUpdateOrCreate()

        binding.btnNewTask.setOnClickListener {
            createOrUpdate()
        }
    }

    private fun checkIfUpdateOrCreate() {
        val updateTask: Boolean = intent.getBooleanExtra("UPDATE", false)
        if (updateTask) {
            isUpdate = true
            getTaskFromIntent()
        }
    }

    private fun getTaskFromIntent() {
        val taskToUpdate = intent.getParcelableExtra<Task>(TASK_ID)

        taskToUpdate.let {
            binding.titleText.text = "Atualizar tarefa"
            task = taskToUpdate!!
            binding.titleInput.setText(task.title)
            binding.descriptionInput.setText(task.description)
        }
    }

    private fun getTaskUpdates() {
        val titleUpdated = binding.titleInput.text.toString()
        val descriptionUpdated = binding.descriptionInput.text.toString()

        task.apply {
            title = titleUpdated
            description = descriptionUpdated
        }
    }

    private fun createOrUpdate() {
        val title = binding.titleInput.text.toString()
        val description = binding.descriptionInput.text.toString()

        when {
            title.isEmpty() -> {
                binding.titleLabel.error = "Insira um título"
            }

            description.isEmpty() -> {
                binding.descriptionLabel.error = "Insira uma descrição"
            }

            else -> {
                binding.titleLabel.error = null
                binding.descriptionLabel.error = null

                if (isUpdate) {
                    getTaskUpdates()
                    newTaskViewModel.updateTask(task)
                    finish()

                } else {
                    newTaskViewModel.newTask(Task(title = title, description = description))
                    finish()
                }
            }
        }
    }
}