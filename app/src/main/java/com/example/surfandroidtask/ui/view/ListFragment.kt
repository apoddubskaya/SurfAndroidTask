package com.example.surfandroidtask.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        filmsAdapter = FilmsAdapter()
        binding.recyclerView.adapter = filmsAdapter

        viewModel.films.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> showData(it.data)
                is Resource.Error -> showError(it.message)
                is Resource.Loading -> showLoading()
            }
        }
    }

    private fun showData(data: List<Film>) = filmsAdapter.setData(data)

    private fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        Toast.makeText(context, "Loading..", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}