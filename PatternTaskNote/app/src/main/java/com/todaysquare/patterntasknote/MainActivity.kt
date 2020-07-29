package com.todaysquare.patterntasknote

import android.app.AlertDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.todaysquare.patterntasknote.data.adapters.ContactAdapter
import com.todaysquare.patterntasknote.data.databases.entities.Contact
import com.todaysquare.patterntasknote.ui.ContactViewModel
import kotlinx.android.synthetic.main.activity_main.*

/*
    * Model-View-ViewModel 패턴 작성 시 유의사항
        1. viewModel 에서는 android 의 activity/fragment 와 같은 View 단에 직접적으로 접근하면 안된다.
        2. viewModel 에서 data 를 가져와 처리 할 때에는 repository 를 생성해 함수로 정의 후 이를 호출한다.
        3. 구성요소는 data(Model), ui(viewModel), activity(View) 로 구성되며, 각각의 역할은 다음과 같다.
        * data(Model)       -> repository 를 통해 직접적으로 data 를 관리
        * ui(viewModel)     -> View 에서 화면 출력 시 필요한 기능들을 호출하고, LiveData 를 이용해 데이터 변경점을 적용
        * activity(View)    -> viewModel 에서 호출한 기능들을 화면에 출력하고, Observe 를 통해 데이터를 관찰

*/
class MainActivity : AppCompatActivity() {
    private val contactViewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  Set contactItemClick & contactItemLongClick lambda
        val adapter = ContactAdapter({ contact ->
            //  put extra of contact info & start AddActivity

        }, { contact -> deleteDialog(contact) })
        val linearLayoutManager = LinearLayoutManager(this)

        main_recycleView.adapter = adapter
        main_recycleView.layoutManager = linearLayoutManager
        main_recycleView.setHasFixedSize(true)

        contactViewModel.getAll().observe(this, Observer { contacts ->
            adapter.setContacts(contacts)
        })

    }

    private fun deleteDialog(contact: Contact) {
        val builder = AlertDialog.Builder(this)

        builder.setMessage("Delete selected contact?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ -> contactViewModel.delete(contact) }
        builder.show()

    }
}