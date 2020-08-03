package com.todaysquare.patterntasknote.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object FirebaseHelper {
    val auth = FirebaseAuth.getInstance()
    var user: FirebaseUser? = null

}