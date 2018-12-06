package com.clin.util;

import java.io.Serializable;

public interface RedisService {
    public void sendMessage(String channel, Serializable message);
}
