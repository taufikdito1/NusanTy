package com.example.nusanty_capstoneproject.ui.activity.main.ui.home

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nusanty_capstoneproject.R
import com.example.nusanty_capstoneproject.data.database.ArticleRepository
import com.example.nusanty_capstoneproject.data.database.NusantyDatabase
import com.example.nusanty_capstoneproject.data.model.article.DetailArticle
import com.example.nusanty_capstoneproject.data.model.article.DetailDB
import com.example.nusanty_capstoneproject.data.model.article.DetailResponse
import com.example.nusanty_capstoneproject.databinding.FragmentHomeBinding
import com.example.nusanty_capstoneproject.ui.activity.detail.DetailArticleActivity
import com.example.nusanty_capstoneproject.ui.activity.main.ui.camera.CameraViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

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

        val repo = ArticleRepository(requireActivity().application)
        val jsonString = writer.toString()
        val gson = Gson()
        val list = gson.fromJson(jsonString, DetailResponse::class.java)
        val data = list.detailArticle.map {
            DetailDB(it.id,it.article_title!!,it.article_imgUrl.toString(),it.article_Description!!,it.article_Location!!) }
        repo.insert(data)
        val detail = ArticleAdapter(list.detailArticle)

        binding.rvList.apply {
            layoutManager = GridLayoutManager(activity,2)

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