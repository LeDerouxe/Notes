package com.example.Notes

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.Notes.databinding.ActivitySaveBinding



class SaveActivity : AppCompatActivity() {
    private lateinit var notesDao: NotesDao
    lateinit var binding: ActivitySaveBinding
    var isInUpdateMode :Boolean =false
    var positionIndex :Int =0
    var ID :Int=0
    override fun onBackPressed() {
        super.onBackPressed()  // Finish the activity by default
        val intent7 = Intent(this,MainActivity::class.java )
        startActivity(intent7)
        finish()
    }
    override fun onCreate(savedInstanceState: Bundle?) {



              super.onCreate(savedInstanceState)
        binding = ActivitySaveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor= ContextCompat.getColor(this ,R.color.Fnd)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            permissions()
        }
              notesDao = NotesDatabase.createDatabase(this).getNotesDao()
       // notesDao = NotesDatabase.createDatabase(this).getNotesDao()
        val inten =intent
        isInUpdateMode=inten.getBooleanExtra("update" ,false )
        positionIndex=inten.getIntExtra("position1" ,1 )
        ID=inten.getIntExtra("ID" ,1 )
if (isInUpdateMode){
    binding.textView3.text = "مشاهده یادداشت"
    binding.saveButton.text= "ویرایش"
    binding.titleET.setText(notesDao.getAllItems()[positionIndex].a)
    binding.descriptionET.setText(notesDao.getAllItems()[positionIndex].b)
    binding.imageView.visibility = View.VISIBLE
}

        binding.saveButton.setOnClickListener {

    if (!isInUpdateMode){
      //  Toast.makeText(this, "InInsert", Toast.LENGTH_LONG).show()
        val title = binding.titleET.text.toString()
        val description = binding.descriptionET.text.toString()
        val vv = NotesData(a=title,b=description)
    insertToDB(vv)
        notification(des = "یادداشت $title با موفقیت اضافه شد")
    }
    else{
        val title1 = binding.titleET.text.toString()
        val description1 = binding.descriptionET.text.toString()
      //  Toast.makeText(this, positionIndex.toString(), Toast.LENGTH_LONG).show()
       notesDao.updateItem(NotesData(id=ID,a=title1,b=description1))
        notification(des = "یادداشت $title1 با موفقیت ویرایش شد")
      //  notesDao.deleteItem(NotesData(id=positionIndex,a=title1,b=description1))
    }


    val intent2 = Intent(this,MainActivity::class.java )
   startActivity(intent2)
    finish()
}
binding.imageView.setOnClickListener {

    val title2 = binding.titleET.text.toString()
    val description2 = binding.descriptionET.text.toString()
    notesDao.deleteItem(NotesData(id=ID,a=title2,b=description2))
    notification(des = "یادداشت $title2 با موفقیت حذف شد")
    val intent3= Intent(this,MainActivity::class.java )
    startActivity(intent3)
    finish()
}
    }
    private fun insertToDB(notesdata : NotesData)
    {

        notesDao.insertItem(notesdata)
    }
    private fun UpdateToDB(nd : NotesData)
    {



    }
    private fun permissions(): Boolean {
        val listOfPermissions = mutableListOf<String>()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val notificationPermission =
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
            if (notificationPermission != PackageManager.PERMISSION_GRANTED) {
                listOfPermissions.add(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }


        if (listOfPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, listOfPermissions.toTypedArray(), 1)
            return false
        }
        return true
    }

    private fun notification(
        icon: Int = R.mipmap.ic_launcher,
        title: String = getString(R.string.app_name),
        des: String = "",
    ) {

        val channelId = "notification"

        val notification = NotificationCompat.Builder(this, channelId)
           // .setSmallIcon(icon)
           .setSmallIcon(R.drawable.q)
            .setContentTitle(title)

            .setDefaults(Notification.DEFAULT_ALL)

            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentText(des)
            // .setLargeIcon(icon)
         //   .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.q))
            .build()

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "one", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, notification)
    }

}