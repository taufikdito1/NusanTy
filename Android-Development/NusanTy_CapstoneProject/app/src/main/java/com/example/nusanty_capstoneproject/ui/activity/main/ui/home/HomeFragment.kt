package com.example.nusanty_capstoneproject.ui.activity.main.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nusanty_capstoneproject.data.database.ArticleRepository
import com.example.nusanty_capstoneproject.data.model.article.DetailArticle
import com.example.nusanty_capstoneproject.data.model.article.DetailDB
import com.example.nusanty_capstoneproject.data.model.article.DetailResponse
import com.example.nusanty_capstoneproject.databinding.FragmentHomeBinding
import com.example.nusanty_capstoneproject.ui.activity.detail.DetailArticleActivity
import com.google.gson.Gson

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var data : List<DetailDB>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupViewModel()
        data = ArrayList()
        viewModel.article.observe(requireActivity()) {
        showToast(it.message!!)
        val detail = ArticleAdapter(it.articles)

        binding.rvList.apply {
            layoutManager = GridLayoutManager(activity, 2)

            adapter = detail
            detail.setOnItemClickCallback(object : ArticleAdapter.OnItemClickCallback {
                override fun onItemClicked(data: DetailArticle) {
                    Toast.makeText(activity, data.title, Toast.LENGTH_SHORT).show()
                    val intent = Intent(getActivity(), DetailArticleActivity::class.java)
                    intent.putExtra(DetailArticleActivity.DETAIL_ARTICLE, data)
                    startActivity(intent)
                }
            })
        }
    }

    return root
}

override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}

private fun setupViewModel() {
    viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    viewModel.getArticle()
}

private fun showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}
}
