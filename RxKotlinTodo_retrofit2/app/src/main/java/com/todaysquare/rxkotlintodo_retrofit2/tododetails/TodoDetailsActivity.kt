package com.todaysquare.rxkotlintodo_retrofit2.tododetails

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import androidx.core.content.ContextCompat

import com.google.gson.Gson
import com.jakewharton.rxbinding2.view.clicks
import com.todaysquare.rxkotlintodo_retrofit2.BaseActivity
import com.todaysquare.rxkotlintodo_retrofit2.R
import com.todaysquare.rxkotlintodo_retrofit2.api.ApiClient
import com.todaysquare.rxkotlintodo_retrofit2.datamodels.TodoModel
import com.todaysquare.rxkotlintodo_retrofit2.utils.Constants.INTENT_TODOITEM

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_todo_details.*
import kotlinx.android.synthetic.main.content_todo_details.*

import org.jetbrains.anko.toast

import java.util.*

class TodoDetailsActivity : BaseActivity() {
    var todoDataModel: TodoModel? = null
    var isEditing = false

    override fun onCreateBaseActivity(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_todo_details)

        detail_toolbar.title = "View and edit todo"
        setSupportActionBar(detail_toolbar)

        todoDataModel = intent?.getSerializableExtra(INTENT_TODOITEM) as? TodoModel

        txtID.text = todoDataModel?.id?.toString()
        txtDesc.setText(todoDataModel?.todoDescription)
        txtStatus.setText(todoDataModel?.status)
        txtDate.text = todoDataModel?.todoTargetDate

        fabEditTodo.clicks().subscribeBy { _ ->
            if (isEditing) saveData()
            else startEdit()

        }
    }

    private fun saveData() {
        todoDataModel?.todoDescription = txtDesc.text.toString()
        todoDataModel?.status = txtStatus.text.toString()
        todoDataModel?.todoTargetDate = txtDate.text.toString()

        val gson = Gson()
        val jsonTodo = gson.toJson(todoDataModel)

        ApiClient()
            .getApiService()
            .editTodo(todoDataModel?.id?.toString()?:"", jsonTodo)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { response->
                if(response.error_code == 0) {
                    stopEdit()
                    toast("Todo successfully saved")
                    setResult(Activity.RESULT_OK)

                } else toast("Couldn't save Todo, please try again")

            }
    }

    @SuppressLint("SetTextI18n")
    private fun startEdit() {
        isEditing = true
        txtDesc.inputType = InputType.TYPE_TEXT_FLAG_MULTI_LINE or InputType.TYPE_CLASS_TEXT
        txtStatus.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS or InputType.TYPE_CLASS_TEXT

        txtDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(this@TodoDetailsActivity,
                DatePickerDialog.OnDateSetListener {
                        _, year, month, dayOfMonth ->
                    txtDate.text = "$year/${month+1}/$dayOfMonth" }
                , calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH))

            datePicker.show()

        }
        txtDesc.requestFocus()
        fabEditTodo.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_save))

    }

    override fun onBackPressed() {
        if(!isEditing) super.onBackPressed()
        else stopEdit()

    }

    private fun stopEdit() {
        isEditing = false
        txtDesc.inputType = InputType.TYPE_NULL or InputType.TYPE_CLASS_TEXT
        txtStatus.inputType = InputType.TYPE_NULL

        txtDate.setOnClickListener{ }
        fabEditTodo.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_edit))

    }
}
