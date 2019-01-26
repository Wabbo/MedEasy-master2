package com.example.nihal.medeasy;

public class User {
  private   String UserName, Address, Occupation, FamilyHistoryLink, Weight, Height;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public String getFamilyHistoryLink() {
        return FamilyHistoryLink;
    }

    public void setFamilyHistoryLink(String familyHistoryLink) {
        FamilyHistoryLink = familyHistoryLink;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public User(String userName, String address, String occupation, String familyHistoryLink, String weight, String height) {
        UserName = userName;
        Address = address;
        Occupation = occupation;
        FamilyHistoryLink = familyHistoryLink;
        Weight = weight;
        Height = height;
    }
    public User(){

    }
}

