package com.example.Notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.Notes.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
 //   val myList = mutableListOf<MyDataClass>()
    lateinit var binding: ActivityMainBinding
    private lateinit var notesDao: NotesDao




    //val a = MyAdapter(this, myList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notesDao = NotesDatabase.createDatabase(this).getNotesDao()
        //a.notifyDataSetChanged()
        window.statusBarColor= ContextCompat.getColor(this ,R.color.Fnd)

        binding.rrr.layoutManager=GridLayoutManager(this,2)
        val a = MyAdapter(this , notesDao.getAllItems()) {this.finish()}
        binding.rrr.adapter = a
        a.notifyDataSetChanged()


        binding.efab.setOnClickListener {
//            a.notifyDataSetChanged()
                val intent1 = Intent(this,SaveActivity::class.java )
            startActivity(intent1)
            finish()
        }


    }
    private fun insertToDB(notesdata : NotesData)
    {
        notesDao.insertItem(notesdata)
    }
    fun finishActivity() {
        finish()
    }


}








