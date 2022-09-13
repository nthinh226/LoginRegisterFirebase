package com.nhom1.loginregisterfirebase.models;

public class Response {
    private int sum;

    public Response(int sum) {
        this.sum = sum;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Response{" +
                "sum=" + sum +
                '}';
    }
}
