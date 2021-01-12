package com.example.tmdbclient.presentation.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.tmdbclient.R

class PopularityProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private var margin: Float = 0f

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PopularityProgressBar,
            0,
            0
        ).apply {
            try {
                margin = getDimension(R.styleable.PopularityProgressBar_ovalSize, 0f)
                Log.e("ProgressBar", margin.toString())
            } catch (e: NumberFormatException) {
                Log.e("ProgressBar", e.message.toString())
            } finally {
                recycle()
            }
        }
    }

    companion object {
        private const val MULTIPLIER = 10
        private const val BACKGROUND_WIDTH = 5f
        private const val PROGRESS_WIDTH = 10f
        private const val RADIUS = 360
        private const val COMPLETION = 100
        private const val START_ANGLE = 270f
        private const val TEXT_SIZE = 15f
    }

    private val backgroundPaint = Paint().apply {
        color = Color.parseColor("#081C22")
        style = Paint.Style.FILL_AND_STROKE
        strokeWidth = BACKGROUND_WIDTH
        isAntiAlias = true
    }

    private val progressBackground = Paint().apply {
        color = Color.parseColor("#204529")
        style = Paint.Style.STROKE
        strokeWidth = BACKGROUND_WIDTH
        isAntiAlias = true
    }

    private val progressPaint = Paint().apply {
        color = Color.parseColor("#21D07A")
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeWidth = PROGRESS_WIDTH
        isAntiAlias = true
    }

    private val textPaint = Paint().apply {
        color = Color.parseColor("white")
        textSize = TEXT_SIZE
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
        centreX = w.toFloat().div(2)
        centreY = h.toFloat().div(2)
        radius = w.toFloat().div(2) - PROGRESS_WIDTH
        radiusBackground = w.toFloat().div(2)
        oval.set(
            centreX - radius + margin,
            centreY - radius + margin,
            centreX + radius - margin,
            centreY + radius - margin
        )
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(centreX, centreY, radius, backgroundPaint)
        canvas?.drawCircle(centreX, centreY, radius - margin, progressBackground)
        canvas?.drawArc(
            oval,
            START_ANGLE,
            progress * RADIUS * popularityScore.times(10).toInt() / COMPLETION,
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
