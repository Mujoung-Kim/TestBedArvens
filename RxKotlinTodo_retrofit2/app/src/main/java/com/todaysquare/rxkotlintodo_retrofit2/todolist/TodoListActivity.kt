package com.todaysquare.rxkotlintodo_retrofit2.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

import com.jakewharton.rxbinding2.view.clicks
import com.todaysquare.rxkotlintodo_retrofit2.BaseActivity
import com.todaysquare.rxkotlintodo_retrofit2.R
import com.todaysquare.rxkotlintodo_retrofit2.addtodo.AddTodoActivity
import com.todaysquare.rxkotlintodo_retrofit2.api.ApiClient
import com.todaysquare.rxkotlintodo_retrofit2.datamodels.TodoModel
import com.todaysquare.rxkotlintodo_retrofit2.tododetails.TodoDetailsActivity
import com.todaysquare.rxkotlintodo_retrofit2.utils.Constants.INTENT_TODOITEM

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

import kotlinx.android.synthetic.main.activity_todo_list.*

import org.jetbrains.anko.intentFor

class TodoListActivity : BaseActivity() {
    private val INTENT_EDIT_TODO: Int = 100
    private val INTENT_ADD_TODO: Int = 101
    lateinit var mAdapter: TodoAdapter

    override fun onCreateBaseActivity(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_todo_list)
        setSupportActionBar(toolbar)

        fabAddTodo.clicks().subscribeBy { _ ->
            startActivityForResult(intentFor<AddTodoActivity>(), INTENT_ADD_TODO)

        }

        val onClickTodoSubject =
            PublishSubject.create<Pair<View, TodoModel?>>()

        onClickTodoSubject.filter { it.second != null }
            .subscribeBy {
                startActivityForResult(
                    intentFor<TodoDetailsActivity>(
                        Pair(INTENT_TODOITEM, it.second)
                    ), INTENT_EDIT_TODO
                )

            }

        mAdapter = TodoAdapter(this, onClickTodoSubject)
        rvTodoList.adapter = mAdapter
        fetchTodoList()

    }

    private fun fetchTodoList() {
        ApiClient()
            .getApiService()
            .getTodoList()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { response -> mAdapter.setDataSet(response.data) },
                onError = { error -> error.printStackTrace() }
            )

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ((requestCode == INTENT_EDIT_TODO || requestCode == INTENT_ADD_TODO)
            && resultCode == Activity.RESULT_OK)
            fetchTodoList()

    }
}
