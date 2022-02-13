package com.myapp.letusinterview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView



class myadapter1(private val userlist1: ArrayList<myclass1> ) : RecyclerView.Adapter<myadapter1.MyviewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myadapter1.MyviewHolder {

        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.zlist,parent,false)
        return MyviewHolder(itemView)
    }



    override fun getItemCount(): Int {

        return   userlist1.size
    }
    public class MyviewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val tw1: TextView = itemView.findViewById(R.id.tw1)
        val tw2: TextView = itemView.findViewById(R.id.tw2)
        val tw3: TextView = itemView.findViewById(R.id.tw3)
        val tw4: TextView = itemView.findViewById(R.id.tw4)
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        val cus1: myclass1 =userlist1[position]
        holder.tw1.text=cus1.candname
        holder.tw2.text=cus1.mob
        holder.tw3.text=cus1.email
        holder.tw4.text=cus1.course
    }
}