package com.ertohru.pagingrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val data = ArrayList<String?>()
    private val peopleAdapter = PeopleAdapter(data)
    private var allDataLoaded = false
    private var isLoadingNext = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        fillData()
    }

    private fun setupRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = peopleAdapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            var directiorDown:Boolean = false

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                directiorDown = dy > 0
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (recyclerView.canScrollVertically(1).not() && newState == RecyclerView.SCROLL_STATE_IDLE && directiorDown) {
                    if(!allDataLoaded && !isLoadingNext)
                        fillDataNext()
                }
            }
        })
    }

    private fun fillData(){
        peopleAdapter.showProgress()

        // simulate network delay
        Handler().postDelayed({
            peopleAdapter.removeProgress()

            val data = ArrayList<String>()
            data.add("A")
            data.add("B")
            data.add("C")
            data.add("D")
            data.add("E")
            data.add("F")
            data.add("G")
            data.add("H")
            data.add("I")
            data.add("J")
            data.add("K")
            data.add("L")
            data.add("M")
            data.add("N")
            data.add("O")
            data.add("P")
            data.add("Q")

            this.data.addAll(data)
            peopleAdapter.notifyDataSetChanged()
        }, 3000)
    }

    private fun fillDataNext(){
        this.isLoadingNext = true

        peopleAdapter.showProgress()

        // simulate network delay
        Handler().postDelayed({
            peopleAdapter.removeProgress()

            val data = ArrayList<String>()
            data.add("R")
            data.add("S")
            data.add("T")
            data.add("U")
            data.add("V")
            data.add("W")
            data.add("X")
            data.add("Y")
            data.add("Z")

            this.isLoadingNext = false
            this.allDataLoaded = true
            this.data.addAll(data)
            peopleAdapter.notifyDataSetChanged()
        }, 3000)
    }
}
