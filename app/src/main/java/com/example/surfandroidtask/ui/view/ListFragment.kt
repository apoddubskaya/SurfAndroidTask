package com.example.surfandroidtask.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import com.example.surfandroidtask.data.Film
import com.example.surfandroidtask.databinding.FragmentListBinding
import com.example.surfandroidtask.ui.adapter.FilmsAdapter
import com.example.surfandroidtask.ui.viewmodel.ListViewModel
import com.example.surfandroidtask.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListViewModel by activityViewModels()

    private lateinit var filmsAdapter: FilmsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        viewModel.films.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> showData(it.data)
                is Resource.Error -> showError()
                is Resource.Loading -> showLoading()
            }
        }

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchFilms(newText)
                return false
            }

        })

        binding.retryButton.setOnClickListener {
            viewModel.retryGetData()
        }
    }

    private fun setAdapter() {
        filmsAdapter = FilmsAdapter(
            { title -> Toast.makeText(context, title, Toast.LENGTH_SHORT).show() },
            {filmId, isChecked -> viewModel.onCheckedChangeHandler(filmId, isChecked)}

        )
        binding.recyclerView.adapter = filmsAdapter
    }

    private fun hideError() {
        binding.errorTextView.visibility = View.GONE
        binding.retryButton.visibility = View.GONE
    }

    private fun hideEmptySearchResults() {
        binding.emptySearchResultsTextView.visibility = View.GONE
    }

    private fun hideLoading() {
        binding.progressBarHorizontal.visibility = View.GONE
    }

    private fun hideRecyclerView() {
        binding.recyclerView.visibility = View.GONE
    }

    private fun showData(data: List<Film>) {
        hideLoading()
        hideError()
        if (data.isEmpty()) {
            hideRecyclerView()
            hideError()
            binding.emptySearchResultsTextView.visibility = View.VISIBLE
            binding.emptySearchResultsTextView.text =
                "По запросу \"${binding.search.query}\" ничего не найдено"
        }
        else {
            hideEmptySearchResults()
            binding.recyclerView.visibility = View.VISIBLE
            filmsAdapter.setData(data)
        }
    }

    private fun showError() {
        hideLoading()
        hideRecyclerView()
        hideEmptySearchResults()
        binding.errorTextView.visibility = View.VISIBLE
        binding.retryButton.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.progressBarHorizontal.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}