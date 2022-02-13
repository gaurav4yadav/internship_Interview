package com.myapp.letusinterview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CampanyDashboard : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore
    var database: FirebaseFirestore?=null
    private lateinit var progressBar: ProgressBar

    var ref:String=""
    var names:String=""
    var mob:String=""
    var hr:String=""
    var em:String=""


    var myemail:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campany_dashboard)
                auth = Firebase.auth

        database = FirebaseFirestore.getInstance()

        val user = Firebase.auth.currentUser
        user?.let {

            myemail = user.email.toString()
        }

        var cne= findViewById<EditText>(R.id.cn) as TextView
        var hre= findViewById<EditText>(R.id.hrnamel) as TextView
        var mobe= findViewById<EditText>(R.id.Phone) as TextView
        var refe= findViewById<EditText>(R.id.refl) as TextView
        var emails= findViewById<EditText>(R.id.emaill) as TextView


        database!!.collection("company").document(myemail.toString().trim()).get()
            .addOnSuccessListener { document->
                if(document!=null)
                {
                    ref=document.getString("refernceid").toString()
                    mob=document.getString("phonedb").toString()
                    names =document.getString("companydb").toString()
                    hr =document.getString("hrdb").toString()
                    em =document.getString("emaildb").toString()

                    hre.setText(hr)
                    cne.setText(names)
                    mobe.setText(mob)
                    refe.setText(ref)
                    emails.setText(em)

                }
            }


    }




    fun cameracall(view: View) {

        val intent = Intent(this, VideoCapture::class.java)
        startActivity(intent)
    }

    fun addquestionfun(view: View) {

    }

    fun delefun(view: View) {
        val intent = Intent(this, adddeleteq::class.java)
        startActivity(intent)
    }

    fun vq(view: View) {

        val intent = Intent(this, addq::class.java)
        startActivity(intent)
    }

    fun addquesfun(view: View) {
        val intent = Intent(this, addq::class.java)
        startActivity(intent)
    }

    fun updateact(view: View) {
        val intent = Intent(this, updateprofilecompany::class.java)
        startActivity(intent)
    }

    fun delete(view: View) {}
    fun viewquestions(view: View) {

        val intent = Intent(this, viewqcompany::class.java)
        startActivity(intent)

    }

    fun seelist(view: View) {
        val intent = Intent(this, viewcandidatelist::class.java)
        startActivity(intent)


    }
}