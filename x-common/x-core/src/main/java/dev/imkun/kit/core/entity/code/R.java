package dev.imkun.kit.core.entity.code;


import dev.imkun.kit.core.service.Code;

import java.util.HashMap;

public class R<K extends CharSequence,V extends Object> extends HashMap {

    private static final long serialVersionUID = -3453965744089769725L;

    public V getData() {
        return (V)this.get("data");
    }

    public R setData(V data) {
        this.put("data",data);
        return this;
    }

    public R put(Object key, Object value) {
        super.put(key, value);
        return this;
    }

    public R put(Code code) {
        super.put("status",code.status());
        super.put("message",code.message());
        return this;
    }

    public static R OK() {
        R r = new R();
        r.put(DefaultResponseCode.SUCCESS);
        return r;
    }
    public static R ok(Object data) {
        return OK().put("data",data);
    }
    public static R error() {
        return new R().put(DefaultResponseCode.FAILURE);
    }
    public static R error(Code code) {
        return new R().put(code);
    }
    public static R error(String messages) {
        return new R().put(DefaultResponseCode.FAILURE).put("message",messages);
    }

}
