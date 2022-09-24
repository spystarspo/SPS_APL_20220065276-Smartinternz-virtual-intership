package com.rudhira.groceryList.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.rudhira.groceryList.databinding.ActivityAddNoteBinding
import com.rudhira.groceryList.db.NoteDatabase
import com.rudhira.groceryList.db.NoteEntity
import com.rudhira.groceryList.utils.Constants.NOTE_DATABASE
import com.google.android.material.snackbar.Snackbar

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private val noteDB : NoteDatabase by lazy {
        Room.databaseBuilder(this,NoteDatabase::class.java,NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    private lateinit var noteEntity: NoteEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSave.setOnClickListener {
                val title = edtTitle.text.toString()
                val desc = edtDesc.text.toString()
                if (title.isNotEmpty() || desc.isNotEmpty()){
                    noteEntity= NoteEntity(0,title,desc)
                    noteDB.doa().insertNote(noteEntity)
                    finish()
                }
                else{
                    Snackbar.make(it,"Title and Description cannot be Empty",Snackbar.LENGTH_LONG).show()
                }
            }
        }

    }
}