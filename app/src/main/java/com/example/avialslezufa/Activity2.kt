package com.example.avialslezufa


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.avialslezufa.databinding.Activity2Binding
import kotlinx.coroutines.*
import kotlinx.coroutines.coroutineScope as coroutineScope
import com.example.avialslezufa.MainActivity

class Activity2 : AppCompatActivity() {
    lateinit var bc: Activity2Binding

    var pref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bc = Activity2Binding.inflate(layoutInflater)
        setContentView(bc.root)

        pref = getSharedPreferences("TABLE2", Context.MODE_PRIVATE)

        val login1 = intent.getStringExtra(dt.key1)

        bc.editTextRegisterLogin.setText(login1)

        bc.buttonRegisterRegister.setOnClickListener {
            if(bc.editTextRegisterName.text.toString() == "" ||
                bc.editTextRegisterFam.text.toString() == "" ||
                bc.editTextRegisterPassword.text.toString() == "" ||
                bc.editTextRegisterRepeatPassword.text.toString() == "")
            {
                bc.textView2.text = getString(R.string.emptyPole)
                bc.textView2.visibility = View.VISIBLE
            }

            else if(bc.editTextRegisterPassword.text.toString() != bc.editTextRegisterRepeatPassword.text.toString())
            {
                bc.textView2.text = getString(R.string.nonRpass)
                bc.textView2.visibility = View.VISIBLE
            }
            else if(bc.editTextRegisterPassword.text.toString().length < 6){
                bc.textView2.text = getString(R.string.bukv6)
                bc.textView2.visibility = View.VISIBLE
            }
            else{
                val getdata = pref?.getString(bc.editTextRegisterLogin.text.toString(), "-1")!!

                if(getdata != "-1"){ // если аккаунт с таким логином уже существует
                    bc.textView2.text = getString(R.string.ujesushestvuet)
                    bc.textView2.visibility = View.VISIBLE
                }
                else{
                    // сохраняем данные
                    val datasave = (bc.editTextRegisterName.text.toString()+ " " +
                            bc.editTextRegisterFam.text.toString() + " " +
                            bc.editTextRegisterPassword.text.toString()
                            )
                    saveData(bc.editTextRegisterLogin.text.toString(), datasave)

                    // передаем в mainActivity данные с ввода
                    intent.putExtra(dt.key_Login, bc.editTextRegisterLogin.text.toString()) // логин
                    intent.putExtra(dt.key_pass, bc.editTextRegisterPassword.text.toString()) // пароль
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        }
    }

    fun saveData(login: String, dates: String){
        val editor = pref?.edit()
        editor?.putString(login, dates)
        editor?.apply()
    }
}