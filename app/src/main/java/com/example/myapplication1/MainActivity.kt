package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication1.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {


 private  lateinit var   binding : ActivityMainBinding
    var count:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        val database = Firebase.database
        val myRef = database.getReference()

        binding.savedata.setOnClickListener {
            val name = binding.personName.text.toString()
            val id = binding.personId.text.toString()
            val age = binding.personAge.text.toString()




            val person = hashMapOf(
                "name" to binding.personName,
                "id" to binding.personId,
                "age" to binding.personAge
            )


            myRef.child("person").child("$count").setValue("person")
            count++
            Toast.makeText(applicationContext,"succsess",Toast.LENGTH_SHORT).show()
        }
        setContentView(R.layout.activity_main)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


             binding.getdata.setOnClickListener {
                 // Read from the database
                 myRef.addValueEventListener(object: ValueEventListener {

                     override fun onDataChange(snapshot: DataSnapshot) {
                         // This method is called once with the initial value and again
                         // whenever data at this location is updated.
                         val value = snapshot.getValue()
                         binding.textdata.text=value.toString()
                         Toast.makeText(applicationContext,"succsess",Toast.LENGTH_SHORT).show()

                     }

                     override fun onCancelled(error: DatabaseError) {
                         Toast.makeText(applicationContext,"ops sory ",Toast.LENGTH_SHORT).show()

                     }

                 })

             }



    }
}