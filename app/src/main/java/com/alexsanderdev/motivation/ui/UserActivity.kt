package com.alexsanderdev.motivation.ui

import android.content.Intent
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

        verifyUserName()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_save) {
            handleSave()
        }
    }

    private fun verifyUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        if (name.isNotEmpty()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun handleSave() {
        val name = binding.editName.text.toString()
        if (name.isNotEmpty()) {
            SecurityPreferences(this).storageString(MotivationConstants.KEY.USER_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, getString(R.string.validation_mandatory_name), Toast.LENGTH_SHORT).show()
        }
    }
}