package com.todaysquare.patterntasknote.data.databases.dao

import androidx.lifecycle.LiveData
import androidx.room.*

import com.todaysquare.patterntasknote.data.databases.entities.Contact

@Dao
interface ContactDao {

    //  LiveData 를 반환하여 값의 변동이 있을 때 마다 DB 에 update 된 내용을 볼 수 있다.
    @Query("select * from contact order by name asc")
    fun getAll(): LiveData<List<Contact>>

    //  onConflict 에서 중복된 데이터의 처리를 설정할 수 있음. REPLACE == 덮어쓰기
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)

}