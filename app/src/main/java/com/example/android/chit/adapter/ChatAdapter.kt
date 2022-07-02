package com.example.android.chit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.android.chit.R
import com.example.android.chit.databinding.ItemContainerReceiveBinding
import com.example.android.chit.databinding.ItemContainerSentBinding
import com.example.android.chit.model.Message
import com.example.android.chit.utilis.Constants
import com.example.android.chit.utilis.Constants.VIEW_TYPE_RECEIVED
import com.example.android.chit.utilis.Constants.VIEW_TYPE_SENT
import com.google.firebase.auth.FirebaseAuth

class ChatAdapter(private val message: ArrayList<Message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
      val currentMessage = message[position]

        return if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            VIEW_TYPE_SENT
        }else{
            VIEW_TYPE_RECEIVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == VIEW_TYPE_SENT){
            SentViewHolder.from(parent)
        }else{
            ReceiveViewHolder.from(parent)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_SENT) {
            (holder as SentViewHolder).bind(message[position],position)
        }
        else {
            (holder as ReceiveViewHolder).bind(message[position],position)
        }
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

        fun bind(message: Message,position: Int) {
            binding.senttxt.text = message.message
            binding.senttxt.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.purplebackground))
            binding.executePendingBindings()


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

        fun bind(message: Message,position: Int) {
            binding.receivetxt.text = message.message
            binding.receivetxt.setBackgroundColor(ContextCompat.getColor(binding.root.context,R.color.pinkbackground))
            binding.executePendingBindings()
        }
    }
}