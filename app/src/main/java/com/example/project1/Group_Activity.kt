package com.example.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.AppData.DataHolder.groups
import java.util.zip.Inflater

class Group_Activity : AppCompatActivity(), OnGroupClickListner
{

    private var groupsadapter: groupAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.group_activity)

        AppData.initialize()
        val groupRecyclerViewr = findViewById<RecyclerView>(R.id.gRecyclerview)

        groupsadapter = groupAdapter(AppData.groups,this)
        groupRecyclerViewr.layoutManager = LinearLayoutManager(this)
        groupRecyclerViewr.adapter = groupsadapter
    }

    override fun onResume()
    {
        super.onResume()
        groupsadapter!!.notifyDataSetChanged()
    }

    fun CreateGroup(v: View)
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("New Group")
        builder.setMessage("Enter a name for your new group")

        val myInput = EditText(this)
        myInput.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(myInput)

        builder.setPositiveButton("Save")
        {
            dialogue, which->
            val groupName: String = myInput.text.toString()
            val newGroup = Group(groupName, mutableListOf())
            AppData.groups.add(newGroup)
            groupsadapter!!.notifyItemInserted(AppData.groups.count())
        }
        builder.setNegativeButton("Cancel")
        {
            dialogue,which ->
        }

        val dialogue: AlertDialog = builder.create()
        dialogue.show()
    }

    override fun groupClicked(index: Int)
    {
        //go to the item activity
        val intent = Intent(this,Item_Activity::class.java)
        intent.putExtra("groupindex",index)
        startActivity(intent)

        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
    }

    override fun groupLongClicked(index: Int)
    {
        //delete the group
        AppData.groups.removeAt(index)
        groupsadapter!!.notifyItemRemoved(index)
    }
}