package me.cwuyi.sharenote.service;

import me.cwuyi.sharenote.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by XIA on 2017/9/11.
 */

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void saveNote(String note) {
        redisTemplate.opsForList().leftPush("shareNote", note);
    }

    @Override
    public String getNote(long index) {
        return redisTemplate.opsForList().index("shareNote", index).toString();
    }
}
