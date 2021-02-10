package com.diegomfv.moodtrackerv2.ui.history

import android.content.res.ColorStateList
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegomfv.moodtrackerv2.R
import com.diegomfv.moodtrackerv2.domain.DayModel
import com.diegomfv.moodtrackerv2.extensions.basicDiffUtil
import com.diegomfv.moodtrackerv2.extensions.inflate
import com.diegomfv.moodtrackerv2.utils.ColourManager
import kotlinx.android.synthetic.main.item_day.view.*

class HistoryAdapter (
    private val listener: (DayModel) -> Unit,
    private val colourManager: ColourManager
) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    var dayModelList: List<DayModel> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.itemId == new.itemId }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_day, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dayModelList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dayModel = dayModelList[position]
        holder.bind(dayModel)
        holder.itemView.setOnClickListener { listener(dayModel) }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(dayModel: DayModel) {
            itemView.text_day.text = dayModel.day
            itemView.mood_bar.backgroundTintList = ColorStateList.valueOf(colourManager.getMoodColour(dayModel.mood))
        }
    }
}