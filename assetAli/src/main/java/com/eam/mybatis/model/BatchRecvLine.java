package com.eam.mybatis.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class BatchRecvLine {

    private String evtCode;
    private String corp;
    private ArrayList<BatchAsset> rcvLines;

    public BatchRecvLine(String evtCode, String corp, ArrayList<BatchAsset> rcvLines) {
        this.evtCode = evtCode;
        this.corp = corp;
        this.rcvLines = rcvLines;
    }
}
