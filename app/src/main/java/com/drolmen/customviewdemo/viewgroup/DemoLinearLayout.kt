package com.drolmen.customviewdemo.viewgroup

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout

import com.drolmen.customviewdemo.R

class DemoLinearLayout : ViewGroup {

    val TAG = "DemoLinearLayout"

    private var mOrientation: Int

    val VERTICAL: Int = 0
    val HORIZONTAL: Int = 1

    init {
        mOrientation = VERTICAL
    }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        attrs?.let {
            readAttribute(it)
        }
    }

    private fun readAttribute(attrs: AttributeSet) {
        //1. 读取属性
        val count = attrs.attributeCount
        for (i in 0 until count) {
            Log.d(TAG, attrs.getAttributeName(i) + "  " + attrs.getAttributeValue(i))
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        for (i in 0 until childCount) {
            val spec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
            getChildAt(i).measure(spec, spec)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        Log.d(TAG, "changed$changed, l = $l, t = $t, r = $r, b = $b")
        if (mOrientation == VERTICAL) {
            layoutVertical(l, t, r, b)
        } else {
            layoutHorizontal(l, t, r, b)
        }
    }

    private fun layoutVertical(l: Int, t: Int, r: Int, b: Int): Unit {
        var childTop = t
        for (i in 0 until childCount) {
            var childAt = getChildAt(i)
            var childLeftMargin = (childAt.layoutParams as MarginLayoutParams).leftMargin
            var childTopMargin = (childAt.layoutParams as MarginLayoutParams).topMargin
            childAt.layout(l + childLeftMargin, childTop + childTopMargin,
                    l + childLeftMargin + childAt.measuredWidth, childTop + childTopMargin + childAt.measuredHeight)
            childTop = childAt.bottom
        }
    }

    private fun layoutHorizontal(l: Int, t: Int, r: Int, b: Int): Unit {

    }

    override fun generateDefaultLayoutParams(): ViewGroup.LayoutParams {
        return LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return LayoutParams(context, attrs)
    }

    override fun generateLayoutParams(p: ViewGroup.LayoutParams?): ViewGroup.LayoutParams {
        return super.generateLayoutParams(p)
    }

    class LayoutParams : MarginLayoutParams {

        companion object {
            const val MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT
            const val WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT
        }

        var weight: Int = 0
        var gravity: Int = -1

        //覆盖父类构造方法为次构造器
        constructor(c: Context?, attrs: AttributeSet?) : super(c, attrs) {
            if (c == null || attrs == null) {
                return
            }

            val typedArray = c.obtainStyledAttributes(attrs, R.styleable.DemoLinearLayout_Layout)

            weight = typedArray.getInt(R.styleable.DemoLinearLayout_Layout_demoOrientation, 0)

            typedArray?.recycle()
        }

        constructor(width: Int, height: Int) : super(width, height)

        constructor(width: Int, height: Int, weight: Int) : super(width, height) {
            this.weight = weight
        }

        constructor(source: ViewGroup.LayoutParams?) : super(source)

        constructor(source: MarginLayoutParams?) : super(source)

    }
}
