package com.xxxx.yebconsumer;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */

public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;


    private String name;


    private String phone;


    private String telephone;


    private String address;


    private Boolean enabled;


    private String username;


    private String password;

    private String userFace;

    private String remark;

    public boolean isAccountNonExpired () {
        return true;
    }

    public boolean isAccountNonLocked () {
        return true;
    }

    public boolean isCredentialsNonExpired () {
        return true;
    }

    public boolean isEnabled () {
        return enabled;
    }

    public static long getSerialVersionUID () {
        return serialVersionUID;
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getPhone () {
        return phone;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    public String getTelephone () {
        return telephone;
    }

    public void setTelephone (String telephone) {
        this.telephone = telephone;
    }

    public String getAddress () {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public Boolean getEnabled () {
        return enabled;
    }

    public void setEnabled (Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername () {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getUserFace () {
        return userFace;
    }

    public void setUserFace (String userFace) {
        this.userFace = userFace;
    }

    public String getRemark () {
        return remark;
    }

    public void setRemark (String remark) {
        this.remark = remark;
    }

    @Override
    public String toString () {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", enabled=" + enabled +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userFace='" + userFace + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
