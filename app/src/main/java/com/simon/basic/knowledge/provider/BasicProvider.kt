package com.simonren.contentprovide.basic.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

class BasicProvider : ContentProvider() {

    private lateinit var  basicDbOpenHelper: BasicDbOpenHelper


    /**
     * 初始化内容提供器的时候调用。通常会在这里完成对数据库的创建和升级等操作，
     * 返回 true 表示内容提供器初始化成功，返回 false 则表示失败。注意，只有
     * 当存在 ContentResolver 尝试访问我们程序中的数据时，内容提供器才会被初始化。
     */
    override fun onCreate(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    /**
     * 向内容提供器中添加一条数据。使用 uri 参数来确定要添加到的表，待添加的数据
     * 保存在 values 参数中。添加完成后，返回一个用于表示这条新记录的 URI。
     */
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    /**
     * 从内容提供器中查询数据。使用 uri 参数来确定查询哪张表，projection 参数用
     * 于确 定查询哪些列，selection 和 selectionArgs 参数用于约束查询哪些行，
     * sortOrder 参数用于 对结果进行排序，查询的结果存放在 Cursor 对象中返回。
     */
    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * 更新内容提供器中已有的数据。使用 uri 参数来确定更新哪一张表中的数据，新数
     * 据保存在 values 参数中，selection 和 selectionArgs 参数用于约束更新哪些行，
     * 受影响的行数将作为返回值返回。
     */
    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * 从内容提供器中删除数据。使用 uri 参数来确定删除哪一张表中的数据，selection
     * 和 selectionArgs 参数用于约束删除哪些行，被删除的行数将作为返回值返回。
     */
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    /**
     * 根据传入的内容 URI 来返回相应的 MIME 类型。 可以看到，几乎每一个方法都会
     * 带有 Uri 这个参数，这个参数也正是调用 ContentResolver的增删改查方法时传
     * 递过来的。而现在，我们需要对传入的 Uri 参数进行解析，从中分析出 调用方
     * 期望访问的表和数据。
     */
    override fun getType(uri: Uri): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}