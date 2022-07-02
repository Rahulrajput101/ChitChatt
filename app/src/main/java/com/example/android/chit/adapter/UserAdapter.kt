package com.example.android.chit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.chit.databinding.UserLayoutBinding
import com.example.android.chit.model.User

class UserAdapter(  private val clickListener: OnUserClickListener,private val  user: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        return UserViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

       holder.bind(user[position],clickListener)


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

        fun bind(user: User, clickListener: OnUserClickListener){
            binding.textView.text = user.name
            binding.user = user
            binding.userClickListener = clickListener

        }

    }

}

class OnUserClickListener(val clickListener : (user :User) -> Unit){
    fun onClick(user: User) = clickListener(user)
}