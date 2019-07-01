package com.dolllo.foryou

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton

class MainActivity : AppCompatActivity() {

    private lateinit var facebookLogin: LoginButton


    private lateinit var callbackManager: CallbackManager

    var usEmail: String = "didn't get"
    lateinit var prefs: SharedPreferences

    lateinit var btnLogIn: Button
    lateinit var btnRegistr: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.sdkInitialize(applicationContext)
        setContentView(R.layout.activity_main)

        AppEventsLogger.activateApp(this)
        facebookLogin = findViewById(R.id.facebook_login_btn)

        facebookLogin.setReadPermissions("email")
        prefs = getSharedPreferences("com.love.anotherdating", Context.MODE_PRIVATE)

        callbackManager = CallbackManager.Factory.create()

        btnLogIn = findViewById(R.id.signIn_btn)
        btnRegistr = findViewById(R.id.sigUp_btn)

        btnLogIn.setOnClickListener {
            startActivity(Intent(this@MainActivity, LogInActivity::class.java))
        }

        btnRegistr.setOnClickListener {
            startActivity(Intent(this@MainActivity, QuestionaireFirstActivity::class.java))
        }

        LoginManager.getInstance().logOut()

        LoginManager.getInstance().registerCallback(callbackManager, object: FacebookCallback<LoginResult> {

            override fun onError(error: FacebookException?) {
                Log.d("MainActivity", error.toString(), error)
            }

            override fun onCancel() {
                Toast.makeText(this@MainActivity, "Авторизация отменена", Toast.LENGTH_SHORT).show()
            }

            override fun onSuccess(result: LoginResult) {
                getUserEmail(AccessToken.getCurrentAccessToken())
                if (isUserInDb()) {
                    startActivity(Intent(this@MainActivity, UserProfileActivity::class.java))
                } else {
                    startActivity(Intent(this@MainActivity, QuestionaireFirstActivity::class.java))
                }
            }

        })

        facebookLogin.setOnClickListener {
            if (AccessToken.getCurrentAccessToken() == null) {
                //Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getUserEmail(token: AccessToken) {

        val request: GraphRequest = GraphRequest.newMeRequest(
            token
        ) { `object`, response ->
            Log.v("MainActivity", response.toString())

            if (`object` != null) {
                usEmail = `object`.getString("email")
            }
        }

        val parameters = Bundle()
        parameters.putString("fields", "email")
        request.parameters = parameters
        request.executeAsync()
    }

    fun isUserInDb(): Boolean {
        return if (prefs.getBoolean("firstrun",true)) {
            prefs.edit().putBoolean("firstrun", false).apply()
            true
        } else {
            false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode,resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
