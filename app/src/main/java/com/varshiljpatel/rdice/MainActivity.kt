package com.varshiljpatel.rdice

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.varshiljpatel.rdice.databinding.ActivityMainBinding
import java.util.Random
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private var randomInt by Delegates.notNull<Int>()
    private var flag : Boolean = true

    // Data Binding
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.rollBtn.setOnClickListener {
            if (flag) {
                flag = false
                binding.diceImg.setImageResource(R.drawable.dice0)
                binding.digitTxt.text = "..."
                Handler().postDelayed({
                    try {
                        randomInt = Random().nextInt(6) + 1
                        when (randomInt) {
                            1 -> binding.diceImg.setImageResource(R.drawable.dice1)
                            3 -> binding.diceImg.setImageResource(R.drawable.dice3)
                            2 -> binding.diceImg.setImageResource(R.drawable.dice2)
                            4 -> binding.diceImg.setImageResource(R.drawable.dice4)
                            5 -> binding.diceImg.setImageResource(R.drawable.dice5)
                            6 -> binding.diceImg.setImageResource(R.drawable.dice6)
                        }
                        binding.digitTxt.text = randomInt.toString()
                        flag = true
                    } catch (e : Exception) {
                        e.printStackTrace()
                    }
                }, 1500)
            }
        }
        /*
        val rollBtn : ImageView = findViewById(R.id.roll_btn)
        val diceImg : ImageView = findViewById(R.id.dice_img)
        val digit : TextView = findViewById(R.id.digit_txt)
         */
    }

}

