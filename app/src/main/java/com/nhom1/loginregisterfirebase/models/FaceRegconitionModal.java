package com.nhom1.loginregisterfirebase.models;

import java.util.List;

public class FaceRegconitionModal {
    private String email;
    private String file;

    public FaceRegconitionModal(String email, String file) {
        this.email = email;
        this.file = file;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
