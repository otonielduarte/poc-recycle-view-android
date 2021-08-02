package com.otoniel.androidrecycleview.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.otoniel.androidrecycleview.R
import com.otoniel.androidrecycleview.model.Ticket
import kotlinx.android.synthetic.main.activity_new_ticket.*

class NewTicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_ticket)
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
        intent.putExtra(TicketsConstants.KEY_INTENT_TICKET, ticket)
        setResult(RESULT_OK, intent)
    }

    private fun getTicket(): Ticket {
        var strTitle = ticketTitle.text.toString()
        var strSubtitle = ticketSubtitle.text.toString()
        return Ticket(strTitle, strSubtitle)
    }
}