package Fragment

import Adapter.SampleAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a1.R
import data.SampleData
import kotlinx.android.synthetic.main.fragment_second.*


class SecondFragment : Fragment() {
    private lateinit var sampleAdapter: SampleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sampleAdapter = SampleAdapter(view.context)

        view_rcv.adapter = sampleAdapter
        //view_rcv.layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)
        view_rcv.layoutManager = LinearLayoutManager(view.context)

        sampleAdapter.data = mutableListOf(
            SampleData(
                "1",
                "소개",
                "2020.10.18",
                "남가은, 22살, 숙명여자대학교 소비자경제학과, IT공학전공"
            ),
            SampleData(
                "2",
                "관심 분야",
                "2020.10.18",
                "IT, 소비자학, 시장분석"
            ),
            SampleData(
                "3",
                "깃허브",
                "2020.10.18",
                "안드로이드 초보의 깃허브 : https://github.com/xxeun"
            )
        )
        sampleAdapter.notifyDataSetChanged()

    }
}