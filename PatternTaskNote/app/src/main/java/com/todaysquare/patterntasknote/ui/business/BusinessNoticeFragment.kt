package com.todaysquare.patterntasknote.ui.business

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.viewModels

import com.todaysquare.patterntasknote.R
import com.todaysquare.patterntasknote.data.adapters.BusinessAdapter
import com.todaysquare.patterntasknote.data.databases.entities.FavoriteNotice
import com.todaysquare.patterntasknote.data.network.NetworkManager
import com.todaysquare.patterntasknote.databinding.FragmentBusinessBinding
import com.todaysquare.patterntasknote.ui.base.BaseFragment
import com.todaysquare.patterntasknote.ui.dialog.DialogAddFragment
import com.todaysquare.patterntasknote.ui.notice_webview.NoticeWebViewActivity
import com.todaysquare.patterntasknote.utils.Constants.Param.Companion.EXTRA_NOTICE_LINK
import com.todaysquare.patterntasknote.utils.Constants.Param.Companion.EXTRA_NOTICE_SAVE
import com.todaysquare.patterntasknote.utils.Constants.Param.Companion.TAG_DIALOG_EVENT

class BusinessNoticeFragment :
    BaseFragment<FragmentBusinessBinding, BusinessNoticeViewModel>(R.layout.fragment_business) {

    override val viewModel: BusinessNoticeViewModel by viewModels()
    private lateinit var noticeAdapter: BusinessAdapter

    override fun init() {
        binding.vm = viewModel
        initAdapter()

        val networkManager = context?.let { NetworkManager(it) }

        if (!networkManager?.checkNetworkState()!!)
            showToast(getString(R.string.network_err_toast))
        viewModel.requestNotice()

    }

    private fun initAdapter() {
        noticeAdapter = BusinessAdapter(itemClick = { item ->
            val intent = Intent(context, NoticeWebViewActivity::class.java)

            intent.putExtra(EXTRA_NOTICE_LINK, item.link)
            startActivity(intent)

        },
            numClick = {
                val bundle = Bundle()

                bundle.putParcelable(EXTRA_NOTICE_SAVE, FavoriteNotice(it.num, it.title, it.link))

                val dialog = DialogAddFragment().getInstance()

                dialog.arguments = bundle
                activity?.supportFragmentManager?.let { fragmentManager ->
                    dialog.show(fragmentManager, TAG_DIALOG_EVENT)

                }
            })
        binding.rvBusiness.adapter = noticeAdapter

    }
}