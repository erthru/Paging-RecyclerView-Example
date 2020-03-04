package com.ertohru.pagingrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_people.view.*

class PeopleAdapter(private val data: ArrayList<String?>?): RecyclerView.Adapter<PeopleAdapter.ViewHolder>(){
    private val TYPE_VIEW = 0
    private val TYPE_PROGRESS = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType){
           TYPE_VIEW -> ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_people, parent, false))
            else -> ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_progress, parent, false))
        }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(getItemViewType(position) != TYPE_PROGRESS){
            holder.build(data?.get(position)!!)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(data?.get(position)){
            null -> TYPE_PROGRESS
            else -> TYPE_VIEW
        }
    }

    fun showProgress(){
        data?.add(null)
        notifyItemInserted(data?.size!! - 1)
    }

    fun removeProgress(){
        data?.removeAt(data.size - 1)
        notifyItemRemoved(data?.size!!)
    }

    class ViewHolder(val v: View): RecyclerView.ViewHolder(v){
        fun build(dataSet:String){
            v.textView.text = dataSet
        }
    }
}