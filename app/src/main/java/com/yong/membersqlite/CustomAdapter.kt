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
        binding.tvID.append(member.id)
        binding.tvName.append(member.name)
        binding.tvIPWD.append(member.password)
        binding.tvgender.append(member.gender)
        binding.tvIPhone.append(member.phone)
        binding.tvIEmail.append(member.email)
        binding.tvIAddress.append(member.address)
        binding.tvILevel.append(member.level)
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    class CustomViewHolder(val binding: ItemMemberBinding) : RecyclerView.ViewHolder(binding.root)
}