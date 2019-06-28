package com.love.anotherdating

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
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

    private lateinit var the_intent1: Intent
    var the_userEmail: String = "didn't get"
    var the_handler = Handler()
    val the_REFERRER_DATA = "REFERRER_DATA"
    lateinit var prefs: SharedPreferences

    lateinit var buttonSighIn: Button
    lateinit var buttonReg: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.sdkInitialize(applicationContext)
        setContentView(R.layout.activity_main)

        AppEventsLogger.activateApp(this)
        facebookLogin = findViewById(R.id.login_button_facebook)

        facebookLogin.setReadPermissions("email")
        prefs = getSharedPreferences("com.love.anotherdating", Context.MODE_PRIVATE)

        callbackManager = CallbackManager.Factory.create()

        buttonSighIn = findViewById(R.id.button_sign_in)
        buttonReg = findViewById(R.id.button_create_account)

        buttonSighIn.setOnClickListener {
            startActivity(Intent(this@MainActivity, SignInActivity::class.java))
        }

        buttonReg.setOnClickListener {
            startActivity(Intent(this@MainActivity, AnketaFirstActivity::class.java))
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
                    startActivity(Intent(this@MainActivity, ProfileActivity::class.java))
                } else {
                    startActivity(Intent(this@MainActivity, AnketaFirstActivity::class.java))
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
                the_userEmail = `object`.getString("email")
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
