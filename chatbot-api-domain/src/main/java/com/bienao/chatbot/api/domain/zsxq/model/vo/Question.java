package com.bienao.chatbot.api.domain.zsxq.model.vo;

public class Question {

    private Owner owner;

    private Questionee questionee;

    private String text;

    private boolean expired;

    private boolean anonymous;

    private OwnerDetail owner_detail;

    private String owner_location;

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Questionee getQuestionee() {
        return questionee;
    }

    public void setQuestionee(Questionee questionee) {
        this.questionee = questionee;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public OwnerDetail getOwner_detail() {
        return owner_detail;
    }

    public void setOwner_detail(OwnerDetail owner_detail) {
        this.owner_detail = owner_detail;
    }

    public String getOwner_location() {
        return owner_location;
    }

    public void setOwner_location(String owner_location) {
        this.owner_location = owner_location;
    }
}
