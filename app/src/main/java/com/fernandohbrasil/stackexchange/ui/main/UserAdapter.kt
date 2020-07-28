package com.fernandohbrasil.stackexchange.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
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
                tvUserId.text = user.user_id.toString()
                tvUserName.text = user.display_name
            }
        }

        init {
            binding.root.setOnClickListener {

                Toast.makeText(
                    this.binding.root.context,
                    getItem(adapterPosition).display_name,
                    Toast.LENGTH_SHORT
                ).show()

                //     goToCharacter(getItem(adapterPosition)!!.id, it)
            }
        }

//        private fun goToCharacter(id: Int, view: View) {
//            val direction = RootFragmentDirections.actionRootFragmentToDetailFragment(id)
//            view.findNavController().navigate(direction)
//        }
    }
}

class UserDiffCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem.user_id == newItem.user_id

    override fun areContentsTheSame(oldItem: User, newItem: User) = oldItem == newItem
}
