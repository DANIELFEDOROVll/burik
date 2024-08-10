package com.example.avialslezufa

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.example.avialslezufa.databinding.ActivityMainBinding
import dataBase.User

class MainActivity : AppCompatActivity() {
    lateinit var bc: ActivityMainBinding

    var entraceOnThisPhone = 0

    var pref: SharedPreferences? = null
    var pref2: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bc = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bc.root)

        pref = getSharedPreferences("TABLE2", Context.MODE_PRIVATE)
        pref2 = getSharedPreferences("HAVEENTRACE1", Context.MODE_PRIVATE)

        entraceOnThisPhone = pref2?.getInt("bool", 0)!!

        if(entraceOnThisPhone == 1){
            val a1 = pref2?.getString("datesEntrace", "0 0")!!.split(" ")
            bc.editTextLogin.setText(a1[0])
            bc.editTextPassword.setText(a1[1])

            var intent2 = Intent(this, ActivityProfile::class.java)
            intent2.putExtra(dt.key2, bc.editTextLogin.text.toString())
            intent2.putExtra(dt.key3, bc.editTextPassword.text.toString())
            intent2.putExtra(dt.keyData, bc.editTextLogin.text.toString())

            startActivityForResult(intent2, dt.key2_code)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == dt.key1_code && data != null && resultCode == RESULT_OK){
            var loginSA = data.getStringExtra(dt.key_Login)
            var passwordSA = data.getStringExtra(dt.key_pass)

            bc.editTextLogin.setText(loginSA)
            bc.editTextPassword.setText(passwordSA)

            val a = pref?.getString(bc.editTextLogin.text.toString(), "0")!!
            Log.i("logone", "login = ${bc.editTextLogin.text.toString()}, password = $a")
        }
    }

    fun onActivity2(view: View){
        var intent = Intent(this, Activity2::class.java)
        intent.putExtra(dt.key1, bc.editTextLogin.text.toString())
        startActivityForResult(intent, dt.key1_code)
    }

    fun onEntrace(view: View){ // кнопка Войти
        if(bc.editTextLogin.text.toString() == "" || bc.editTextPassword.text.toString() == ""){
            bc.textInfa.visibility = View.VISIBLE
            bc.textInfa.text = getString(R.string.emptyPole)
        }
        else{
            // найте логин и сравнить правильность пароля
            if( (pref?.getString(bc.editTextLogin.text.toString(), "-1")!!) == "-1" ){ // логин не существует
                bc.textInfa.text = getString(R.string.nevernlog)
                bc.textInfa.visibility = View.VISIBLE
            }
            else{
                val us = getDates(bc.editTextLogin.text.toString())
                if(us.password == bc.editTextPassword.text.toString()){ // если пароль совпал
                    bc.textInfa.visibility = View.GONE

                    var intent2 = Intent(this, ActivityProfile::class.java)
                    intent2.putExtra(dt.key2, bc.editTextLogin.text.toString())
                    intent2.putExtra(dt.key3, bc.editTextPassword.text.toString())
                    intent2.putExtra(dt.keyData, us.login)

                    entracephone(bc.editTextLogin.text.toString(), bc.editTextPassword.text.toString())

                    startActivityForResult(intent2, dt.key2_code)
                }
                else{
                    bc.textInfa.text = getText(R.string.nevernlog)
                    bc.textInfa.visibility = View.VISIBLE
                }
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

    fun entracephone(login: String, password: String){
        val editor = pref2?.edit()
        editor?.putString("datesEntrace", "$login $password")
        editor?.putInt("bool", 1)
        editor?.apply()
    }
}