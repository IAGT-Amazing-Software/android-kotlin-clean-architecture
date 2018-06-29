package es.iagt.android.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.net.Uri
import android.os.Environment
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.view.View
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@Suppress("MagicNumber")
object ImageUtils {

    fun blur(context: Context, image: Bitmap): Bitmap {
        val BITMAP_SCALE = 0.5f
        val BLUR_RADIUS = 25f
        val width = Math.round(image.width * BITMAP_SCALE)
        val height = Math.round(image.height * BITMAP_SCALE)
        val inputBitmap = Bitmap.createScaledBitmap(image, width, height, false)
        val outputBitmap = Bitmap.createBitmap(inputBitmap)
        val rs = RenderScript.create(context)

        val intrinsicBlur: ScriptIntrinsicBlur?
        intrinsicBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        val tmpIn = Allocation.createFromBitmap(rs, inputBitmap)
        val tmpOut = Allocation.createFromBitmap(rs, outputBitmap)
        intrinsicBlur!!.setRadius(BLUR_RADIUS)
        intrinsicBlur.setInput(tmpIn)
        intrinsicBlur.forEach(tmpOut)

        tmpOut.copyTo(outputBitmap)

        return outputBitmap
    }

    fun screenShot(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width,
                view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    fun getBlurScreenShot(context: Context, view: View): Bitmap {
        return blur(context, screenShot(view))
    }

    fun getUriFromDrawableResource(context: Context, resource: Int?): Uri? {
//        val baos = ByteArrayOutputStream()
        val bitmap = BitmapFactory.decodeResource(context.resources, resource!!)
        return getLocalBitmapUri(context, bitmap)
    }

    fun getLocalBitmapUri(context: Context, bmp: Bitmap): Uri? {
        var bmpUri: Uri? = null
        try {
            val file = File(context
                    .getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png")
            val out = FileOutputStream(file)
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out)
            out.close()
            bmpUri = Uri.fromFile(file)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return bmpUri
    }
}
