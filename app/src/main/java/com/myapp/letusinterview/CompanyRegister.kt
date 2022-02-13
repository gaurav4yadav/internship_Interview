package com.myapp.letusinterview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CompanyRegister : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore
    var database: FirebaseFirestore?=null
    private lateinit var progressBar:ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_register)
        progressBar=findViewById<ProgressBar>(R.id.progressBar1)
        auth = Firebase.auth
        database = FirebaseFirestore.getInstance()
    }
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            updateUI(currentUser);
        }
    }



    private fun updateUI(firebaseUser: FirebaseUser?) {
        if (firebaseUser != null) {
            val intent = Intent(this, CampanyDashboard::class.java)
            startActivity(intent)
        }
        else
        {
            progressBar.visibility = View.GONE
        }

    }

    fun firelogincom(view: View) {

        val editText1 = findViewById<EditText>(R.id.ed1)
        val refid = editText1.text.toString()

        val editText2 = findViewById<EditText>(R.id.ed2)
        val company= editText2.text.toString()

        val editText3 = findViewById<EditText>(R.id.ed3)
        val phone = editText3.text.toString()


        val editText4 = findViewById<EditText>(R.id.ed4)
        val hr =        editText4.text.toString()

        val editText5 = findViewById<EditText>(R.id.ed5)
        val email =     editText5.text.toString()

        val editText6 =     findViewById<EditText>(R.id.ed6)
        val password =      editText6.text.toString()









        if(email.isEmpty()) {
            editText5.requestFocus();
            editText5.setError("FIELD CANNOT BE EMPTY")
        }
        else if(password.length<8 )
        {
            editText6.requestFocus();
            editText6.error = "length should be larger than 8 digits"
        }
        else   if(refid.isEmpty())
        {
            editText1.requestFocus();
            editText1.error="FIELD CANNOT BE EMPTY"
        }
        else   if(company.isEmpty())
        {
            editText2.requestFocus();
            editText2.error="FIELD CANNOT BE EMPTY"
        }
        else   if(hr.isEmpty())
        {
            editText4.requestFocus();
            editText4.error="FIELD CANNOT BE EMPTY"
        }
        else  if(phone.isEmpty() || phone.length<10) {
            editText3.requestFocus();
            editText3.setError("Phone no. must be 10 digit long ")
        }

        else {
            progressBar.visibility = View.VISIBLE

            database?.collection("company")?.whereEqualTo(  "refernceid", refid)?.get()
                ?.addOnSuccessListener { document ->

                    if (document.size() > 0) {

                        editText1.requestFocus()
                        editText1.error = "ref. ID is already used"
                        Toast.makeText(
                            baseContext,
                            "Reference ID is already taken by some other recruiter, try another",
                            LENGTH_LONG
                        ).show()
                    }

            else {


                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    // Sign in success, update UI with the signed-in user's information
                                    val data = hashMapOf(
                                        "refernceid" to refid,
                                        "companydb" to company,
                                        "phonedb" to phone,
                                        "hrdb" to hr,
                                        "emaildb" to email,
                                        "passworddb" to password,


                                        )
//

                                    db.collection("company").document(email)
                                        .set(data)
                                        .addOnSuccessListener {
                                            //   Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
                                            Toast.makeText(
                                                baseContext, "Company  Registered Successfully",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            val user = auth.currentUser
                                            updateUI(user)

                                        }
                                        .addOnFailureListener {
                                            //Log.w(TAG, "Error adding document", e)
                                            Toast.makeText(
                                                baseContext, "scomething went wrong ",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            Toast.makeText(
                                                baseContext,
                                                "Authentication failed.",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            updateUI(null)

                                            // Log.d(TAG, "createUserWithEmail:success")
//                            val user = auth.currentUser
//                            updateUI(user)
                                        }
                                } else {
                                    // If sign in fails, display a message to the user.
                                    //  Log.w(TAG, "createUserWithEmail:failure", task.exception)
                                    Toast.makeText(
                                        baseContext,
                                        "Authentication failed.",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                    updateUI(null)
                                }
                            }
                    }
                }
                }







    }

}