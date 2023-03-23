package com.bienao.chatbot.api.domain.zsxq.model.aggregates;


import com.bienao.chatbot.api.domain.zsxq.model.res.ResData;

/**
 * 未回答问题的聚合信息
 */
public class UnAnsweredQuestionsAggregates {

    private boolean succeeded;

    private ResData res_data;

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public ResData getRes_data() {
        return res_data;
    }

    public void setRes_data(ResData res_data) {
        this.res_data = res_data;
    }
}
