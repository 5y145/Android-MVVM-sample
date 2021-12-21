package seongjun.mvvm_sample.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import seongjun.mvvm_sample.R
import seongjun.mvvm_sample.model.RoomTodoData

class RoomAdapter(private val context: Context, private var list: List<RoomTodoData>): RecyclerView.Adapter<RoomAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.tvWord.text = list[position].word
        holder.tvId.text = list[position].id.toString()

        holder.itemView.setOnClickListener {
            listener?.onItemClick(holder.itemView, list[position])
        }

        holder.itemView.setOnLongClickListener {
            listener?.onItemLongClick(holder.itemView, list[position])
            return@setOnLongClickListener false
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<RoomTodoData>) {
        list = newList
        notifyDataSetChanged()
    }

    // ClickListener
    interface OnItemClickListener {
        fun onItemClick(v: View, item: RoomTodoData)
        fun onItemLongClick(v: View, item: RoomTodoData)
    }

    private var listener : OnItemClickListener? = null

    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    // ViewHolder
    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvWord: TextView = itemView.findViewById(R.id.tvWord)
        val tvId: TextView = itemView.findViewById(R.id.tvId)
    }
}