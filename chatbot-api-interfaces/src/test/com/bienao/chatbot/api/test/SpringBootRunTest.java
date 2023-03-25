package com.bienao.chatbot.api.test;

import com.alibaba.fastjson.JSON;
import com.bienao.chatbot.api.domain.ai.IOpenAI;
import com.bienao.chatbot.api.domain.zsxq.IZsxqApi;
import com.bienao.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.bienao.chatbot.api.domain.zsxq.model.vo.Topics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {

    private Logger logger = (Logger) LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;
    @Value("${chatbot-api.openAiKey}")
    private String openAiKey;

    @Autowired
    private IZsxqApi zsxqApi;
    @Autowired
    private IOpenAI openAI;

    /**
     * 爬取知识星球的问题，并回答它
     *
     * @throws IOException
     */
    @Test
    public void test_zsxqApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));

        List<Topics> topics = unAnsweredQuestionsAggregates.getRes_data().getTopics();
        for (Topics topic : topics) {
            String topicId = topic.getTopic_id();
            String text = topic.getQuestion().getText();
            logger.info("topicId：{} text：{}", topicId, text);

            // 回答问题
            zsxqApi.answer(groupId, cookie, topicId, text, false);
        }
    }

    /**
     * 调用OpenAI API来回答问题
     *
     * @throws IOException
     */
    @Test
    public void test_openAi() throws IOException {
        String response = openAI.doChatGPT(openAiKey, "怎么使用java来调用openai API");
        logger.info("测试结果：{}", response);
    }

}
