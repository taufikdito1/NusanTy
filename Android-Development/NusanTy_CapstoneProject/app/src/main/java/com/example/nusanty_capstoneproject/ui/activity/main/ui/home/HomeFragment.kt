package com.example.nusanty_capstoneproject.ui.activity.main.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nusanty_capstoneproject.R
import com.example.nusanty_capstoneproject.data.model.article.DetailArticle
import com.example.nusanty_capstoneproject.databinding.FragmentHomeBinding
import com.example.nusanty_capstoneproject.ui.activity.detail.DetailArticleActivity
import com.example.nusanty_capstoneproject.ui.activity.detail.DetailArticleViewModel
import java.io.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ArticleAdapter.ListViewHolder>? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val raw = resources.openRawResource(R.raw.article)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        raw.use { rawData ->
            val reader: Reader = BufferedReader(InputStreamReader(rawData, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        }


        val jsonString = writer.toString()

        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.rvList.apply {
            layoutManager = GridLayoutManager(activity,2)
            val detail = ArticleAdapter(jsonString)
            adapter = detail
            detail.setOnItemClickCallback(object : ArticleAdapter.OnItemClickCallback{
                override fun onItemClicked(data: DetailArticle) {
                    Toast.makeText(activity,data.article_title,Toast.LENGTH_SHORT).show()
                    val intent = Intent(getActivity(), DetailArticleActivity::class.java)
                    intent.putExtra(DetailArticleActivity.DETAIL_ARTICLE, data)
                    startActivity(intent)
                }
            })
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}