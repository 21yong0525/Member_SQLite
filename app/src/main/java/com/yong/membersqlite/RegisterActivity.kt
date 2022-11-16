package com.yong.membersqlite

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yong.membersqlite.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var spinnerList = arrayOf("선택안함", "남성", "여성")
        binding.spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerList)
        lateinit var gender: String
        //아이템 선택 리스너
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                gender = spinnerList[position]
            }
        }

        binding.btnSave.setOnClickListener {
            val dbHelper = DBHelper(applicationContext,"memberDB.db",null,1)

            val id = binding.edtID.text.toString()
            val name = binding.edtName.text.toString()
            val password = binding.edtPWD1.text.toString()
            val password2 = binding.edtPWD2.text.toString()
            val phone = "${binding.edtPhone.text}-${binding.edtPhone2.text}-${binding.edtPhone3.text}"
            val email = binding.edtEmail.text.toString()
            val address = binding.edtAddress.text.toString()
            val level = binding.edtLevel.text.toString()
            val member = Member(id, name, password, gender, phone, email, address, level)
            var flag = true

            if (id.isEmpty() || name.isEmpty() || phone == "010--" || password.isEmpty() || password2.isEmpty() || email.isEmpty() || address.isEmpty() || level.isEmpty() || gender.isEmpty()) {
                Toast.makeText(this,"모든 정보를 입력하세요",Toast.LENGTH_SHORT).show()
                flag = false
            }

            if (!password.equals(password2)){
                Toast.makeText(this,"비번을 확인하세용",Toast.LENGTH_SHORT).show()
                flag = false
            }

            if (dbHelper.selectCheckID(id)){
                Toast.makeText(this,"아이디가 중복이에요",Toast.LENGTH_SHORT).show()
                flag = false
            }

            if (!dbHelper.insert(member)) {
                Toast.makeText(this,"회원가입 오류",Toast.LENGTH_SHORT).show()
                flag = false
            }

            if (flag) {
                finish()
            }
            dbHelper.close()
        }

        binding.btnReturn.setOnClickListener {
            finish()
        }
    }

}