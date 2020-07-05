package com.assignment.shadiandroidtest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.shadiandroidtest.databinding.RowItemUserListBinding
import com.assignment.shadiandroidtest.entities.user.UserEntity
import com.assignment.shadiandroidtest.utils.GlideUtil

class UserAdapter(private val context: Context, private val userData: List<UserEntity>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            RowItemUserListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return userData.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userData.get(position)
        holder.binding.apply {
            val name = currentUser.name
            tvUserName.text = "${name?.title} ${name?.first} ${name?.first}"
            tvEmailId.text = currentUser.email
            tvDob.text = currentUser.dob?.date
        }

        GlideUtil.loadImage(currentUser.picture?.large, holder.binding.civPersonProfilePic, context)
    }

    class UserViewHolder(val binding: RowItemUserListBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            // Accept
            binding.tvBtnAccept.setOnClickListener {

            }

            // Decline
            binding.tvBtnDecline.setOnClickListener {

            }

        }

    }
}