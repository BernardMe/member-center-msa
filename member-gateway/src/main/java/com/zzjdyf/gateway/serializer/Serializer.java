package com.zzjdyf.gateway.serializer;

/**
 * Created by xueqing wang on 2021/6/24 20:22
 */
public interface Serializer<I, O> {
    byte[] serialize(I var1) throws IllegalArgumentException;

    O deSerialize(byte[] var1, Class<O> var2) throws IllegalArgumentException, ClassCastException;

    O deSerialize(byte[] var1) throws IllegalArgumentException, ClassCastException;
}
