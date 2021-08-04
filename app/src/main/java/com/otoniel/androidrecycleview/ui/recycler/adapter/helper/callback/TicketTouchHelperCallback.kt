package com.otoniel.androidrecycleview.ui.recycler.adapter.helper.callback

import android.graphics.Canvas
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.Callback
import androidx.recyclerview.widget.RecyclerView
import com.otoniel.androidrecycleview.ui.recycler.adapter.TicketsAdapter

class TicketTouchHelperCallback(private val adapterTicket: TicketsAdapter): Callback() {

    private var isDragging = false

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
    ): Int {
        val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        val dragFlags = ItemTouchHelper.DOWN or
                ItemTouchHelper.UP or
                ItemTouchHelper.LEFT or
                ItemTouchHelper.RIGHT
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder,
    ): Boolean {
        val initPos = viewHolder.adapterPosition
        val endPos = target.adapterPosition
        adapterTicket.changeTicketsOrder(initPos, endPos)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        adapterTicket.removeTicket(position)
    }
}