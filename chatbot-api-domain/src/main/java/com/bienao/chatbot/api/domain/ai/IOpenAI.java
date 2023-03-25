package com.bienao.chatbot.api.domain.ai;

import java.io.IOException;

/**
 * ChatGPT open ai 接口
 */
public interface IOpenAI {

    String doChatGPT(String openAiKey, String question) throws IOException;

}
