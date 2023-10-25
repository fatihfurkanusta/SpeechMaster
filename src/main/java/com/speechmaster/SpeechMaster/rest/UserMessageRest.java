package com.speechmaster.SpeechMaster.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/userMessage")
@CrossOrigin(origins = "*")
public interface UserMessageRest {

    @PostMapping("/get")
    public String getUserMessage(@RequestBody String message);
}
