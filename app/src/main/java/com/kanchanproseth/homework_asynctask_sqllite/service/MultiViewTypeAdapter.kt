package com.kanchanproseth.homework_asynctask_sqllite.service

import android.media.MediaPlayer
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.kanchanproseth.homework_asynctask_sqllite.R.id.card_view
import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import com.kanchanproseth.homework_asynctask_sqllite.R
import com.kanchanproseth.homework_asynctask_sqllite.R.id.card_view_2
import com.kanchanproseth.homework_asynctask_sqllite.model.PostModel
import kotlinx.android.synthetic.main.cards_layout.view.*
import kotlinx.android.synthetic.main.cards_layout_2.view.*
import java.util.*


/**
 * Created by cyberk on 12/6/17.
 */
class MultiViewTypeAdapter(internal var mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    internal var total_types: Int = 0
    internal var mPlayer: MediaPlayer = MediaPlayer()
    private var fabStateVolume = false
    var dataSet = arrayListOf<PostModel>()

    val random = Random()

    fun rand(from: Int, to: Int) : Int {
        return random.nextInt(to - from) + from
    }




    class FirstCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var thumbnail: ImageView
        var txtTitle: TextView
        var txtDescription: TextView
        var cardView: CardView

        init {
            this.thumbnail = itemView.imagePost
            this.txtTitle = itemView.title
            this.txtDescription = itemView.description
            this.cardView = itemView.card_view
        }
    }

    class SecondCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var username: TextView
        var statusText: TextView
        var userImage: ImageView
        var cardView: CardView

        init {
            this.username = itemView.username
            this.statusText = itemView.status_text
            this.userImage = itemView.userImage
            this.cardView = itemView.card_view_2
        }
    }


    init {
        for(i in 1 .. 20){
            var postData: PostModel? = null
            val ranImgNum = rand(1, 5)
            val rantypeNum = rand(1, 3)
            print(rantypeNum)
            if (rantypeNum == 1){
                if (ranImgNum == 1){
                    postData = PostModel(username = null, userImage = null, userStatus = null, image = R.drawable.img1, title = "iPhone X problems", description = "All the latest news about the iPhone X, including problems with unresponsive screens, Face ID security, buzzing speakers, headaches and a green line", type = rantypeNum)
                }else if (ranImgNum == 2){
                    postData = PostModel(username = null, userImage = null, userStatus = null, image = R.drawable.img2, title = "iPhone X deals, specs and news", description = "THE LONG AWAITED iPhone X is now available. Apple's 10th-anniversary smartphone went on sale across the globe on 3 November, with thousands of customers queueing to get their hands on the £1,000 handset", type = rantypeNum)
                }else{
                    postData = PostModel(username = null, userImage = null, userStatus = null, image = R.drawable.img3, title = "Apple iPhone X Explained", description = "New and existing customers on the carrier's Sprint Flex plan will be able to get a 64GB iPhone for \$22.22 per month for 18 months (\$400) with an eligible trade-in.", type = rantypeNum)
                }

            }else if (rantypeNum == 2){
                val u_name_1 = "kan Chanproseth"
                val u_status_1 = "sad life"
                val u_name_2 = "srey Sart"
                val u_status_2 = "Happy New year"
                val u_name_3 = "Vean Viney"
                val u_status_3 = "This is just for fun"
                if (ranImgNum == 1){
                    postData = PostModel(username = u_name_1, userImage = R.drawable.profile_1, userStatus = u_status_1, image = null, title = null, description = null, type = rantypeNum)
                }else if (ranImgNum == 2){
                    postData = PostModel(username = u_name_2, userImage = R.drawable.profile_2, userStatus = u_status_2, image = null, title = null, description = null, type = rantypeNum)
                }else{
                    postData = PostModel(username = u_name_3, userImage = R.drawable.profile_3, userStatus = u_status_3, image = null, title = null, description = null, type = rantypeNum)
                }

            }

            dataSet.add(postData!!)
        }

        total_types = dataSet.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {

        val view: View
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.cards_layout, parent, false)
                return FirstCardViewHolder(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.cards_layout_2, parent, false)
                return SecondCardViewHolder(view)
            }
        }
        return null
    }

    override fun getItemViewType(position: Int): Int {
//        val status:UserStatusPost = dataSet[position] as UserStatusPost
        when (dataSet[position].type) {
            1 -> return 1
            2 -> return 2
            else -> return 0
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, listPosition: Int) {

        val mydata = dataSet[listPosition]
        if ( mydata != null) {
            when (mydata!!.type) {
                1 -> {
                    (holder as FirstCardViewHolder).txtTitle.text = mydata!!.title
                    (holder as FirstCardViewHolder).txtDescription.text = mydata!!.desc
                    holder.thumbnail.setImageResource(mydata.img!!)
                }
                2 -> {
                    (holder as SecondCardViewHolder).username.setText(mydata!!.username)
                    holder.statusText.text = mydata.userStatus
                    holder.userImage.setImageResource(mydata.userimage!!)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}
