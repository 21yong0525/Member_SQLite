package com.yong.membersqlite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.yong.membersqlite.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun viewOnClick(view: View?) {
        when(view?.id) {
            R.id.btnAdd -> {
                val intent = Intent(this,RegisterActivity::class.java)
                startActivity(intent)
            }
            R.id.btnDelete -> {
                val intent = Intent(this,DeleteActivity::class.java)
                startActivity(intent)
            }
            R.id.btnUpdate -> {
                val intent = Intent(this,UpdateActivity::class.java)
                startActivity(intent)
            }
            R.id.btnList -> {
                val intent = Intent(this,ListActivity::class.java)
                startActivity(intent)
            }
            R.id.btnBack -> {
                finish()
            }
        }
    }
}