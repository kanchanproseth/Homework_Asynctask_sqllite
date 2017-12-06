package com.kanchanproseth.homework_asynctask_sqllite.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.kanchanproseth.homework_asynctask_sqllite.R
import com.kanchanproseth.homework_asynctask_sqllite.service.MultiViewTypeAdapter
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = recycler_view_home as RecyclerView
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView!!.setHasFixedSize(true)
        // use a linear layout manager
        mLayoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = mLayoutManager

        mAdapter = MultiViewTypeAdapter(this)
        recyclerView!!.adapter = mAdapter
    }
}
