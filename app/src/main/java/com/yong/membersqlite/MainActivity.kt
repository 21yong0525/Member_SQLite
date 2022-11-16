package com.yong.membersqlite

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yong.membersqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun viewOnClick(view: View?) {
        when (view?.id) {
            R.id.btnMain2 -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.btnLogin -> {

                val dbHelper = DBHelper(applicationContext,"memberDB.db",null,1)
                var flag = false
                val id = binding.edtID.text.toString()
                val password = binding.edtPWD.text.toString()

                if (id.isEmpty() || password.isEmpty()){
                    Toast.makeText(this,"모든 정보를 입력하세요",Toast.LENGTH_SHORT).show()
                    flag = false
                }

                if (dbHelper.selectLogin(id, password)){
                    flag = true
                }

                if (flag) {
                    val intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this,"로그인 실패",Toast.LENGTH_SHORT).show()
                }
            }
            R.id.btnRegister -> {
                val intent = Intent(this,RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }
}