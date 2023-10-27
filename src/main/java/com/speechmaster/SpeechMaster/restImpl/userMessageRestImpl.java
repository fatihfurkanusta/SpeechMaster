package com.speechmaster.SpeechMaster.restImpl;

import com.speechmaster.SpeechMaster.rest.UserMessageRest;
import com.speechmaster.SpeechMaster.service.ChatGPTService;
import com.speechmaster.SpeechMaster.serviceImpl.ChatGPTServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userMessageRestImpl implements UserMessageRest {


    @Override
    public String getUserMessage(String message) {
        System.out.println("Message: " + message);
        return "Hello Baby!";
    }

    @Override
    public String getChatGPTMessage() {
        return "Hello World";
    }


}
