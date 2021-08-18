package com.email.model;

public class EmailResponse {
    private  String resMsg;

    public EmailResponse(String resMsg) {
        this.resMsg = resMsg;
    }

    public EmailResponse() {
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    @Override
    public String toString() {
        return "EmailResponse{" +
                "resMsg='" + resMsg + '\'' +
                '}';
    }
}
