package seongjun.mvvm_sample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import seongjun.mvvm_sample.databinding.ActivityMainBinding
import seongjun.mvvm_sample.model.RetrofitTodoData
import seongjun.mvvm_sample.model.RoomTodoData

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel by lazy { ViewModelProvider(this,MainViewModel.Factory(application))[MainViewModel::class.java] }
    private val roomAdapter = MyAdapter(1)
    private val retrofitAdapter = MyAdapter(2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.selectAllRetrofit()
        Log.d("DEBUG", "호출")

        binding.rvRoom.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = roomAdapter
        }

        binding.rvRetrofit.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = retrofitAdapter
        }

        binding.btn1.setOnClickListener {
            viewModel.insertRoom(RoomTodoData(0, binding.et1.text.toString()))
            binding.et1.setText("")
        }

        binding.btn2.setOnClickListener {
            viewModel.insertRetrofit(RetrofitTodoData(0, binding.et1.text.toString()))
            binding.et2.setText("")
        }
        
        viewModel.roomTodoList.observe(this, {
            roomAdapter.setRoomData(viewModel.roomTodoList.value!!)
        })

        viewModel.retrofitTodoList.observe(this, {
                roomAdapter.setRetrofitData(it)
                Log.d("DEBUG", "호출4 ${it}")
        })
    }

    inner class MyAdapter(val type: Int): RecyclerView.Adapter<MyAdapter.CustomViewHolder>() {

        private var roomList = emptyList<RoomTodoData>()
        private var retrofitList = emptyList<RetrofitTodoData>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
            return CustomViewHolder(view)
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

            if (type == 1) {
                holder.tvWord.text = "${roomList[position].word}"
                holder.tvId.text ="${roomList[position].id}"

                holder.itemView.setOnClickListener {
                    // ...
                }

                holder.itemView.setOnLongClickListener {
                    viewModel.deleteRoom(roomList[position])
                    return@setOnLongClickListener false
                }
            }

            if (type == 2) {
                holder.tvWord.text = "${retrofitList[position].word}"
                holder.tvId.text ="${retrofitList[position].id}"

                holder.itemView.setOnClickListener {
                    // ...
                }

                holder.itemView.setOnLongClickListener {
                    viewModel.deleteRetrofit(retrofitList[position])
                    return@setOnLongClickListener false
                }
            }
        }

        override fun getItemCount(): Int {
            if (type == 1) return roomList.size
            else return retrofitList.size
        }

        fun setRoomData(r: List<RoomTodoData>) {
            roomList = r
            notifyDataSetChanged()
        }

        fun setRetrofitData(r: List<RetrofitTodoData>) {
            retrofitList = r
            notifyDataSetChanged()
        }

        inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tvWord: TextView = itemView.findViewById(R.id.tvWord)
            val tvId: TextView = itemView.findViewById(R.id.tvId)
        }
    }
}