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
import kotlinx.android.synthetic.main.activity_register.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class Register : AppCompatActivity() {
    var realm: Realm? = null
    var app = AppBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        realm = Realm.getInstance(this)
        app.runConfigure()
        val username = registerUsername.text
        val password = registerPassword.text
        registerButton.setOnClickListener {
            register(username = username.toString(),password = password.toString())
        }

    }

    fun register(username: String, password: String){
        val registerService: RegisterService = app.retrofit!!.create(RegisterService::class.java)
        val model_obj = User()
        model_obj.username = username
        model_obj.password = password

        registerService.register(model_obj) .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            if (result["code"] == "200"){
                                Toast.makeText(this, result["message"], Toast.LENGTH_LONG).show()
                                val myIntent = Intent(this, MainActivity::class.java)
//                                myIntent.putExtra("key", value) //Optional parameters
                                startActivity(myIntent)
                                this.finish()
                            }else{
                                Toast.makeText(this, result["message"], Toast.LENGTH_SHORT).show()
                            }
                        },
                        { error ->
                            Log.e("Error", error.message)
                        }
                )
    }
}
