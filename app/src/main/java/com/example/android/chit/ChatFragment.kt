package com.example.android.chit

import android.content.Intent
import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.chit.Adapter.ChatAdapter
import com.example.android.chit.databinding.FragmentChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ChatFragment : Fragment() {

    private lateinit var binding : FragmentChatBinding
//    private lateinit var message : ArrayList<Message>
//    private lateinit var mDbRef : DatabaseReference
//    private lateinit var chatAdapter : ChatAdapter
//    private lateinit var mAuth : FirebaseAuth
//
//    val receiverRoom : String? = null
//    val senderRoom : String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false)





//        mAuth = FirebaseAuth.getInstance()
//        mDbRef = FirebaseDatabase.getInstance().reference
//        message = ArrayList()
//
//        chatAdapter = ChatAdapter()
//        binding.recyclerViewchat.layoutManager = LinearLayoutManager(requireContext())
//        binding.recyclerViewchat.adapter = chatAdapter
//
//       // addMsg()


        return binding.root
    }

//    private fun addMsg() {
//        binding.framelayut.setOnClickListener{
//            val message = binding.chatBox.text.toString()
//            val messageObject = Message(message)
//        }
//    }


}