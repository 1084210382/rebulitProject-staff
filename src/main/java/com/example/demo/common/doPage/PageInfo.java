package com.example.demo.common.doPage;

/**

 * @author whn
 */
public class PageInfo {
    private int start;// pageNo 从0开始
    private int end;// pageSize
    private int count;//总记录数

    public PageInfo() {
    }

    // items=0-14
    public PageInfo(String rangeText) {
        String[] nums = rangeText.split(":");
        this.start = Integer.parseInt(nums[0]);
        this.end = Integer.parseInt(nums[1]);
    }

    //items=0-1/3
    @Override
    public String toString() {
        return String.format("%d:%d/%d", start, end, count);
    }

    public void setCount(int count) {
        if (count == 0) {
            this.start = this.end = this.count = 0;
        } else {
            this.count = count;
        }
    }

    public int getStart() {
        if (start == 0) {
            this.start = 1;
        }
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getCount() {
        return count;
    }
}