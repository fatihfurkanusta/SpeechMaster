package com.speechmaster.SpeechMaster.restImpl;

import com.speechmaster.SpeechMaster.constant.Constant;
import com.speechmaster.SpeechMaster.model.SearchRequest;
import com.speechmaster.SpeechMaster.rest.ChatGPTRest;
import com.speechmaster.SpeechMaster.service.ChatGPTService;
import com.theokanning.openai.audio.CreateTranscriptionRequest;
import com.theokanning.openai.service.OpenAiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@Slf4j
public class ChatGPTRestImpl implements ChatGPTRest {

    @Autowired
    ChatGPTService chatGPTService;

    @Override
    public String searchChatGPT(SearchRequest searchRequest) {
        log.info("sarchChatGPT Started query: " + searchRequest.getQuery());
        return chatGPTService.processChatGPTSearch(searchRequest.getQuery());
    }

}
