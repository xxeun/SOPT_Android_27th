package Fragment

import Adapter.FragmentViewpagerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a1.R
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {
    private lateinit var fragmentViewpagerAdapter: FragmentViewpagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_first, container, false)
        //Fragment는 Inflater를 이용해서 Layout파일을 Inflate 시켜줌

        val view = inflater.inflate(R.layout.fragment_first, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // UI 작업 등
        fragmentViewpagerAdapter =
            FragmentViewpagerAdapter(childFragmentManager)
        fragmentViewpagerAdapter.fragments = listOf(
            ProjectsFragment(),
            OtherFragment()
        )
        fragment_viewpager.adapter = fragmentViewpagerAdapter

        sample_tab.setupWithViewPager(fragment_viewpager)
        sample_tab.apply {
            getTabAt(0)?.text = "PROJECTS"
            getTabAt(1)?.text = "OTHER"
        }
    }
}