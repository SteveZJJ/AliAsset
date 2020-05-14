package com.eam.mybatis.model;

import lombok.Data;

import java.util.List;

/**
 * @Description 三方比价报价行
 * @Author Steve Zhang
 * @Date 2019年6月10日15:48:48
 */

@Data
public class Quotation {

    private String code; //报价行号
    private String supplier; //供应商
    private String supplierDesc; //供应商名称
    private String price; //报价
    private String selected; //是否选中
    private String payer; //付款方
    private String comments; //备注
    private String evtCode; //关联单据
    private String currency;//币种
    private List<Attachment> attachments; //上传附件

}
