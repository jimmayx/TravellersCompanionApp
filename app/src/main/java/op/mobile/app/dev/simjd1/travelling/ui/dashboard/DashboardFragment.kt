package op.mobile.app.dev.simjd1.travelling.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import op.mobile.app.dev.simjd1.travelling.R


class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View =
            inflater.inflate(R.layout.fragment_dashboard, container, false)

        val viewModelFactory =
            DashboardViewModelFactory(
                DashboardFragmentArgs.fromBundle(requireArguments()).country,
            )
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(DashboardViewModel::class.java)

        val btnQuiz: Button = view.findViewById(R.id.btn_takeQuiz)!!
        btnQuiz.setOnClickListener {
            findNavController().navigate(
                DashboardFragmentDirections.actionNavigationDashboardToNavigationGame(
                    viewModel.country
                )
            )
        }

        val btnTranslator: Button = view.findViewById(R.id.btn_translate)!!
        btnTranslator.setOnClickListener {
            findNavController().navigate(
                DashboardFragmentDirections.actionNavigationDashboardToNavigationTranslator(
                    viewModel.country
                )
            )
        }

        val btnPhrases: Button = view.findViewById(R.id.btn_phrases)!!
        btnPhrases.setOnClickListener {
            findNavController().navigate(
                DashboardFragmentDirections.actionNavigationDashboardToPhrasesFragment(
                    viewModel.country
                )
            )
        }

        return view
    }


}