package com.jleo.jcontrol.bean.DO;

/**
 * @author jleo
 * @date 2021/1/31
 */
public class RoleDO {
    private String name;

    private String whitelist;

    private String blacklist;

    public String getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(String whitelist) {
        this.whitelist = whitelist;
    }

    public String getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(String blacklist) {
        this.blacklist = blacklist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
