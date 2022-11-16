package com.yong.membersqlite

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yong.membersqlite.databinding.ActivityDeleteBinding

class DeleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDelete2.setOnClickListener {
            val dbHelper = DBHelper(applicationContext,"memberDB.db",null,1)
            var flag = false
            val id = binding.edtID.text.toString()

            if (id.isEmpty()){
                Toast.makeText(this,"ID 정보 입력바람", Toast.LENGTH_SHORT).show()
                flag = false
            }
            
            if (dbHelper.delete(id)){
                flag = true
                Toast.makeText(this,"삭제 성공", Toast.LENGTH_SHORT).show()
            }

            if (flag) {
                finish()
            } else {
                Toast.makeText(this,"삭제 실패", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnMain2.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}