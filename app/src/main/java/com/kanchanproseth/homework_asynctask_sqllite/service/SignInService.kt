package com.kanchanproseth.homework_asynctask_sqllite.service

import com.kanchanproseth.homework_asynctask_sqllite.model.request.User
import retrofit.http.Body
import retrofit.http.Headers
import retrofit.http.POST

/**
 * Created by kanchanproseth on 11/6/17.
 */
interface SignInService{
    @Headers("Content-Type: application/json")
    @POST("/signin")
    fun signIn(@Body body: User): rx.Observable<HashMap<String,String>>
}