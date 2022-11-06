package top.mikevane;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: whb
 * @date: 2022-10-13-10-12
 * @version: 1.0
 */
@Slf4j
public class Test {
    @org.junit.jupiter.api.Test
    public void test(){
        Object o = new Object();
        o.hashCode();
        String a = "123";
        String b = new String("123");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(1);
        System.out.println(a == b);
        a.hashCode();
        a.equals(b);
    }
}
