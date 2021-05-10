package com.example.web.VO;

/**
 * @author jleo
 * @date 2021/5/10
 */
public class RoleMenuVO {
    String name;
    String have;
    String haveNot;
    String whitelist;
    String blacklist;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHave() {
        return have;
    }

    public void setHave(String have) {
        this.have = have;
    }

    public String getHaveNot() {
        return haveNot;
    }

    public void setHaveNot(String haveNot) {
        this.haveNot = haveNot;
    }

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
}
