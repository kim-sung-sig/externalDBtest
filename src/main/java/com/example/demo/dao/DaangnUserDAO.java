package com.example.demo.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DaangnUserDAO {

    List<Long> findIdAll() throws SQLException;

}
