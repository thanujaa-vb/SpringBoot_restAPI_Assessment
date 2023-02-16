package com.project.FirstJavaApplication;

public class keyvalue {
    // @Id
    private String _id;

    private String key;
    private String value;

    public keyvalue(String _id, String key, String value) {
        this._id = _id;
        this.key = key;
        this.value = value;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
