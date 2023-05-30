package com.antoniocostadossantos.listadetarefasapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.antoniocostadossantos.listadetarefasapp.databinding.ActivityItemsListBinding

class ItemsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}