package com.antoniocostadossantos.listadetarefasapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.antoniocostadossantos.listadetarefasapp.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        fun getInstance(context: Context): AppDataBase {
            return Room.databaseBuilder(
                context,
                AppDataBase::class.java, "tasks_db"
            ).build()
        }
    }

}