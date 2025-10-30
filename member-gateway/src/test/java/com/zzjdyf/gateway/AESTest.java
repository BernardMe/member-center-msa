//package com.zzjdyf.gateway;
//
//import cn.hutool.core.date.DateUtil;
//import cn.hutool.core.map.MapUtil;
//import cn.hutool.core.util.IdUtil;
//import cn.hutool.core.util.StrUtil;
//import cn.hutool.crypto.Mode;
//import cn.hutool.crypto.Padding;
//import cn.hutool.crypto.SecureUtil;
//import cn.hutool.crypto.symmetric.AES;
//import cn.hutool.json.JSONUtil;
//import org.junit.Test;
//import sun.security.krb5.internal.crypto.Aes128;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Map;
//import java.util.TreeMap;
//
///**
// * Created by xueqing wang on 2021/4/25 12:01
// */
//public class AESTest {
//
//    String key = "03f6530bc7d141419999071a23144780";
//
//    @Test
//    public void encrypt() {
//
//        Map<Object, Object> payload = MapUtil.builder()
//                .put("etcNumber", "123456789123456791")
//                .put("newEtcNumber", "123456789123456792")
//                .put("etcState", "3")
//                .put("orderNo", "10021734")
//                .build();
//
//        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding,key.getBytes());
//
//
//        String s1 = aes.encryptBase64(JSONUtil.toJsonStr(payload));
//        System.out.println(s1);
//
//
//    }
//
//    @Test
//    public void decrypt() {
//
//        String reqData = "DljOXzBmLBIepcMQ69gnjwt/jXdyVqK3oCWAgudbTAvM9vvzBOoyECgDUNyEGShfcFo3fKei2q9Dd4AuwlWPgXtak8msUx7Zm1jGWY2X/o4d2N55eWD5SMHNX3gfgy7hFhFYkC/vN5vVpHrBwZ+dzQ==";
//
//        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding,key.getBytes());
//
//
//        String s1 = aes.decryptStr(reqData);
//        System.out.println(s1);
//
//
//    }
//
//    @Test
//    public void test2() {
//        TreeMap<Object, Object> sort = MapUtil.sort(
//                MapUtil.builder()
//                        .put("jnlId", IdUtil.simpleUUID())
//                        .put("code", null)
//                        .put("message", "")
//                        .put("timestamp", DateUtil.now())
//                        .build()
//        );
//
//
//        System.out.println(JSONUtil.toJsonStr(sort));
//    }
//}
