package op.mobile.app.dev.simjd1.travelling.ui.translator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import op.mobile.app.dev.simjd1.travelling.R
import op.mobile.app.dev.simjd1.travelling.databinding.FragmentTranslatorBinding

class TranslatorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentTranslatorBinding =
            DataBindingUtil.inflate(
                inflater, R.layout.fragment_translator, container, false
            )

        val viewModelFactory =
            TranslatorViewModelFactory(
                TranslatorFragmentArgs.fromBundle(requireArguments()).country,
            )

        val viewModel: TranslatorViewModel by viewModels { viewModelFactory }


        binding.translatorViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

}