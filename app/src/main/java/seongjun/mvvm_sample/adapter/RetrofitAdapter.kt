package seongjun.mvvm_sample.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import seongjun.mvvm_sample.databinding.ItemRetrofitBinding
import seongjun.mvvm_sample.model.RetrofitTodoData

class RetrofitAdapter(): RecyclerView.Adapter<RetrofitAdapter.Holder>() {

    private var list: List<RetrofitTodoData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRetrofitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
    fun setData(newList: List<RetrofitTodoData>) {
        list = newList
        notifyDataSetChanged()
    }

    // ClickListener
    interface OnItemClickListener {
        fun onItemClick(v: View, item: RetrofitTodoData)
        fun onItemLongClick(v: View, item: RetrofitTodoData)
    }

    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) { this.listener = listener }

    // ViewHolder
    class Holder(val binding: ItemRetrofitBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RetrofitTodoData) { binding.item = item }
    }
}