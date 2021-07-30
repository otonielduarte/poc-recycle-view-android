package com.otoniel.androidrecycleview.dao

import com.otoniel.androidrecycleview.model.Ticket
import java.util.*
import kotlin.collections.ArrayList

class TicketDAO {
    companion object {
        @JvmStatic
        private var tickets: ArrayList<Ticket> = ArrayList()
    }

    fun all(): ArrayList<Ticket> {
        return tickets.clone() as ArrayList<Ticket>
    }

    fun add(ticket: Ticket) {
        tickets.add(ticket)
    }

    fun update(ticket: Ticket, position: Int) {
        tickets.set(position, ticket)
    }

    fun remove(position: Int) {
        tickets.removeAt(position)
    }

    fun change(positionStart: Int, positionEnd: Int) {
        Collections.swap(tickets, positionStart, positionEnd)
    }

    fun clear() {
        tickets.clear()
    }

}