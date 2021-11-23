package com.example.demo.common.doPage;



import java.util.ArrayList;
import java.util.List;

/**
 * @author zcc
 * @date 2019/4/12 11:00
 */
public class PageResult<T>  {
    private List<T> data = new ArrayList<>();

    private int total = 0;

    private int pageNo = 0;

    private int pageSize = 0;

    public int getPageNo() {

        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
