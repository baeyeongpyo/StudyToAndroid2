package com.example.yeongpyo.studytoandroid2

import android.content.Context
import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView

class SufaceViewActivity : AppCompatActivity() {

    var deviceWidth = 0
    var deviceHight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_suface_view)
        setContentView(MySurFaceView(this))

        val disp = application.resources.displayMetrics
        deviceWidth = disp.widthPixels
        deviceHight = disp.heightPixels

    }

    class MySurFaceView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

        private lateinit var surfaceThread: Thread

        init {
//            surfaceThread = SurfaceThread()

        }

        override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {}

        override fun surfaceDestroyed(holder: SurfaceHolder?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun surfaceCreated(holder: SurfaceHolder?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }


        class SurfaceThread(var surfacehoder: SurfaceHolder, var surFaceView: MySurFaceView) : Thread() {
            var MyThreadRun = false
            var x = 0
            var quadWidth = 100
            var quadHeight = 100

            fun setRunning(b: Boolean) {
                MyThreadRun = b
            }

            override fun run() {
                while (MyThreadRun) {
                    var canvas: Canvas
                    try {
                        canvas = surfacehoder.lockCanvas(null)
                        synchronized(surfacehoder) {
                            var mPaint = Paint()
                            mPaint.color = Color.WHITE
//                            canvas.drawRect(0, 0, deviceWidth)


                        }
                    } finally {

                    }
                }
            }
        }

    }
}
