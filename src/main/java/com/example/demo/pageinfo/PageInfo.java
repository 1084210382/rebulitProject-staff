package com.example.demo.pageinfo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author whn
 */
public class PageInfo {
    private String name;//名称
    private int start;// 开始记录数
    private int end;// 结束记录数
    private int count;//总记录数

    private int tempStrat;
    private int tempEnd;
    private int tempCurrent;

    @Value("${DBType}")
    private String dbType;

    public PageInfo() {
    }

    // items=0-14
    public PageInfo(String rangeText) {
        String[] keyValuePair = rangeText.split("=");
        assert keyValuePair.length == 2 : "range文本被拆分为两个字符串";
        this.name = keyValuePair[0];
        String[] nums = keyValuePair[1].split("-");
        assert nums.length == 2 : "拆分出起始和结束两个字符串";
        this.start = Integer.parseInt(nums[0]);
        this.end = Integer.parseInt(nums[1]);
        this.tempStrat = start;
        this.tempEnd = end;
    }

    //items=0-1/3
    @Override
    public String toString() {
        if (StringUtils.isEmpty(dbType) || "mysql".equals(dbType)) {
            this.end = tempEnd;
            this.start = tempStrat;
        }
        return String.format("items %d-%d/%d", start, end, count);
    }

    public void setCount(int count) {
        if (count == 0) {
            this.start = this.end = this.count = 0;
        } else {
            this.count = count;
        }
    }

    public String getName() {
        return name;
    }

    public int getStart() {
        if (StringUtils.isEmpty(dbType) || "mysql".equals(dbType)) {
            int temp = tempEnd + 1 - tempStrat;
            if (start == 0) {
                this.start = 1;
            } else {
                this.start = tempStrat / temp + 1;
            }
            this.tempCurrent = this.start;
        } else if ("oracle".equals(dbType)) {
            assert count >= 0 : "开始索引应大于0";
            if (count == 0) {
                return start;
            }
            if (start >= count) {
                return count - 1;
            }
        }
        return start;
    }

    public int getEnd() {
        if (StringUtils.isEmpty(dbType) || "mysql".equals(dbType)) {
            if (tempCurrent == 1) {
                this.end = tempEnd + 1;
            } else {
                this.end = tempEnd + 1 - tempStrat;
            }
        } else if ("oracle".equals(dbType)) {
            assert count >= 0 : "开始索引应大于0";
            if (count == 0) {
                return end;
            }
            if (end >= count) {
                return count - 1;
            }
        }
        return end;
    }

    public int getCount() {
        return count;
    }
}
