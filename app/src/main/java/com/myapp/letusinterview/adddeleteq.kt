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

class adddeleteq : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore
    var database: FirebaseFirestore? = null
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adddeleteq)


        progressBar = findViewById<ProgressBar>(R.id.progressBar1)
        auth = Firebase.auth
        database = FirebaseFirestore.getInstance()
    }


    fun deleteme(view: View) {

        val editText1 = findViewById<EditText>(R.id.ed1)
        val quid = editText1.text.toString()

        if(quid.isEmpty()) {
            editText1.requestFocus()
            editText1.error = "FIELD CANNOT BE EMPTY"
        }
        else
        {
            progressBar.visibility = View.VISIBLE
            var email = ""
            val useras = auth.currentUser
            if (useras !== null) {

                email = useras.email.toString()
            }

        db.collection("questions").document(email).collection(email).document(quid)
            .delete()
            .addOnSuccessListener { Toast.makeText(baseContext,"DELETED",Toast.LENGTH_LONG).show()
                progressBar.visibility = View.GONE}
            .addOnFailureListener { e -> Toast.makeText(baseContext,"Failed to delete",Toast.LENGTH_LONG).show()
                progressBar.visibility = View.GONE
            }
    }
}

}