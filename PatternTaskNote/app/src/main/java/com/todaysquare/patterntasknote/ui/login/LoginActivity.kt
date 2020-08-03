package com.todaysquare.patterntasknote.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.viewModels

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

import com.todaysquare.patterntasknote.R
import com.todaysquare.patterntasknote.databinding.ActivityLoginBinding
import com.todaysquare.patterntasknote.ui.CMainActivity
import com.todaysquare.patterntasknote.ui.base.BaseActivity
import com.todaysquare.patterntasknote.utils.Constants.Param.Companion.TEST
import com.todaysquare.patterntasknote.utils.FirebaseHelper

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val RC_SIGN_IN = 1
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var signInIntent: Intent
    val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        initGso()
        initEvent()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)

                firebaseAuthWithGoogle(account!!)

            } catch (error: ApiException) {
                showToast(getString(R.string.login_fail_text))
                error.printStackTrace()
                Log.d(javaClass.simpleName + TEST, error.message.toString())

            }
        }
    }

    private fun initEvent() {
        btn_google_login.setOnClickListener {
            startActivityForResult(signInIntent, RC_SIGN_IN)

        }
    }

    private fun initGso() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
            .requestIdToken("getString(R.string.default_web_client_id)")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        signInIntent = googleSignInClient.signInIntent

    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)

        FirebaseHelper.auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    showToast(getString(R.string.login_success_text))
                    FirebaseHelper.user = FirebaseHelper.auth.currentUser
                    startActivity(Intent(this, CMainActivity::class.java))

                } else
                    showToast(getString(R.string.login_fail_text))

            }
    }
}