package me.cwuyi.sharenote.service;

import me.cwuyi.sharenote.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

/**
 * Created by XIA on 2017/9/11.
 */

@Service
public class NoteServiceImpl implements NoteService {

    private static final String DEFAULT_ERR_STR = "ERR_MSG:我们似乎遇到了一些问题...Call me if you can find me";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void saveNote(String note) {
        redisTemplate.opsForList().leftPush("sharenote", note);
    }

    @Override
    public String getNote(long index) {
        Object o = redisTemplate.opsForList().index("sharenote", index);
        if (null == o) {
            return DEFAULT_ERR_STR;
        }
        return redisTemplate.opsForList().index("sharenote", index).toString();
    }
}
