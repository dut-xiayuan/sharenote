package me.cwuyi.sharenote.service;


/**
 * Created by XIA on 2017/9/11.
 */

public interface NoteService {
    void saveNote (String note);
    String getNote (long index);
}
