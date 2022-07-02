package com.example.android.chit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.chit.adapter.ChatAdapter
import com.example.android.chit.adapter.OnUserClickListener
import com.example.android.chit.adapter.UserAdapter
import com.example.android.chit.databinding.FragmentChatBinding
import com.example.android.chit.model.Message
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ChatFragment : Fragment() {

    private lateinit var binding : FragmentChatBinding
    private lateinit var messageList : ArrayList<Message>
    private lateinit var mDbRef : DatabaseReference
    private lateinit var chatAdapter : ChatAdapter

    private lateinit var mAuth : FirebaseAuth

    var receiverRoom : String? = null
    var senderRoom : String? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false)


        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().reference
        messageList = ArrayList()

        val args = ChatFragmentArgs.fromBundle(requireArguments())
        val name = args.userName
        val receiverUid = args.uid
        val senderUid = mAuth.currentUser?.uid
        (activity as AppCompatActivity).supportActionBar?.title = name

       receiverRoom = senderUid + receiverUid
       senderRoom = receiverUid + senderUid

        chatAdapter = ChatAdapter(messageList)
        binding.recyclerViewchat.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewchat.adapter = chatAdapter

        mDbRef.child("chats").child(senderRoom!!).child("message")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    messageList.clear()

                    for(postSnapshot in snapshot.children){
                        val message = postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)
                    }
                    chatAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        binding.framelayut.setOnClickListener{
            val message = binding.chatBox.text.toString()
            val messageObject = Message(message,senderUid)

            mDbRef.child("chats").child(senderRoom!!).child("message").push()
                .setValue(messageObject).addOnSuccessListener {
                    mDbRef.child("chats").child(receiverRoom!!).child("message").push()
                        .setValue(messageObject)
                }
            binding.chatBox.setText("")
        }


        return binding.root
    }


}


