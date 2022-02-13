package com.myapp.letusinterview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView



class MyAdapter(private val userlist: ArrayList<myclass> ) : RecyclerView.Adapter<MyAdapter.MyviewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyviewHolder {

        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
   return MyviewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyviewHolder, position: Int) {

        val cus:myclass=userlist[position]
        holder.tw1.text=cus.u
        holder.tw2.text=cus.q



    }

    override fun getItemCount(): Int {

      return   userlist.size
    }
    public class MyviewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val tw1: TextView = itemView.findViewById(R.id.uidlay)
        val tw2: TextView = itemView.findViewById(R.id.qlay)




    }
}