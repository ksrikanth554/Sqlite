package com.sritechsoftsolutions.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var dBase:SQLiteDatabase=openOrCreateDatabase("user",Context.MODE_PRIVATE,null)
        dBase.execSQL("create table if not exists emp(_id integer primary key autoincrement,name varchar(50),email varchar(50),mobile integer(50),gender varchar(50))")


        btninsert.setOnClickListener {
            var cv=ContentValues()
            cv.put("name",edtname.text.toString())
            cv.put("email",edtemail.text.toString())
            cv.put("mobile",edtmobile.text.toString().toInt())
            cv.put("gender",edtgender.text.toString())
          var status:Long= dBase.insert("emp",null,cv)

            if (status!=-1L)
            {
                Toast.makeText(this@MainActivity,"one row inserted successfully",Toast.LENGTH_LONG).show()
                edtname.setText("")
                edtemail.setText("")
                edtmobile.setText("")
                edtgender.setText("")

            }
            else
            {
                Toast.makeText(this@MainActivity,"row is not inserted",Toast.LENGTH_LONG).show()

            }
        }
        btnread.setOnClickListener {
            var c:Cursor=dBase.query("emp",null,null,
                null,null,null,null)
            //columns not working
           /* var c:Cursor=dBase.query("emp", arrayOf("name","email","mobile","gender"),null,null,null,null,null)*/

            /*var c:Cursor=dBase.query("emp",null,"mobile=?",
                arrayOf(edtmobile.text.toString()),null,null,null)*/
           /* var c:Cursor=dBase.query("emp",null,null,
                null,"mobile","mobile>852522",null)*/
            /*var c:Cursor=dBase.query("emp",null,null,
                null,null,null,"mobile ")*/

           var cAdapter=SimpleCursorAdapter(this@MainActivity,R.layout.indiview,c, arrayOf("name","email","mobile","gender"),
               intArrayOf(R.id.txtname,R.id.txtemail,R.id.txtmobile,R.id.txtgender),0)
            lview.adapter=cAdapter
        }
        btnupdate.setOnClickListener {

            var cv=ContentValues()

            cv.put("name",edtname.text.toString())
            cv.put("email",edtemail.text.toString())
            var status:Int=dBase.update("emp",cv,"mobile=?", arrayOf(edtmobile.text.toString()))
            if (status>0)
            {
                Toast.makeText(this@MainActivity,"row updated successfully",Toast.LENGTH_LONG).show()
                edtname.setText("")
                edtemail.setText("")
                edtmobile.setText("")
                edtgender.setText("")

            }
            else
            {
                Toast.makeText(this@MainActivity,"row updation failed",Toast.LENGTH_LONG).show()
            }
        }
        btndelete.setOnClickListener {

            var status:Int=dBase.delete("emp","mobile=?", arrayOf(edtmobile.text.toString()))
            if (status>0)
            {
                Toast.makeText(this@MainActivity,"row deleted successfully",Toast.LENGTH_LONG).show()
                edtname.setText("")
                edtemail.setText("")
                edtmobile.setText("")
                edtgender.setText("")
            }
            else
            {
                Toast.makeText(this@MainActivity,"row deletion failed",Toast.LENGTH_LONG).show()
            }
        }

        }
    }

