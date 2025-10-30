package com.zzjdyf.auth.entity.enums;

import java.io.Serializable;

public interface IEnum<T extends Serializable> {
    T getValue();
}
