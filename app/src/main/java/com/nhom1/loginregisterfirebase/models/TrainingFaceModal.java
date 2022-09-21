package com.nhom1.loginregisterfirebase.models;

import java.util.ArrayList;
import java.util.List;

public class TrainingFaceModal {
    private String email;
    private List<String> file;

    public TrainingFaceModal(String email, List<String> file) {
        this.email = email;
        this.file = file;
    }

    public List<String> getFile() {
        return file;
    }

    public void setFile(List<String> file) {
        this.file = file;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
