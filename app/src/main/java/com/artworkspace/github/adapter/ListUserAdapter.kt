package com.artworkspace.github.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artworkspace.github.R
import com.artworkspace.github.databinding.UserCardBinding
import com.artworkspace.github.model.User
import com.bumptech.glide.Glide

class ListUserAdapter(private val listUser: ArrayList<User>) :
    RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    /**
     * Set an item click callback
     *
     * @param onItemClickCallback   object that implements onItemClickCallback
     * @return Unit
     */
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: UserCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = UserCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = listUser[position]

        holder.binding.apply {
            cardTvName.text = user.name
            cardTvCompany.text = user.company

            Glide
                .with(holder.itemView.context)
                .load(user.avatar)
                .placeholder(R.drawable.profile_placeholder)
                .into(cardImageProfile)
        }

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(user) }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(user: User)
    }
}