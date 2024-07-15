package org.example;

public class QueryParams {
    private int id;
    private String keyAttr;
    private long valid;
    private long invalid;

    public QueryParams(int id, String keyAttr, long valid, long invalid) {
        this.id = id;
        this.keyAttr = keyAttr;
        this.valid = valid;
        this.invalid = invalid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyAttr() {
        return keyAttr;
    }

    public void setKeyAttr(String keyAttr) {
        this.keyAttr = keyAttr;
    }

    public long getValid() {
        return valid;
    }

    public void setValid(long valid) {
        this.valid = valid;
    }

    public long getInvalid() {
        return invalid;
    }

    public void setInvalid(long invalid) {
        this.invalid = invalid;
    }
}
