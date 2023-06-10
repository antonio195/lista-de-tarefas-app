package com.antoniocostadossantos.listadetarefasapp.view.itemsListActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.antoniocostadossantos.listadetarefasapp.databinding.ActivityItemsListBinding
import com.antoniocostadossantos.listadetarefasapp.model.Task
import com.antoniocostadossantos.listadetarefasapp.view.adapter.TaskAdapter
import com.antoniocostadossantos.listadetarefasapp.view.newTaskActivity.NewTaskActivity
import com.antoniocostadossantos.util.TASK_ID
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemsListBinding
    private lateinit var taskAdapter: TaskAdapter
    private val itemsListViewModel: ItemsListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initFab()
        getAllTasks()
        observeData()
    }

    override fun onResume() {
        super.onResume()
        getAllTasks()
    }

    private fun getAllTasks() {
        itemsListViewModel.getAllTasks()
    }

    private fun observeData() {
        itemsListViewModel.tasks.observe(this) {
            taskAdapter.updateData(it)
        }
    }

    private fun initRecyclerView() {
        taskAdapter = TaskAdapter(
            updateTask = { task -> updateTask(task) },
            checkTask = { task -> checkTask(task) },
            deleteTask = { task -> deleteTask(task) }
        )
        val recyclerView = binding.rvItems
        recyclerView.adapter = taskAdapter
    }

    private fun updateTask(task: Task) {
        val intent = Intent(this, NewTaskActivity::class.java)
        intent.putExtra(TASK_ID, task)
        intent.putExtra("UPDATE", true)
        startActivity(intent)

    }

    private fun checkTask(task: Task) {
        itemsListViewModel.checkTask(task)
    }

    private fun deleteTask(task: Task) {
        itemsListViewModel.deleteTask(task)
        getAllTasks()
    }

    private fun initFab() {
        binding.fabNewTask.setOnClickListener {
            startActivity(
                Intent(this, NewTaskActivity::class.java)
            )
        }
    }
}