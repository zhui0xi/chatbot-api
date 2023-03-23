package com.bienao.chatbot.api.domain.zsxq.model.vo;

public class UserSpecific {

    private boolean liked;

    private boolean subscribed;

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }
}
