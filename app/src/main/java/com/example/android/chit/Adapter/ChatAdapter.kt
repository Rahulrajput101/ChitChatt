package com.example.android.chit.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.chit.databinding.ItemContainerReceiveBinding
import com.example.android.chit.databinding.ItemContainerSentBinding
import com.example.android.chit.model.Message
import com.google.firebase.auth.FirebaseAuth

class ChatAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_SENT = 1
    private val VIEW_TYPE_RECEIVED = 2

    private var message: List<Message> = ArrayList()

    override fun getItemViewType(position: Int): Int {
      val currentMessage = message[position]

        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            return VIEW_TYPE_SENT
        }else{
            return VIEW_TYPE_RECEIVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == VIEW_TYPE_SENT){
            return SentViewHolder.from(parent)
        }else{
            return ReceiveViewHolder.from(parent)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_SENT) {
            (holder as SentViewHolder).bind(message)
        }
        (holder as ReceiveViewHolder).bind(message)
    }

    override fun getItemCount(): Int {
        return message.size
    }

    class SentViewHolder(val binding: ItemContainerSentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): SentViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemContainerSentBinding.inflate(inflater, parent, false)
                return SentViewHolder(binding)

            }


        }

        fun bind(message: List<Message>) {
            binding.senttxt.text = message.toString()


        }


    }

    class ReceiveViewHolder(val binding: ItemContainerReceiveBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ReceiveViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemContainerReceiveBinding.inflate(inflater, parent, false)
                return ReceiveViewHolder(binding)

            }

        }

        fun bind(message: List<Message>) {
            binding.receivetxt.text = message.toString()
        }
    }
}