package com.example.demo.external.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
@MapperScan(basePackages = "com.example.demo.external.dao", sqlSessionFactoryRef = "externalSqlSessionFactory")
public interface DaangnMemberDAO {

    List<Integer> findIdxAll() throws SQLException;

}
