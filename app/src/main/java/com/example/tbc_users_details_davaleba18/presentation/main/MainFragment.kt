package com.example.tbc_users_details_davaleba18.presentation.main

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_users_details_davaleba18.data.common.Resource
import com.example.tbc_users_details_davaleba18.databinding.FragmentMainBinding
import com.example.tbc_users_details_davaleba18.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: MainFragmentViewModel by viewModels()
    private lateinit var usersRecyclerAdapter: UsersRecyclerAdapter

    override fun setUp() {
        bindObservers()
        setUpUsersRv()
        listeners()
    }

    private fun setUpUsersRv() {
        usersRecyclerAdapter = UsersRecyclerAdapter()
        binding.rvUsersList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = usersRecyclerAdapter
        }
    }

    private fun listeners() {
        usersRecyclerAdapter.itemOnClick = { it ->
            navigateToDetails(it)
        }
    }

    private fun navigateToDetails(id: Int) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailsFragment(id))
    }

    private fun bindObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.itemFlow.collect {
                    when (it) {
                        is Resource.Success -> usersRecyclerAdapter.setData(it.data)
                        is Resource.Loading -> binding.progressBar.visibility =
                            if (it.loading) View.VISIBLE else View.GONE

                        is Resource.Error -> Toast.makeText(context, it.error, Toast.LENGTH_SHORT)
                            .show()

                        else -> {}
                    }
                }
            }
        }
    }
}