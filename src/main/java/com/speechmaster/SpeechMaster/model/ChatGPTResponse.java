package com.speechmaster.SpeechMaster.model;

import lombok.Data;

import java.util.List;

@Data
public class ChatGPTResponse {
    private List<ChatGPTChoice> choices;
}
