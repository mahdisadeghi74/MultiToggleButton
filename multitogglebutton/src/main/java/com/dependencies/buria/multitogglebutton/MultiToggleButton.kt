package com.dependencies.buria.multitogglebutton

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginLeft
import androidx.core.view.size
import kotlinx.android.synthetic.main.layout_multi_toggle_button.view.*
import kotlin.math.log

class MultiToggleButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    // typeface value
    private var TEXTSTYLE_NORMAL = 0
    private var TEXTSTYLE_BOLD = 1
    private var TEXTSTYLE_ITALIC = 2


    //attrs put user
    private var toggleDrawables: ArrayList<Int?>? = null
    var text: String? = ""
    var buttonPadding: Float = 0f
    var toggleButtonTint: Int = 0
    var textSize: Float = toDP(12f)
    var textColor: Int = 0
    private var textStyle: Int = TEXTSTYLE_NORMAL
    var toggleButtonSize: Float = 24f

    // variables counter
    private var currentItem: Int = 0
    private var itemCount: Int = 0


    // listener
    private var onItemChangeListener: ((Int, Int) -> Unit)? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout_multi_toggle_button, this)

        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.MultiToggleButton)

        try {
            text = typeArray.getString(R.styleable.MultiToggleButton_text)
            buttonPadding = typeArray.getDimension(R.styleable.MultiToggleButton_buttonPadding, 0f)
            toggleButtonTint =
                typeArray.getResourceId(R.styleable.MultiToggleButton_toggleButtonTint, 0)
            textSize = typeArray.getDimension(R.styleable.MultiToggleButton_textSize, toDP(12f))
            textColor = typeArray.getResourceId(R.styleable.MultiToggleButton_textColor, 12)
            textStyle = typeArray.getInt(R.styleable.MultiToggleButton_textStyle,  TEXTSTYLE_NORMAL)
            toggleButtonSize = typeArray.getDimensionPixelSize(R.styleable.MultiToggleButton_toggleButtonSize,  24).toFloat()

            tvTgb.text = text
            tvTgb.setPadding(buttonPadding.toInt(), 0, 0, 0)
            tvTgb.setTextColor(context.resources.getColor(textColor))
            tvTgb.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)

            when(textStyle){
                TEXTSTYLE_BOLD -> tvTgb.typeface = Typeface.DEFAULT_BOLD
                TEXTSTYLE_NORMAL -> tvTgb.typeface = Typeface.DEFAULT
                TEXTSTYLE_ITALIC -> tvTgb.setTypeface(tvTgb.typeface, Typeface.ITALIC)

                else -> tvTgb.typeface = Typeface.DEFAULT
            }

            if (toggleButtonTint != 0)
                tgb.imageTintList = context.resources.getColorStateList(toggleButtonTint)

            tgb.layoutParams.height = toggleButtonSize.toInt()
            tgb.layoutParams.width = toggleButtonSize.toInt()

        } finally {
            typeArray.recycle()
        }

        tgb.setOnClickListener {
            setToggleImageResource()
            setCurrentImageResource()
            onItemChangeListener?.let { function ->
                getCurrentResource()?.let { it1 -> function(it1, currentItem) }
            }
        }

    }

    fun addToggleDrawables(vararg resources: Int?) {
        toggleDrawables = arrayListOf()
        toggleDrawables?.addAll(resources)

        itemCount = resources.size
        setCurrentImageResource()
    }

    private fun setCurrentImageResource() {
        if (itemCount > 0)
            tgb.setImageDrawable(toggleDrawables?.get(currentItem)?.let { context.getDrawable(it) })
    }

    private fun setToggleImageResource() {
        currentItem = if (currentItem == itemCount - 1) 0 else currentItem + 1
    }

    private fun getCurrentResource(): Int? {
        return toggleDrawables?.get(currentItem)
    }


    fun setOnItemChangeListener(onItemChangeListener: (resourceId: Int, position: Int) -> Unit) {
        this.onItemChangeListener = onItemChangeListener
    }

    fun getCurrentItem(): Int? {
        return getCurrentResource()
    }

    private fun toDP(value: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value,
            context.resources.displayMetrics
        )
    }
}