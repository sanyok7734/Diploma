package com.quoteoftheday.raccoonapps.diploma.mode.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;

public class User implements Parcelable {


    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String type;
    private int color;
    private String yourself;
    private ArrayList<String> privileges;

    public User(String firstName, String lastName, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.color = ColorGenerator.MATERIAL.getRandomColor();
    }

    public User(String firstName, String lastName, String login, String password, String type, int color, String yourself, ArrayList<String> privileges) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.type = type;
        this.color = color;
        this.yourself = yourself;
        this.privileges = privileges;
    }

    public void setYourself(String yourself) {
        this.yourself = yourself;
    }

    public String getYourself() {
        return yourself;
    }

    public void setPrivileges(ArrayList<String> privileges) {
        this.privileges = privileges;
    }

    protected User(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        login = in.readString();
        password = in.readString();
        type = in.readString();
        color = in.readInt();
        yourself = in.readString();
        privileges = in.readArrayList(null);
    }

    public void addPrivileges(String i) {
        if (privileges == null) {
            privileges = new ArrayList<>();
        }
        if (privileges.contains(i)) {
            privileges.remove(i);
        } else {
            privileges.add(i);
        }
    }

    public ArrayList<String> getPrivileges() {
        return privileges;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(login);
        dest.writeString(password);
        dest.writeString(type);
        dest.writeInt(color);
        dest.writeString(yourself);
        dest.writeStringList(privileges);
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

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", your='" + yourself + '\'' +
                '}';
    }
}