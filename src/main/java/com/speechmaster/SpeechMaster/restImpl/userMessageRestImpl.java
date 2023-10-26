package com.speechmaster.SpeechMaster.restImpl;

import com.speechmaster.SpeechMaster.rest.UserMessageRest;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userMessageRestImpl implements UserMessageRest {
    @Override
    public String getUserMessage(String message) {
        System.out.println("Message: " + message);
        return "Success.";
    }

    @Override
    public String getChatGPTMessage() {
        return "Hello World";
    }


}
