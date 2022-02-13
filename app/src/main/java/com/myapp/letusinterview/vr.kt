//package com.myapp.letusinterview
//
//import android.Manifest
//import android.annotation.SuppressLint
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.net.Uri
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.os.Environment
//import android.provider.MediaStore
//import android.view.View
//import android.widget.VideoView
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.core.content.FileProvider
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.ktx.auth
//import com.google.firebase.ktx.Firebase
//import com.google.firebase.storage.FirebaseStorage
//import com.google.firebase.storage.StorageReference
//import java.io.File
//import java.io.IOException
//import java.text.SimpleDateFormat
//
//class vr : AppCompatActivity() {
//    private var myUrl = ""
//    private var videoUri: Uri? = null
//   // private var storagePostPicRef: StorageReference? = null
//    val permissions = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
//
//   val REQUEST_VIDEO_CAPTURE = 1
//   lateinit var vv: VideoView
//
//    private lateinit var auth: FirebaseAuth
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_vr)
//        vv=findViewById(R.id.v2) as VideoView
//       // storagePostPicRef = FirebaseStorage.getInstance().reference.child("Posts Videos")
//       // auth = Firebase.auth
//
//    }
//    override fun onStart() {
//        super.onStart()
//        if (hasNoPermissions()) {
//            requestPermission()
//        }
//    }
//    fun hasNoPermissions(): Boolean{
//        return ContextCompat.checkSelfPermission(this,
//            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
//            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
//    }
//
//    fun requestPermission(){
//        ActivityCompat.requestPermissions(this, permissions,0)
//    }
//
//    fun fund(view: View) {
//        dispatchTakeVideoIntent()
//    }
//
//        private fun dispatchTakeVideoIntent() {
//            var uidd = ""
////                        val useras = auth.currentUser
////                        if (useras !== null) {
////
////                           uidd = useras.uid.toString()
////                       }
//            Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { takeVideoIntent ->
//                takeVideoIntent.resolveActivity(packageManager)?.also {
//                    val videoFile: File? = createVideoFile()
//
////                    } catch (ex: IOException) {
////
////                        null
////                    }
////                    // Continue only if the File was successfully created
//                    videoFile?.also {
//                        videoUri = FileProvider.getUriForFile(this, "com.myapp.letusinterview", videoFile)
//                    }
//
//                       // takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT,videoUri)
//                    //takeVideoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY , 0)
//                        startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
//                    }
//                }
//            }
//
//   // @Throws(IOException::class)
//    private fun createVideoFile(): File {
//        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(java.util.Date())
//        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_MOVIES)
//        return File.createTempFile(
//            "Video_${timeStamp}_", /* prefix */
//            ".mp4", /* suffix */
//            storageDir /* directory */
//        )
//    }
//
////    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent) {
////        super.onActivityResult(requestCode, resultCode, intent)
////        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
////            val videoUri: Uri? = intent.data
////            vv.setVideoURI(videoUri)
////override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
////
////         if (requestCode == 101) {
////             vv.setVideoURI(data!!.data);
////             vv.start();
////        }
////    }
//}
//package com.myapp.letusinterview;
//
//
//import android.app.Activity;
//import android.content.Intent;
//import android.content.pm.ActivityInfo;
//import android.media.CamcorderProfile;
//import android.media.MediaRecorder;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//
//public class VideoCapture extends AppCompatActivity {
//
//    int  VIDEO_CAPTURE=1;
//
//    public void onCreate(Bundle savedInstanceState) {
//        Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show();
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.javawali);
//        Button recordButton = (Button) findViewById(R.id.button2);
//
//        recordButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//
////                String  timestamp = new SimpleDateFormat("MM-dd-yyyy_HH-mm-ss aa").format(Calendar.getInstance().getTime());
////                File filepath = Environment.getExternalStorageDirectory();
////                File dir = new File(filepath.getAbsolutePath()+ "/samplevideofolder/");
////                dir.mkdirs();
////                File mediaFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/samplevideofolder/Video_"+timestamp+".mp4");
////                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
////                Uri fileUri = Uri.fromFile(mediaFile);
//
//                String Storage =Environment.getExternalStorageDirectory().toString()+"/ok.mp4";
//                File file=new File(Storage);
//                Uri uri;
//                if(Build.VERSION.SDK_INT <24){
//                    uri=Uri.fromFile(file);
//
//                }
//                else {
//                    uri=Uri.parse(file.getPath());
//                }
//                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 30);
//                startActivityForResult(intent, VIDEO_CAPTURE);
//            }
//        });
//
//    }
//
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == VIDEO_CAPTURE) {
//            if (resultCode == RESULT_OK) {
//
//
//                Toast.makeText(this, "Video saved to:\n" + data.getData(), Toast.LENGTH_LONG).show();
//
//
//            } else if (resultCode == RESULT_CANCELED) {
//                Toast.makeText(this, "Video recording cancelled.", Toast.LENGTH_LONG).show();
//            } else {
//                //Toast.makeText(this, "Failed to record video",                        Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//
//    public void funsd(View view) {
//
//    }
//}
