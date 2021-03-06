package com.example.demo.common.doPage;

/**
 * @author zcc
 * @date 2019/4/12 11:00
 */
public class PageQuery {
    private int isPage = 0;
    private int pageNo = 1;
    private int pageSize = 10;
    private int offset;
    private int pageFlag = 1;

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

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return (pageNo - 1) * pageSize;
    }

    public int getIsPage() {
        return isPage;
    }

    public void setIsPage(int isPage) {
        this.isPage = isPage;
    }

    public int getPageFlag() {
        return pageFlag;
    }

    public void setPageFlag(int pageFlag) {
        this.pageFlag = pageFlag;
    }
}
