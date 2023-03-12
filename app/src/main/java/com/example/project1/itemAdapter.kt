package com.example.project1

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

class itemAdapter(private val group: Group, listnerContext: OnItemClickListner): RecyclerView.Adapter<itemViewHolder>()
{
    private var myinterface: OnItemClickListner = listnerContext
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        return itemViewHolder(inflater,parent)
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int)
    {
        var item: Item = group.item[position]
        holder.onBind(item)

        holder.itemView.setOnLongClickListener {
            myinterface.itemLongClicked(position)
            true
        }
        holder.itemView.setOnClickListener {
            myinterface.itemClicked(position)
        }
    }
    override fun getItemCount(): Int = group.item.size
}



class itemViewHolder(inflater: LayoutInflater,parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_row,parent,false))
{
    var itemname:TextView? = itemView.findViewById(R.id.itemname)
    var itemcheckbox: CheckBox? = itemView.findViewById(R.id.itemcheckbox)

    fun onBind(item: Item)
    {
        itemname!!.text = item.name
        itemcheckbox!!.isChecked = item.complete
    }

}