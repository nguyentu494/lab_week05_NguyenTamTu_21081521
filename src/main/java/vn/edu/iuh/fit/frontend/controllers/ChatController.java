/*
 * @ (#) ChatController.java   1.0     07/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.frontend.controllers;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 07/12/2024
 * @version: 1.0
 */

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.mistralai.MistralAiChatModel;


import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@RestController
public class ChatController {



//    @Autowired
//    private VectorStore vectorStore;
//
//    @GetMapping("/ai/vector")
//    public String vector(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
//        List <Document> documents = List.of(
//                new Document("Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!!", Map.of("meta1", "meta1")),
//                new Document("The World is Big and Salvation Lurks Around the Corner"),
//                new Document("You walk forward facing the past and you turn back toward the future.", Map.of("meta2", "meta2")));
//
//// Add the documents
//        vectorStore.add(documents);
//
//// Retrieve documents similar to a query
//        List<Document> results = this.vectorStore.similaritySearch(SearchRequest.query("Spring").withTopK(5));
//        System.out.println(results);
//        return null;
//    }

    private final MistralAiChatModel chatModel;


    @Autowired
    public ChatController(MistralAiChatModel chatModel) {
        this.chatModel = chatModel;
    }


    @GetMapping("/ai/generate")
    public Map<String,String> generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return Map.of("generation", this.chatModel.call(message));
    }

//    private final OpenAiChatModel chatModel;
//
//    @Autowired
//    public ChatController(OpenAiChatModel chatModel) {
//        this.chatModel = chatModel;
//    }
//
//    @GetMapping("/ai/generate")
//    public Map<String,String> generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
//        return Map.of("generation", this.chatModel.call(message));
//    }
}