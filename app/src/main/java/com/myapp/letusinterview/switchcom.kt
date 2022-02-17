package com.myapp.letusinterview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class switchcom : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    private var db = Firebase.firestore
    var database: FirebaseFirestore? = null
    private lateinit var progressBar: ProgressBar
    var ref: String = ""
    var names: String = ""
    var mob: String = ""
    var hr: String = ""
    var em: String = ""
    var myemail: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switchcom)
        progressBar=findViewById(R.id.progressBar1)
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()
        database = FirebaseFirestore.getInstance()


    }

    fun swicthfun(view: View) {
        val v = findViewById<EditText>(R.id.edtext1)
        var newref = v.text.toString()



        if (newref.isEmpty()) {
            v.requestFocus();
            v.setError("FIELD CANNOT BE EMPTY")
        } else {

            progressBar.visibility = View.VISIBLE
            database?.collection("company")?.whereEqualTo("refernceid", newref)?.get()
                ?.addOnSuccessListener { document ->

                    if (document.size() <= 0) {

                        v.requestFocus()
                        v.error = "Invalid company reference ID"
                        progressBar.visibility = View.GONE


                    } else {

                        val user = Firebase.auth.currentUser
                        user?.let {

                            myemail = user.email.toString()
                        }

                        database!!.collection("candidate").document(myemail.toString().trim()).get()
                            .addOnSuccessListener { document ->
                                if (document != null) {
                                    ref = document.getString("refernceid").toString()
                                    mob = document.getString("phonedb").toString()
                                    names = document.getString("namedb").toString()
                                    hr = document.getString("coursedb").toString()
                                    em = document.getString("emaildb").toString()

                                    val data = hashMapOf(
                                        "refernceid" to newref,
                                        "namedb" to names,
                                        "phonedb" to mob,
                                        "coursedb" to hr,
                                        "emaildb" to em
                                    )

                                    db.collection("candidate").document(myemail.toString())
                                        .set(data)
                                        .addOnSuccessListener {
                                            //   Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
                                            Toast.makeText(
                                                baseContext, "Candidate Switched Successfully",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            progressBar.visibility = View.GONE


                                        }
                                        .addOnFailureListener {
                                            //Log.w(TAG, "Error adding document", e)
                                            Toast.makeText(
                                                baseContext, "scomething went wrong ",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            progressBar.visibility = View.GONE

                                        }

                                }
                            }

                    }

                                }
                            }




    }

}