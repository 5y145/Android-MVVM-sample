package seongjun.mvvm_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
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

    private lateinit var roomAdapter: RoomAdapter
    private lateinit var retrofitAdapter: RetrofitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setView()
        setObserver()
    }

    private fun setView() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.et1.addTextChangedListener {
            viewModel.roomInput.value = binding.et1.text.toString()
        }

        binding.et2.addTextChangedListener {
            viewModel.retrofitInput.value = binding.et2.text.toString()
        }

        roomAdapter = RoomAdapter().apply {
            setHasStableIds(true) // 리사이클러 뷰 업데이트 시 깜빡임 방지
            setOnItemClickListener(object: RoomAdapter.OnItemClickListener{ // 이벤트 리스너
                override fun onItemClick(v: View, item: RoomTodoData) {}
                override fun onItemLongClick(v: View, item: RoomTodoData) {
                    viewModel.deleteRoom(item)
                }
            })
        }

        binding.rvRoom.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = roomAdapter
        }

        retrofitAdapter =  RetrofitAdapter().apply {
            setHasStableIds(true) // 리사이클러 뷰 업데이트 시 깜빡임 방지
            setOnItemClickListener(object: RetrofitAdapter.OnItemClickListener{ // 이벤트 리스너
                override fun onItemClick(v: View, item: RetrofitTodoData) {}
                override fun onItemLongClick(v: View, item: RetrofitTodoData) {
                    viewModel.deleteRetrofit(item)
                }
            })
        }

        binding.rvRetrofit.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = retrofitAdapter
        }
    }

    private fun setObserver() {
        viewModel.roomTodoList.observe(this, {
            roomAdapter.setData(viewModel.roomTodoList.value!!)
        })

        viewModel.retrofitTodoList.observe(this, {
            retrofitAdapter.setData(viewModel.retrofitTodoList.value!!)
        })
    }
}