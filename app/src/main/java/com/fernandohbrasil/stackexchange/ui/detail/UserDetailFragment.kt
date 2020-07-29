package com.fernandohbrasil.stackexchange.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.fernandohbrasil.stackexchange.databinding.UserDetailFragmentBinding
import com.fernandohbrasil.stackexchange.di.Injectable
import com.fernandohbrasil.stackexchange.extensions.load
import com.fernandohbrasil.stackexchange.extensions.toStringDateTime
import com.fernandohbrasil.stackexchange.network.model.Badges
import com.fernandohbrasil.stackexchange.network.model.User
import com.fernandohbrasil.stackexchange.ui.viewmodel.SharedViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class UserDetailFragment : Fragment(), Injectable {

    private var _binding: UserDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UserDetailFragmentArgs>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val userDetailViewModel: UserDetailViewModel by viewModels {
        viewModelFactory
    }

    private val navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UserDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userDetailViewModel.userState.observe(viewLifecycleOwner, userStateObserver())
    }

    private fun userStateObserver(): Observer<UserState> = Observer { state ->
        when (state) {
            is UserStateStarted -> userDetailViewModel.getUserById(args.userId)
            is UserStateSuccess -> bindUser(state.user)
            is UserStateError -> handleError(state.error)
        }
    }

    override fun onStart() {
        super.onStart()
        sharedViewModel.setValuesAppBar("User", true)
        userDetailViewModel.start()
    }

    override fun onPause() {
        super.onPause()
        userDetailViewModel.finish()
    }

    private fun handleError(error: String?) {
        Snackbar.make(
            binding.root,
            error.toString(),
            Snackbar.LENGTH_LONG
        ).show()

        requireActivity().onBackPressed()
    }

    private fun bindUser(user: User) {
        binding.apply {
            ivUserAvatar.load(user.profileImageUrl)
            tvUserName.text = user.name
            tvLocation.text = user.location
            tvReputation.text = user.reputation.toString()
            tvCreationDate.text = user.creationDate.toStringDateTime()
            bindBadges(user.badges)
        }
    }

    private fun bindBadges(badges: Badges) {
        binding.inBadges.apply {
            tvBronze.text = badges.bronze.toString()
            tvGold.text = badges.gold.toString()
            tvSilver.text = badges.gold.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}