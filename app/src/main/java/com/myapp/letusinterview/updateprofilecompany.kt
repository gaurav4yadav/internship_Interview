package com.myapp.letusinterview

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class updateprofilecompany : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore
    var database: FirebaseFirestore?=null
    private lateinit var progressBar: ProgressBar


    var ref:String=""
    var names:String=""
    var mob:String=""
    var hr:String=""
    var em:String=""
    lateinit var bt:Button


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updateprofilecompany)

        progressBar = findViewById<ProgressBar>(R.id.progressBar1)
        auth = Firebase.auth
        database = FirebaseFirestore.getInstance()
        bt = findViewById<Button>(R.id.b)


        val editText1 = findViewById<EditText>(R.id.ed1)
        // val refid = editText1.text.toString()

        val editText2 = findViewById<EditText>(R.id.ed2)
        // val company= editText2.text.toString()

        val editText3 = findViewById<EditText>(R.id.ed3)
        ///  val phone = editText3.text.toString()


        val editText4 = findViewById<EditText>(R.id.ed4)
        // var hr =        editText4.text.toString()

        val editText5 = findViewById<EditText>(R.id.ed5)
        //val email =     editText5.text.toString()

        val editText6 = findViewById<EditText>(R.id.ed6)
        //  val password =      editText6.text.toString()

        val user = Firebase.auth.currentUser
        var myemail: String = ""
        user?.let {

            myemail = user.email.toString()
        }
        database!!.collection("company").document(myemail.toString().trim()).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    ref = document.getString("refernceid").toString()
                    mob = document.getString("phonedb").toString()
                    names = document.getString("companydb").toString()
                    hr = document.getString("hrdb").toString()
                    em = document.getString("emaildb").toString()

                    editText4.setText(hr)
                    editText2.setText(names)
                    editText3.setText(mob)
                    editText1.setText(ref)
                    editText5.setText(em)

                }
            }

//       bt.setOnClickListener(object:View.OnClickListener () {
//
//            @Override
//            public void onClick(View view) {
//
//
//    }

        bt.setOnClickListener(View.OnClickListener {

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










            if(email.isEmpty()) {
                editText5.requestFocus();
                editText5.setError("FIELD CANNOT BE EMPTY")
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

                database?.collection("customer")?.whereEqualTo(  "refernce id", refid)?.get()
                    ?.addOnSuccessListener { document ->

                        if (document.size() > 0) {

                            editText1.requestFocus()
                            editText1.error = "ref. ID is already used"
                            Toast.makeText(
                                baseContext,
                                "Reference ID is already taken by some other recruiter, try another",
                                Toast.LENGTH_LONG
                            ).show()
                            progressBar.visibility = View.GONE
                        }

                        else {



                                        // Sign in success, update UI with the signed-in user's information
                                        val data = hashMapOf(
                                            "refernceid" to refid,
                                            "companydb" to company,
                                            "phonedb" to phone,
                                            "hrdb" to hr,
                                            "emaildb" to email,



                                            )

                                        db.collection("company").document(email)
                                            .set(data)
                                            .addOnSuccessListener {
                                                //   Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
                                                Toast.makeText(
                                                    baseContext, "Company  Registered Successfully",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                val user = auth.currentUser
                                                progressBar.visibility = View.GONE

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
                                                progressBar.visibility = View.GONE



                                            }
                                    }
                        }
                    }
            })
    }

}


