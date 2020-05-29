package com.dependencies.buria.moduleproject

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dependencies.buria.multitogglebutton.MultiToggleButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mtb.addToggleDrawables(R.drawable.ic_launcher_background, R.drawable.ic_android)
        Toast.makeText(applicationContext, "resourceId: ${mtb.getCurrentItem()}", Toast.LENGTH_SHORT).show()
        mtb.setOnItemChangeListener { resourceId, position ->
            Toast.makeText(applicationContext, "resourceId: $resourceId position: $position", Toast.LENGTH_SHORT).show()
        }


    }
}
