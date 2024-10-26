package com.example.Notes
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.Notes.databinding.RvBinding



class MyAdapter(val context: Context, var myList: MutableList<NotesData>, function: () -> Unit): RecyclerView.Adapter<MyAdapter.MyViewHolder>()
{
    inner class MyViewHolder(var binding: RvBinding) : //RVBinding is the algorythemed name for use rv.xml binding
    RecyclerView.ViewHolder(binding.root)// *** its like an array // straightly connect (view holder) to the xml we made

    //as we read in first lessons when u equal a value to a func it considers as the Return
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) // creats view holder
    // in flater function = make the xml to a view
            = MyViewHolder(RvBinding.inflate(LayoutInflater.from(context),parent,false))

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) { // calls when u scroll // can edit Recycler view
        // exact concept of any recycler item
        // position = is the number of any item
        // opposite if 2 other dont need return value
        //now the access of evth in rv.xml
        holder.binding.textView.text = myList[position].a //the name of the constructor var in dataClass class that we need
        holder.binding.textView2.text = myList[position].b
        val ID = myList[position].id
        holder.binding.anyItemRes.setOnClickListener {
        val intent = Intent(context,SaveActivity::class.java)
            intent.putExtra("update",true)
            intent.putExtra("position1",position)
            intent.putExtra("ID",ID)

            context.startActivity(intent)
            (context as Activity).finish()
        }
    }
    override fun getItemCount(): Int =myList.size //Recycler view item numbers
}