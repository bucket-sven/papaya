package com.sunshine.papaya.service

import com.sunshine.papaya.bean.User
import com.sunshine.papaya.dao.UserDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService {
    @Autowired
    lateinit var userDAO: UserDAO

    @Transactional
    fun updateUser(user: User): Int {
        return userDAO.update(user)
    }

    fun findUserById(id: Long): User? {
        return userDAO.findById(id)
    }
}