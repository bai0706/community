package com.nowcoder.community.dao;

/*
 * @auther bai
 * date 2022/4/20
 */

import org.springframework.stereotype.Repository;

@Repository
public class AlphaDaoHibernateImpl implements AlphaDao{
    @Override
    public String select() {
        return "Hinernate";
    }
}
