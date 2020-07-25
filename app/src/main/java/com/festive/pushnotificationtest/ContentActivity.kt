package com.festive.pushnotificationtest

import android.content.ContentResolver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_content.textView

class ContentActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null)
        val contacts = arrayListOf<String>()
        while (cursor!!.moveToNext()){
            contacts.add(cursor.getString((cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))))
        }

        textView.setAdapter(ArrayAdapter(this,android.R.layout.simple_list_item_1,contacts))
        cursor.close()
        textView.onItemClickListener = object :AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@ContentActivity,contacts[position],Toast.LENGTH_SHORT).show()
            }
        }
    }



}
