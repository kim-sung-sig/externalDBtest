package com.example.demo.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.DaangnUserDAO;
import com.example.demo.external.dao.DaangnMemberDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequiredArgsConstructor
public class DBToDBController {

    private final DaangnUserDAO daangnUserDAO;

    private final DaangnMemberDAO daangnMemberDAO;

    @GetMapping("/")
    public Map<String, Object> getMethodName() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Integer> externalList = daangnMemberDAO.findIdxAll();
            List<Long> internalList = daangnUserDAO.findIdAll();
            log.info("externalList: {}", externalList);
            log.info("internalList: {}", internalList);
            result.put("externalList", externalList);
            result.put("internalList", internalList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
}
