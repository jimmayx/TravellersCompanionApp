package op.mobile.app.dev.simjd1.travelling.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment
import op.mobile.app.dev.simjd1.travelling.R
import op.mobile.app.dev.simjd1.travelling.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private lateinit var viewModelFactory: GameViewModelFactory
    private val viewModel: GameViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentGameBinding =
            DataBindingUtil.inflate(
                inflater, R.layout.fragment_game, container, false
            )

        viewModelFactory =
            GameViewModelFactory(
                GameFragmentArgs.fromBundle(requireArguments()).country,
            )


        binding.gameViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.isEnd.observe(viewLifecycleOwner) {
            if (it) onEnd()
        }

        return binding.root
    }

    private fun onEnd() {
        val action = GameFragmentDirections.actionGameFragmentToResultsFragment(viewModel.country)
        action.score = viewModel.score.value!!
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onEndComplete()
    }
}