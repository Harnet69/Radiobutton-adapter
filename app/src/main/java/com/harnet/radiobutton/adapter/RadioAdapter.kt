import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.harnet.radiobutton.R
import com.harnet.radiobutton.model.Report

class RadioAdapter : RecyclerView.Adapter<RadioAdapter.RadioHolder>() {

    // More efficient way!!! calculates the difference between two lists and outputs a list of update operations that
    // converts the first list into the second one
    private val diffUtil = object : DiffUtil.ItemCallback<Report>(){
        override fun areItemsTheSame(oldItem: Report, newItem: Report): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Report, newItem: Report): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerViewDiffer = AsyncListDiffer(this, diffUtil)

    var reports: List<Report>
        get() = recyclerViewDiffer.currentList
        set(value) = recyclerViewDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_radio_btn, parent, false)
        return RadioHolder(view)
    }

    override fun onBindViewHolder(holder: RadioHolder, position: Int) {
        //TODO implement dataBinding
        val reportName = holder.itemView.findViewById<RadioButton>(R.id.reportNameBtn)

        val report = reports[position]
        holder.itemView.apply {
            reportName.text = report.name
        }

    }

    class RadioHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
        return reports.size
    }
}