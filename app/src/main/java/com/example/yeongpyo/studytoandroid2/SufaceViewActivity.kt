package com.example.yeongpyo.studytoandroid2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView

class SufaceViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suface_view)
    }
}

class MySurFaceView(context: Context ) : SurfaceView(context), SurfaceHolder.Callback{

    val mcontext = context
    val mHolder : SurfaceHolder= holder
    val mRThread = RenderingThread()

    init { mHolder.addCallback(this) }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) : Unit = (mRThread).start()

    override fun surfaceDestroyed(holder: SurfaceHolder?) {}

    override fun surfaceCreated(holder: SurfaceHolder?) {
        try { mRThread.join()
        }catch (e: InterruptedException){}
    }

    inner class RenderingThread : Thread() {
        val android_img: Bitmap = BitmapFactory.decodeResource(mcontext.resources, R.drawable.ic_launcher_foreground)
        override fun run() {
            while(true){
                val canvas_ = mHolder.lockCanvas()
                try {
                    synchronized(mHolder){
                        canvas_.drawBitmap(android_img, 0F, 0F, null)
                    }
                }
                finally {
                    if ( canvas_ == null ) return
                    mHolder.unlockCanvasAndPost(canvas_)
                }
            }
        }
    }

}

