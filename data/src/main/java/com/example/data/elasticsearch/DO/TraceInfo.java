package com.example.data.elasticsearch.DO;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author jleo
 * @date 2021/2/28
 */
@Document(indexName = "trace_info", indexStoreType = "trace")
public class TraceInfo {
    @Id
    String traceId;

    String serverName;

    String time;

    String consume;

    Boolean normal;

    String traceMessage;

    public TraceInfo() {
    }

    public TraceInfo(String traceId, String serverName, String time, String consume, Boolean normal, String traceMessage) {
        this.traceId = traceId;
        this.serverName = serverName;
        this.time = time;
        this.consume = consume;
        this.normal = normal;
        this.traceMessage = traceMessage;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public Boolean getNormal() {
        return normal;
    }

    public void setNormal(Boolean normal) {
        this.normal = normal;
    }

    public String getTraceMessage() {
        return traceMessage;
    }

    public void setTraceMessage(String traceMessage) {
        this.traceMessage = traceMessage;
    }
}
