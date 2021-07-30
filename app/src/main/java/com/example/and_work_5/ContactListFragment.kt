package com.example.and_work_5

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment

class ContactListFragment : ListFragment() {

    private lateinit var onContactItemClickListener: OnContactItemClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onContactItemClickListener = context as OnContactItemClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val contactNames: MutableList<String> = mutableListOf()
        for (contact in Contact.CONTACTS) {
            contactNames.add(contact.toString())
        }
        val adapter = ArrayAdapter(
            inflater.context, android.R.layout.simple_list_item_1, contactNames
        )
        listAdapter = adapter

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        onContactItemClickListener.onContactListItemClick(position)
    }

    interface OnContactItemClickListener {
        fun onContactListItemClick(position: Int)
    }

    companion object {
        const val TAG = "ContactListFragment"

        fun newInstance() = ContactListFragment()
    }
}