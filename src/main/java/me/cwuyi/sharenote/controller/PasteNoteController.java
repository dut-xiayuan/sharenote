package me.cwuyi.sharenote.controller;

import me.cwuyi.sharenote.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by XIA on 2017/9/11.
 */

@Controller
@RequestMapping("/")
public class PasteNoteController {

    @Autowired
    NoteService noteService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(String note) {
        noteService.saveNote(note);
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public String get(String index) {
        if (index == null || index.length() == 0 || index.equals("null")) {
            return noteService.getNote(1l);
        }
        return noteService.getNote(Long.parseLong(index));
    }

    @RequestMapping(value = "")
    public ModelAndView index(String index) {
        ModelAndView mav = new ModelAndView("/templates");
        String note = noteService.getNote(1l);
        mav.addObject("note", note);
        mav.addObject("index", "1");
        return mav;
    }
}
