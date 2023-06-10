package com.antoniocostadossantos.listadetarefasapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.antoniocostadossantos.listadetarefasapp.R
import com.antoniocostadossantos.listadetarefasapp.databinding.ItemTaskBinding
import com.antoniocostadossantos.listadetarefasapp.model.Task

class TaskAdapter(
    val checkTask: (Task) -> Unit,
    val updateTask: (Task) -> Unit,
    val deleteTask: (Task) -> Unit
) : RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    private val items = mutableListOf<Task>()

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(items[position])

        holder.checkedTask.setOnClickListener {
            checkTask(items[position])
        }

        holder.itemView.setOnLongClickListener {
            val context = holder.itemView.context
            val popupMenu = PopupMenu(context, holder.itemView)
            val inflater = popupMenu.menuInflater

            inflater.inflate(R.menu.floating_menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.update_task -> {
                        updateTask(items[position])
                        true
                    }

                    R.id.delete_task -> {
                        deleteTask(items[position])
                        notifyItemRemoved(position)

                        true
                    }

                    else -> false
                }
            }

            popupMenu.show()
            true
        }
    }

    fun updateData(newList: List<Task>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
}

class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {

    val titleTask = binding.titleTask
    val descriptionTask = binding.descriptionTask
    val checkedTask = binding.checkbox

    fun bind(taskItem: Task) {
        titleTask.text = taskItem.title
        descriptionTask.text = taskItem.description
        checkedTask.isChecked = taskItem.checked
    }
}