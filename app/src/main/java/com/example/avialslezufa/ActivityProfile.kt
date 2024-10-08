package com.example.avialslezufa

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsetsAnimation
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.avialslezufa.databinding.ActivityProfileBinding
import com.example.avialslezufa.recyclerview.Element
import com.example.avialslezufa.recyclerview.ElementAdapter
import com.example.avialslezufa.retrofit.ApiClass
import com.example.avialslezufa.retrofit.Service
import dataBase.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.*


class ActivityProfile : AppCompatActivity() {
    //update---------

    var pref: SharedPreferences? = null
    var pref2: SharedPreferences? = null

    var A = 0

    lateinit var bc: ActivityProfileBinding

    private val adapter = ElementAdapter()

    private val imageID = listOf(R.drawable.rrr, R.drawable.pn2, R.drawable.one1, 0)

    private val coroutineScope1 = CoroutineScope(Dispatchers.Main + SupervisorJob())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bc = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(bc.root)

        /*val retrofit = Retrofit.Builder()
            .baseUrl("https://www.dropbox.com/scl/fi/yxrh65lmyj4fy2vvprw2o/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        
        val apiService = retrofit.create(ApiClass::class.java)
        Log.i("logone", "privet1")

       GlobalScope.launch {
            val call = apiService.getInf()
        } */


        pref = getSharedPreferences("TABLE2", MODE_PRIVATE)
        pref2 = getSharedPreferences("HAVEENTRACE1", MODE_PRIVATE)

        var nowUser = getDates(intent.getStringExtra(dt.keyData)!!)

        bc.textName.text = "${nowUser.login}"

        init()
    }


    private fun init(){
        bc.apply {
            rcView.layoutManager = LinearLayoutManager(this@ActivityProfile)
            rcView.adapter = adapter

            for(i in 0 until 4){
                var elem = Element(imageID[i], dt.serviceList[i])
                adapter.addElement(elem)
            }

        }
    }

    fun getDates(login: String): User {
        val a = pref?.getString(login, "-1")!!

        if(a == "-1"){
            return User("0", "0", "0", "0")
        }
        val lst = a.split(" ")
        val us = User(login, lst[0], lst[1], lst[2])

        return us
    }

    fun back(view: View){
        val editor = pref2?.edit()
        editor?.putInt("bool", 0)
        editor?.apply()
        finish()

    }

    fun toSite(view: View){
        //val url = "https://eutcf.tb.ru"
        //val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        val url = "https://github.com/DANIELFEDOROVll/burik/blob/main/services.json"
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    fun clickCardView(view: View){
        if(A == 0){
            A++
            val text1 = "Нажмите еще раз для преобретения продукта"
            val toast1 = Toast.makeText(applicationContext, text1, Toast.LENGTH_SHORT)
            toast1.show()
        }
        else{
            A--
            val text2 = "Заявка на преобретение продукта отправлена"
            val toast2 = Toast.makeText(applicationContext, text2, Toast.LENGTH_SHORT)
            toast2.show()
        }

    }

    fun test1() = runBlocking {
        launch {
            delay(15000)
            Log.i("logone", "srabotalo")
            A = 0
        }
    }
}