package com.example.project1

class AppData {

    companion object DataHolder
    {
        var groups: MutableList<Group> = mutableListOf()
        fun initialize()
        {
            val item1 = Item("tester",false)
            val item2 = Item("plair",false)
            val item3 = Item("milk",false)
            val item4 = Item("bred",false)

            val group1 = Group("Electrical", mutableListOf(item1,item2))
            val group2 = Group("Food", mutableListOf(item3,item4))

            groups = mutableListOf(group1,group2)
        }
    }

}