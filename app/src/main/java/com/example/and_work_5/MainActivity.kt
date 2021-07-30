package com.example.and_work_5

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(),
    ContactListFragment.OnContactItemClickListener,
    ContactDetailsFragment.OnCloseListener {

    private var detailsContainer: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        detailsContainer = findViewById(R.id.details_container)
        if (savedInstanceState == null) {
            updateContactListFragment()
        }
    }

    override fun onContactListItemClick(position: Int) {
        createContactDetailsFragment(position)
    }

    override fun onCloseDetails() {
        supportFragmentManager.popBackStack()
        detailsContainer?.let { updateContactListFragment() }
    }

    private fun updateContactListFragment() {
        supportFragmentManager.beginTransaction().run {
            val contactListFragment = ContactListFragment.newInstance()
            replace(R.id.fragment_container, contactListFragment, ContactListFragment.TAG)
            commit()
        }
    }

    private fun createContactDetailsFragment(position: Int) {
        var containerId = R.id.fragment_container
        detailsContainer?.let { containerId = R.id.details_container }
        supportFragmentManager.beginTransaction().run {
            val contactDetailsFragment = ContactDetailsFragment.newInstance(position)
            replace(containerId, contactDetailsFragment, ContactDetailsFragment.TAG)
            addToBackStack(ContactDetailsFragment.TAG)
            commit()
        }
    }
}