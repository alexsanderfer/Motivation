package com.alexsanderdev.motivation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import com.alexsanderdev.motivation.R
import com.alexsanderdev.motivation.databinding.ActivityMainBinding
import com.alexsanderdev.motivation.infrastructure.MotivationConstants
import com.alexsanderdev.motivation.infrastructure.SecurityPreferences

class MainActivity : ComponentActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handeUserName()

        // Eventos
        binding.buttonNewPhrase.setOnClickListener(this)

        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase) {
            var s = ""
        } else if (view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)) {
            handleFilter(view.id)
        }
    }

    private fun handleFilter(id: Int) {

        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))

        if (id == R.id.image_all) {
        }
    }

    private fun handeUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = getString(R.string.greetings, name)
    }
}
