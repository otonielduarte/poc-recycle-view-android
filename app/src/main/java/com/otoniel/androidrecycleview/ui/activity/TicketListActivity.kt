package com.otoniel.androidrecycleview.ui.activity

import android.app.Activity
import android.content.Intent
import com.otoniel.androidrecycleview.ui.recycler.adapter.TicketsAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.ItemTouchHelper
import com.otoniel.androidrecycleview.R
import com.otoniel.androidrecycleview.dao.TicketDAO
import com.otoniel.androidrecycleview.model.Ticket
import com.otoniel.androidrecycleview.ui.activity.TicketsConstants.Companion.EDIT_MODE
import com.otoniel.androidrecycleview.ui.activity.TicketsConstants.Companion.KEY_INTENT_TICKET
import com.otoniel.androidrecycleview.ui.activity.TicketsConstants.Companion.KEY_TICKET_POSITION
import com.otoniel.androidrecycleview.ui.recycler.adapter.helper.callback.TicketTouchHelperCallback
import com.otoniel.androidrecycleview.ui.recycler.adapter.listener.OnItemClickListener
import kotlinx.android.synthetic.main.activity_main.*

class TicketListActivity : AppCompatActivity(), OnItemClickListener {

    lateinit var ticketsAdapter: TicketsAdapter
    lateinit var startForResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle(R.string.list_tickets_appbar)

        initAcitivityLauncher()
        createAdapter()
        handleRecyclerView()
        bindButton()
    }

    private fun bindButton() = bottomText.setOnClickListener {
        val intent = formIntent()
        navigateToTicketForm(intent)
    }

    private fun navigateToTicketForm(intent: Intent?) {
        startForResult.launch(intent)
    }

    private fun formIntent(): Intent =
        Intent(this@TicketListActivity, TicketFormActivity::class.java)

    private fun initAcitivityLauncher() {
        startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                handleResultDataFromTicketActivity(result)

            }
    }

    private fun handleResultDataFromTicketActivity(result: ActivityResult) {
        val intent = result.data
        val resultCode = result.resultCode
        if (intent != null && hasTicket(intent)) {
            if (resultCode == Activity.RESULT_OK) {
                val ticket: Ticket = getSerializableTicket(intent)
                if (isEditMode(intent)) {
                    val position = getIntPosition(intent)
                    if (position > -1) {
                        ticketsAdapter.editTicket(ticket, position)
                    }
                } else {
                    ticketsAdapter.addTicket(ticket)
                }
            }
        }
    }

    private fun isEditMode(intent: Intent) = intent.getBooleanExtra(EDIT_MODE, false)

    private fun getIntPosition(intent: Intent): Int =
        intent?.getIntExtra(KEY_TICKET_POSITION, -1)

    private fun getSerializableTicket(intent: Intent): Ticket =
        intent?.getSerializableExtra(KEY_INTENT_TICKET) as Ticket

    private fun hasTicket(intent: Intent) =
        intent?.hasExtra(KEY_INTENT_TICKET) == true

    private fun handleRecyclerView() {
        recyclerView.adapter = ticketsAdapter
        val callback = TicketTouchHelperCallback(ticketsAdapter);
        ItemTouchHelper(callback).attachToRecyclerView(recyclerView)
    }

    private fun createAdapter() {
        val dao = TicketDAO()
        for (i in 0..10) {
            dao.add(Ticket("title $i", "Subscription $i"))
        }
        ticketsAdapter = TicketsAdapter(dao.all(), this);
    }

    override fun onItemClick(ticket: Ticket, position: Int) {
        val intent = formIntent()
        intent.putExtra(KEY_INTENT_TICKET, ticket)
        intent.putExtra(KEY_TICKET_POSITION, position)
        navigateToTicketForm(intent)
    }
}