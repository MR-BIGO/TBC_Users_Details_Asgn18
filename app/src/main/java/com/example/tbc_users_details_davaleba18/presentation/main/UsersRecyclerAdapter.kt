package com.example.tbc_users_details_davaleba18.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbc_users_details_davaleba18.databinding.UsersListItemBinding
import com.example.tbc_users_details_davaleba18.domain.model.User

class UsersRecyclerAdapter :
    ListAdapter<User, UsersRecyclerAdapter.UsersRecyclerViewHolder>(DiffCallback()) {

    var itemOnClick: ((Int) -> Unit)? = null

    inner class UsersRecyclerViewHolder(private val binding: UsersListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val user: User = currentList[adapterPosition]
            with(binding) {
                tvEmail.text = user.email
                tvFirstName.text = user.firstName
                tvLastName.text = user.lastName
                Glide.with(itemView.context).load(user.img).into(ivUserProfile)
            }
        }

        fun listener() {
            val user: User = currentList[adapterPosition]
            binding.root.setOnClickListener {
                itemOnClick!!(user.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersRecyclerViewHolder {
        return UsersRecyclerViewHolder(
            UsersListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UsersRecyclerViewHolder, position: Int) {
        holder.bind()
        holder.listener()
    }

    fun setData(users: List<User>) {
        submitList(users)
    }

    private class DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}