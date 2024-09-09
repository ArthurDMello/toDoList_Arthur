package br.edu.satc.todolistbase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import br.edu.satc.todolistbase.data.ToDoItemAdapter
import br.edu.satc.todolistbase.roomdatabase.AppDatabase
import br.edu.satc.todolistbase.roomdatabase.ToDoItem
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private var TAG = "MainActivity"
    private lateinit var db: AppDatabase
    private lateinit var toDoItemList: ArrayList<ToDoItem>
    private lateinit var toDoItemAdapter: ToDoItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDatabase()

        initRecyclerViewAdapter()

        loadData()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {

            startActivity(Intent(this, NewEditActivity:: class.java))



        }
    }

    private fun initDatabase() {
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-todolist"
        )
            .allowMainThreadQueries()
            .build()


    }

    private fun initRecyclerViewAdapter() {

        val rv = findViewById<RecyclerView>(R.id.rv_itens)
        rv.layoutManager = LinearLayoutManager(this)

        toDoItemList = ArrayList()

        val itemOnClick: (Int, ToDoItem) -> Unit = { position, item ->
            Log.d(TAG, "Click pos: $position | desc: ${item.description}")
        }

        val itemOnChecked: (Boolean, ToDoItem) -> Unit = { isChecked, item ->
            Log.d(TAG, "Checked pos: $isChecked | desc: ${item.description}")
            item.complete = isChecked
            db.toDoItemDao().updateToDoItems(item)
        }

        toDoItemAdapter = ToDoItemAdapter(toDoItemList, itemOnClick, itemOnChecked)

        rv.adapter = toDoItemAdapter

    }

    private fun loadData() {
        toDoItemList.clear()
        toDoItemList.addAll(db.toDoItemDao().getAll() as ArrayList<ToDoItem>)
        toDoItemAdapter.notifyDataSetChanged()
    }
}