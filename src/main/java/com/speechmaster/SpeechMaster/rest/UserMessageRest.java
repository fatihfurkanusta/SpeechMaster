package com.speechmaster.SpeechMaster.rest;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/userMessage")
@CrossOrigin(origins = "*")
public interface UserMessageRest {

    @PostMapping("/post")
    public String getUserMessage(@RequestBody String message);

    @GetMapping("/get")
    public String getChatGPTMessage();
}
