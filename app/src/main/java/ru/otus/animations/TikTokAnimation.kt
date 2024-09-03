package ru.otus.animations

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.animation.ValueAnimator.INFINITE
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

class TikTokAnimation @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {

    private val purplePaint = Paint().apply { color = resources.getColor(R.color.purple, null) }
    private val pinkPaint = Paint().apply { color = resources.getColor(R.color.pink, null) }

    private val radius = 150f
    private val purpleScale = 1f
    private val purpleOpacity = 1f
    private var pinkScale = 1f
    private var pinkOpacity = 1f

    private val purpleInitX: Float
        get() = width / 2 - radius - 20f
    private val pinkInitX: Float
        get() = width / 2 + radius + 20f
    private val margin = pinkInitX - purpleInitX

    private var purpleX = 0f
    private var pinkX = 0f

    override fun onDraw(canvas: Canvas) {
        purplePaint.alpha = (purpleOpacity * 255).toInt()
        pinkPaint.alpha = (pinkOpacity * 255).toInt()

        canvas.drawCircle(purpleInitX + purpleX, height / 2f, radius * purpleScale, purplePaint)
        canvas.drawCircle(pinkInitX + pinkX, height / 2f, radius * pinkScale, pinkPaint)
        super.onDraw(canvas)
    }

    fun animateCircles() {
        AnimatorSet().apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = 2500L
            playTogether(
                ValueAnimator.ofFloat(0f, margin, margin, 0f).apply {
                    addUpdateListener {
                        purpleX = it.animatedValue as Float
                        repeatCount = INFINITE
                        invalidate()
                    }
                }, ValueAnimator.ofFloat(0f, -margin, -margin, 0f).apply {
                    addUpdateListener {
                        pinkX = it.animatedValue as Float
                        repeatCount = INFINITE
                        invalidate()
                    }
                }, ValueAnimator.ofFloat(1f, 0.5f, 1f, 1f, 1f).apply {
                    addUpdateListener {
                        pinkScale = it.animatedValue as Float
                        repeatCount = INFINITE
                        invalidate()
                    }
                }, ValueAnimator.ofFloat(1f, 0f, 1f, 1f, 1f).apply {
                    addUpdateListener {
                        pinkOpacity = it.animatedValue as Float
                        repeatCount = INFINITE
                        invalidate()
                    }
                })
            start()
        }
    }
}