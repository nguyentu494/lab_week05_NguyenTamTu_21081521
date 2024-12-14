/*
 * @ (#) EmbeddingModel.java   1.0     08/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.models;

import org.springframework.ai.mistralai.MistralAiEmbeddingModel;
import org.springframework.ai.mistralai.api.MistralAiApi;
import org.springframework.context.annotation.Bean;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 08/12/2024
 * @version: 1.0
 */
public class EmbeddingModel {
    @Bean
    public org.springframework.ai.embedding.EmbeddingModel embeddingModel() {
        // Can be any other EmbeddingModel implementation.
        return new MistralAiEmbeddingModel(new MistralAiApi(System.getenv("api_key")));
    }
}
