import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.repository.instruction.model.InstructionModel

class InstructionAdapter(private val instructions: List<InstructionModel>) :
    RecyclerView.Adapter<InstructionAdapter.InstructionViewHolder>() {

    class InstructionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val displayText: TextView = itemView.findViewById(R.id.textViewDisplayText)
        val time: TextView = itemView.findViewById(R.id.textViewTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return InstructionViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: InstructionViewHolder, position: Int) {
        val currentInstruction = instructions[position]
        holder.displayText.text = currentInstruction.display_text
        holder.time.text = "Start: ${currentInstruction.time?.start_time}, End: ${currentInstruction.time?.end_time}"
    }

    override fun getItemCount(): Int {
        return instructions.size
    }
}
