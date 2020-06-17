package com.todaysquare.rxkotlintodo_retrofit2.addtodo

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle

import com.google.gson.Gson
import com.todaysquare.rxkotlintodo_retrofit2.BaseActivity
import com.todaysquare.rxkotlintodo_retrofit2.R
import com.todaysquare.rxkotlintodo_retrofit2.api.ApiClient
import com.todaysquare.rxkotlintodo_retrofit2.datamodels.TodoModel

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.rxkotlin.subscribeBy

import kotlinx.android.synthetic.main.activity_add_todo.*

import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

import java.util.*

@Suppress("UNREACHABLE_CODE")
class AddTodoActivity : BaseActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreateBaseActivity(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_add_todo)

        txtDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(this@AddTodoActivity,
                DatePickerDialog.OnDateSetListener {
                        _, year, month, dayOfMonth ->
                    txtDate.text = "$year/${month + 1}/$dayOfMonth"
                },
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))

            datePicker.datePicker.minDate = calendar.timeInMillis
            datePicker.show()

        }

        btnAddTodo.setOnClickListener {
            val desc = txtDesc.text.toString()
            val date = txtDate.text.toString()
            val status = txtStatus.text.toString()

            if (desc.isBlank() || date.isBlank())
                longToast("Todo description and date are mandatory")
            else {
                val todoDataModel = TodoModel(0, desc, date, status)
                val gson = Gson()
                val jsonTodo = gson.toJson(todoDataModel)

                ApiClient().getApiService().addTodo(jsonTodo).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).subscribeBy {
                        response ->
                        if (response.error_code == 0) {
                            toast("Todo successfully saved")
                            setResult(Activity.RESULT_OK)
                            finish()

                        } else toast("Couldn't save todo, please try again")

                    }
            }
        }
    }
}
