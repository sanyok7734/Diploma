package com.quoteoftheday.raccoonapps.diploma.utils

import android.content.Context
import android.provider.SyncStateContract
import android.util.Log

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.*

import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.lang.reflect.Type
import java.util.ArrayList

object CacheDataRetriever {

    inline fun <reified T> genericType() = object: TypeToken<T>() {}.type

    private fun <T> getListFromFile(file: File, t: String): ArrayList<T> {
        val reader = BufferedReader(FileReader(file))
        val listType = when(t) {
            "User" -> object : TypeToken<ArrayList<User>>() {}.type
            "BaseHierarchies" -> object : TypeToken<ArrayList<BaseHierarchies>>() {}.type
            "ModelKEO" -> object : TypeToken<ArrayList<ModelKEO>>() {}.type
            "MethodKEO" -> object : TypeToken<ArrayList<Method>>() {}.type
            "MethodAdapter" -> object : TypeToken<ArrayList<Method>>() {}.type
            else -> object : TypeToken<ArrayList<User>>() {}.type
        }
        val quoteList = Gson().fromJson<ArrayList<T>>(reader, listType)
        return quoteList
    }

/*    private fun saveToFile(file: File, users: List<User>) {
        val string = Gson().toJson(users)
        val fileWriter = FileWriter(file)
        fileWriter.write(string)nj
        fileWriter.close()
    }*/

    fun <T> File.saveToFile(list: List<T>) {
        val string = Gson().toJson(list)
        val fileWriter = FileWriter(this)
        fileWriter.write(string)
        fileWriter.close()
    }

    fun <T> saveToFileListUsers(context: Context, users: List<T>, name: String) {
        for (user1 in users) {
            Log.d("SANOOOO", "ololo0 " + user1.toString())
        }
        val file = File(context.filesDir.absolutePath + name)
        file.saveToFile(users)
    }

    fun <T> getFromFileListUsers(context: Context, name: String, t: String): ArrayList<T> {
        val file = File(context.filesDir.absolutePath + name)
        if (file.exists()) {
            val list: ArrayList<T> = getListFromFile(file, t)
            return list
        }else
            return ArrayList()
    }
}
