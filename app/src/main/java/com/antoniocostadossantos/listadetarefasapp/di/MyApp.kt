package com.antoniocostadossantos.listadetarefasapp.di

import android.app.Application
import com.antoniocostadossantos.listadetarefasapp.data.AppDataBase
import com.antoniocostadossantos.listadetarefasapp.repository.TaskRepository
import com.antoniocostadossantos.listadetarefasapp.view.itemsListActivity.ItemsListViewModel
import com.antoniocostadossantos.listadetarefasapp.view.newTaskActivity.NewTaskViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(mainDependency)
        }
    }
}

val mainDependency = module {

    single { AppDataBase.getInstance(androidContext()) }
    single { TaskRepository(taskDao = AppDataBase.getInstance(androidContext()).taskDao()) }
    viewModel { ItemsListViewModel(taskRepository = get()) }
    viewModel { NewTaskViewModel(taskRepository = get()) }
}
