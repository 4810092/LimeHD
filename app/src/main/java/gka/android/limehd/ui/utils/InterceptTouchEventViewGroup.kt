package gka.android.limehd.ui.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout

interface OnInterceptTouchEvent {
    fun onInterceptTouchEvent(ev: MotionEvent?)
}

interface InterceptTouchEventViewGroup {
    val self: View
    var interceptTouchEventListener: OnInterceptTouchEvent?
}

@SuppressLint("ClickableViewAccessibility")
fun InterceptTouchEventViewGroup.syncTouchesWith(views: List<View>) {
    if (views.isEmpty())
        return
    val onInterceptTouchEvent = object : OnInterceptTouchEvent {
        override fun onInterceptTouchEvent(ev: MotionEvent?) {
            if (ev?.action == MotionEvent.ACTION_MOVE || ev?.action == MotionEvent.ACTION_UP || ev?.action == MotionEvent.ACTION_CANCEL) {
                self.hideKeyboard()
            }
        }
    }
    interceptTouchEventListener = onInterceptTouchEvent
    val onTouchListener = View.OnTouchListener { v, event ->
        if (event?.action == MotionEvent.ACTION_DOWN) {
            interceptTouchEventListener = null
        } else if (event?.action == MotionEvent.ACTION_UP || event?.action == MotionEvent.ACTION_CANCEL) {
            interceptTouchEventListener = onInterceptTouchEvent
        }
        false
    }
    views.forEach {
        it.setOnTouchListener(onTouchListener)
    }
}


class InterceptTouchEventLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), InterceptTouchEventViewGroup {

    override val self: View = this
    override var interceptTouchEventListener: OnInterceptTouchEvent? = null

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        interceptTouchEventListener?.onInterceptTouchEvent(ev)
        return super.onInterceptTouchEvent(ev)
    }
}
