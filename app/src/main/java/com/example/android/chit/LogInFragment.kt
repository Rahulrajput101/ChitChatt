package com.example.android.chit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.android.chit.databinding.FragmentLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


class LogInFragment : Fragment() {

    private lateinit var binding : FragmentLogInBinding
    private lateinit var mAuth : FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_log_in, container, false)
        mAuth = FirebaseAuth.getInstance()

        signUpClick()

        binding.loginButton.setOnClickListener {
           val  email = binding.passwordEdit.text.toString()
            val password = binding.emailEdit.text.toString()
            login(email,password)
        }



        return binding.root
    }

    private fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToMainFragment())
                } else {

                    Toast.makeText(context, "Some error occured", Toast.LENGTH_SHORT).show()

                }

            }
        }


    private fun signUpClick() {

        binding.signup.setOnClickListener { view : View->
            view.findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToSignUpFragment())
        }

    }

    }






