package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.HorizontalScrollView
import android.widget.LinearLayout


const val LEFT_TO_RIGHT = 1
const val RIGHT_TO_LEFT = 2

class MarqueeView : HorizontalScrollView, Runnable {


    lateinit var tempStr: String
    var screenWidth: Int = 0  //屏幕总宽度
    lateinit var mainLayout: LinearLayout
    var viewRightMargin: Int = 0
    var viewWidth: Int = 0//View总宽度
    var viewMargin = 20//View间距
    var isScrolling = false
    var scrollSpeed = 5//滚动速度
    var currentX: Int = 0//当前x坐标


    var scrollDirection = LEFT_TO_RIGHT//滚动方向


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        initView()
    }

    fun initView() {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        screenWidth = wm.defaultDisplay.width
        mainLayout = LayoutInflater.from(context).inflate(R.layout.scroll_content, null) as LinearLayout

        this.addView(mainLayout)
    }

    fun addViewsInQueue(views: List<View>) {
        for (view in views) {
            var index: Int = views.indexOf(view)
            view.measure(0, 0)

            viewRightMargin = if (index != views.size - 1) {
                if (screenWidth - view.measuredWidth < 0) screenWidth / 2 else screenWidth - view.measuredWidth
            } else {
                0
            }
            val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
            lp.setMargins(viewMargin, 0, viewRightMargin, 0)
            view.layoutParams = lp
            mainLayout.addView(view)

            viewWidth += view.measuredWidth + viewMargin + viewRightMargin
        }
    }

    fun addViewInQueue(view: View) {
        view.measure(0, 0)//测量view
        viewRightMargin = if (screenWidth - view.measuredWidth < 0) screenWidth / 2 else screenWidth - view.measuredWidth
        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
        lp.setMargins(viewMargin, 0, viewRightMargin, 0)
        view.layoutParams = lp
        mainLayout.addView(view)

        viewWidth += view.measuredWidth + viewMargin + viewRightMargin
    }

    fun getViewCount(): Int {
        return if (null != mainLayout) {
            mainLayout.childCount
        } else {
            0
        }
    }

    //开始滚动
    fun startScroll() {
        removeCallbacks(this)
        currentX = if (scrollDirection == LEFT_TO_RIGHT) viewWidth else 0
        post(this)
        isScrolling = true
    }

    //停止滚动
    fun stopScroll() {
        removeCallbacks(this)
        isScrolling = false
    }

    override fun run() {
        when (scrollDirection) {
            LEFT_TO_RIGHT -> {
                mainLayout.scrollTo(currentX, 0)
                currentX--

                if (-currentX >= screenWidth) {
                    mainLayout.scrollTo(viewWidth - 100, 0)
                    currentX = viewWidth - 100
                }
            }
            RIGHT_TO_LEFT -> {
                mainLayout.scrollTo(currentX, 0)
                currentX++

                if (currentX >= viewWidth) {
                    mainLayout.scrollTo(-screenWidth + 100, 0)
                    currentX = -screenWidth + 100
                }
            }
            else -> {
            }
        }

        postDelayed(this, (50 / scrollSpeed).toLong())
    }


    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return false
    }
}