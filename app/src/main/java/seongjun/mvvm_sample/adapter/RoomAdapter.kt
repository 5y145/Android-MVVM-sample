package seongjun.mvvm_sample.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import seongjun.mvvm_sample.databinding.ItemRoomBinding
import seongjun.mvvm_sample.model.RoomTodoData

class RoomAdapter(): RecyclerView.Adapter<RoomAdapter.Holder>() {

    private var list: List<RoomTodoData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])

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
    fun setOnItemClickListener(listener : OnItemClickListener) { this.listener = listener }

    // ViewHolder
    class Holder(val binding: ItemRoomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RoomTodoData) { binding.item = item }
    }
}