package com.myapp.letusinterview


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference


class viewcandidatelist : AppCompatActivity() {


    private val storagePostPicRef: StorageReference? = null
    // Create a storage reference from our app
   // val mountainsRef = StorageReference("f")

    private lateinit var recyclerView: RecyclerView
    private lateinit var userArayList: ArrayList<myclass1>
    private lateinit var myAdapter: myadapter1
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore
    var database: FirebaseFirestore?=null
    var re: String = ""
//    lateinit var ok: myclass1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewcandidatelist)
        auth = Firebase.auth
        database = FirebaseFirestore.getInstance()
        recyclerView = findViewById(R.id.recyclerview1)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        userArayList = arrayListOf()

        myAdapter = myadapter1(userArayList)
        recyclerView.adapter = myAdapter

        var myemail = ""
        val user = Firebase.auth.currentUser
        user?.let {

            myemail = user.email.toString()
        }



        database = FirebaseFirestore.getInstance()
        database!!.collection("company").document(myemail.toString().trim()).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    re = document.getString("refernceid").toString()
                    Toast.makeText(baseContext,"{$re}",Toast.LENGTH_SHORT).show()
                    database = FirebaseFirestore.getInstance()
                database!!.collection("candidate").whereEqualTo("refernceid", re.toString()).get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {

                            var ok1: myclass1? = document.toObject(myclass1::class.java)
                            //Toast.makeText(baseContext," data is {${document.data["phonedb"]}}",Toast.LENGTH_SHORT).show()

                            ok1!!.candname = document.getString("namedb").toString()
                           // Toast.makeText(baseContext," data is  we{${ok1.candname}}",Toast.LENGTH_LONG).show()

                            ok1?.mob = document.getString("phonedb").toString()
                            ok1?.course = document.getString("coursedb").toString()
                            ok1?.email = document.getString("emaildb").toString()

        //                    var yy=ok1.candname
//Toast.makeText(baseContext,"{$yy}",Toast.LENGTH_SHORT).show()
                userArayList.add(ok1!!)


                myAdapter.notifyDataSetChanged()

            }
    }

    }}}}

//        ok?.candname = "asd"
//        ok?.email = "asdfasdfa"
//        ok?.let { userArayList.add(it) }
//        Toast.makeText(baseContext,"{$ok}", Toast.LENGTH_SHORT).show()



