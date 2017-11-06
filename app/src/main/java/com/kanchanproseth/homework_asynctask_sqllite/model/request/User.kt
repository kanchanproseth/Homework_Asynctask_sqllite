package com.kanchanproseth.homework_asynctask_sqllite.model.request

import com.google.gson.annotations.SerializedName

/**
 * Created by kanchanproseth on 11/6/17.
 */

class User {
    @SerializedName("username")
    var username: String? = null
    @SerializedName("password")
    var password: String? = null
}