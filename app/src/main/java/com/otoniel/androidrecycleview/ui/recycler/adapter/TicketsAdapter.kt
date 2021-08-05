package com.otoniel.androidrecycleview.ui.recycler.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.otoniel.androidrecycleview.R
import com.otoniel.androidrecycleview.model.Ticket
import com.otoniel.androidrecycleview.ui.recycler.adapter.listener.OnItemClickListener
import java.util.*
import kotlin.collections.ArrayList


class TicketsAdapter(
    private val ticketList: ArrayList<Ticket>,
    private val listener: OnItemClickListener
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
        holder.setClickListener(listener)
    }

    override fun getItemCount(): Int {
        return ticketList.size
    }

    fun addTicket(ticket: Ticket) {
        ticketList.add(ticket)
        notifyItemInserted(ticketList.size)
       // notifyDataSetChanged()
    }

    fun editTicket(ticket: Ticket, position: Int){
        ticketList[position] = ticket
        //notifyDataSetChanged()
        notifyItemChanged(position)
    }

    fun removeTicket(position: Int) {
        ticketList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun changeTicketsOrder(initPos: Int, endPos: Int) {
        Collections.swap(ticketList, initPos, endPos)
        notifyItemMoved(initPos, endPos)
    }

    class TicketViewHolder(private val ticketView: View) : RecyclerView.ViewHolder(ticketView) {

        private lateinit var ticket: Ticket
        private val title: TextView = ticketView.findViewById(R.id.itemTitle)
        private val description: TextView = ticketView.findViewById(R.id.itemDescription)

        fun setClickListener(onItemClickListener: OnItemClickListener) {
            ticketView.setOnClickListener{
                onItemClickListener.onItemClick(ticket, adapterPosition)
            }
        }

        fun setTicket(ticket: Ticket) {
            this.ticket = ticket
            bindFields(ticket)
        }

        private fun bindFields(ticket: Ticket) {
            title.text = ticket.title;
            description.text = ticket.subtitle;
        }
    }
}