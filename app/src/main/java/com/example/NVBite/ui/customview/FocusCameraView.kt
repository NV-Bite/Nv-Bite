package com.example.NVBite.ui.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View


class FocusCameraView : View {
    private var mTransparentPaint: Paint? = null
    private var mSemiBlackPaint: Paint? = null
    private val mPath: Path = Path()

    private var rectWidth = 640
    private var rectHeight = 880

    private var centerX = 0F
    private var centerY = 0F

    var rectCoordinates: RectF = RectF()

    constructor(context: Context?) : super(context) {
        initPaints()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initPaints()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initPaints()
    }

    private fun initPaints() {
        mTransparentPaint = Paint()
        mTransparentPaint!!.color = Color.TRANSPARENT
        mTransparentPaint!!.strokeWidth = 10f

        mSemiBlackPaint = Paint()
        mSemiBlackPaint!!.color = Color.TRANSPARENT
        mSemiBlackPaint!!.strokeWidth = 10f
    }

    fun setRectangleSize(width: Int, height: Int) {
        this.rectWidth = width
        this.rectHeight = height
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mPath.reset()

         centerX = width.toFloat() / 2
         centerY = height.toFloat() / 2.85F

        val left = centerX - rectWidth / 2
        val top = centerY - rectHeight / 2
        val right = centerX + rectWidth / 2
        val bottom = centerY + rectHeight / 2

        Log.e("FTEST", "FOCUSRESCT FOCUSVIEW = Left: ${left}, Top: ${top}, Right: ${right}, Bottom: ${bottom}")

        rectCoordinates.set(left, top, right, bottom)

        mPath.addRect(left, top, right, bottom, Path.Direction.CW)
        mPath.fillType = Path.FillType.INVERSE_EVEN_ODD

        canvas.drawRect(left, top, right, bottom, mTransparentPaint!!)
        canvas.drawPath(mPath, mSemiBlackPaint!!)
        canvas.clipPath(mPath)
        canvas.drawColor(Color.parseColor("#A6000000"))
    }
}
