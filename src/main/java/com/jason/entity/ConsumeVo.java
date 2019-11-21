package com.jason.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Desc: 消费记录
 *
 * @author jason
 * @date 2019-10-14 14:34
 */
public class ConsumeVo {
    @ExcelIgnore
    private Long consumeId;
    @ExcelProperty(value = "批次号", index = 0)
    @ColumnWidth(35)
    private String batchNo;
    @ExcelIgnore
    private Long merchantId;
    @ExcelProperty(value = "商户名称", index = 1)
    @ColumnWidth(20)
    private String merchantName;
    @ExcelIgnore
    private Long accountId;
    @ExcelIgnore
    private Long productId;
    @ExcelProperty(value = "产品名称", index = 2)
    @ColumnWidth(20)
    private String productName;
    @ExcelIgnore
    private Long accessId;
    @ExcelIgnore
    private Byte accessType;
    @ExcelIgnore
    private String statusCode;
    @ExcelIgnore
    private String resultCode;
    @ExcelIgnore
    private Date requestTime;
    @ExcelProperty(value = "费用(单位：元)", index = 3)
    @ColumnWidth(25)
    private BigDecimal amount;
    @ExcelIgnore
    private Byte status;
    @ExcelIgnore
    private Date createTime;
    @ExcelIgnore
    private Date updateTime;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getConsumeId() {
        return consumeId;
    }

    public void setConsumeId(Long consumeId) {
        this.consumeId = consumeId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getAccessId() {
        return accessId;
    }

    public void setAccessId(Long accessId) {
        this.accessId = accessId;
    }

    public Byte getAccessType() {
        return accessType;
    }

    public void setAccessType(Byte accessType) {
        this.accessType = accessType;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
