package com.otoniel.androidrecycleview.ui.recycler.adapter.listener

import com.otoniel.androidrecycleview.model.Ticket

interface OnItemClickListener {
    fun onItemClick(ticket: Ticket, position: Int)
}