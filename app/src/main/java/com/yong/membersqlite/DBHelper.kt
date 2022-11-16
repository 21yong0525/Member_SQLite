package com.yong.membersqlite

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(
    val context: Context?,
    val name: String?,
    val factory: SQLiteDatabase.CursorFactory?,
    val version: Int
) :
    SQLiteOpenHelper(context, name, factory, version) {

    //멤버테이블 정의 (앱이 설치된 다음 딱 한 번만 실행된다.)
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(
                "create table memberTBL(" +
                        "id text primary key," +
                        "name text not null," +
                        "password text not null," +
                        "gender text not null," +
                        "phone text not null," +
                        "email text not null," +
                        "address text not null," +
                        "level text not null)"
            )
        }
    }

    //데이타베이스 버전이 바뀔 때 마다 실행된다.
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists memberTBL")
        onCreate(db)
    }

    // memberTBL member 삽입 검사
    fun insert(member: Member?): Boolean {
        val db: SQLiteDatabase = writableDatabase
        var flag = false
        try {
            db.execSQL("insert into memberTBL values('${member?.id}','${member?.name}','${member?.password}','${member?.gender}','${member?.phone}','${member?.email}','${member?.address}','${member?.level}')")
            Log.d("membersqlite", "insert 성공")
            flag = true
        } catch (e: java.lang.Exception) {
            Log.e("membersqlite", "insert 실패 $e.stackTraceToString()")
            flag = false
        } finally {
            db.close()
        }
        return flag
    }

    // memberTBL 아이디 중복 검사
    fun selectCheckID(id: String): Boolean {
        val db: SQLiteDatabase = readableDatabase
        var cursor: Cursor? = null
        var flag = false
        try {
            cursor = db.rawQuery("select id from memberTBL where id = '$id'", null)
            if (cursor.moveToFirst()) {
                if (cursor.getString(0).equals(id)) flag = true
                Log.e("membersqlite", "selectCheckID 성공")
            }
        } catch (e: java.lang.Exception) {
            Log.e("membersqlite", "selectCheckID 실패 $e.stackTraceToString()")
            flag = false
        } finally {
            db.close()
        }
        return flag
    }

    // memberTBL 로그인 기능
    fun selectLogin(id: String, password: String): Boolean {
        val db: SQLiteDatabase = readableDatabase
        var cursor: Cursor? = null
        var flag = false
        try {
            cursor = db.rawQuery(
                "select id, password from memberTBL where id = '$id' and password = '$password'",
                null
            )
            if (cursor.moveToFirst()) {
                if (cursor.getString(0).equals(id) && cursor.getString(1).equals(password)) flag =
                    true
                Log.e("membersqlite", "selectLogin $id $password 성공")
            }
        } catch (e: java.lang.Exception) {
            Log.e("membersqlite", "selectLogin $id $password 실패 $e.stackTraceToString()")
            flag = false
        } finally {
            db.close()
        }
        return flag
    }

    //memberTBL id record를 가져오는것
    fun selectID(id: String): Member? {
        val db: SQLiteDatabase = readableDatabase
        var cursor: Cursor? = null
        var member: Member? = null
        try {
            cursor = db.rawQuery("select * from memberTBL where id = '$id'", null)
            if (cursor.moveToFirst()) {
                Log.e("membersqlite", "select $id 성공")
                member = Member(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7)
                )
            }
        } catch (e: java.lang.Exception) {
            Log.e("membersqlite", "select $id 실패 $e.stackTraceToString()")
        } finally {
            db.close()
        }
        return member
    }

    // memberTBL 삭제기능
    fun delete(id: String): Boolean {
        val db: SQLiteDatabase = writableDatabase
        var flag = false
        try {
            db.execSQL("delete from memberTBL where id = '$id'")
            Log.e("membersqlite", "delete $id  성공")
            flag = true
        } catch (e: java.lang.Exception) {
            Log.e("membersqlite", "delete $id  실패 $e.stackTraceToString()")
            flag = false
        } finally {
            db.close()
        }
        return flag
    }

    // memberTBL 수정기능
    fun update(member: Member?): Boolean {
        val db: SQLiteDatabase = writableDatabase
        var flag = false
        if (member != null) {
            flag = try {
                db.execSQL("update memberTBL set phone = '${member?.phone}', name = '${member?.name}', level='${member?.level}' where id = '${member?.id}'")
                Log.e("membersqlite", "update 성공")
                true
            } catch (e: java.lang.Exception) {
                Log.e("membersqlite", "update 실패 ${e.stackTraceToString()}")
                false
            }  finally {
                db.close()
            }
        }
        return flag
    }

    // member 목록기능
    fun selectList(): ArrayList<Member> {
        val memberList = arrayListOf<Member>()
        val db: SQLiteDatabase = readableDatabase
        var cursor: Cursor? = null
        var member: Member? = null
        try {
            cursor = db.rawQuery("select * from memberTBL", null)
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    member = Member(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)
                    )
                    memberList.add(member)
                }
                Log.e("membersqlite", "selectList 성공")
            } else {
                Log.e("membersqlite", "selectList 목록 없음")
            }
        } catch (e: java.lang.Exception) {
            Log.e("membersqlite", "selectList 실패 ${e.stackTraceToString()}")
        } finally {
            db.close()
        }
        return memberList
    }
}
