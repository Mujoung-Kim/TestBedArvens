package com.todaysquare.iamportsamplecode.ui

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.webkit.JavascriptInterface

import com.todaysquare.iamportsamplecode.utils.Constants.Param.Companion.IMP_UID
import com.todaysquare.iamportsamplecode.utils.Constants.String.Companion.FAILURE
import com.todaysquare.iamportsamplecode.utils.Constants.String.Companion.RESULT
import com.todaysquare.iamportsamplecode.utils.Constants.String.Companion.SUCCESS

class SmsAuthFactory(activity: Activity) {
    companion object {
        private const val TAG = "SmsActivity"

    }
    private val activity = activity

    @JavascriptInterface
    fun resultAuth(message: String, impUid: String?) {
        val intent = Intent()

        if (message == SUCCESS && impUid != null) {
            intent.putExtra(RESULT, SUCCESS)
            intent.putExtra(IMP_UID, impUid)
            activity.setResult(RESULT_OK, intent)
            activity.finish()

        } else {
            intent.putExtra(RESULT, FAILURE)
            activity.setResult(RESULT_OK, intent)
            activity.finish()

        }
    }
}