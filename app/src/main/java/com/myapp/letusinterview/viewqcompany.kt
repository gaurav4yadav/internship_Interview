package com.myapp.letusinterview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class viewqcompany : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var db = Firebase.firestore
    var database: FirebaseFirestore? = null
    var re: String = ""

    lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var userArayList: ArrayList<myclass>
    private lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewqcompany)

        auth = Firebase.auth
        database = FirebaseFirestore.getInstance()

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        userArayList = arrayListOf()
        //Toast.makeText(baseContext, "ok",Toast.LENGTH_SHORT).show()

        myAdapter = MyAdapter(userArayList)
        recyclerView.adapter = myAdapter

        var myemail = ""
        val user = Firebase.auth.currentUser
        user?.let {

            myemail = user.email.toString()
        }
        db = FirebaseFirestore.getInstance()

       // progressBar.visibility = View.VISIBLE
        var email = ""
        val useras = auth.currentUser
        if (useras !== null) {

            email = useras.email.toString()
        }
       // Toast.makeText(baseContext, " remailf {$email}",Toast.LENGTH_SHORT).show()
        var   ref:String=""
        database!!.collection("company").document(email.toString().trim()).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    ref = document.getString("refernceid").toString()
Toast.makeText(baseContext, "Please Wait ,\nFetching Question ",Toast.LENGTH_SHORT).show()
                    database = FirebaseFirestore.getInstance()
                    database!!.collection("questions").document(email).collection(email).whereEqualTo("ref",ref).get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                var ok1: myclass? = document.toObject(myclass::class.java)
                                ok1!!.q = document.getString("ques").toString()
                                    ok1!!.u=document.getString("uid").toString()
//Toast.makeText(baseContext," dtata{${ok1?.q}}",Toast.LENGTH_SHORT).show()

                                userArayList.add(ok1!!)
                                myAdapter.notifyDataSetChanged()


                            }

                        }
                }
            }
//        var we:myclass=myclass()
//        we.q="Asdf"
//        we.u="asdfsd"
//    userArayList.add(we)
//        var we1:myclass=myclass()
//        we1.q="Asdf"
//        we1.u="asdfsd"
//        userArayList.add(we1)

    }
}
