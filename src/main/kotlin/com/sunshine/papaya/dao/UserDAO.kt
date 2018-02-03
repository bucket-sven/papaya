package com.sunshine.papaya.dao

import com.sunshine.papaya.bean.User
import org.apache.ibatis.annotations.*

interface UserDAO {
    companion object {
        const val TABLE_NAME = "app_user"
    }
    @Select("SELECT * FROM $TABLE_NAME WHERE id = #{id}")
    fun findById(id: Long): User?

    @Insert("INSERT INTO $TABLE_NAME(nickname) VALUES (#{nickname})")
    fun save(user: User): Int

    @Update("UPDATE $TABLE_NAME SET nickname = #{nickname} WHERE id = #{id}")
    fun update(user: User): Int

    @Delete("DELETE FROM $TABLE_NAME WHERE id = #{id}")
    fun delete(id: Long)

    @Select("SELECT * FROM $TABLE_NAME WHERE id IN (#{ids})")
    fun findByIds(ids: Array<Long>): Array<User>
}
