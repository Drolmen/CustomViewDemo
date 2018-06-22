package com.drolmen.customviewdemo.viewgroup

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup

import com.drolmen.customviewdemo.R

class DemoLinearLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : ViewGroup(context, attrs) {

    val TAG = "DemoLinearLayout"

    private var mOrientation: Int

    val VERTICAL : Int = 0
    val HORIZONTAL : Int = 1

    init {
        mOrientation = VERTICAL
        attrs?.run {
            readAttribute(attrs)
        }
    }

    private fun readAttribute(attrs: AttributeSet) {
        //1. 读取属性
        val count = attrs.attributeCount
        for (i in 0 until count) {
            Log.d(TAG, attrs.getAttributeName(i) +"  " + attrs.getAttributeValue(i))
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        for (i in 0 until  childCount) {
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

    //TODO: 继承MarginLayoutParam
    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return generateLayoutParams(super.generateLayoutParams(attrs))
    }

    //TODO: 继承MarginLayoutParam
    override fun generateLayoutParams(p: LayoutParams?): LayoutParams {
        return MarginLayoutParams(p)
    }
}
