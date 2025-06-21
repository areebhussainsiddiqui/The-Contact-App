package com.ahs.contactapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahs.contactapp.databinding.ActivityMainBinding
import com.ahs.contactapp.room.entity.Contact
import com.ahs.contactapp.utils.ContactAdapter
import com.ahs.contactapp.utils.ItemClick
import com.ahs.contactapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.etAddContactBtn.setOnClickListener() {
            val name = binding.etName.text.toString().trim()
            val phone = binding.etPhoneNumber.text.toString().trim()

            if (name.isEmpty()) {
                binding.etName.error = "Enter Name"
            } else {
                binding.etName.error = null
            }
            if (phone.isEmpty()) {
                binding.etPhoneNumber.error = "Enter Phone Number"

            } else {
                binding.etPhoneNumber.error = null
            }

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                mainViewModel.addContact(Contact(name = name, number = phone))
                binding.etName.text.clear()
                binding.etPhoneNumber.text.clear()
            }
        }

        val adapter = ContactAdapter(object : ItemClick {
            override fun delete(contact: Contact) {
                mainViewModel.deleteContact(contact)
            }
        })

        mainViewModel.getContacts.observe(this) { list ->
            adapter.differ.submitList(list)

        }

        binding.apply {
            recyclerView.layoutManager =
                LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }
    }
}