package com.bybit.blog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bybit.blog.model.User;

//DAO
//@자동으로 bean등록이 된다
//@Repository //생략가능
public interface UserRepository<T> extends JpaRepository<User, Integer>{


}
