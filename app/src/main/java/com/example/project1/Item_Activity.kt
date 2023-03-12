package com.example.project1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Item_Activity : AppCompatActivity() , OnItemClickListner
{
    lateinit var thisGroup: Group
    var itemadapter: itemAdapter? = null

    override fun itemClicked(index: Int)
    {
        thisGroup.item[index].complete = !thisGroup.item[index].complete
        itemadapter!!.notifyDataSetChanged()

    }

    override fun itemLongClicked(index: Int) {
        thisGroup.item.removeAt(index)
        itemadapter!!.notifyItemRemoved(index)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)


        val toolbartitle = findViewById<TextView>(R.id.initemGrouptitle)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.mytoolbar)
        val itemRecyclerView = findViewById<RecyclerView>(R.id.itemRecyclerView)
        val et_add_newitem: EditText? = findViewById<EditText>(R.id.etaddnewitem)



        et_add_newitem!!.setOnKeyListener { view, keycode, event ->
            if (keycode == KeyEvent.KEYCODE_ENTER)
            {
                if (event.action == KeyEvent.ACTION_DOWN )
                {
                    val name: String = et_add_newitem.text.toString()
                    val item: Item   = Item(name,false)
                    thisGroup.item.add(item)
                    itemadapter!!.notifyItemInserted(thisGroup.item.count())
                    et_add_newitem.text.clear()

                    val inputmgr: InputMethodManager =
                        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputmgr.hideSoftInputFromWindow(view.windowToken,0)
                }
            }
            false
        }



        val selectedindex : Int = intent.getIntExtra("groupindex",0)
        thisGroup = AppData.groups[selectedindex]

        toolbartitle.text = thisGroup.name

        itemRecyclerView.layoutManager = LinearLayoutManager(this)
        itemadapter = itemAdapter(thisGroup,this)
        itemRecyclerView.adapter = itemadapter



        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
    }


}