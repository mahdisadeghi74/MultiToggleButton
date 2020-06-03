package com.dependencies.buria.moduleproject

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var toggleDrawables = arrayListOf<String>("R.drawable.ic_launcher_background", "R.drawable.ic_android")

        tgba.addToggleText(toggleDrawables)

        tgba.setOnTextChangeListener { text, position ->
            Toast.makeText(this, "" + text + " : " + position, Toast.LENGTH_SHORT).show()
        }

        tgba.setOnItemChangeListener { text, position ->
            Toast.makeText(this, "" + text + " : " + position, Toast.LENGTH_SHORT).show()
        }
    }
}
