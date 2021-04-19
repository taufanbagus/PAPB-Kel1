/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.e_linguistik

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.e_linguistik.HistoryAdapter.HistoryViewHolder
import com.example.e_linguistik.R
import com.example.e_linguistik.db.HistoryModel
import com.example.e_linguistik.db.HistoryRepository

class HistoryAdapter : ListAdapter<HistoryModel, HistoryViewHolder>(HISTORY_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.typeTranslation, current.originWord, current.resultWordTranslation)
    }

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvtypehsitory: TextView = itemView.findViewById(R.id.tv_history_type)
        private val tvwordorigin: TextView = itemView.findViewById(R.id.tv_word_origin)
        private val tvwordtranslation: TextView = itemView.findViewById(R.id.tv_word_translation)

        fun bind(type: String?, origin: String?, translation: String?) {
            tvtypehsitory.text = type
            tvwordorigin.text = origin
            tvwordtranslation.text = translation
        }

        companion object {
            fun create(parent: ViewGroup): HistoryViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_history, parent, false)
                return HistoryViewHolder(view)
            }
        }
    }

    companion object {
        private val HISTORY_COMPARATOR = object : DiffUtil.ItemCallback<HistoryModel>() {
            override fun areItemsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
                return oldItem.originWord == newItem.originWord
            }
        }
    }
}

