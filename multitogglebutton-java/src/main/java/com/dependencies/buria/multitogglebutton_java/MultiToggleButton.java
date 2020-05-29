package com.dependencies.buria.multitogglebutton_java;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class MultiToggleButton extends LinearLayout {
    // typeface value
    private final int TEXT_STYLE_NORMAL = 0;
    private final int TEXT_STYLE_BOLD = 1;
    private final int TEXT_STYLE_ITALIC = 2;


    //attrs put user
    private ArrayList<Integer> toggleDrawables = null;
    String text = "";
    Float buttonPadding = 0f;
    int toggleButtonTint = 0;
    Float textSize = 12f;
    int textColor =  0;
    private int textStyle = TEXT_STYLE_NORMAL;
    float toggleButtonSize = 24f;

    // variables counter
    private int currentItem = 0;
    private int itemCount = 0;

    // listener
    private OnChangeListener onItemChangeListener = null;

    private Context context;

    public MultiToggleButton(Context context) {
        super(context);
    }

    public MultiToggleButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiToggleButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context, attrs, defStyleAttr);
    }

    public MultiToggleButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(final Context context, @Nullable AttributeSet attrs, int defStyleAttr){
        // views
        TextView tvTgb = findViewById(R.id.tvTgb);
        ImageButton tgb = findViewById(R.id.tgb);

        textSize = toDP(context, 12f);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null)
            inflater.inflate(R.layout.layout_multi_toggle_button, this);
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.MultiToggleButton);


        try {
            text = typeArray.getString(R.styleable.MultiToggleButton_text);
            buttonPadding = typeArray.getDimension(R.styleable.MultiToggleButton_buttonPadding, toDP(context,8f));
            toggleButtonTint =
                    typeArray.getResourceId(R.styleable.MultiToggleButton_toggleButtonTint, 0);
            textSize = typeArray.getDimension(R.styleable.MultiToggleButton_textSize, toDP(context,14f));
            textColor = typeArray.getResourceId(R.styleable.MultiToggleButton_textColor, 12);
            textStyle = typeArray.getInt(R.styleable.MultiToggleButton_textStyle, TEXT_STYLE_NORMAL);
            toggleButtonSize = typeArray.getDimensionPixelSize(R.styleable.MultiToggleButton_toggleButtonSize,  Math.round(toDP(context,24f)));

            tvTgb.setText(text);
            tvTgb.setPadding(buttonPadding.intValue(), 0, 0, 0);
            tvTgb.setTextColor(context.getResources().getColor(textColor));
            tvTgb.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);


            switch (textStyle){
                case TEXT_STYLE_BOLD:
                    tvTgb.setTypeface(Typeface.DEFAULT_BOLD);
                    break;
                case TEXT_STYLE_ITALIC:
                    tvTgb.setTypeface(tvTgb.getTypeface(), Typeface.ITALIC);
                    break;
                default:
                    tvTgb.setTypeface(Typeface.DEFAULT);
                    break;
            }

            if (toggleButtonTint != 0)
                tgb.setImageTintList(context.getResources().getColorStateList(toggleButtonTint));

            tgb.getLayoutParams().height = Math.round(toggleButtonSize);
            tgb.getLayoutParams().width = Math.round(toggleButtonSize);
        }finally {
            typeArray.recycle();
        }

        tgb.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setToggleImageResource();
                setCurrentImageResource(context);

                if (onItemChangeListener != null){
                    onItemChangeListener.onItemChangeListener(getCurrentResource(),
                            currentItem);
                }
            }
        });
    }

    public void addToggleDrawables(int... resources) {
        toggleDrawables = new ArrayList<>();
        if (resources != null) {
            for (int r : resources) {
                toggleDrawables.add(r);
            }
            itemCount = resources.length;
            setCurrentImageResource(context);
        }
    }

    public void addToggleDrawables(ArrayList<Integer> resources) {
        toggleDrawables = new ArrayList<>();
        if (resources != null) {
            toggleDrawables.addAll(resources);
            itemCount = resources.size();
            setCurrentImageResource(context);
        }
    }

    private void setCurrentImageResource(Context context) {
        if (itemCount > 0){
            if (toggleDrawables != null){
                if (currentItem < itemCount) {
                    if (toggleDrawables.get(currentItem) != null){
                        context.getResources().getDrawable(toggleDrawables.get(currentItem));
                    }
                }
            }
        }
    }

    private void setToggleImageResource() {
        currentItem = (currentItem == itemCount - 1) ? 0 : currentItem + 1;
    }

    private int getCurrentResource(){
        if (toggleDrawables != null){
            if (currentItem < itemCount){
                return toggleDrawables.get(currentItem);
            }
        }
        return 0;
    }


    public void setOnItemChangeListener(OnChangeListener onItemChangeListener) {
        this.onItemChangeListener = onItemChangeListener;
    }

    public int getCurrentItem(){
        return getCurrentResource();
    }


    private Float toDP(Context context, Float value) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                value,
                context.getResources().getDisplayMetrics()
        );
    }

    private interface OnChangeListener{
        void onItemChangeListener(int resourceId, int position);
    }
}
