package com.example.checkiprojekt

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var PrzedmiotyKoszyk: Map<String, List<String>>
    private var WybraneArtykuły: MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PrzedmiotyKoszyk = mapOf(
            "Warzywa" to listOf("Pomidor", "Bakłażan", "Ogórek"),
            "Mięsa" to listOf("Kurczak", "Szynka", "Kiełbasa"),
            "Pieczywo" to listOf("Bagietka", "Chleb", "Bułka","Bajgiel")
        )
        fun DodawanieProduktu() {
            val LayoutCheckbox = findViewById<View>(R.id.checkboxesLayout)
            val LayoutChip = findViewById<View>(R.id.chipsLayout)

            LayoutCheckbox.visibility = View.VISIBLE
            LayoutChip.visibility = View.GONE
        }
        fun UsuwanieProduktu() {
            val checkboxesLayout = findViewById<View>(R.id.checkboxesLayout)
            val chipsLayout = findViewById<View>(R.id.chipsLayout)

            checkboxesLayout.visibility = View.GONE
            chipsLayout.visibility = View.VISIBLE

            val GrupaChip = findViewById<ChipGroup>(R.id.chipsGroup)
            GrupaChip.removeAllViews()

            for ((groupName, items) in PrzedmiotyKoszyk) {
                for (item in items) {
                    if (WybraneArtykuły.contains(item)) {
                        val chip = Chip(this)
                        chip.text = item
                        chip.isCloseIconVisible = true
                        chip.tag = groupName

                        when (groupName) {
                            "Warzywa" -> {
                                chip.chipBackgroundColor =
                                    resources.getColorStateList(R.color.KolorWarzywa)
                            }
                            "Mięsa" -> {
                                chip.chipBackgroundColor =
                                    resources.getColorStateList(R.color.KolorMieso)
                            }
                            "Pieczywo" -> {
                                chip.chipBackgroundColor =
                                    resources.getColorStateList(R.color.KolorPieczywo)
                            }
                        }
                        GrupaChip.addView(chip)

                        chip.setOnCloseIconClickListener {
                            WybraneArtykuły.remove(item)
                            GrupaChip.removeView(chip)
                        }
                    }
                }
            }
        }
    }
}