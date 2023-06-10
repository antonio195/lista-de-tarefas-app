package com.antoniocostadossantos.listadetarefasapp.view.newTaskActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antoniocostadossantos.listadetarefasapp.model.Task
import com.antoniocostadossantos.listadetarefasapp.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewTaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    private val _task = MutableLiveData<Task>()
    val task: LiveData<Task> = _task

    fun newTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        taskRepository.newTask(task)
    }

    fun getTask(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        taskRepository.getTaskById(id)
    }

    fun updateTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        taskRepository.updateTask(task)
    }
}