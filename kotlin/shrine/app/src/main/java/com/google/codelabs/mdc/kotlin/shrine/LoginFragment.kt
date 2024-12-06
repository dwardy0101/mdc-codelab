package com.google.codelabs.mdc.kotlin.shrine

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

/**
 * Fragment representing the login screen for Shrine.
 */
class LoginFragment : Fragment() {

    private var etPassword: TextInputEditText? = null
    private var etPasswordTextInputLayout: TextInputLayout? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.shr_login_fragment, container, false)

        etPassword = view.findViewById(R.id.password_edit_text)
        etPasswordTextInputLayout = view.findViewById(R.id.password_text_input)

        // Set an error if the password is less than 8 characters.
        view.findViewById<View>(R.id.next_button).setOnClickListener {
            if (!isPasswordValid(etPassword?.text)) {
                etPasswordTextInputLayout?.error = getString(R.string.shr_error_password)
            } else {
                // Clear the error.
                etPasswordTextInputLayout?.error = null
                (activity as NavigationHost).navigateTo(ProductGridFragment(), false)
            }
        }

        // Clear the error once more than 8 characters are typed.
        etPassword?.setOnKeyListener { _, _, _ ->
            if (isPasswordValid(etPassword?.text)) {
                // Clear the error.
                etPasswordTextInputLayout?.error = null
            }
            false
        }

        return view
    }

    // "isPasswordValid" from "Navigate to the next Fragment" section method goes here
    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }
}
