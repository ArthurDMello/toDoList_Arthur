package br.edu.satc.todolistbase.data

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.satc.todolistbase.R
import br.edu.satc.todolistbase.roomdatabase.ToDoItem

class ToDoItemAdapter (
    private val dataSet: ArrayList<ToDoItem>,
    private val itemOnClick: (Int, ToDoItem) -> Unit,
    private val itemOnChecked: (Boolean, ToDoItem) -> Unit,
) : RecyclerView.Adapter<ToDoItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.rv_item, viewGroup, false)
        return ViewHolder(view)
    }

 
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var toDoItem: ToDoItem = dataSet[position]

        holder.tvTitle.text = toDoItem.title
        holder.tvDescription.text = toDoItem.description
        holder.cbComplete.isChecked = toDoItem.complete == true



        holder.cbComplete.setOnCheckedChangeListener { buttonView, isChecked ->
            itemOnChecked(isChecked, toDoItem)


        }

        holder.itemView.setOnClickListener {
            itemOnClick(position, toDoItem)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvTitle: TextView
        var tvDescription: TextView
        var cbComplete: CheckBox

        init {
            tvTitle = view.findViewById(R.id.tv_title)
            cbComplete = view.findViewById(R.id.cb_complete)
            tvDescription = view.findViewById(R.id.tv_description)
        }
    }
}