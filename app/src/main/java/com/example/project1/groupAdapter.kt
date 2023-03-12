package com.example.project1

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ExpandableListView.OnGroupClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class groupViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder
    (inflater.inflate(R.layout.group_row,parent,false))
{
    var groupName: TextView? = itemView.findViewById(R.id.groupnametv)
    var groupCount: TextView? = itemView.findViewById(R.id.groupcount)

    fun bind(group: Group)
    {
        groupName?.text = group.name
        groupCount?.text = "${group.item.count()} items"

    }
}



class groupAdapter(private val list: List<Group>,listnerContext :OnGroupClickListner) : RecyclerView.Adapter<groupViewHolder>()
{
    private var myinterface: OnGroupClickListner = listnerContext
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): groupViewHolder
    {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return groupViewHolder(inflater,parent)
    }



    override fun onBindViewHolder(holder: groupViewHolder, position: Int)
    {
        val group:Group = list[position]
        holder.bind(group)


        //On Click at the position call interface class function and give them position as index
        holder.itemView.setOnClickListener(){
            myinterface.groupClicked(position)
        }
        holder.itemView.setOnLongClickListener{
            myinterface.groupLongClicked(position)
            true
        }

    }

    override fun getItemCount(): Int = list.size
}