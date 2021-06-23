package op.mobile.app.dev.simjd1.travelling.ui.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import op.mobile.app.dev.simjd1.travelling.R

class ResultsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_results, container, false)

        val tvFinalScore: TextView = view.findViewById(R.id.tv_final_score)
        val btnFinish: Button = view.findViewById(R.id.btn_finish)

        val viewModelFactory = ResultsViewModelFactory(
            ResultsFragmentArgs.fromBundle(
                requireArguments()
            ).score, ResultsFragmentArgs.fromBundle(requireArguments()).country
        )
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ResultsViewModel::class.java)

        tvFinalScore.text =
            activity?.getString(R.string.final_score, viewModel.score.value.toString())

        btnFinish.setOnClickListener {
            findNavController().navigate(
                ResultsFragmentDirections.actionNavigationResultsToNavigationDashboard(
                    viewModel.country
                )
            )
        }

        return view
    }
}