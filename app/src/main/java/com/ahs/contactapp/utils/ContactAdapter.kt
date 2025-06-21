package com.ahs.contactapp.utils


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahs.contactapp.databinding.ItemContactBinding
import com.ahs.contactapp.room.entity.Contact


class ContactAdapter(private val itemClick: ItemClick) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    class ContactViewHolder(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    private val differCallback = object : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return (oldItem.id == newItem.id &&
                    oldItem.name == newItem.name &&
                    oldItem.number == newItem.number)
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = differ.currentList[position]
        holder.binding.contact = contact

        holder.binding.btnDelete.setOnClickListener {
            itemClick.delete(contact)
        }
    }
}

interface ItemClick {
    fun delete(contact: Contact)
}
