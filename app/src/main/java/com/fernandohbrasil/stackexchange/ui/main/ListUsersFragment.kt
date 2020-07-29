package com.fernandohbrasil.stackexchange.ui.main

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fernandohbrasil.stackexchange.R
import com.fernandohbrasil.stackexchange.databinding.ListUsersFragmentBinding
import com.fernandohbrasil.stackexchange.di.Injectable
import com.fernandohbrasil.stackexchange.network.model.Users
import com.fernandohbrasil.stackexchange.ui.viewmodel.SharedViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class ListUsersFragment : Fragment(), Injectable {

    private var _binding: ListUsersFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val listUsersViewModel: ListUsersViewModel by viewModels {
        viewModelFactory
    }

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListUsersFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.setValuesAppBar(getString(R.string.app_name),hasUpButton = false)

        listUsersViewModel.usersState.observe(viewLifecycleOwner, usersStateObserver())

        binding.apply {
            btSearch.setOnClickListener {
                listUsersViewModel.getUsers(etName.text.toString())
            }

            etName.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    btSearch.performClick()
                    true
                } else {
                    false
                }
            }

        }

    }

    private fun usersStateObserver(): Observer<UsersState> = Observer { state ->
        when (state) {
            is UsersSuccess -> bindAdapter(state.users)
            is UsersError -> Snackbar.make(
                binding.root,
                state.error.toString(),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun bindAdapter(users: Users) {
        val userAdapter = UserAdapter()
        binding.rvUsers.adapter = userAdapter
        userAdapter.submitList(users.items)
    }
}