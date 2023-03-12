package com.example.project1

data class Item(val name:String,
                var complete: Boolean)

data class Group(val name: String,
                 val item: MutableList<Item>)