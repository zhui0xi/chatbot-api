package com.bienao.chatbot.api.test;

import com.alibaba.fastjson.JSON;
import com.bienao.chatbot.api.domain.zsxq.IZsxqApi;
import com.bienao.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.bienao.chatbot.api.domain.zsxq.model.vo.Topics;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class SpringBootRunTest {

    private Logger logger = (Logger) LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Autowired
    private IZsxqApi zsxqApi;

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




}
