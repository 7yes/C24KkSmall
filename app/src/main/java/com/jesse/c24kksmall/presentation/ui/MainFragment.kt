package com.jesse.c24kksmall.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jesse.c24kksmall.R
import com.jesse.c24kksmall.databinding.FragmentMainBinding
import com.jesse.c24kksmall.presentation.MainVM
import com.jesse.c24kksmall.presentation.adapter.SimpleAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: SimpleAdapter
    private var lista = mutableListOf<String>()

    private val viewmodel: MainVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        initRV()
        viewmodel.data.observe(viewLifecycleOwner){
            Log.d("TAf", "onCreateView: $it")
            lista.clear()
            lista.addAll(it)
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            viewmodel.getByBreed("african/images")
        }
    }

    private fun initRV() {
        Log.d("TAf", "initRV: $lista ")
        adapter = SimpleAdapter(lista)
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}