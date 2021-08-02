package com.otoniel.androidrecycleview.ui.activity

import android.app.Activity
import android.content.Intent
import com.otoniel.androidrecycleview.ui.recycler.adapter.TicketsAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.otoniel.androidrecycleview.R
import com.otoniel.androidrecycleview.dao.TicketDAO
import com.otoniel.androidrecycleview.model.Ticket
import kotlinx.android.synthetic.main.activity_main.*

class TicketListActivity : AppCompatActivity() {

    lateinit var ticketsAdapter: TicketsAdapter
    lateinit var startForResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle(R.string.app_name)

        initAcitivityLauncher()
        createAdapter(TicketDAO().all())
        handleRecyclerView()
        bindButton()
    }

    private fun bindButton() = bottomText.setOnClickListener {
        navigateToNewTicket()
    }

    private fun navigateToNewTicket() {
        val intent = Intent(this@TicketListActivity, NewTicketActivity::class.java)
        startForResult.launch(intent)
    }

    private fun initAcitivityLauncher() {
        startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult -> handleResultDataFromTicketActivity(result)

        }
    }

    private fun handleResultDataFromTicketActivity(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            if(intent?.hasExtra(TicketsConstants.KEY_INTENT_TICKET) == true) {
                val ticket: Ticket = intent.getSerializableExtra(TicketsConstants.KEY_INTENT_TICKET) as Ticket
                ticketsAdapter.addTicket(ticket)
            }
        }
    }

    private fun handleRecyclerView() {

        recyclerView.adapter = ticketsAdapter
    }

    private fun createAdapter(all: ArrayList<Ticket>) {
        ticketsAdapter = TicketsAdapter(all);
    }
}