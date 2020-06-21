package webservice.BHXH.model.dto;

import java.io.Serializable;

public class InsuranceDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String regDate;
    private UserDto user;
    private MethodDto method;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public MethodDto getMethod() {
        return method;
    }

    public void setMethod(MethodDto method) {
        this.method = method;
    }
}
