package com.bienao.chatbot.api.domain.zsxq.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bienao.chatbot.api.domain.zsxq.IZsxqApi;
import com.bienao.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.bienao.chatbot.api.domain.zsxq.model.req.AnswerReq;
import com.bienao.chatbot.api.domain.zsxq.model.req.ReqData;
import com.bienao.chatbot.api.domain.zsxq.model.res.AnswerRes;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ZsxqApi implements IZsxqApi {

    private Logger logger = (Logger) LoggerFactory.getLogger(ZsxqApi.class);

    /**
     * 拉取提问数据
     * @param groupId
     * @param cookie
     * @return
     * @throws IOException
     */
    @Override
    public UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/48411118851818/topics?scope=unanswered_questions&count=20");
        HttpGet get = new HttpGet("知识星球的 等我回答 的请求地址");

        get.addHeader("cookie", "知识星球个人cookie信息");
        get.addHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("拉取提问数据。groupId：{} jsonStr：{}", groupId, jsonStr);
            return JSON.parseObject(jsonStr, UnAnsweredQuestionsAggregates.class);
        } else {
            throw new RuntimeException("queryUnAnsweredQuestionsTopicId Err Code is " + response.getStatusLine().getStatusCode());
        }
    }

    /**
     * 回答问题结果
     * @param groupId
     * @param cookie
     * @param topicId
     * @param text
     * @param silenced
     * @return
     * @throws IOException
     */
    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/412884248251548/answer");
        HttpPost post = new HttpPost("知识星球的  回答该问题 的请求地址");

        post.addHeader("cookie", "知识星球个人cookie信息");
        post.addHeader("Content-Type", "application/json;charset=utf8");

        /* 测试
        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"回答的文本\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";
         */

        AnswerReq answerReq = new AnswerReq(new ReqData(text, silenced));
        String paramJson = JSONObject.toJSONString(answerReq);

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("回答问题结果。groupId：{} topicId：{} jsonStr：{}", groupId, topicId, jsonStr);
            AnswerRes answerRes = JSON.parseObject(jsonStr, AnswerRes.class);
            return answerRes.isSucceeded();
        } else {
            throw new RuntimeException("answer Err Code is " + response.getStatusLine().getStatusCode());
        }
    }
}
