package com.assignment.shadiandroidtest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.TorrMonk.extension.setVisibility
import com.assignment.shadiandroidtest.R
import com.assignment.shadiandroidtest.constants.Status
import com.assignment.shadiandroidtest.databinding.RowItemUserListBinding
import com.assignment.shadiandroidtest.entities.user.UserEntity
import com.assignment.shadiandroidtest.utils.GlideUtil

class UserAdapter(
    private val context: Context,
    private val userData: MutableList<UserEntity>,
    private val userAdapterListener: UserAdapterListener
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

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
        holder.bind(currentUser)
    }

    fun updateUser(updatedUser: UserEntity, adapterPosition: Int) {
        userData[adapterPosition] = updatedUser
        notifyItemChanged(adapterPosition)
    }

    inner class UserViewHolder(val binding: RowItemUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

            // Accept
            binding.tvBtnAccept.setOnClickListener {
                val currentUser = userData.get(absoluteAdapterPosition)
                userAdapterListener.acceptUser(currentUser, absoluteAdapterPosition)
            }

            // Decline
            binding.tvBtnDecline.setOnClickListener {
                val currentUser = userData.get(absoluteAdapterPosition)
                userAdapterListener.declineUser(currentUser, absoluteAdapterPosition)
            }

        }

        fun bind(currentUser: UserEntity) {
            binding.apply {
                val name = currentUser.name
                tvUserName.text = "${name?.title} ${name?.first} ${name?.first}"
                tvEmailId.text = currentUser.email
                tvDob.text = currentUser.dob?.date

                currentUser.userStatus.let {
                    tvUserStatus.apply {
                        text = "User ${it.status?.name}!"
                    }
                }
                toggleButtonView(currentUser.userStatus.voted)
                GlideUtil.loadImage(
                    currentUser.picture?.large,
                    binding.civPersonProfilePic,
                    context
                )
            }
        }

        private fun toggleButtonView(voted: Boolean) {
            binding.llButton.setVisibility(!voted)
            binding.tvUserStatus.setVisibility(voted)
        }


    }
}