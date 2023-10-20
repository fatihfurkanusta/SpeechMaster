package com.speechmaster.SpeechMaster.rest;

import com.speechmaster.SpeechMaster.model.SearchRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1")
public interface ChatGPTRest {

    @PostMapping(path = "searchChatGPT")
    String searchChatGPT(@RequestBody SearchRequest searchRequest);

}
