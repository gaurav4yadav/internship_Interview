package com.myapp.letusinterview;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.okhttp.internal.DiskLruCache;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import kotlin.Result;

public class VideoCapture extends AppCompatActivity {

    private FirebaseAuth fbAuth;
    private FirebaseAuth.AuthStateListener authListener;
    String  timestamp;
    Uri uri;
    int  VIDEO_CAPTURE=1;
    private  StorageReference storagePostPicRef = null;String ok="";
    ProgressBar progressBar;
    String uploadString="";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String ref="";
    String names= "";
    String mob= "";
    String hr= "";
    String em  = "";
    String myemail="";
    //StorageReference storageRef = storage.getReference();

    public void onCreate(Bundle savedInstanceState) {

       // Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.javawali);
        Button recordButton = (Button) findViewById(R.id.button2);

       progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        fbAuth = FirebaseAuth.getInstance();



                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (user != null) {
                    uploadString = user.getEmail().toString();
                    Toast.makeText(getBaseContext(),"emailo "+uploadString,Toast.LENGTH_LONG).show();
                }

        Toast.makeText(getBaseContext(),"emailo "+uploadString,Toast.LENGTH_LONG).show();

                    recordButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                timestamp = new SimpleDateFormat("MM-dd-yyyy_HH-mm-ss aa").format(Calendar.getInstance().getTime());
//                File filepath = Environment.getExternalStorageDirectory();
//                File dir = new File(filepath.getAbsolutePath()+ "/samplevideofolder/");
//                dir.mkdirs();
//                File mediaFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/samplevideofolder/Video_"+timestamp+".mp4");
//                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//                Uri fileUri = Uri.fromFile(mediaFile);

               // String Storage =Environment.getExternalStorageDirectory().getAbsolutePath().toString();
              String path =Environment.getExternalStorageDirectory().getAbsolutePath() + "/letusinterview/V"+timestamp+".mp4";


                File file=new File(path);
             //   if(!file.exists())
             //   file.mkdirs();


               // Toast.makeText(getApplicationContext(), uri2.toString(), Toast.LENGTH_LONG).show();
                if(Build.VERSION.SDK_INT <24){
                    uri=Uri.fromFile(file);

                }
                else {
                    uri=Uri.parse(file.getPath());
                }
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,0);
                intent.putExtra(MediaStore.Video.Media.RESOLUTION,0);

                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 450);

                startActivityForResult(intent, VIDEO_CAPTURE);
            }
        });

    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getBaseContext(),"Please wait ,It may take 15 minutes or more ,uploading is in progress",Toast.LENGTH_LONG).show();

                progressBar.setVisibility(View.VISIBLE);

                Uri w = data.getData();
                FirebaseStorage firebasestorage = FirebaseStorage.getInstance();

                StorageReference videosRef = firebasestorage.getReference().child(uploadString);
                UploadTask uploadTask = videosRef.child(w.getLastPathSegment() + ".mp4").putFile(w);

//
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getBaseContext(),
                                "Upload failed: " + e.getLocalizedMessage(),
                                Toast.LENGTH_LONG).show();

                    }
                }).addOnCompleteListener(
                        new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (task.isSuccessful())
                                {


//                                    videosRef.getDownloadUrl().addOnSuccessListener {
//                                    myUrl = it.toString()
//                                    ok = urlTask.toString();
                               // }
                                    Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                        @Override
                                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                            if (!task.isSuccessful()) {
                                                throw task.getException();
                                            }
                                            // Continue with the task to get the download URL
                                            return videosRef.getDownloadUrl();
                                        }
                                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Uri> task) {
                                            if (task.isSuccessful()) {
                                                Uri downloadUri = task.getResult();


                                                Toast.makeText(getBaseContext(), "ok" + downloadUri.toString(),
                                                        Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                    ok=urlTask.toString();
                    // Task<Uri> ww=videosRef.getDownloadUrl();
//                                String ok=ww.toString();
//                                Task<Uri> ww=videosRef.getDownloadUrl();
////                                String flink=ww.toString();
                                  //  Uri downloadUrl1 = (Uri) task.getResult();
                                //    String e=downloadUrl1.toString();
//                                    videosRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                        @Override
//                                        public void onSuccess(Uri uri) {
//                                            ok=uri.toString();


//
//                                Toast.makeText(getBaseContext(),"flink ="+ flink,
//                                        Toast.LENGTH_LONG).show();

                                    db.collection("candidate").document(uploadString)
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                                    Toast.makeText(getBaseContext(), "fhk", Toast.LENGTH_LONG).show();
                                                    if (task.isSuccessful()) {
                                                        DocumentSnapshot document = task.getResult();
                                                        {
                                                            String ref = document.getData().get("refernceid").toString();
                                                            String names = document.getData().get("namedb").toString();
                                                            String mob = document.getData().get("phonedb").toString();
                                                            String hr = document.getData().get("coursedb").toString();
                                                            String em = document.getData().get("emaildb").toString();


                                                            Map<String, Object> user = new HashMap<>();
                                                            user.put("refernceid", ref);
                                                            user.put("namedb", names);
                                                            user.put("phonedb", mob);
                                                            user.put("coursedb", hr);
                                                            user.put("emaildb", em);
                                                            user.put("videolink",ok);
                                                            db.collection("candidate").document(em)
                                                                    .set(user)
                                                                    .addOnSuccessListener(new OnSuccessListener() {
                                                                        @Override
                                                                        public void onSuccess(Object o) {
    Toast.makeText(getBaseContext(), "Sucessfully saved", Toast.LENGTH_SHORT).show();

                                                                        }


                                                                    })
                                                                    .addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {
                                                                            Toast.makeText(getBaseContext(), "Error adding document", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    });
                                                        }
                                                    } else {
                                                        Toast.makeText(getBaseContext(), "Error getting documents.", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });



//                                });
                                        }
                                Toast.makeText(getBaseContext(), "Upload complete",
                                        Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);


                                                                }


                     });

            }
            else if (resultCode == RESULT_CANCELED)
                Toast.makeText(this, "Video recording cancelled.", Toast.LENGTH_LONG).show();
             else
            Toast.makeText(this, "Failed to record video",  Toast.LENGTH_LONG).show();

        }
    }



}

