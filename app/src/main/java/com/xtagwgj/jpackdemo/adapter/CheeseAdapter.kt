package com.xtagwgj.jpackdemo.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.xtagwgj.jpackdemo.BaseViewHolder
import com.xtagwgj.jpackdemo.R
import com.xtagwgj.jpackdemo.domain.TestDomain

class CheeseAdapter : PagedListAdapter<TestDomain, BaseViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_demo, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.setText(R.id.tv_name, getItem(position)?.content ?: "")
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<TestDomain>() {
            override fun areItemsTheSame(oldItem: TestDomain, newItem: TestDomain): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TestDomain, newItem: TestDomain): Boolean {
                return oldItem.content == newItem.content
            }

        }
    }
}