package com.example.foodapp.model;

public class QueryResult {
    String result;
    String type;
/* a
* america country
* lamb category
* oil ingredient
*  */
    public QueryResult(String result, String type) {
        this.result = result;
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
