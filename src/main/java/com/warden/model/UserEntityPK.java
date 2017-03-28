package com.warden.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Warden on 2017/3/28.
 */
public class UserEntityPK implements Serializable {
    private int userId;
    private String ssoId;

    @Column(name = "user_id", nullable = false)
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "sso_id", nullable = false, length = 255)
    @Id
    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntityPK that = (UserEntityPK) o;

        if (userId != that.userId) return false;
        if (ssoId != null ? !ssoId.equals(that.ssoId) : that.ssoId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (ssoId != null ? ssoId.hashCode() : 0);
        return result;
    }
}
