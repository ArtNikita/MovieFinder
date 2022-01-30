package ru.nikitaartamonov.moviefinder.ui.pages.recycler_view.description

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nikitaartamonov.moviefinder.data.retrofit.CastEntity

class CastRecyclerViewAdapter : RecyclerView.Adapter<CastItemViewHolder>() {
    private var castList: List<CastEntity> = emptyList()

    fun setDataAndNotify(castList: List<CastEntity>) {
        this.castList = castList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastItemViewHolder {
        return CastItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CastItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = castList.size

    private fun getItem(position: Int) = castList[position]
}