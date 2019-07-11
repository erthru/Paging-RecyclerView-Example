package com.ertohru.pagingrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_people.view.*

class PeopleAdapter(val data: ArrayList<String>?): RecyclerView.Adapter<PeopleAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_people, parent, false))
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.build(data?.get(position)!!)
    }


    class ViewHolder(val v: View): RecyclerView.ViewHolder(v){

        fun build(dataSet:String){
            v.textView.text = dataSet
        }

    }
}