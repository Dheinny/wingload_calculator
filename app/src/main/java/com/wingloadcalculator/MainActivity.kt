package com.wingloadcalculator

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calcWingLoadAction(view: View) {
        val weightView = findViewById<EditText>(R.id.pesoAtletaVal)
        val sizeView = findViewById<EditText>(R.id.tamVelameVal)
        val weight = if (weightView.text.toString().isEmpty()) 0.0 else weightView.text.toString().toDouble()
        val size = if (sizeView.text.toString().isEmpty()) 1.0 else sizeView.text.toString().toDouble()

        var result = calcWindLoad(weight, size)

        findViewById<TextView>(R.id.wlResult).text = String.format("%.4f", result)
    }

    fun calcWindLoad(weight: Double = 0.0, size: Double = 1.0): Double {
        val result = ((weight * 2.2) / size)
        return result
    }

}