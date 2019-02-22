package com.example.hwweek2day4;

import android.os.Parcel ;
import android.os.Parcelable ;

public class User implements Parcelable {

    private String name ;
    private String address ;
    private String city ;
    private String state ;
    private String zipCode ;
    private String phoneNumber ;
    private String emailAddress ;
    private int id ;


    protected User(Parcel in) {
        name = in.readString();
        address = in.readString();
        city = in.readString();
        state = in.readString();
        zipCode = in.readString();
        phoneNumber = in.readString();
        emailAddress = in.readString();
        id = in.readInt() ;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public User(String name, String address, String city, String state, String zipCode, String phoneNumber, String emailAddress, int id) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.id = id ;
    }

    public User() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(zipCode);
        dest.writeString(phoneNumber);
        dest.writeString(emailAddress);
        dest.writeInt(id) ;
     }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getId() {
        return id ;
    }

    public void setId(int id) {
        this.id = id ;
    }



    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                ", phoneNumber=" + phoneNumber +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
