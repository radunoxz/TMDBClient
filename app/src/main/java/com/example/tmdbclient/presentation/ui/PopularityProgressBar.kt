package com.example.tmdbclient.presentation.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class PopularityProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val MULTIPLIER = 10
    }

    private val backgroundWidth = 5f
    private val progressWidth = 10f


    private val backgroundPaint = Paint().apply {
        color = Color.parseColor("#081C22")
        style = Paint.Style.FILL_AND_STROKE
        strokeWidth = backgroundWidth
        isAntiAlias = true
    }

    private val progressBackground = Paint().apply {
        color = Color.parseColor("#204529")
        style = Paint.Style.STROKE
        strokeWidth = backgroundWidth
        isAntiAlias = true
    }

    private val progressPaint = Paint().apply {
        color = Color.parseColor("#21D07A")
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeWidth = progressWidth
        isAntiAlias = true
    }

    private val textPaint = Paint().apply {
        color = Color.parseColor("white")
        textSize = 15f
        textAlign = Paint.Align.CENTER
    }

    var progress: Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    var progressText: Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    var popularityScore: Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val oval = RectF()
    private var centreX = 0f
    private var centreY = 0f
    private var radius = 0f
    private var radiusBackground = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        centreX = w.toFloat() / 2
        centreY = h.toFloat() / 2
        radius = w.toFloat() / 2 - progressWidth
        radiusBackground = w.toFloat() / 2
        oval.set(
            centreX - radius + 20,
            centreY - radius + 20,
            centreX + radius - 20,
            centreY + radius - 20
        )
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(centreX, centreY, radius, backgroundPaint)
        canvas?.drawCircle(centreX, centreY, radius - 20, progressBackground)
        canvas?.drawArc(
            oval,
            270f,
            progress * 360 * popularityScore.times(10).toInt() / 100,
            false,
            progressPaint
        )
        canvas?.drawText(
            transformVoteAverageToInteger(progressText),
            centreX,
            centreY,
            textPaint
        )
    }

    private fun transformVoteAverageToInteger(voteAverage: Float): String =
        voteAverage.times(MULTIPLIER).toInt().toString() + "%"
}
