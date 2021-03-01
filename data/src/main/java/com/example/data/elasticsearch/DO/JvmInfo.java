package com.example.data.elasticsearch.DO;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author jleo
 * @date 2021/2/24
 */
@Document(indexName = "jvm_info", indexStoreType = "jvm")
public class JvmInfo {

    @Id
    String id;

    String serverName;

    String systemLoadInfo;

    String heapMemoryUsage;

    String nonHeapMemoryUsage;

    String gcInfo;

    public JvmInfo() {
    }

    public JvmInfo(String id, String serverName, String systemLoadInfo, String heapMemoryUsage, String nonHeapMemoryUsage, String gcInfo) {
        this.id = id;
        this.serverName = serverName;
        this.systemLoadInfo = systemLoadInfo;
        this.heapMemoryUsage = heapMemoryUsage;
        this.nonHeapMemoryUsage = nonHeapMemoryUsage;
        this.gcInfo = gcInfo;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSystemLoadInfo() {
        return systemLoadInfo;
    }

    public void setSystemLoadInfo(String systemLoadInfo) {
        this.systemLoadInfo = systemLoadInfo;
    }

    public String getHeapMemoryUsage() {
        return heapMemoryUsage;
    }

    public void setHeapMemoryUsage(String heapMemoryUsage) {
        this.heapMemoryUsage = heapMemoryUsage;
    }

    public String getNonHeapMemoryUsage() {
        return nonHeapMemoryUsage;
    }

    public void setNonHeapMemoryUsage(String nonHeapMemoryUsage) {
        this.nonHeapMemoryUsage = nonHeapMemoryUsage;
    }

    public String getGcInfo() {
        return gcInfo;
    }

    public void setGcInfo(String gcInfo) {
        this.gcInfo = gcInfo;
    }
}
