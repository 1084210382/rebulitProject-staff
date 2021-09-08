package com.example.demo.commonRequest.responsehandler;

/**
 * @author whn
 */

public enum ReturnStateEnum {
    STATUS("STATUS"),

    MESSAGE("MESSAGE"),

    DATA("DATA"),

    SUCCESS("SUCCESS"),

    WARNING("WARNING");

    private String value;

    private ReturnStateEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
