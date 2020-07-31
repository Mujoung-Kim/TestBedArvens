package com.todaysquare.patterntasknote.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.todaysquare.patterntasknote.R
import com.todaysquare.patterntasknote.data.databases.entities.FavoriteNotice
import com.todaysquare.patterntasknote.ui.favorite.FavoriteViewModel
import com.todaysquare.patterntasknote.utils.Constants.Param.Companion.EXTRA_NOTICE_DELETE
import com.todaysquare.patterntasknote.utils.Constants.Param.Companion.EXTRA_NOTICE_SAVE
import kotlinx.android.synthetic.main.fragment_dialog_add.view.*

class DialogAddFragment : DialogFragment(), View.OnClickListener {
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_dialog_add, container, false)

        processBundle(view)

        return view
    }

    override fun onClick(v: View?) {
        dismiss()

    }

    private fun processBundle(view: View) {
        val bundle = arguments
        val notice = bundle?.getParcelable<FavoriteNotice>(EXTRA_NOTICE_SAVE)

        when (bundle?.getString(EXTRA_NOTICE_DELETE, "")) {
            EXTRA_NOTICE_DELETE -> {
                view.dialog_tv_question.text = getString(R.string.dialog_favorite_delete_text)
                view.dialog_tv_yes.setOnClickListener {
                    notice?.let { notice ->
                        viewModel.delete(notice)
                        showToast(getString(R.string.delete_success_text))
                        dismiss()
                    }
                }
                view.dialog_tv_no.setOnClickListener {
                    dismiss()

                }
            }
            else -> {
                view.dialog_tv_yes.setOnClickListener {
                    notice?.let { notice ->
                        viewModel.insert(notice)
                        showToast(getString(R.string.add_success_text))
                        dismiss()

                    }
                }
                view.dialog_tv_no.setOnClickListener {
                    dismiss()

                }
            }
        }
    }

    fun getInstance(): DialogAddFragment = DialogAddFragment()

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    }
}