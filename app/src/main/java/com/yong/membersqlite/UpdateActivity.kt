package com.yong.membersqlite

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yong.membersqlite.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    var member: Member? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearchId.setOnClickListener {
            val dbHelper = DBHelper(applicationContext,"memberDB.db",null,1)
            val id = binding.edtUpdateId.text.toString()
            var flag = true

            if (id.isEmpty()) {
                flag = false
                Toast.makeText(this,"아이디를 입력하세요",Toast.LENGTH_SHORT).show()
            }

            if (flag) {
                member = dbHelper.selectID(id)
                if (member != null){
                    binding.edtUpdatePhone.setText(member?.phone.toString())
                    binding.edtUpdateName.setText(member?.name.toString())
                    binding.edtUpdateLevel.setText(member?.level.toString())
                    binding.edtUpdatePhone.isFocusableInTouchMode = true
                    binding.edtUpdateName.isFocusableInTouchMode = true
                    binding.edtUpdateLevel.isFocusableInTouchMode = true
                } else {
                    flag = false
                    Toast.makeText(this,"수정할 데이터 정보 가져오지 못함",Toast.LENGTH_SHORT).show()
                }
            }

            dbHelper.close()
        }

        binding.btnUpdate.setOnClickListener {
            val dbHelper = DBHelper(applicationContext,"memberDB.db",null,1)
            val phone = binding.edtUpdatePhone.text.toString()
            val name = binding.edtUpdateName.text.toString()
            val level = binding.edtUpdateLevel.text.toString()
            var flag = false

            if (member != null && phone.isNotBlank() && name.isNotBlank() && level.isNotBlank()) {
                member!!.phone = "전화번호 : $phone"
                member!!.name = "이름 : $name"
                member!!.level = "직급 : $level"

                flag = dbHelper.update(member)

                if (flag) {
                    Toast.makeText(this,"수정 성공",Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this,"수정 실패",Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this,"수정할 데이터를 입력 바랍니다",Toast.LENGTH_SHORT).show()
            }
            dbHelper.close()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

    }
}