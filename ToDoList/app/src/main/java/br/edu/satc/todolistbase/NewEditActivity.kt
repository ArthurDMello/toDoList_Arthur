package br.edu.satc.todolistbase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import br.edu.satc.todolistbase.roomdatabase.AppDatabase
import br.edu.satc.todolistbase.roomdatabase.ToDoItem

class NewEditActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var btSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_edit)

        initDatabase()

        etTitle = findViewById(R.id.etTitle)
        etDescription = findViewById(R.id.etDescription)
        btSave = findViewById(R.id.btSave)

        btSave.setOnClickListener{
            save()
        }
    }

    private fun save(){

        val toDoItem = ToDoItem(
            null,
            etTitle.text.toString(),
            etDescription.text.toString(),
            false
        )

        db.toDoItemDao().insertAll(toDoItem)

        Toast.makeText(this, "Tarefa salva com sucesso", Toast.LENGTH_SHORT).show()


        finish()

    }

    private fun initDatabase() {
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-todolist"
        )
            .allowMainThreadQueries()
            .build()
    }
}