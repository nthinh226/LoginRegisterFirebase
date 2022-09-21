package com.nhom1.loginregisterfirebase.models;

import java.util.ArrayList;
import java.util.List;

public class TrainingFaceModal {
    private List<String> file;

    public TrainingFaceModal(List<String> file) {
        this.file = file;
    }

    public List<String> getFile() {
        return file;
    }

    public void setFile(List<String> file) {
        this.file = file;
    }
}
