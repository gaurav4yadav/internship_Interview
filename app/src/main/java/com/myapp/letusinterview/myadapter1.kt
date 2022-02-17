package com.myapp.letusinterview

import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.URLUtil
import android.webkit.WebView
import android.widget.*

import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.appcompat.view.menu.MenuView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import java.security.AccessController.getContext


class myadapter1(private val userlist1: ArrayList<myclass1> ) : RecyclerView.Adapter<myadapter1.MyviewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myadapter1.MyviewHolder {

        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.zlist,parent,false)
        return MyviewHolder(itemView)
    }



    override fun getItemCount(): Int {

        return   userlist1.size
    }
    public class MyviewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val tw1: TextView = itemView.findViewById(R.id.tw1)
        val tw2: TextView = itemView.findViewById(R.id.tw2)
        val tw3: TextView = itemView.findViewById(R.id.tw3)
        val tw4: TextView = itemView.findViewById(R.id.tw4)
        val tw8: TextView = itemView.findViewById(R.id.tw8)
       // val vd:  VideoView = itemView.findViewById(R.id.video1)
       val wv:  WebView = itemView.findViewById(R.id.web)
     var progressBar: ProgressBar =itemView.findViewById(R.id.progressBar1)
      //  var dwld: Button =itemView.findViewById(R.id.adwonlaod)

    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {

        val cus1: myclass1 =userlist1[position]
        holder.tw1.text=cus1.candname
        holder.tw2.text=cus1.mob
        holder.tw3.text=cus1.email
        holder.tw4.text=cus1.course
        holder.wv.setDownloadListener({ url, userAgent, contentDisposition, mimeType, contentLength ->
            val request = DownloadManager.Request(Uri.parse(url))
            request.setMimeType(mimeType)
            request.addRequestHeader("cookie", CookieManager.getInstance().getCookie(url))
            request.addRequestHeader("User-Agent", userAgent)
            request.setDescription("Downloading file...")
            request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType))
            request.allowScanningByMediaScanner()
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

            val dm =holder.itemView.context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            dm.enqueue(request)
            Toast.makeText(holder.itemView.context, "Downloading File", Toast.LENGTH_LONG).show()
        })
//        var o:Context=holder.itemView.context;
//        var mc:MediaController= MediaController(o)
//        mc.setAnchorView(holder.vd)
//        holder.vd.setMediaController(mc)
//        holder.vd.setVideoURI(cus1?.url.toUri())
//        holder.vd.requestFocus()

     //  holder. progressBar.visibility = View.VISIBLE
//
//        holder.vd.setOnPreparedListener(MediaPlayer.OnPreparedListener {
//
//            holder. progressBar.visibility = View.GONE
//            holder.vd.start()
//
//        })
//        holder.dwld.setOnClickListener(View.OnClickListener {
//            var dm:DownloadManager()
//
//        })


      if(cus1.url.equals("http://null/"))
        {
            holder.tw8.text="Interview Not Uploaded"
            holder.wv.visibility=View.GONE
        }
else if(cus1.url.equals(""))
        {
            holder.tw8.text="Interview Not Uploaded"
            holder.wv.visibility=View.GONE
        }
else   if(cus1.url.toString()!=null)
          holder.wv.loadUrl(cus1?.url);
else if(cus1.url.toString()===null)
      {
          holder.tw8.text="Interview Not Uploaded"
          holder.wv.visibility=View.GONE
      }
else if(cus1.url.toString()==="")
      {
          holder.tw8.text="Interview Not Uploaded"
          holder.wv.visibility=View.GONE
      }
else
        {
            holder.tw8.text="Interview Not Uploaded"
            holder.wv.visibility=View.GONE
        }

    }

    override fun onViewAttachedToWindow(holder: MyviewHolder) {
        super.onViewAttachedToWindow(holder)

    }

}