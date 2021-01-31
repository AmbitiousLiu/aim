package com.jleo.jcontrol.bean.VO;

import com.jleo.jcontrol.bean.DO.MenuDO;

import java.util.List;

/**
 * @author jleo
 * @date 2021/1/31
 */
public class MenuVO {
    private String id;
    private String name;
    private String address;
    private List<MenuVO> child;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<MenuVO> getChild() {
        return child;
    }

    public void setChild(List<MenuVO> child) {
        this.child = child;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        MenuVO menuVO = (MenuVO)obj;
        return this.id.equals(menuVO.id);
    }
}
