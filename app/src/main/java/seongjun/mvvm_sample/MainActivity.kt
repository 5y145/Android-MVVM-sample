package seongjun.mvvm_sample

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import seongjun.mvvm_sample.databinding.ActivityMainBinding
import seongjun.mvvm_sample.model.Movie
import seongjun.mvvm_sample.repository.Repository

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel by lazy { ViewModelProvider(this,MainViewModel.Factory(Repository(application))).get(MainViewModel::class.java) }
    private val mAdapter = MyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        binding.btn.setOnClickListener {
            viewModel.insertMovie(Movie(0, binding.et.text.toString()))
            viewModel.getPost()
        }
        
        viewModel.movieList.observe(this, {
            mAdapter.setData(viewModel.movieList.value!!)
        })

        viewModel.myResponse.observe(this, {
            if (it.isSuccessful) {
                val title = it.body()?.myUserId!!
                Toast.makeText(this, "$title", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("Response",it.errorBody().toString())
                Log.d("Response",it.code().toString()) // 404
            }
        })
    }

    inner class MyAdapter: RecyclerView.Adapter<MyAdapter.CustomViewHolder>() {

        private var list = emptyList<Movie>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
            return CustomViewHolder(view)
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            holder.tvId.text = "id : ${list[position].id}"
            holder.tvTitle.text ="title : ${list[position].title}"

            holder.itemView.setOnClickListener {
                startActivity(Intent(this@MainActivity, DetailActivity::class.java).apply { putExtra("id", list[position].id) })
            }

            holder.itemView.setOnLongClickListener {
                viewModel.deleteMovie(list[position])
                return@setOnLongClickListener false
            }
        }

        override fun getItemCount(): Int {
            return list.size
        }

        fun setData(newList: List<Movie>) {
            list = newList
            notifyDataSetChanged()
        }

        inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tvId: TextView = itemView.findViewById(R.id.tvId)
            val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        }
    }
}