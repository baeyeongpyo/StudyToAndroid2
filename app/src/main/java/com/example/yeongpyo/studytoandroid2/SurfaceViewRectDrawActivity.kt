package com.example.yeongpyo.studytoandroid2

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.SurfaceHolder
import android.view.SurfaceView

class SurfaceViewRectDrawActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_surface_view_draw);
        val mySurfaceView = EventSufaceView(this)
        setContentView(mySurfaceView)

        val disp = applicationContext.resources.displayMetrics
        deviceWidth = disp.widthPixels
        deviceHeight = disp.heightPixels
    }

    inner class EventSufaceView : SurfaceView, SurfaceHolder.Callback {

        private var thread: SurfaceThread? = null

        override fun performClick(): Boolean {
            return super.performClick()
        }

        constructor(context: Context) : super(context) {
            init()
        }

        constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
            init()
        }

        constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
            init()
        }

        private fun init() {
            holder.addCallback(this)
            thread = SurfaceThread(holder, this)

            isFocusable = true // make sure we get key events
        }

        override fun surfaceChanged(arg0: SurfaceHolder, arg1: Int, arg2: Int, arg3: Int) {}

        override fun surfaceCreated(holder: SurfaceHolder) {
            thread!!.setRunning(true)
            thread!!.start()
        }

        override fun surfaceDestroyed(holder: SurfaceHolder) {
            var retry = true
            thread!!.setRunning(false)
            while (retry) {
                try {
                    thread!!.join()
                    retry = false
                } catch (e: InterruptedException) {
                }

            }
        }
    }

    inner class SurfaceThread(private val mThreadSurfaceHolder: SurfaceHolder, private val mThreadSurfaceView: EventSufaceView) : Thread() {
        private var myThreadRun = false
        private var x = 0
        private val quadWidth = 100
        private val quadHeight = 100

        fun setRunning(b: Boolean) {
            myThreadRun = b
        }

        override fun run() {
            while (myThreadRun) {
                var c: Canvas? = null
                try {
                    c = mThreadSurfaceHolder.lockCanvas(null)
                    synchronized(mThreadSurfaceHolder) {
                        val mPaint = Paint()
                        mPaint.color = Color.WHITE
                        c!!.drawRect(0f, 0f, deviceWidth.toFloat(), deviceHeight.toFloat(), mPaint)
                        mPaint.color = Color.RED

                        c.drawRect(x.toFloat(), x.toFloat(), (x - quadWidth).toFloat(), (x - quadHeight).toFloat(), mPaint)

                        x += 5
                        if (x - quadWidth >= deviceWidth) {
                            x = 0
                        }
                    }
                } finally {
                    if (c != null) {
                        mThreadSurfaceHolder.unlockCanvasAndPost(c)
                    }
                }
            }
        }
    }

    companion object {
        private var deviceWidth: Int = 0
        private var deviceHeight: Int = 0
    }
}