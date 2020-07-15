package com.todaysquare.simplefragmenttest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.fragment_my.view.*

class MyFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Log.d("data", arguments?.getString("kkk").toString())

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my, container, false)

        view.button_fragment.setOnClickListener {
            val fragment = TestFragment()
            val bundle = Bundle()

            bundle.putString("key", view.edit_fragment.text.toString())
            fragment.arguments = bundle

            fragmentManager?.beginTransaction()?.replace(R.id.main_container, fragment)?.commit()

        }
        // Inflate the layout for this fragment
        return view

    }
}
