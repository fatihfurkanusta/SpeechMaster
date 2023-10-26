package com.speechmaster.SpeechMaster.rest;

import com.speechmaster.SpeechMaster.model.SearchRequest;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
public interface ChatGPTRest {

    @PostMapping(path = "/searchChatGPT")
    String searchChatGPT(@RequestBody SearchRequest searchRequest);


}
