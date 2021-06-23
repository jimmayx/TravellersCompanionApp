package op.mobile.app.dev.simjd1.travelling.ui.phrases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import op.mobile.app.dev.simjd1.travelling.R
import op.mobile.app.dev.simjd1.travelling.databinding.FragmentPhrasesBinding

class PhrasesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentPhrasesBinding =
            DataBindingUtil.inflate(
                inflater, R.layout.fragment_phrases, container, false
            )

        val viewModelFactory =
            PhrasesViewModelFactory(
                PhrasesFragmentArgs.fromBundle(requireArguments()).country,
            )

        val viewModel: PhrasesViewModel by viewModels { viewModelFactory }


        binding.phrasesViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}