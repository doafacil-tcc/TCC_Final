package com.example.tcc.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

public class User implements Parcelable {

    private String uuid;
    private String username;
    private String profileUrl;
    private String CPF;
    private String CEP;
    private String endereco;
    private String email;
    private String telefone;
    private String CNPJ;
    private String site;

    public User(){ }

    public User(String uuid, String username, String profileUrl, String CPF, String CEP,
                String email, String telefone, String CNPJ, String endereco, String site) {
        this.uuid = uuid;
        this.username = username;
        this.profileUrl = profileUrl;
        this.CPF = CPF;
        this.CEP = CEP;
        this.email = email;
        this.telefone = telefone;
        this.CNPJ = CNPJ;
        this.endereco = endereco;
        this.site = site;

    }

    public User(Parcel in) {
        uuid = in.toString();
        username = in.toString();
        profileUrl = in.toString();
        CPF = in.toString();
        CEP = in.toString();
        email = in.toString();
        telefone = in.toString();
        CNPJ = in.toString();
        site = in.toString();
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


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uuid);
        dest.writeString(username);
        dest.writeString(profileUrl);
        dest.writeString(email);
        dest.writeString(CEP);
        dest.writeString(CPF);
        dest.writeString(telefone);
        dest.writeString(endereco);
        dest.writeString(CNPJ);
        dest.writeString(site);

    }
}
