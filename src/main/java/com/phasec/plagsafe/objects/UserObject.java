package com.phasec.plagsafe.objects;

import javax.persistence.*;

/**
 * the class with all information needed for a user
 */

@Entity(name="registered_user")
public class UserObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="user_name")
    private String userName;
    @Column(name="secret")
    private String secret;
    @Column(name="status_id")
    private String statusId;

    //getters and setters for user name

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    //getters and setters for secret
    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
    //getters and setters for sStatusID
    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    //getters and setters for Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
