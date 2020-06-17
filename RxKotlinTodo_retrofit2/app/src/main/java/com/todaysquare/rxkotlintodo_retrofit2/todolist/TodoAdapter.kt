package com.todaysquare.rxkotlintodo_retrofit2.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.jakewharton.rxbinding2.view.clicks
import com.todaysquare.rxkotlintodo_retrofit2.R
import com.todaysquare.rxkotlintodo_retrofit2.datamodels.TodoModel

import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.Subject

import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(private val context: Context,
    val onClickTodoSubject: Subject<Pair<View, TodoModel?>>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val todoList: ArrayList<TodoModel> = arrayListOf()

    fun setDataSet(list: List<TodoModel>) {
        todoList.clear()
        todoList.addAll(list)
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int = todoList.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bindView(todoList[position])

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder =
        TodoViewHolder(inflater.inflate(R.layout.item_todo, parent, false))

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(todoItem: TodoModel?) {
            with(itemView) {
                txtID.text = todoItem?.id?.toString()
                txtDesc.text = todoItem?.todoDescription
                txtStatus.text = todoItem?.status
                txtDate.text = todoItem?.todoTargetDate

                itemView.clicks()
                    .subscribeBy {
                        onClickTodoSubject.onNext(Pair(itemView,todoItem))

                    }
            }
        }
    }
}