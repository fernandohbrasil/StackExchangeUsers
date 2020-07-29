package com.fernandohbrasil.stackexchange.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fernandohbrasil.stackexchange.databinding.ItemUserBinding
import com.fernandohbrasil.stackexchange.network.model.User


class UserAdapter : ListAdapter<User, RecyclerView.ViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = getItem(position)
        (holder as UserViewHolder).bind(user!!)
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.apply {
                tvUserId.text = user.id.toString()
                tvUserName.text = user.name
            }
        }

        init {
            binding.root.setOnClickListener {
                goToUserDetail(getItem(adapterPosition)!!.id, it)
            }
        }

        private fun goToUserDetail(id: Int, view: View) {
            val direction = ListUsersFragmentDirections.actionListUsersFragmentToUserDetailFragment(id)
            view.findNavController().navigate(direction)
        }
    }
}

class UserDiffCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: User, newItem: User) = oldItem == newItem
}
