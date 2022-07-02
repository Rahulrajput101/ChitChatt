package com.example.android.chit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.android.chit.databinding.FragmentLogInBinding
import com.example.android.chit.model.User
import com.example.android.chit.utilis.PreferenceManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var prefernce: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        prefernce = PreferenceManager(requireContext())
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_log_in, container, false)
        mAuth = FirebaseAuth.getInstance()

        signUpClick()

        binding.loginButton.setOnClickListener {
            val email = binding.passwordEdit.text.toString()
            val password = binding.emailEdit.text.toString()
            if (checking()) {
                login(email, password)
            } else {
                Toast.makeText(requireContext(), " fill the fields", Toast.LENGTH_LONG).show()
            }

        }
        return binding.root
    }

    private fun checking(): Boolean {
        if (binding.passwordEdit.text.toString().isNotEmpty()
            && binding.emailEdit.text.toString().isNotEmpty()
        ) {
            return true
        }
        return false

    }

    private fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { it
                if (it.isSuccessful) {

                    findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToMainFragment())
                } else {
                    Toast.makeText(context, "Some error occured", Toast.LENGTH_SHORT).show()
                }
            }
    }


    private fun signUpClick() {
        binding.signUpText.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(LogInFragmentDirections.actionLogInFragmentToSignUpFragment())
        }
    }

}






