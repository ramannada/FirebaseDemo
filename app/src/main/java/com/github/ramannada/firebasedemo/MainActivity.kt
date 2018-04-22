package com.github.ramannada.firebasedemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etData  = findViewById<EditText>(R.id.et_data)
        val tvData  = findViewById<TextView>(R.id.tv_data)
        val btnSave = findViewById<Button>(R.id.btn_save)

        val fireDb  = FirebaseDatabase.getInstance()
        val fireRef = fireDb.getReference("message")

        fireRef.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Toast.makeText(this@MainActivity, p0!!.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(p0: DataSnapshot?) {
                var value = p0!!.getValue(String::class.java)
                tvData.text = value
            }

        })

        btnSave.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                fireRef.setValue(etData.text.toString())
            }
        })
    }
}
