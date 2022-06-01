package com.example.android.chit.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.chit.databinding.UserLayoutBinding
import com.example.android.chit.model.User

class UserAdapter(val context: Context ,  private val  user: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
       holder.bind(user[position])
    }

    override fun getItemCount(): Int {
        return user.size
    }

    class UserViewHolder( private val binding: UserLayoutBinding): RecyclerView.ViewHolder(binding.root){

        companion object{
            fun from(parent: ViewGroup) : UserViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val binding = UserLayoutBinding.inflate(inflater, parent, false )
                return UserViewHolder(binding)

            }

        }

        fun bind(user: User){
            binding.textView.text = user.name


        }

    }

}