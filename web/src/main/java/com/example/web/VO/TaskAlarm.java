package com.example.web.VO;

/**
 * @author jleo
 * @date 2021/3/4
 */
public class TaskAlarm {
    String id;
    String name;
    Integer type;
    String typeName;
    String person;
    String alarmName;
    String cycle;
    String config;
    String url;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TaskAlarm() {
    }

    public TaskAlarm(String id, String name, Integer type, String typeName, String person, String alarmName, String cycle, String config, String url) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.typeName = typeName;
        this.person = person;
        this.alarmName = alarmName;
        this.cycle = cycle;
        this.config = config;
        this.url = url;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }
}
