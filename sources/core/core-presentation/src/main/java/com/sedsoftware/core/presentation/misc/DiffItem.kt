package com.sedsoftware.core.presentation.misc

import androidx.recyclerview.widget.DiffUtil

interface DiffItem {
    fun getItemName(): String
    fun getItemHash(): Int

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DiffItem>() {

            override fun areItemsTheSame(oldItem: DiffItem, newItem: DiffItem): Boolean {
                return oldItem.getItemName() == newItem.getItemName()
            }

            override fun areContentsTheSame(oldItem: DiffItem, newItem: DiffItem): Boolean {
                return oldItem.getItemHash() == newItem.getItemHash()
            }
        }
    }
}
