package id.miralabs.sailing.fakeaccessibilityservice.services

import android.accessibilityservice.AccessibilityService
import android.graphics.PixelFormat
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.accessibility.AccessibilityEvent
import android.widget.FrameLayout
import id.miralabs.sailing.fakeaccessibilityservice.R

class RemoteAccessibilityService : AccessibilityService() {

    companion object {
        private const val TAG = "RAService-"
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.d(TAG.plus("Event"), event?.action.toString())
    }

    override fun onInterrupt() {
        Log.d(TAG, "onInterrupted")
    }

    override fun onServiceConnected() {
        val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        val frameLayout = FrameLayout(this)
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.apply {
            type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY
            format = PixelFormat.TRANSPARENT
            flags = layoutParams.flags or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            height = WindowManager.LayoutParams.WRAP_CONTENT
            gravity = Gravity.TOP
        }
        val layoutInflater = LayoutInflater.from(this)
        layoutInflater.inflate(R.layout.content_overlay_mask, frameLayout)
        windowManager.addView(frameLayout, layoutParams)
        Log.d(TAG, "onServiceConnected")
    }
}