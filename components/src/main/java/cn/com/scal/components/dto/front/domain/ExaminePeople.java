package cn.com.scal.components.dto.front.domain;

public class ExaminePeople {
    private Integer examinePeopleId;
    private String examinePeopleName;
    private String examinePeoplePost;

    public ExaminePeople() {
    }

    public Integer getExaminePeopleId() {
        return examinePeopleId;
    }

    public void setExaminePeopleId(Integer examinePeopleId) {
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
}
