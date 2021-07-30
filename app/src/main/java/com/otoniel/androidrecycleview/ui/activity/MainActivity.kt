package com.otoniel.androidrecycleview.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.otoniel.androidrecycleview.R
import com.otoniel.androidrecycleview.dao.TicketDAO
import com.otoniel.androidrecycleview.model.Ticket
import com.otoniel.androidrecycleview.ui.adapter.TicketsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle(R.string.app_name)

        handleList()
    }

    private fun handleList() {
        val dao = TicketDAO()
        dao.add(Ticket("First title", "This is a simple description test"))
        listView.adapter = TicketsAdapter(this, dao.all())
    }
}