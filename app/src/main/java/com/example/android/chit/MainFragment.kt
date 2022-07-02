package com.example.android.chit

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.chit.adapter.OnUserClickListener
import com.example.android.chit.adapter.UserAdapter
import com.example.android.chit.R.*
import com.example.android.chit.databinding.FragmentMainBinding
import com.example.android.chit.model.User
import com.example.android.chit.utilis.PreferenceManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var mDbRef: DatabaseReference
    private lateinit var userList: ArrayList<User>
    private lateinit var adapter: UserAdapter
    private lateinit var mAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layout.fragment_main, container, false)
        mDbRef = FirebaseDatabase.getInstance().reference
        mAuth = FirebaseAuth.getInstance()
        userList = ArrayList()

        adapter = UserAdapter(OnUserClickListener { user ->
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToChatFragment(
                    user.name!!,
                    user.uid!!
                )
            )
        }, userList)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter


        setRecyclerView()
        setHasOptionsMenu(true)

        return binding.root
    }


    private fun setRecyclerView() {
        mDbRef.child("userList").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                userList.clear()
                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue(User::class.java)
                    if (mAuth.currentUser?.uid != currentUser?.uid) {
                        if (currentUser != null) {
                            userList.add(currentUser)
                        }

                    }
                    adapter.notifyDataSetChanged()
                }


            }


            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.log_out) {
            // logic for log Out
            mAuth.signOut()
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToLogInFragment())
            return true
        }
        return true
    }
}