/*
 * Copyright (c) 2023. Created by Alexsander at 11/6. All rights reserved.
 * GitHub: https://github.com/alexsanderfer/
 * Portfolio: https://alexsanderfer.netlify.app/
 */

package com.alexsanderdev.motivation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.alexsanderdev.motivation.R
import com.alexsanderdev.motivation.databinding.ActivityUserBinding
import com.alexsanderdev.motivation.infrastructure.MotivationConstants
import com.alexsanderdev.motivation.infrastructure.SecurityPreferences

class UserActivity : ComponentActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        binding.buttonSave.setOnClickListener(this)

        setContentView(binding.root)


    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_save) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = binding.editName.text.toString()
        if (name.isNotEmpty()) {
            SecurityPreferences(this).storageString(MotivationConstants.KEY.USER_NAME, name)

            finish()
        } else {
            Toast.makeText(this, getString(R.string.validation_mandatory_name), Toast.LENGTH_SHORT).show()
        }
    }
}