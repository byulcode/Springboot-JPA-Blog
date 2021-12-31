package com.bybit.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bybit.blog.model.Board;
import com.bybit.blog.model.User;


public interface BoardRepository extends JpaRepository<Board, Integer>{
	
}
