package com.otoniel.androidrecycleview.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.otoniel.androidrecycleview.R
import com.otoniel.androidrecycleview.model.Ticket

class ListViewAdapter(private val context: Context, private val all: ArrayList<Ticket>) : BaseAdapter() {

    override fun getCount(): Int {
        return all.size
    }

    override fun getItem(position: Int): Ticket {
        return all[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflated = getView(parent)
        val ticket: Ticket = getItem(position)
        bindingFields(inflated, ticket)
        return inflated
    }

    private fun bindingFields(inflated: View, model: Ticket) {
        val tvTitle = inflated.findViewById<TextView>(R.id.itemTitle)
        val tvDescription = inflated.findViewById<TextView>(R.id.itemDescription)

        tvTitle.text = model.title
        tvDescription.text = model.subtitle
    }

    private fun getView(parent: ViewGroup?): View {
        return LayoutInflater
            .from(context)
            .inflate(
                R.layout.ticket_item,
                parent,
                false
            )
    }
}
