package com.antoniocostadossantos.listadetarefasapp.view.itemsListActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antoniocostadossantos.listadetarefasapp.model.Task
import com.antoniocostadossantos.listadetarefasapp.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemsListViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    fun getAllTasks() = viewModelScope.launch(Dispatchers.IO) {
        _tasks.postValue(taskRepository.getAllTasks())
    }

    fun checkTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        task.checked = !task.checked
        taskRepository.updateTask(task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        taskRepository.deleteTask(task)
    }

}