package com.kanchanproseth.homework_asynctask_sqllite.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.kanchanproseth.homework_asynctask_sqllite.R
import com.kanchanproseth.homework_asynctask_sqllite.model.request.User
import com.kanchanproseth.homework_asynctask_sqllite.service.RegisterService
import com.kanchanproseth.homework_asynctask_sqllite.util.AppBuilder
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import android.support.v4.content.ContextCompat.startActivity
import com.kanchanproseth.homework_asynctask_sqllite.service.SignInService


class MainActivity : AppCompatActivity() {
    var realm: Realm? = null
    var app = AppBuilder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        realm = Realm.getInstance(this)
        app.runConfigure()
        val usernameTextField = signInUsername.text
        val passwordTextfield = signInPassword.text
        signInButton.setOnClickListener {
            if(usernameTextField.isEmpty()){
                Toast.makeText(this, "Please Enter Username", Toast.LENGTH_SHORT).show()
            }else if (passwordTextfield.isEmpty()){
                Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show()
            }else{
                signin(username = usernameTextField.toString(), password = passwordTextfield.toString())
            }
        }

        registerNowButton.setOnClickListener {
            val myIntent = Intent(this, Register::class.java)
            startActivity(myIntent)
        }
    }
    


    fun signin(username: String, password: String){
        val signinService: SignInService = app.retrofit!!.create(SignInService::class.java)
        val model_obj = User()
        model_obj.username = username
        model_obj.password = password

        signinService.signIn(model_obj) .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            if (result["code"] == "200"){

                                val myIntent = Intent(this, Home::class.java)
//                                myIntent.putExtra("key", value) //Optional parameters
                                startActivity(myIntent)
                            }else{
                                Toast.makeText(this, result["message"], Toast.LENGTH_SHORT).show()
//                                val myIntent = Intent(this, Register::class.java)
////                                myIntent.putExtra("key", value) //Optional parameters
//                                startActivity(myIntent)


                            }
                        },
                        { error ->
                            Log.e("Error", error.message)
                        }
                )
    }
}
