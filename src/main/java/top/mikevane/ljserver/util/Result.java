package top.mikevane.ljserver.util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: whb
 * @date: 2022-09-15-15-43
 * @version: 1.0
 */
@Data
public class Result<T> {
    /**
     * 编码：200成功，500和其它数字为失败
     */
    private Integer code;

    /**
     * 错误或者成功信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    /**
     * 动态数据
     */
    private Map map = new HashMap();

    public static <T> Result<T> success(T object) {
        Result<T> r = new Result<T>();
        r.data = object;
        r.code = 200;
        return r;
    }

    public static <T> Result<T> error(String msg) {
        Result r = new Result();
        r.msg = msg;
        r.code = 500;
        return r;
    }

    public static <T> Result<T> error(String msg,Integer code) {
        Result r = new Result();
        r.msg = msg;
        r.code = code;
        return r;
    }

    public Result<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
