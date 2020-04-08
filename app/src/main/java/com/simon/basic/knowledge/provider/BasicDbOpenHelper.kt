package com.simon.basic.knowledge.provider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BasicDbOpenHelper(
    context: Context?,
    name: String? = "BASIC_DB",
    factory: SQLiteDatabase.CursorFactory?,
    version: Int = 1
) : SQLiteOpenHelper(context, name, factory, version) {

    companion object {
        private const val BASIC_TABLE: String = "basic_table"
        private const val CREATE_USER_TABLE = ("CREATE TABLE IF NOT EXISTS " + BASIC_TABLE +
                "(_id INTEGER PRIMARY KEY," +
                "id TEXT, " +
                "name TEXT, " +
                "phone TEXT, " +
                "gender INT, " +
                ")")
        private val INSERT_BASIC_TABLE = "insert into$BASIC_TABLE("
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
