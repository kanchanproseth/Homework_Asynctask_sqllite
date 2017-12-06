package com.kanchanproseth.homework_asynctask_sqllite.model

/**
 * Created by kanchanproseth on 12/6/17.
 */

class PostModel(username: String?, userImage: Int?, userStatus: String?, image: Int?, title: String?, description: String?, type: Int){
    var username: String?
    var userimage: Int?
    var userStatus: String?

    var img: Int?
    var title: String?
    var desc: String?
    var type: Int?

    init {

        this.username = username
        this.userimage = userImage
        this.userStatus = userStatus

        img = image
        this.title = title
        desc = description

        this.type = type
    }
}