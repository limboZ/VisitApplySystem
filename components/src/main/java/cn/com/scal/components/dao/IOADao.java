package cn.com.scal.components.dao;

import java.util.List;

public interface IOADao {
    List<Object[]> queryBySql(String sql);
}
