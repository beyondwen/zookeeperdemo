package com.wenhao.zookeeperdemo.registerconfig;

import com.google.common.base.Charsets;
import org.wenhao.zkclient.exception.ZkMarshallingError;
import org.wenhao.zkclient.serialize.ZkSerializer;


public class MyZkSerializer implements ZkSerializer {
    @Override
    public byte[] serialize(Object o) throws ZkMarshallingError {
        return String.valueOf(o).getBytes(Charsets.UTF_8);
    }

    @Override
    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        return new String(bytes, Charsets.UTF_8);
    }
}
