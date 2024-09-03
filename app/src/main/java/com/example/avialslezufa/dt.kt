package com.example.avialslezufa

object dt {
    val LOG1 = "mylogone"

    // для передачи данных между mainActivity и Activity2
    val key1 = "key1"
    val key1_code = 100

    val key2 = "key2"
    val key2_code = 101

    val key3 = "key3"

    // передача данных с Activity2 на MainActivity
    val key_pass = "keypass"
    val key_Login = "keylogin"

    // ключ для передачи данных пользователя с mainActivity в ProfileActivity
    val keyData = "keydata"
    val keyData_code = 102

    // список названий и цен услуг
    val serviceList = listOf<String>("Рабочая сила\n 5000 р/сутки",
        "Техника\n 10 000 р/сутки",
        "Поиск места \n бесплатно",
        "У вас нет особых предложений")
}