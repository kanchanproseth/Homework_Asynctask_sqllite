package com.kanchanproseth.homework_asynctask_sqllite.service

import com.kanchanproseth.homework_asynctask_sqllite.model.request.User
import retrofit.Retrofit
import retrofit.http.Body
import retrofit.http.Headers
import retrofit.http.POST

/**
 * Created by kanchanproseth on 11/6/17.
 */
interface RegisterService{
    @Headers("Content-Type: application/json")
    @POST("/register")
    fun register(@Body body: User): rx.Observable<HashMap<String,String>>

    companion object {
        val instance: RegisterService by lazy {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .build()
            retrofit.create(RegisterService::class.java)
        }
    }
}