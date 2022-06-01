package com.example.android.chit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.chit.Adapter.UserAdapter
import com.example.android.chit.databinding.FragmentMainBinding
import com.example.android.chit.model.User
import com.google.firebase.database.*

class MainFragment : Fragment() {
   private lateinit var binding : FragmentMainBinding
   private lateinit var mDbRef : DatabaseReference
    private lateinit var  userList: ArrayList<User>
    private lateinit var adapter : UserAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        mDbRef = FirebaseDatabase.getInstance().reference
        userList = ArrayList()
        adapter = UserAdapter(requireContext(), userList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        setRecyclerView()


        return binding.root
    }

    private fun setRecyclerView() {
        mDbRef.child("user").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                userList.clear()
                for(postSnapshot in snapshot.children){
                    val currentUser = postSnapshot.getValue(User::class.java)
                    if (currentUser != null) {
                        userList.add(currentUser)
                    }
                    adapter.notifyDataSetChanged()
                }




            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }


}