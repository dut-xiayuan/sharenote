package me.cwuyi.sharenote.service;

import org.springframework.stereotype.Service;

/**
 * Created by XIA on 2017/9/11.
 */

public interface NoteService {
    void saveNote (String note);
    String getNote (long index);
}
