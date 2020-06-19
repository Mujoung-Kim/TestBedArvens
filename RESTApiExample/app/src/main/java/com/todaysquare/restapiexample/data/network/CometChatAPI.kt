package com.todaysquare.restapiexample.data.network

import android.app.Activity.RESULT_OK
import android.content.Context
import android.util.Log

import com.todaysquare.restapiexample.R
import com.todaysquare.restapiexample.ui.activities.AddFriendDialogActivity
import com.todaysquare.restapiexample.utils.Constants.Url.Companion.BASE_URL
import com.todaysquare.restapiexample.utils.Constants.Url.Companion.version
import com.todaysquare.restapiexample.utils.Data
import com.todaysquare.restapiexample.utils.showToast

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class CometChatAPI(private val context: Context) {
    companion object {
        private const val TAG = "CometChatAPI"
    }

    fun addFriend(userID: String, friendID: String, activity: AddFriendDialogActivity) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CometChatFriendsService::class.java)

        val friends = arrayListOf<String>()

        friends.add(friendID)

        val body = HashMap<String, List<String>>()

        body["accepted"] = friends

        service.addFriend(context.getString(R.string.apiKey), context.getString(R.string.appID),
            body, version, userID).enqueue(object : Callback<Data> {

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.d(TAG, "Failed API call with call : $call exception : $t")
                activity.showToast("Failed to add a friend. please enter a valid ID")

            }

            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                Log.d(TAG, response.body().toString())

                val friends = response.body()!!.data.accepted

                if (friends == null) activity.showToast("Failed to add a friend. please enter a valid ID")
                else {
                    for (friendName in friends.keys) {
                        Log.d(TAG, friends[friendName].toString())

                        if (friends[friendName] != null) {
                            if (!friends[friendName]!!.success)
                                activity.showToast(friends[friendName]!!.message)
                            else {
                                activity.showToast("$friendName is added as a friend")
                                activity.setResult(RESULT_OK)
                                activity.finish()

                            }
                        }
                        else activity.showToast("Failed to add a friend. please enter a valid ID")

                    }
                }
            }
        })
    }

    interface CometChatFriendsService {
        @Headers("accept: application/json", "Content-Type: application/json")
        @POST("{version}/users/{userID}/friends")
        fun addFriend(
            @Header("apikey") apiKey: String,
            @Header("appid") appID: String,
            @Body params: HashMap<String, List<String>>,
            @Path("version") version: String,
            @Path("uid") userID: String

        ): Call<Data>

    }
}