package op.mobile.app.dev.simjd1.travelling.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import op.mobile.app.dev.simjd1.travelling.R
import op.mobile.app.dev.simjd1.travelling.databinding.FragmentHomeBinding
import op.mobile.app.dev.simjd1.travelling.helpers.IOnClickListener
import op.mobile.app.dev.simjd1.travelling.helpers.RecyclerViewAdapter

class HomeFragment : Fragment(), IOnClickListener {
    private lateinit var binding: FragmentHomeBinding
    private var backPressedTime: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val backToast = Toast.makeText(
                        activity, "Press back again to leave", Toast.LENGTH_SHORT
                    )
                    if (backPressedTime + 1000 > System.currentTimeMillis()) {
                        backToast.cancel()
                        isEnabled = false
                        activity?.onBackPressed()
                        return
                    } else {
                        backToast.show()
                    }
                    backPressedTime = System.currentTimeMillis()
                }
            })

        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.homeViewModel = viewModel

        binding.rvCountry.adapter = RecyclerViewAdapter(this@HomeFragment)

        return binding.root
    }

    override fun onItemClick(position: Int) {
        val item = binding.homeViewModel!!.response.value!![position]
        val action = HomeFragmentDirections.actionNavigationHomeToNavigationDashboard(item)
        findNavController().navigate(action)
    }
}
