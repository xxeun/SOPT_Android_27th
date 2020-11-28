package Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a1.R
import data.SampleData
import com.example.a1.SampleViewholder
import com.example.a1.ShowDetailActivity

class SampleAdapter(private val context: Context) : RecyclerView.Adapter<SampleViewholder>() {

    var data = mutableListOf<SampleData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewholder {
        val view = LayoutInflater.from(context).inflate(R.layout.sample, parent, false)

        return SampleViewholder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: SampleViewholder, position: Int) {
        holder.onBind(data[position])

        holder.itemView.setOnClickListener() {
            //SampleData.xml의 리스트가 들어있는 data 변수를 get함수와 함께 사용하여 sampleData 변수에 넣어줌
            val sampleData = data.get(position)

            var gTitle: String = sampleData.title
            var gSubtitle: String = sampleData.subTitle
            var gWriteday: String = sampleData.writeDay
            var gInfo: String = sampleData.info

            //Intent 활용하여 ShowDetailActivity에게 데이터 넘겨주는 과정
            val intent = Intent(context, ShowDetailActivity::class.java)

            //putExtra 이용해서 sampleData의 각 변수를 String으로 저장했던 gTitle~gInfo와 key값을 넘겨줌
            intent.putExtra("iTitle", gTitle)
            intent.putExtra("iSubTitle", gSubtitle)
            intent.putExtra("iWriteDay", gWriteday)
            intent.putExtra("iInfo", gInfo)

            context.startActivity(intent)
        }
    }

}
