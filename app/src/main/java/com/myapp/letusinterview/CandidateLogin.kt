package com.myapp.letusinterview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CandidateLogin : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var progressBar:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidate_login)
        progressBar=findViewById<ProgressBar>(R.id.progressBar1)
        auth = Firebase.auth
    }
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            updateUI(currentUser);

        }
    }

    fun regiserpagefun(view: View) {

        val intent = Intent(this, CandidateRegister::class.java)
        startActivity(intent)
    }

    fun forgetpwd(view: View) {
        val intent = Intent(this, ForgetPassword::class.java)
        startActivity(intent)

    }

    fun firelogincand(view: View) {
        val editText = findViewById<EditText>(R.id.ed1)
        val email = editText.text.toString()

        val editText1 = findViewById<EditText>(R.id.ed2)
        val password = editText1.text.toString()

        if (email.isEmpty()) {
            editText.requestFocus()
            editText.error = "FIELD CANNOT BE EMPTY"
        }
        else if (password.length < 8)
        {
            editText1.requestFocus()
            editText1.error = "length should be larger than 8 digits"
        }
        else
        {

            progressBar.visibility = View.VISIBLE
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        //  Log.d(TAG, "signInWithEmail:success")
                        Toast.makeText(
                            baseContext, " !!! Welcome !!!!.",
                            Toast.LENGTH_SHORT
                        ).show()
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        //  Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null)
                    }
                }
        }
    }
    private fun updateUI(firebaseUser: FirebaseUser?) {
        if(firebaseUser != null) {
            val intent= Intent(this,CandidateDashboard::class.java)
            startActivity(intent)
            finish();
        }
        else
        {
            progressBar.visibility = View.GONE
        }



    }
}