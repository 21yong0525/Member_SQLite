package com.yong.membersqlite

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.yong.membersqlite.databinding.ItemMemberBinding

class CustomAdapter(val memberList: MutableList<Member>) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = ItemMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val customViewHolder = CustomViewHolder(binding)

        customViewHolder.itemView.setOnClickListener {
            val position: Int = customViewHolder.adapterPosition
            var member = memberList.get(position)
            val customDialog = CustomDialog(parent.context)
            customDialog.showDialog(member,binding)
        }

        customViewHolder.itemView.setOnLongClickListener {
            val position: Int = customViewHolder.adapterPosition
            val member = memberList.get(position)
            val dbHelper = DBHelper(parent.context,"memberDB.db",null,1)
            val flag = dbHelper.delete(member.id)
            if (flag) {
                Toast.makeText(parent.context,"삭제완료",Toast.LENGTH_SHORT).show()
            }
            val listActivity = parent.context as ListActivity
            listActivity.refreshRecyclerViewDrop(member)
            true
        }
        return customViewHolder
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val binding = holder.binding
        val member = memberList.get(position)
        binding.tvID.text = "아이디 : ${member.id}"
        binding.tvName.text = "이름 : ${member.name}"
        binding.tvIPWD.text = "비밀번호 : ${member.password}"
        binding.tvgender.text = "성별 : ${member.gender}"
        binding.tvIPhone.text = "전화번호 : ${member.phone}"
        binding.tvIEmail.text = "이메일 : ${member.email}"
        binding.tvIAddress.text = "주소 : ${member.address}"
        binding.tvILevel.text = "직급 : ${member.level}"
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    class CustomViewHolder(val binding: ItemMemberBinding) : RecyclerView.ViewHolder(binding.root)
}