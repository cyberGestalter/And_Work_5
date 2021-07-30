package com.example.and_work_5

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactDetailsFragment() : Fragment() {

    private var contactId: Int = Int.MAX_VALUE
    private lateinit var onCloseListener: OnCloseListener

    constructor(position: Int) : this() {
        contactId = position
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onCloseListener = context as OnCloseListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            contactId = savedInstanceState.getInt(CONTACT_POSITION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_contact_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstNameView = view.findViewById<EditText>(R.id.first_name)
        val lastNameView = view.findViewById<EditText>(R.id.last_name)
        val phoneView = view.findViewById<EditText>(R.id.phone)

        val contact = if (contactId < Contact.CONTACTS.size) {
            Contact.CONTACTS[contactId]
        } else {
            null
        }

        firstNameView?.setText(contact?.firstName)
        lastNameView?.setText(contact?.lastName)
        phoneView?.setText(contact?.phone)

        val saveButton = view.findViewById<FloatingActionButton>(R.id.save_button)
        saveButton.setOnClickListener {
            val editedFirstName = firstNameView?.text.toString()
            val editedLastName = lastNameView?.text.toString()
            val editedPhone = phoneView?.text.toString()

            contact?.firstName = editedFirstName
            contact?.lastName = editedLastName
            contact?.phone = editedPhone

            onCloseListener.onCloseDetails()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CONTACT_POSITION, contactId)
    }

    interface OnCloseListener {
        fun onCloseDetails()
    }

    companion object {
        const val TAG = "ContactDetailsFragment"
        const val CONTACT_POSITION = "contact_position"

        fun newInstance(position: Int) = ContactDetailsFragment(position)
    }
}