package com.example.gomart.models;

public class AddressModel {

    String userAddres;
    boolean isSelected;

    public AddressModel() {
    }

    public AddressModel(String userAddres, boolean isSelected) {
        this.userAddres = userAddres;
        this.isSelected = isSelected;
    }

    public String getUserAddres() {
        return userAddres;
    }

    public void setUserAddres(String userAddres) {
        this.userAddres = userAddres;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
