package com.otoniel.androidrecycleview.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.otoniel.androidrecycleview.R
import com.otoniel.androidrecycleview.model.Ticket
import com.otoniel.androidrecycleview.ui.activity.TicketsConstants.Companion.EDIT_MODE
import com.otoniel.androidrecycleview.ui.activity.TicketsConstants.Companion.KEY_INTENT_TICKET
import com.otoniel.androidrecycleview.ui.activity.TicketsConstants.Companion.KEY_TICKET_POSITION
import kotlinx.android.synthetic.main.activity_new_ticket.*

class TicketFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_ticket)

        if (isEditMode()) {
            bindFields()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.new_ticket_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val index = item.itemId
        if (index == R.id.activity_new_ticket_menu_save) {
            handleClick()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleClick() {
        val ticket = getTicket()
        generateResult(ticket)
        finish()
    }

    private fun generateResult(ticket: Ticket) {
        val intent = Intent()
        intent.putExtra(KEY_INTENT_TICKET, ticket)
        intent.putExtra(EDIT_MODE, isEditMode())
        if (isEditMode()) {
            intent.putExtra(KEY_TICKET_POSITION, ticketPosition())
            setResult(Activity.RESULT_OK, intent)
        } else {
            setResult(Activity.RESULT_OK, intent)
        }
    }

    private fun getTicket(): Ticket {
        var strTitle = ticketTitle.text.toString()
        var strSubtitle = ticketSubtitle.text.toString()
        return Ticket(strTitle, strSubtitle)
    }

    private fun isEditMode() = intent.hasExtra(KEY_INTENT_TICKET)

    private fun ticketPosition(): Int = intent.getIntExtra(KEY_TICKET_POSITION, -1)

    private fun bindFields() {
        val ticket = intent.getSerializableExtra(KEY_INTENT_TICKET) as Ticket
        ticketTitle.text.append(ticket.title)
        ticketSubtitle.text.append(ticket.subtitle)
    }
}