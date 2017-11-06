package com.kanchanproseth.homework_asynctask_sqllite.util

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit.RxJavaCallAdapterFactory
import io.realm.RealmObject
import retrofit.Retrofit
import retrofit.GsonConverterFactory

/**
 * Created by kanchanproseth on 11/6/17.
 */
object AppBuilder {

    val BASEURL = "http://192.168.1.117:5000"
    var retrofit: Retrofit? = null
    var gson: Gson? = null

    init {
        println("This ($this) is a singleton")
    }

    fun runConfigure(){
        gson = GsonBuilder().setExclusionStrategies(object : ExclusionStrategy {
            override fun shouldSkipField(f: FieldAttributes?): Boolean {
                return  f!!.declaringClass == RealmObject::class.java
            }

            override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                return  false
            }
        }).create()

        retrofit = Retrofit.Builder().
                addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson!!))
                .baseUrl(BASEURL).build()
    }

}