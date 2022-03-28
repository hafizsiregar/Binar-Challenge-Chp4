package com.example.binarchallengechp4

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.binarchallengechp4.data.Computer
import com.example.binarchallengechp4.data.Player
import com.example.binarchallengechp4.databinding.ActivityMainBinding
import com.example.binarchallengechp4.domain.Choice

class MainActivity : AppCompatActivity(), Game {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var binding: ActivityMainBinding
    private lateinit var computer: Computer
    private lateinit var player: Player
    private var isGameFinished: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        computer = Computer()
        player = Player()
        startGame()
    }

    override fun startGame() {
        initialChoice()
        setBackgroundChoice()
        initialState()
        setOnClickListeners()
    }

    private fun initialChoice() {
        player.choice = arrayListOf(
            binding.imgBatu1,
            binding.icKertas1,
            binding.imgGunting1
        )

        computer.choice = arrayListOf(
            binding.imgBatu2,
            binding.imgKertas2,
            binding.imgGunting2
        )
    }

    private fun setBackgroundChoice() {
        player.choice[Choice.BATU.index].setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_batu
            )
        )
        player.choice[Choice.KERTAS.index].setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_kertas
            )
        )
        player.choice[Choice.GUNTING.index].setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_gunting
            )
        )

        computer.choice[Choice.BATU.index].setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_batu
            )
        )
        computer.choice[Choice.KERTAS.index].setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_kertas
            )
        )
        computer.choice[Choice.GUNTING.index].setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_gunting
            )
        )
    }

    private fun initialState() {
        (player.choice + computer.choice).forEach { it.background = null }
        binding.imgVersus.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_versus))
        isGameFinished = false
    }

    private fun setOnClickListeners() {
        player.choice.forEachIndexed { index, image ->
            image.setOnClickListener {
                if (!isGameFinished) {
                    Log.i(TAG, "setOnClickListeners: ${Choice.getValueFromIndex(index)}")
                    player.player = index
                    it.background = ContextCompat.getDrawable(this, R.drawable.shape_rectangle_8_ffc700)
                    computer.player = (0..2).random()
                    computer.choice[computer.player].background =
                        ContextCompat.getDrawable(this, R.drawable.shape_rectangle_8_ffc700)
                    resultWinner()
                    isGameFinished = true
                }
            }
        }

        binding.imgRefresh.setOnClickListener {
            initialState()
        }
    }

    override fun resultWinner() {
        when {
            (player.player + 1) % 3 == computer.player -> {
                Log.i(TAG, "decideWinner: Player 2 Win")
                binding.imgVersus.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_result2
                    )
                )
            }
            player.player == computer.player -> {
                Log.i(TAG, "decideWinner: Draw")
                binding.imgVersus.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_draw
                    )
                )
            }
            else -> {
                Log.i(TAG, "decideWinner: Player 1 Win")
                binding.imgVersus.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_result1
                    )
                )
            }
        }
    }
}