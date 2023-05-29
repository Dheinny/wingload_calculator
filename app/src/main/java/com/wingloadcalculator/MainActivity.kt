package com.wingloadcalculator

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children

class MainActivity : AppCompatActivity() {

    val default_edit_text_material = androidx.appcompat.R.drawable.abc_edit_text_material

    lateinit var weightView: EditText
    lateinit var sizeView: EditText
    lateinit var wlView: EditText
    lateinit var fieldCalcRadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fieldCalcRadioGroup= findViewById<RadioGroup>(R.id.fieldCalc)
        val weightRadio = findViewById<RadioButton>(R.id.skydiverWeight_radio)
        val sizeRadio = findViewById<RadioButton>(R.id.canopySize_radio)

        weightView = findViewById<EditText>(R.id.skydiverWeightVal)
        sizeView = findViewById<EditText>(R.id.canopySizeVal)
        wlView = findViewById<EditText>(R.id.wingloadVal)

        fieldCalcRadioGroup.setOnCheckedChangeListener { radioGroup, optionId ->
            run {
                weightView.setBackgroundResource(default_edit_text_material)
                weightView.setTextColor(Color.BLACK)
                weightView.isEnabled = true
                sizeView.setBackgroundResource(default_edit_text_material)
                sizeView.setTextColor(Color.BLACK)
                sizeView.isEnabled = true
                wlView.setBackgroundResource(default_edit_text_material)
                wlView.setTextColor(Color.BLACK)
                wlView.isEnabled = true

                val resultColor = Color.DKGRAY
                val resultBGColor = Color.LTGRAY
                when (findViewById<RadioButton>(optionId)) {
                    weightRadio -> {
                        weightView.setBackgroundColor(resultBGColor)
                        weightView.setTextColor(resultColor)
                        weightView.isEnabled = false
                    }
                    sizeRadio -> {
                        sizeView.setBackgroundColor(resultBGColor)
                        sizeView.setTextColor(resultColor)
                        sizeView.isEnabled = false
                    }
                    else -> {
                        wlView.setBackgroundColor(resultBGColor)
                        wlView.setTextColor(resultColor)
                        wlView.isEnabled = false
                    }
                }
            }
        }
    }

    fun calcParamAction(view: View) {
        val weight = if (weightView.text.toString().isEmpty()) 0.0 else weightView.text.toString().toDouble()
        val size = if (sizeView.text.toString().isEmpty()) 1.0 else sizeView.text.toString().toDouble()
        val wingload = if (wlView.text.toString().isEmpty()) 1.0 else wlView.text.toString().toDouble()

        when (fieldCalcRadioGroup.checkedRadioButtonId) {
            R.id.skydiverWeight_radio -> weightView.text =
                                        String.format("%.1f", calcSkydiverWeight(size, wingload))
                                            .toEditable()
            R.id.canopySize_radio -> sizeView.text =
                                        String.format("%.0f", calcCanopySize(weight, wingload))
                                            .toEditable()
            R.id.wingload_radio -> wlView.text =
                                        String.format("%.4f", calcWindload(weight, size))
                                            .toEditable()
            else -> 0
        }
    }



    fun calcSkydiverWeight(size: Double = 1.0, wingload: Double = 1.0): Double {
        val weight = (wingload * size) / 2.2
        return weight
    }

    fun calcCanopySize(weight: Double = 0.0, wingload: Double = 1.0): Double {
        val size = (weight * 2.2) / wingload
        return size
    }

    fun calcWindload(weight: Double = 0.0, size: Double = 1.0): Double {
        val wingload = (weight * 2.2) / size
        return wingload
    }

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
}

