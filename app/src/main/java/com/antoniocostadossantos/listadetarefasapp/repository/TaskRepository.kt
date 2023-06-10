package com.antoniocostadossantos.listadetarefasapp.repository

import com.antoniocostadossantos.listadetarefasapp.data.TaskDao
import com.antoniocostadossantos.listadetarefasapp.model.Task

class TaskRepository(private val taskDao: TaskDao) {

    fun getAllTasks() = taskDao.getAll()

    fun getTaskById(id: Int) = taskDao.getTaskById(id)

    fun newTask(task: Task) = taskDao.newTask(task)

    fun updateTask(task: Task) = taskDao.updateTask(task)

    fun deleteTask(task: Task) = taskDao.deleteTask(task)
}