package cn.com.scal.components.dto.front.domain;

public class ExaminePeople {
    private Integer id;
    private String examinePeopleId;
    private String examinePeopleName;
    private String examinePeoplePost;
    private String examinePeopleDept;
    private Integer orders;

    public ExaminePeople() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExaminePeopleId() {
        return examinePeopleId;
    }

    public void setExaminePeopleId(String examinePeopleId) {
        this.examinePeopleId = examinePeopleId;
    }

    public String getExaminePeopleName() {
        return examinePeopleName;
    }

    public void setExaminePeopleName(String examinePeopleName) {
        this.examinePeopleName = examinePeopleName;
    }

    public String getExaminePeoplePost() {
        return examinePeoplePost;
    }

    public void setExaminePeoplePost(String examinePeoplePost) {
        this.examinePeoplePost = examinePeoplePost;
    }

    public String getExaminePeopleDept() {
        return examinePeopleDept;
    }

    public void setExaminePeopleDept(String examinePeopleDept) {
        this.examinePeopleDept = examinePeopleDept;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }
}
