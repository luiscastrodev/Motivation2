package com.example.motivation2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation2.R
import com.example.motivation2.databinding.ActivityUserBinding
import com.example.motivation2.ui.infra.MotivationConstants
import com.example.motivation2.ui.infra.SecurityPreferences

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding
    private lateinit var securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        securityPreferences = SecurityPreferences(this)

        setListeners()

        VerifyUsername()
    }

    override fun onClick(v: View) {

        var name = binding.editName.text.toString()

        if (name.isEmpty()) {
            Toast.makeText(this, "Preencha o nome", Toast.LENGTH_SHORT).show()
            return
        }

        securityPreferences.storeString(MotivationConstants.KEY.PERSON_NAME,name)

        redirectToMain()
    }

    private fun VerifyUsername(){
        val name = SecurityPreferences(this).getStoredString(MotivationConstants.KEY.PERSON_NAME)

        if(name != ""){
            redirectToMain()
        }
    }

    private fun setListeners() {
        binding.buttonSave.setOnClickListener(this)
    }

    private fun redirectToMain(){
        var newIntent = Intent(this, MainActivity::class.java)
        startActivity(newIntent)
        finish()
    }
}