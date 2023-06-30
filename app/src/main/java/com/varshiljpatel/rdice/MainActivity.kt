package com.varshiljpatel.rdice

import android.annotation.SuppressLint
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.varshiljpatel.rdice.databinding.ActivityMainBinding
import java.util.Random
import kotlin.properties.Delegates
import android.os.Vibrator
import android.os.VibrationEffect
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@SuppressLint("ServiceCast")
class MainActivity : AppCompatActivity() {
    private var randomInt by Delegates.notNull<Int>()
    private var flag : Boolean = true
    private var prevRandomInt : Int = 0
    @RequiresApi(Build.VERSION_CODES.S)

    // Data Binding
    private lateinit var binding : ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.S)
    @SuppressLint("SetTextI18n", "ServiceCast")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        val vibrator = this.getSystemService(VIBRATOR_SERVICE) as Vibrator
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.rollBtn.setOnClickListener {
            if (flag) {

                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(VibrationEffect.createOneShot(75, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    vibrator.vibrate(75)
                }

                flag = false
                try {
                    Glide.with(this)
                        .asGif()
                        .load(R.drawable.dice_gif)
                        .apply(RequestOptions().centerCrop())
                        .into(binding.diceImg)
                } catch (e : Exception) {
                    binding.diceImg.setImageResource(R.drawable.dice0)
                }
                binding.digitTxt.text = "..."

                // Handler
                Handler(Looper.myLooper()!!).postDelayed({
                    try {
                        randomInt = Random().nextInt(6) + 1
                        if (prevRandomInt == randomInt) {
                            randomInt = Random().nextInt(6) + 1
                        }
                        prevRandomInt = randomInt
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
