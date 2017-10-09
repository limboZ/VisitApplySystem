package cn.com.scal.components.command;


import cn.com.scal.components.domain.Base;
import cn.com.scal.components.dto.BaseDTO;
import cn.com.scal.components.service.ICommonService;
import cn.com.scal.components.utils.CollectionUtil;
import cn.com.scal.components.utils.StringUtil;
import org.modelmapper.ModelMapper;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.vslimit on 15-1-19.
 */
@SuppressWarnings("unchecked")
public class BaseCommand<T extends Base<T>, K extends BaseDTO<K, T>> {

    protected ICommonService<T, K, Integer> service;

    private SortEnum sort = SortEnum.DESC;
    private String order = "id";
    protected String sql;

    private int page = 1;
    private int pageSize = 10;
    protected Map<String, Object> queryMap = new HashMap<String, Object>();


    private String clazzName;

    public enum SortEnum {
        DESC, ASC
    }

    public SortEnum getSort() {
        return sort;
    }

    public void setSort(SortEnum sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderBy() {
        return StringUtil.isEmpty(order) ? "" : " " + order + " " + sort;
    }

    public Map<String, Object> getQueryMap() {
        return queryMap;
    }


    public Class<T> getClazzBase() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Class<K> getClazzDTO() {
        return (Class<K>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public String conditions() {
        return "";
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setQueryMap(Map<String, Object> queryMap) {
        this.queryMap = queryMap;
    }

    public String getClazzName() {
        return StringUtil.isEmpty(clazzName) ? getClazzBase().getName() : clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public List<K> execute() {
        return toDtos(service.query(this));
    }

    public long executeCount() {
        return service.count(this);
    }

    public K toDto(T t) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(t, getClazzDTO());
    }

    public List<K> toDtos(List<T> list) {
        List<K> retList = new ArrayList<>();
        if (!CollectionUtil.blankList(list)) {
            for (T t : list) {
                retList.add(toDto(t));
            }
        }
        return retList;
    }

    public void setService(ICommonService<T, K, Integer> service) {
        this.service = service;
    }
}
