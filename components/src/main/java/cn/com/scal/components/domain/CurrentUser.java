package cn.com.scal.components.domain;

import cn.com.scal.components.enums.RoleEnum;

/**
 * Created by vslimit on 15/8/27.
 */
public class CurrentUser {

    private int id;
    private String empNo;
    private String userName;
    private String password;
    private String deptName;
    private RoleEnum role =  RoleEnum.USER ;
    private String tele;
    private String email;
    private String orgNo;

    public CurrentUser() {
    }

    public CurrentUser(String empNo) {
        this.empNo = empNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpNo() {
        return empNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }
}
