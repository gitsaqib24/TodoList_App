package com.example.project1

interface OnGroupClickListner
{
    fun groupClicked(index: Int)
    fun groupLongClicked(index: Int)
}

interface OnItemClickListner
{
    fun itemClicked(index: Int)
    fun itemLongClicked(index: Int)
}