package com.example.cusromview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {

    init {
        isClickable = true
    }

    private var mode = false
    private var clickCounter = 0
    private var radius = 300f
    private val pointPosition: PointF = PointF(0.0f, 0.0f)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create("", Typeface.BOLD)
    }

    private fun PointF.computeXYForSpeed() {
        x = width / 2.toFloat()
        y = height / 2.toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.GREEN
        pointPosition.computeXYForSpeed()
        canvas.drawCircle(pointPosition.x, pointPosition.y, radius, paint)
        paint.color = Color.BLACK
        canvas.drawText(clickCounter.toString(), pointPosition.x, pointPosition.y, paint)
    }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomView,
            0, 0).apply {
            try {
                mode = getBoolean(R.styleable.CustomView_mode, true)
                clickCounter = getInteger(R.styleable.CustomView_start_value, 0)
            } finally {
                recycle()
            }
        }
    }

    override fun performClick(): Boolean {
        if (super.performClick()) return true

        if (mode) addClick() else removeClick()
        invalidate()
        return true
    }

    private fun addClick() {
        clickCounter++
    }

    private fun removeClick() {
        if (clickCounter > 0) clickCounter--
    }

    fun switchMode() {
        mode = !mode
    }
}
