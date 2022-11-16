package com.yong.membersqlite

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Toast
import com.yong.membersqlite.databinding.DialogCustomBinding
import com.yong.membersqlite.databinding.ItemMemberBinding

class CustomDialog(val context: Context) {
    val dialog = Dialog(context)
    val binding = DialogCustomBinding.inflate(LayoutInflater.from(context))

    fun showDialog(member: Member, binding2: ItemMemberBinding) {
        dialog.setContentView(this.binding.root)
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
        )

        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        dialog.show()

        binding.tvUpdateId.setText("${member.id}님의 정보를 수정합니다.")
        binding.edtUpdatePhone.setText(member.phone)
        binding.edtUpdateName.setText(member.name)
        binding.edtUpdateLevel.setText(member.level)
        binding.edtUpdatePhone.isFocusableInTouchMode = true
        binding.edtUpdateName.isFocusableInTouchMode = true
        binding.edtUpdateLevel.isFocusableInTouchMode = true

        this.binding.btnUpdate.setOnClickListener {
            val dbHelper = DBHelper(context,"memberDB.db",null,1)

            val phone = binding.edtUpdatePhone.text.toString()
            val name = binding.edtUpdateName.text.toString()
            val level = binding.edtUpdateLevel.text.toString()
            var flag = false

            if (member != null && phone.isNotBlank() && name.isNotBlank() && level.isNotBlank()) {
                flag = dbHelper.update(member)
                binding2.tvName.text = "이름 : $name"
                binding2.tvIPhone.text = "전화번호 : $phone"
                binding2.tvILevel.text = "직급 : $level"

                if (flag) {
                    Toast.makeText(context,"수정 성공",Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context,"수정할 실패",Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context,"수정할 데이터 입력 바랍니다",Toast.LENGTH_SHORT).show()
            }
            dbHelper.close()
            dialog.dismiss()
        }

        this.binding.btnCancle.setOnClickListener {
            dialog.dismiss()
        }

    }
}