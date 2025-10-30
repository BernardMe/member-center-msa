//package com.zzjdyf.gateway;
//
//import cn.hutool.core.codec.Base64;
//import cn.hutool.core.date.DateUtil;
//import cn.hutool.core.map.MapUtil;
//import cn.hutool.core.util.IdUtil;
//import cn.hutool.core.util.RandomUtil;
//import cn.hutool.core.util.StrUtil;
//import cn.hutool.crypto.Mode;
//import cn.hutool.crypto.Padding;
//import cn.hutool.crypto.asymmetric.*;
//import cn.hutool.crypto.symmetric.AES;
//import cn.hutool.json.JSONUtil;
//import com.zzjdyf.gateway.util.Permutation;
//import org.junit.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Map;
//
//@SpringBootTest
//public class GatewayApplicationTests {
//
//    @Test
//    public void contextLoads() {
//        RSA rsa = new RSA(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue());
//        System.out.println(rsa.getPrivateKeyBase64());
//        System.out.println(rsa.getPublicKeyBase64());
//    }
//
//    private static String clientPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCHyO2vFQK1bW6EIgE7thEzUQTgAXqYdlh1Cu0NkH2K3aQVoOWh4LM7EFFgt+icRY2ysg7YkMXShhFnPO/dwqtzd4/xNV5wy4npVZmc4omtyK6u9yoa/pp2m7DI5WG/Tpb//u0AgnAUJQVlAZ0uCZ/shTXfc0gzvNcaCT28y+8zmQIDAQAB";
//    private static String clientPrivateKey = "MIICWwIBAAKBgQCHyO2vFQK1bW6EIgE7thEzUQTgAXqYdlh1Cu0NkH2K3aQVoOWh4LM7EFFgt+icRY2ysg7YkMXShhFnPO/dwqtzd4/xNV5wy4npVZmc4omtyK6u9yoa/pp2m7DI5WG/Tpb//u0AgnAUJQVlAZ0uCZ/shTXfc0gzvNcaCT28y+8zmQIDAQABAoGAPV325eD2B278uqTvr7/aSDn+nQquEIez9LnKZw39XyxKgyfjgGSgPDpkvPSBIVdh8ff9M61CaqIC7Yih07SubWZArbfpe6BH1KGHiJI7XH8k3L5SpPM2fe29Z7AEPGWCHBzMfH8oyhHJPmAHJw5mJwY/5GLNXRSP9mh4n8u5G90CQQDixDdZCB+fthB6jyhM/LIqmIzINDNbh6mAOtqtXXXhn7oSdzuSGE4hgww1BWKq+2EZlqWb9c+GNEOMb4erSd/vAkEAmUofey2nwE2sH91LWZgXc9yfkTqxYq/qDQ7xvdaqOwfEd9VgcJ8NGSYWyhuZ2T5LBpxcDZpJKUGV/2KbdXYc9wJAZriJddwXLyqy5kGo+M7TCjaWNy5mQUT/xCVk33lhp7XJ/Qljw7cc1VNemTn6SweaIZQ7Kp27xsgv/RKcezv4owJAMF/t82eDCvL4IM4rW0jeDdhQGvrUw/oT+K8ApxI5got++vpBhwkKjycwBfQT5zT7ZEiv3DMWZ4ilF4U83iK6HQJALMHmnDT4n65/zjpgCEWwVEQ02cNvrFJSgeET1H+SJ1zu40k2U8czKqAN4c6Vv3vL0uEzz3eoCCXaoY5/yZHixQ==";
//
//    private static String serverPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCZloNjfRrS+jKMEgnEEHbEkrvrA1XmG8BzN+O+ELck9Q3vRWk824ulv/QepUdtqeE7psLu7Z/CT3GeHiHG83OsQghk4gOX+hoiAmitQIU0tbmoSNbufCJprW5w2hckqI44uDeDneVV73NyTx538E3d26cIp3X8xH1TmBzsB74OEwIDAQAB";
//    private static String serverPrivateKey = "MIICXQIBAAKBgQCZloNjfRrS+jKMEgnEEHbEkrvrA1XmG8BzN+O+ELck9Q3vRWk824ulv/QepUdtqeE7psLu7Z/CT3GeHiHG83OsQghk4gOX+hoiAmitQIU0tbmoSNbufCJprW5w2hckqI44uDeDneVV73NyTx538E3d26cIp3X8xH1TmBzsB74OEwIDAQABAoGAC8Rgpm+WoNTP3ADWMOhzNdLpXqaZRX5MntpvDCG7HmcMailFWFPLxBp/HHzDCoLDwZTtUqGJcwYwj5HO1BYH5etIFy9/IC0in/VM9a0OxRxpUu0rur/b4tFLCd+9YuBAIoRcUM7aiEBN8Ii5aPABq53iYwFeJz8iryREASQxAA0CQQDmmQqSuLgkVxpo3M0JZtqLMB9zDa654vgIcHXeHYLTldWn+uD79lIzY6B/bDL8GgFHLnsqa84QJowkyTue1HJtAkEAqoHCjdMnM1L10fCXraQEA07HE+IATGzWQQNYfyalV8gR/h7KVLcNqiLQh0E+NTFLwTW+IAz1218UokgW9gAyfwJBAIzB9lZBMwXbtV81CGMoyVYU9d2BCJqnFMfx/cCrOuR0nYp/9T1Xyi1cLugY37ZLGWSd1si6LXpmTT1XKHF3NLECQFb8H97uq/75AcN6tEa3d/ygAdvY3kIe/CbqqAkmFA/2V4eG+b3CLUxwumVmGXBVl8oXusjrE7o4hXRMxrn1W80CQQC60g6ShrSzPk9j3tkFdfa1M0D6YeAfKEDROknXspu8PNFomVlBnr7RNGu52LQ9iOEidfKX5593S8gEx5+mNr08";
//
//
//
//    public static void main(String[] args) throws UnsupportedEncodingException {
//        String algorithm = SignAlgorithm.SHA256withRSA.getValue();
//        String sequenceId = "web";
//        String clientType = "web";
//        String timestamp = "2021-04-15 08:59:03";
//        byte[] ivBytes = "0000000000000000".getBytes();
//        Map<Object, Object> data = null;
//        Map<Object, Object> sort = null;
//
//        data =
//                MapUtil.builder()
//                        .put("mobile", "17621152225")
//                        .put("name", "admin")
//                        .build();
//        RSA rsa = new RSA(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue(),null, serverPublicKey);
//
//
//        String messageDigest = RandomUtil.randomString(16);
//        System.out.println("客户端数据加密的原始秘钥：" + messageDigest);
//
//        //客户端采用服务端公钥加密
//        String payload = new AES(Mode.CBC, Padding.PKCS5Padding,StrUtil.bytes(messageDigest),ivBytes).encryptBase64(JSONUtil.toJsonStr(data));
//        System.out.println("客户端数据加密payload:" + payload);
//
//        messageDigest = rsa.encryptBase64(messageDigest, KeyType.PublicKey);
//        System.out.println("客户端经过RSA加密的AES秘钥：" + messageDigest);
//
//
//        sort =
//                MapUtil.builder()
//                        .put("sequenceId", sequenceId)
//                        .put("timestamp", timestamp)
//                        .put("algorithm", algorithm)
//                        .put("messageDigest", messageDigest)
//                        .put("payload", payload)
//                        .put("clientType", clientType)
//                        .build();
//
//        //客户端采用客户端私钥加签
//        Sign sign = new Sign(algorithm, clientPrivateKey, null);
//        byte[] signData = sign.sign(StrUtil.bytes(Permutation.sort(sort)));
//        String signature = Base64.encode(signData);
//        System.out.println("客户端的数据签名signature:" + signature);
//
//
//        //服务端采用客户端公钥验签
//        sign = new Sign(algorithm, null, clientPublicKey);
//        boolean verify = sign.verify(StrUtil.bytes(Permutation.sort(sort)), Base64.decode(signature));
//        System.out.println("服务端对客户端数据验签:" + verify);
//
//
//        //服务端采用服务端私钥解密
//        rsa = new RSA(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue(),serverPrivateKey, null);
//        messageDigest = rsa.decryptStr(messageDigest, KeyType.PrivateKey);
//        System.out.println("解密后的客户端数据加密的AES秘钥：" + messageDigest);
//        payload = new AES(Mode.CBC, Padding.PKCS5Padding,StrUtil.bytes(messageDigest),ivBytes).decryptStr(payload);
//        System.out.println("解密后的客户端数据：" + payload);
//
//
//        //服务端返回客户端数据为
//        data =
//                MapUtil.builder()
//                        .put("token", "t25fd9pYeuxbCDkQHif1akXjw2")
//                        .build();
//
//
//        //服务端采用客户端公钥加密
//        rsa = new RSA(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue(),null, clientPublicKey);
//
//        messageDigest = RandomUtil.randomString(16);
//        System.out.println("服务端返回的原始AES秘钥：" + messageDigest);
//
//        //客户端采用服务端公钥加密AES秘钥
//        payload = new AES(Mode.CBC, Padding.PKCS5Padding,StrUtil.bytes(messageDigest),ivBytes).encryptBase64(JSONUtil.toJsonStr(data));
//        System.out.println("服务端返回加密数据payload:" + payload);
//
//        messageDigest = rsa.encryptBase64(messageDigest, KeyType.PublicKey);
//        System.out.println("服务端返回RSA加密的AES秘钥：" + messageDigest);
//
//
//
//
//        //服务端采用服务端私钥加签
//        sort =
//                MapUtil.builder()
//                        .put("sequenceId", sequenceId)
//                        .put("jnlId", IdUtil.simpleUUID())
//                        .put("code", "200")
//                        .put("message", "success")
//                        .put("timestamp", DateUtil.now())
//                        .put("algorithm", algorithm)
//                        .put("messageDigest", messageDigest)
//                        .put("payload", payload)
//                        .build();
//
//
//        sign = new Sign(algorithm, serverPrivateKey, null);
//        signData = sign.sign(StrUtil.bytes(Permutation.sort(sort)));
//        signature = Base64.encode(signData);
//        System.out.println("服务端返回的数据签名signature:" + signature);
//
//        //客户端采用服务端公钥验签
//        sign = new Sign(algorithm, null, serverPublicKey);
//        verify = sign.verify(StrUtil.bytes(Permutation.sort(sort)), Base64.decode(signature));
//        System.out.println("客户端对服务端数据验签:" + verify);
//
//
//        //客户端采用客户端私钥解密
//        rsa = new RSA(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue(),clientPrivateKey, null);
//        messageDigest = rsa.decryptStr(messageDigest, KeyType.PrivateKey);
//        System.out.println("解密后的服务端AES秘钥：" + messageDigest);
//
//        payload = new AES(Mode.CBC, Padding.PKCS5Padding,StrUtil.bytes(messageDigest),ivBytes).decryptStr(payload);
//        System.out.println("解密后的服务端返回的数据：" + payload);
//
//
////        //从证书中获取公钥
////        KeyStore keyStore = KeyUtil.readKeyStore("pkcs12", FileUtil.file("C:\\Users\\imooc\\Desktop\\test.pfx"), "11111111".toCharArray());
////        Certificate certificate = SecureUtil.getCertificate(keyStore, "test");
////        PublicKey publicKey = certificate.getPublicKey();
////
////        rsa = new RSA(null, publicKey);
//
//
//    }
//
//}
