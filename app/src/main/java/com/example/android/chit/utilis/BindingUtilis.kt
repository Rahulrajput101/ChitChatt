//package com.example.android.chit.utilis
//
//import androidx.databinding.BindingAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.android.chit.Adapter.UserAdapter
//import com.example.android.chit.model.User
//
//class BindingUtilis {
//
//    // select user page
//    @BindingAdapter("addUsersList")
//    fun addUsersList(recyclerView: RecyclerView, data: List<User>?){
//        val adapter = recyclerView.adapter as UserAdapter
//        if (data != null) {
//            adapter.setData(data)
//        }
//    }
//
//}