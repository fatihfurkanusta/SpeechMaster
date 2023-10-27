package com.speechmaster.SpeechMaster.serviceImpl;

import com.google.gson.Gson;
import com.speechmaster.SpeechMaster.constant.Constant;
import com.speechmaster.SpeechMaster.model.ChatGPTRequest;
import com.speechmaster.SpeechMaster.model.ChatGPTResponse;
import com.speechmaster.SpeechMaster.model.SearchRequest;
import com.speechmaster.SpeechMaster.service.ChatGPTService;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChatGPTServiceImpl implements ChatGPTService {

    public static String response1;


    @Override
    public String processChatGPTSearch(String query) {

        ChatGPTRequest chatGPTRequest = new ChatGPTRequest();
        String request = "'" + query + "' Score this paragraph out of 10 according to Grammar, Vocabulary, Topic Knowledge and Content, Meaning, Context and Expression, Complexity of the Sentence. Later, correct the paragraph.";
        chatGPTRequest.setPrompt(request);

        HttpPost post = new HttpPost(Constant.OPEN_AI_URL);
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer " + Constant.OPEN_AI_KEY);

        Gson gson = new Gson();


        String body = gson.toJson(chatGPTRequest);

        log.info("body: " + body);

        try {
            final StringEntity entity = new StringEntity(body);

            post.setEntity(entity);

            try (CloseableHttpClient httpClient = HttpClients.custom().build();
                 CloseableHttpResponse response = httpClient.execute(post)) {

                String responseBody = EntityUtils.toString(response.getEntity());

                log.info("responseBody: " + responseBody);

                ChatGPTResponse chatGPTResponse = gson.fromJson(responseBody, ChatGPTResponse.class);

                response1 = chatGPTResponse.getChoices().get(0).getText();

                return chatGPTResponse.getChoices().get(0).getText();
            } catch (Exception ex) {
                return "failed";
            }
        }catch(Exception ex){
            ex.toString();
        }
        return "failed 2";
    }
}
