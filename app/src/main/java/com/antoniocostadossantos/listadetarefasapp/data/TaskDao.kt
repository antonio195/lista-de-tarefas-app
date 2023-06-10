package com.antoniocostadossantos.listadetarefasapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.antoniocostadossantos.listadetarefasapp.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM TASK")
    fun getAll(): List<Task>

    @Insert
    fun newTask(task: Task)

    @Update()
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("SELECT * FROM TASK WHERE id=:id")
    fun getTaskById(id: Int): Task
}