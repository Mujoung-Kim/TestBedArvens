package com.todaysquare.simplefragmenttest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.fragment_my.*

class MyFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("data", arguments?.getString("kkk").toString())

        setFragmentResultListener("Download") { key, bundle ->
            val result = bundle.getString("PosterPath")

        }

        button_fragment.setOnClickListener {
            val result = "result"

            setFragmentResult("Download", bundleOf("PosterPath" to result))

        }
        val bundle = Bundle()

        bundle.putString("", "")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false)

    }

}
