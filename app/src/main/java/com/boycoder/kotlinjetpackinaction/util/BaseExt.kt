package com.boycoder.kotlinjetpackinaction.util

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.util.Log
import android.widget.Toast
import com.boycoder.kotlinjetpackinaction.BuildConfig

/**
 * Created by zhu.tao on 2020/7/22.
 */

fun Bitmap.toGrayScale(): Bitmap {
    val height: Int = this.height
    val width: Int = this.width
    val bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
    val c = Canvas(bmpGrayscale)
    val paint = Paint()
    val cm = ColorMatrix()
    cm.setSaturation(0F)
    val f = ColorMatrixColorFilter(cm)
    paint.colorFilter = f
    c.drawBitmap(this, 0F, 0F, paint)
    return bmpGrayscale
}

fun Bitmap.toCircle(): Bitmap? {
    val w = this.width
    val h = this.height
    val r = Math.min(w, h)

    val paint = Paint()
    paint.isAntiAlias = true

    val newBitmap = Bitmap.createBitmap(r, r, Bitmap.Config.ARGB_8888)

    val canvas = Canvas(newBitmap)

    val path = Path()
    path.addCircle((r / 2).toFloat(), (r / 2).toFloat(), (r / 2).toFloat(), Path.Direction.CW)
    canvas.clipPath(path)
    canvas.drawBitmap(this, 0F, 0F, paint)
    return newBitmap
}

fun Context.toast(message: Any, isLengthLong: Boolean = true) =
        Toast.makeText(
                this,
                if (message is CharSequence) message else message.toString(),
                if (isLengthLong) {Toast.LENGTH_LONG } else { Toast.LENGTH_SHORT}
        ).show()

fun Activity.logD(message: Any) {
    if (BuildConfig.DEBUG) {
        Log.d(this::class.java.simpleName, if (message is String) message else message.toString())
    }
}