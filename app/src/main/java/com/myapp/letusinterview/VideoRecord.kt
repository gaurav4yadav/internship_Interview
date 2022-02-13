//package com.myapp.letusinterview
//
//import android.Manifest
//import android.content.ContentValues
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.hardware.camera2.CameraCharacteristics
//import android.media.MediaRecorder.AudioSource.MIC
//import android.net.Uri
//import android.os.Build
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.provider.MediaStore
//import android.util.Log
//import android.view.View
//import android.widget.*
//import android.widget.Toast.LENGTH_SHORT
//import androidx.camera.camera2.interop.Camera2CameraInfo
//import androidx.camera.core.CameraSelector
//import androidx.camera.core.ImageCapture
//import androidx.camera.video.*
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import java.text.SimpleDateFormat
//import java.util.*
//import java.util.concurrent.ExecutorService
//
//abstract class VideoRecord : AppCompatActivity() {
//
//    val permissions = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
//    val REQUEST_VIDEO_CAPTURE = 1
//      lateinit var  vv: VideoView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_video_record)
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
////    override fun onRequestPermissionsResult(requestCode:Int, permissions:Array<out String>, grantResults: IntArray) {
////        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
////        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////            button.isEnabled = true
////        }
////    }
//    fun funstart(view: View) {
//
//
//Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { takeVideoIntent ->
//    takeVideoIntent.resolveActivity(packageManager)?.also {
////        intent.putExtra(android.provider.MediaStore.EXTRA_VIDEO_QUALITY, 0)
//
//        startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
//    }
////           var i= Intent(MediaStore.ACTION_VIDEO_CAPTURE)
////    intent.putExtra(android.provider.MediaStore.EXTRA_VIDEO_QUALITY, 0)
////            startActivityForResult(i,101)
//}
//
//    Toast.makeText(this, "else blck", LENGTH_SHORT).show();
//}
////    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
////        super.onActivityResult(requestCode, resultCode, intent)
////        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
////            val videoUri: Uri = intent?.data!!
////            vv.setVideoURI(videoUri)
////        }
////    }
//
//    // Create MediaStoreOutputOptions for our recorder
////    val name = "CameraX-recording-" +
////            SimpleDateFormat(FILENAME_FORMAT, Locale.US)
////                .format(System.currentTimeMillis()) + ".mp4"
////    val contentValues = ContentValues().apply {
////        put(MediaStore.Video.Media.DISPLAY_NAME, name)
////    }
////    val mediaStoreOutput = MediaStoreOutputOptions.Builder(
////        this.contentResolver,
////        MediaStore.Video.Media.EXTERNAL_CONTENT_URI
////    )
////        .setContentValues(contentValues)
////        .build()
//}
//
//// 2. Configure Recorder and Start recording to the mediaStoreOutput.
////    val recording = videoCapture.output
////        .prepareRecording(context, mediaStoreOutput)
////        .withAudioEnabled()
////        .start(ContextCompat.getMainExecutor(this), captureListener)
////
////    }
////     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
////         super.onActivityResult(requestCode, resultCode, data)
////         if (requestCode == 101) {
////             vv1.setVideoURI(data?.data);
////             vv1.start();
////
////
////         }
////     }
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
//    // private var storagePostPicRef: StorageReference? = null
//    val permissions = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
//
//    val REQUEST_VIDEO_CAPTURE = 1
//    lateinit var vv: VideoView
//
//    private lateinit var auth: FirebaseAuth
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_vr)
//        vv=findViewById(R.id.v2) as VideoView
//        // storagePostPicRef = FirebaseStorage.getInstance().reference.child("Posts Videos")
//        // auth = Firebase.auth
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
//    private fun dispatchTakeVideoIntent() {
//        var uidd = ""
////                        val useras = auth.currentUser
////                        if (useras !== null) {
////
////                           uidd = useras.uid.toString()
////                       }
//        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { takeVideoIntent ->
//            takeVideoIntent.resolveActivity(packageManager)?.also {
//                val videoFile: File? = createVideoFile()
//
////                    } catch (ex: IOException) {
////
////                        null
////                    }
////                    // Continue only if the File was successfully created
//                videoFile?.also {
//                    videoUri = FileProvider.getUriForFile(this, "com.myapp.letusinterview", videoFile)
//                }
//                takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT,videoUri)
//                takeVideoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY , 0)
//                startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
//            }
//        }
//    }
//
//    // @Throws(IOException::class)
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