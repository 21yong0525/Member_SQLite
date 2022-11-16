package com.yong.membersqlite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.yong.membersqlite.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {
    var memberList = arrayListOf<Member>()
    lateinit var customAdapter: CustomAdapter
    lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataSetting()

        binding.ftbtnAddUser.setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    fun refreshRecyclerViewDrop(member: Member) {
        memberList.remove(member)
        customAdapter?.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        dataSetting()
    }

    fun dataSetting() {
        val dbHelper = DBHelper(this,"memberDB.db",null,1)
        memberList = dbHelper.selectList()

        val linearLayoutManager = LinearLayoutManager(this)
        customAdapter = CustomAdapter(memberList)
        binding.recyclerview.adapter = customAdapter
        binding.recyclerview.layoutManager = linearLayoutManager
    }
}