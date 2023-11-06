/*
 * Copyright (c) 2023. Created by Alexsander at 11/6. All rights reserved.
 * GitHub: https://github.com/alexsanderfer/
 * Portfolio: https://alexsanderfer.netlify.app/
 */

package com.alexsanderdev.motivation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import com.alexsanderdev.motivation.R
import com.alexsanderdev.motivation.data.Mock
import com.alexsanderdev.motivation.databinding.ActivityMainBinding
import com.alexsanderdev.motivation.infrastructure.MotivationConstants
import com.alexsanderdev.motivation.infrastructure.SecurityPreferences

class MainActivity : ComponentActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryID = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handeUserName()
        handleFilter(R.id.image_all)
        handleNextPhrase()

        // Eventos
        binding.buttonNewPhrase.setOnClickListener(this)

        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase) {
            handleNextPhrase()
        } else if (view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)) {
            handleFilter(view.id)
        }
    }

    private fun handleNextPhrase() {
        binding.textPhrase.text = Mock().getPhrase(categoryID)
    }

    private fun handleFilter(id: Int) {

        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryID = MotivationConstants.FILTER.ALL
            }

            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryID = MotivationConstants.FILTER.HAPPY
            }

            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryID = MotivationConstants.FILTER.SUNNY
            }

        }
    }

    private fun handeUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = getString(R.string.greetings, name)
    }
}
