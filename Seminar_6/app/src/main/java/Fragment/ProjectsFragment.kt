package Fragment

import Adapter.FragmentViewpagerAdapter
import Adapter.SampleAdapter
import Adapter.SampleViewPagerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a1.R
import kotlinx.android.synthetic.main.activity_recycler_view.*
import kotlinx.android.synthetic.main.fragment_first.*

class ProjectsFragment():Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_projects, container, false)
    }
}