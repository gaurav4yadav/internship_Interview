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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class addq : AppCompatActivity() {
    lateinit var progressBar:ProgressBar
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore
    var database: FirebaseFirestore?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addq)
        progressBar=findViewById<ProgressBar>(R.id.progressBar1)
        auth = Firebase.auth
        database = FirebaseFirestore.getInstance()






    }

    fun addquestionfund(view: View) {



        val idx = findViewById<EditText>(R.id.id)
        val idu = idx.text.toString()

        val quest = findViewById<EditText>(R.id.ques)
        val quesu= quest.text.toString()


    if (idu.isEmpty()) {
        idx.requestFocus()
        idx.error = "FIELD CANNOT BE EMPTY"
    }
    else if (quesu.isEmpty())
    {
       quest.requestFocus()
       quest.error = "FIELD CANNOT BE EMPTY"
    }
    else
    {

        progressBar.visibility = View.VISIBLE
        var email = ""
        val useras = auth.currentUser
        if (useras !== null) {

            email = useras.email.toString()
        }
        var   ref:String=""
        database!!.collection("company").document(email.toString().trim()).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    ref = document.getString("refernceid").toString()




        val data = hashMapOf(

             "ques" to quesu,
             "ref"  to ref,
             "uid" to idu
            )





        db.collection("questions").document(email).collection(email).document(idu)
            .set(data)
            .addOnSuccessListener {
                //   Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
                Toast.makeText(
                    baseContext, "Question Added Successfully",
                    Toast.LENGTH_SHORT
                ).show()
                val user = auth.currentUser
                progressBar.visibility = View.GONE

            }
            .addOnFailureListener {
                //Log.w(TAG, "Error adding document", e)
                Toast.makeText(
                    baseContext, "something went wrong ",
                    Toast.LENGTH_SHORT
                ).show()
                Toast.makeText(
                    baseContext,
                    "Authentication failed.",
                    Toast.LENGTH_SHORT
                ).show()
              //  updateUI(null)
                progressBar.visibility = View.GONE
                // Log.d(TAG, "createUserWithEmail:success")
//                            val user = auth.currentUser
//                            updateUI(user)
            }
    }
}
    }
    }


}








