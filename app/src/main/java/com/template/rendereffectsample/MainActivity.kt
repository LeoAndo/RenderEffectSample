package com.template.rendereffectsample

import android.graphics.RenderEffect
import android.graphics.Shader
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class MainActivity : AppCompatActivity() {
    private val blurTargets: List<BlurTarget>
        get() {
            val tmp = mutableListOf<BlurTarget>()
            val checkBox = findViewById<CheckBox>(R.id.checkBox)
            val imageView = findViewById<ImageView>(R.id.imageView)
            tmp.add(BlurTarget(imageView, checkBox.isChecked))

            val checkBox2 = findViewById<CheckBox>(R.id.checkBox2)
            tmp.add(BlurTarget(window.decorView, checkBox2.isChecked))
            return tmp.toList()
        }
    private val tileMode: Shader.TileMode?
        get() {
            val dropdown = findViewById<MaterialAutoCompleteTextView>(R.id.dropdown)
            return Shader.TileMode.values().firstOrNull {
                it.toString() == dropdown.text.toString()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // find Views.
        val sliderRadiusX = findViewById<Slider>(R.id.slider1)
        val sliderRadiusY = findViewById<Slider>(R.id.slider2)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val checkBox2 = findViewById<CheckBox>(R.id.checkBox2)

        // setup dropdown.
        val firstItem = Shader.TileMode.values().map { it.toString() }.first()
        val items = Shader.TileMode.values().map { it.toString() }
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, items)
        val dropdown = findViewById<MaterialAutoCompleteTextView>(R.id.dropdown)
        dropdown.setAdapter(adapter)
        dropdown.setOnItemClickListener { _, _, _, _ ->
            setRenderEffect(blurTargets, sliderRadiusX.value, sliderRadiusY.value, tileMode)
        }

        // setup slider
        sliderRadiusX.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {}
            override fun onStopTrackingTouch(slider: Slider) {
                setRenderEffect(blurTargets, sliderRadiusX.value, sliderRadiusY.value, tileMode)
            }
        })
        sliderRadiusY.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {}
            override fun onStopTrackingTouch(slider: Slider) {
                setRenderEffect(blurTargets, sliderRadiusX.value, sliderRadiusY.value, tileMode)
            }
        })

        // setup checkbox
        checkBox.setOnCheckedChangeListener { _, _ ->
            setRenderEffect(blurTargets, sliderRadiusX.value, sliderRadiusY.value, tileMode)
        }
        checkBox2.setOnCheckedChangeListener { _, _ ->
            setRenderEffect(blurTargets, sliderRadiusX.value, sliderRadiusY.value, tileMode)
        }
    }

    /**
     * ぼかし設定を行う.
     *
     * @param blurTargets ぼかし設定を行う対象のView
     */
    private fun setRenderEffect(
        blurTargets: List<BlurTarget>,
        radiusX: Float,
        radiusY: Float,
        edgeTreatment: Shader.TileMode?
    ) {
        Log.d(
            "MainActivity",
            "blurTargets: $blurTargets radiusX: $radiusX radiusY: $radiusY edgeTreatment: $edgeTreatment"
        )
        for (blurTarget in blurTargets) {
            if (blurTarget.blur) {
                // ぼかし設定.
                try {
                    edgeTreatment?.let {
                        blurTarget.view.setRenderEffect(
                            RenderEffect.createBlurEffect(
                                radiusX,
                                radiusY,
                                it
                            )
                        )
                    } ?: run {
                        Log.e("MainActivity", "Select Tile Mode (required parameter)")
                        Toast.makeText(
                            this,
                            "Select Tile Mode (required parameter)",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (ex: IllegalArgumentException) {
                    // radiusX, radiusYに 0.0を指定すると以下のExceptionが発生する.
                    // java.lang.IllegalArgumentException: nativePtr is null
                    Log.e("MainActivity", "ex: $ex")
                    Toast.makeText(this, "ex: $ex", Toast.LENGTH_SHORT).show()
                }
            } else {
                // ぼかし設定をクリアする.
                blurTarget.view.setRenderEffect(null)
            }
        }
    }
}