package com.zzjdyf.gateway.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by xueqing wang on 2021/6/24 20:22
 */
@Component
public class KryoSerializer<I, O> implements Serializer<I, O> {
    private static final Logger logger = LoggerFactory.getLogger(KryoSerializer.class);
    private static int DEFAUL_BUFF_SIZE = 4096;
    private static final ThreadLocal<Kryo> KRYOS = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        KryoSerializer.logger.info("{} Thread create a Kryo instance  {}!", Thread.currentThread(), kryo);
        return kryo;
    });

    public KryoSerializer() {
    }

    @Override
    public byte[] serialize(I obj) {
        Output out = new Output(DEFAUL_BUFF_SIZE);
        KRYOS.get().writeClassAndObject(out, obj);
        out.flush();
        return out.getBuffer();
    }

    @Override
    public O deSerialize(byte[] objByte, Class<O> clazz) throws IllegalArgumentException, ClassCastException {
        if (objByte != null && objByte.length != 0) {
            Input input = new Input(objByte);
            return clazz != null ? KRYOS.get().readObject(input, clazz) : this.deSerialize(objByte);
        } else {
            throw new IllegalArgumentException("should must input source!");
        }
    }

    @Override
    public O deSerialize(byte[] data) {
        if (data != null) {
            Input input = new Input(data);
            return (O) KRYOS.get().readClassAndObject(input);
        } else {
            return null;
        }
    }
}

