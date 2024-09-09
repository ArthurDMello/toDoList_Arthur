package br.edu.satc.todolistbase.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todoitens")
data class ToDoItem(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "complete") var complete: Boolean?,
) {}