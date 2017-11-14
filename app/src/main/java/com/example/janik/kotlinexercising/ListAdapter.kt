package com.example.janik.kotlinexercising

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.row_detail.view.*

/**
 * Created by janik on 05.11.2017.
 */

class ListAdapter(val context: Context, val list: List<Item>) : BaseAdapter(){
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = mInflater.inflate(R.layout.row_detail, parent, false)

        val item = list[position]
        view.textView.text = item.name
        view.textView2.text = item.count.toString()
        view.textView3.text = item.price.toString()

        return view
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }


}