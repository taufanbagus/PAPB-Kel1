package com.example.e_linguistik.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.e_linguistik.R
import com.example.e_linguistik.db.HistoryModel
import com.example.e_linguistik.adapter.HistoryAdapter.HistoryViewHolder

class HistoryAdapter: ListAdapter<HistoryModel, HistoryViewHolder>(HISTORY_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.word)
    }

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTypeHistory: TextView = itemView.findViewById(R.id.tv_history_type)
        private val tvWordOrigin: TextView = itemView.findViewById(R.id.tv_word_origin)
        private val tvWordTranslation : TextView = itemView.findViewById(R.id.tv_word_translation)

        fun bind(typeHistory: String?, wordOrigin: String?, wordTranslation: String? ){
            tvTypeHistory.text = typeHistory
            tvWordOrigin.text = wordOrigin
            tvWordTranslation.text = wordTranslation
        }

        companion object{
            fun create(parent: ViewGroup): HistoryViewHolder{
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_history, parent, false)
                return HistoryViewHolder(view)
            }
        }

    }

    companion object{
        private  val HISTORY_COMPARATOR = object: DiffUtil.ItemCallback<HistoryModel>() {
            override fun areItemsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
                TODO("Not yet implemented")
            }

        }
    }
}

