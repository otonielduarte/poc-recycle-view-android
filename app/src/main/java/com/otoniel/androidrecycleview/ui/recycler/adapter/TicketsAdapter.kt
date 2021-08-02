package com.otoniel.androidrecycleview.ui.recycler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.otoniel.androidrecycleview.R
import com.otoniel.androidrecycleview.model.Ticket


class TicketsAdapter(
    private val ticketList: ArrayList<Ticket>,
) :
    RecyclerView.Adapter<TicketsAdapter.TicketViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.ticket_item, parent, false)
        return TicketViewHolder(view)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val ticket = ticketList[position]
        holder.setTicket(ticket)
    }

    override fun getItemCount(): Int {
        return ticketList.size
    }

    fun addTicket(ticket: Ticket) {
        ticketList.add(ticket)
        notifyDataSetChanged()
    }

    class TicketViewHolder(ticketView: View) : RecyclerView.ViewHolder(ticketView) {

        private val title: TextView = ticketView.findViewById(R.id.itemTitle)
        private val description: TextView = ticketView.findViewById(R.id.itemDescription)

        fun setTicket(ticket: Ticket) {
            bindFields(ticket)
        }

        private fun bindFields(ticket: Ticket) {
            title.text = ticket.title;
            description.text = ticket.subtitle;
        }
    }
}