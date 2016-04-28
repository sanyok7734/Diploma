package com.quoteoftheday.raccoonapps.diploma.mode.pojo

import android.util.Log
import java.util.*


object Constants {
    @JvmStatic val USERS = "/usersList";
    @JvmStatic val BASE_HIERARCHIES = "/BaseHierarchies";
    @JvmStatic val MODEL_KEO = "/ModelKEO";
    @JvmStatic val METHOD_KEO = "/MethodKEO";
    @JvmStatic val METHOD_ADAPTER = "/MethodAdapter";

    val privilegeRU: List<String> = Arrays.asList("Редактирование пользователей",
            "Редактирование базы иерархии",
            "Редактирование моделей KEO",
            "Редактирование базы методов голосования")

    val privilegeEN: List<String> = Arrays.asList("Editing users",
            "Editing base hierarchy",
            "Editing patterns KEO",
            "Editing base voting methods")



    fun getPrivilege(locale: String): List<String> {
        when(locale){
            "ru_RU" -> return privilegeRU
            else -> return privilegeEN
        }
    }

}