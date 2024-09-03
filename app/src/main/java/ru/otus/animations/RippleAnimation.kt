package ru.otus.animations

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.animation.ValueAnimator.INFINITE
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

class RippleAnimation @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    data class Circle(var radius: Float, var alpha: Int)

    private val delay = 500L
    private var circle = Paint().apply { color = resources.getColor(R.color.teal, null) }
    private val circles = listOf(
        Circle(0f, 255),
        Circle(47f, 222),
        Circle(125f, 158),
        Circle(203f, 97),
        Circle(281f, 33),
        Circle(320f, 0),
    )

    override fun onDraw(canvas: Canvas) {
        for (index in circles.indices) {
            circle.alpha = circles[index].alpha
            canvas.drawCircle(
                (width / 2).toFloat(),
                (height / 2).toFloat(),
                circles[index].radius,
                circle
            )
            super.onDraw(canvas)
        }
    }

    fun animateCircles() {
        for (index in 0..circles.size - 2) {
            val radiusHolder = PropertyValuesHolder.ofFloat(
                "radius",
                circles[index].radius,
                circles[index + 1].radius
            )
            val alphaHolder = PropertyValuesHolder.ofInt(
                "alpha",
                circles[index].alpha,
                circles[index + 1].alpha
            )
            ValueAnimator.ofPropertyValuesHolder(radiusHolder, alphaHolder)
                .apply {
                    duration = delay
                    interpolator = LinearInterpolator()
                    repeatCount = INFINITE
                    addUpdateListener {
                        circles[index].radius = it.getAnimatedValue("radius") as Float
                        circles[index].alpha = it.getAnimatedValue("alpha") as Int
                        invalidate()
                    }
                    start()
                }
        }
    }
}