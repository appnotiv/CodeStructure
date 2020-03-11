package com.app.sample.customs

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.viewpager.widget.ViewPager

class HeightWrappingViewPager : ViewPager {

    private var mCurrentPagePosition = 0

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // find the first child view
        val view = getChildAt(mCurrentPagePosition)
        view?.measure(widthMeasureSpec, heightMeasureSpec)

        setMeasuredDimension(measuredWidth, measureHeight(heightMeasureSpec, view))

    }


    private fun measureHeight(measureSpec: Int, view: View?): Int {
        var result = 0
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            // set the height from the base view if available
            if (view != null) {
                result = view.measuredHeight
            }
            if (specMode == MeasureSpec.AT_MOST) {
                result = result.coerceAtMost(specSize)
            }
        }
        return result
    }

    fun reMeasureCurrentPage(position: Int) {
        mCurrentPagePosition = position
        requestLayout()
    }

}