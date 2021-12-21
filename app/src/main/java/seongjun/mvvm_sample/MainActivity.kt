package seongjun.mvvm_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import seongjun.mvvm_sample.adapter.RetrofitAdapter
import seongjun.mvvm_sample.adapter.RoomAdapter
import seongjun.mvvm_sample.databinding.ActivityMainBinding
import seongjun.mvvm_sample.model.RetrofitTodoData
import seongjun.mvvm_sample.model.RoomTodoData

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel by lazy { ViewModelProvider(this,MainViewModel.Factory(application))[MainViewModel::class.java] }

    private val roomAdapter by lazy {
        RoomAdapter(this, emptyList()).apply {
            setOnItemClickListener(object: RoomAdapter.OnItemClickListener{
                override fun onItemClick(v: View, item: RoomTodoData) {}
                override fun onItemLongClick(v: View, item: RoomTodoData) {
                    viewModel.deleteRoom(item)
                }
            })
        }
    }

    private val retrofitAdapter by lazy {
        RetrofitAdapter(this, emptyList()).apply {
            setOnItemClickListener(object: RetrofitAdapter.OnItemClickListener{
                override fun onItemClick(v: View, item: RetrofitTodoData) {}
                override fun onItemLongClick(v: View, item: RetrofitTodoData) {
                    viewModel.deleteRetrofit(item)
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
            viewModel.insertRetrofit(RetrofitTodoData(0, binding.et2.text.toString()))
            binding.et2.setText("")
        }
        
        viewModel.roomTodoList.observe(this, {
            roomAdapter.setData(viewModel.roomTodoList.value!!)
        })

        viewModel.retrofitTodoList.observe(this, {
            retrofitAdapter.setData(viewModel.retrofitTodoList.value!!)
        })
    }
}