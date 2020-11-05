package com.example.tcc.Entities;

import android.os.Parcel;
import android.os.Parcelable;

public class DoacaoMoveis implements Parcelable {

    String id_Moveis;
    String id_user;
    String id_ong;
    String tipo_Moveis;
    String qtd_Moveis;
    String condicao_Moveis;
    String descricao_Moveis;
    String imgUrl1_Moveis;
    String imgUrl2_Moveis;
    String imgUrl3_Moveis;
    String status;
    String unica_ou_campanha;
    String categoria;

    public DoacaoMoveis(String id_Moveis, String id_user, String id_ong, String tipo_Moveis, String qtd_Moveis, String condicao_Moveis, String descricao_Moveis, String imgUrl1_Moveis, String imgUrl2_Moveis, String imgUrl3_Moveis, String status, String unica_ou_campanha, String categoria){
        this.id_Moveis = id_Moveis;
        this.id_user = id_user;
        this.id_ong = id_ong;
        this.tipo_Moveis = tipo_Moveis;
        this.qtd_Moveis = qtd_Moveis;
        this.condicao_Moveis = condicao_Moveis;
        this.descricao_Moveis = descricao_Moveis;
        this.imgUrl1_Moveis = imgUrl1_Moveis;
        this.imgUrl2_Moveis = imgUrl2_Moveis;
        this.imgUrl3_Moveis = imgUrl3_Moveis;
        this.status = status;
        this.unica_ou_campanha = unica_ou_campanha;
        this.categoria = categoria;
    }

    public DoacaoMoveis(Parcel in) {
        id_Moveis = in.toString();
        id_user = in.toString();
        id_ong = in.toString();
        tipo_Moveis = in.toString();
        qtd_Moveis = in.toString();
        condicao_Moveis = in.toString();
        descricao_Moveis = in.toString();
        imgUrl1_Moveis = in.toString();
        imgUrl2_Moveis = in.toString();
        imgUrl3_Moveis = in.toString();
        status = in.toString();
        unica_ou_campanha = in.toString();
        categoria = in.toString();

    }

    public static final Creator<DoacaoMoveis> CREATOR = new Creator<DoacaoMoveis>() {
        @Override
        public DoacaoMoveis createFromParcel(Parcel in) {
            return new DoacaoMoveis(in);
        }

        @Override
        public DoacaoMoveis[] newArray(int size) {
            return new DoacaoMoveis[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id_Moveis);
        parcel.writeString(tipo_Moveis);
        parcel.writeString(qtd_Moveis);
        parcel.writeString(condicao_Moveis);
        parcel.writeString(descricao_Moveis);
        parcel.writeString(imgUrl1_Moveis);
        parcel.writeString(imgUrl2_Moveis);
        parcel.writeString(imgUrl3_Moveis);
        parcel.writeString(status);
        parcel.writeString(unica_ou_campanha);
        parcel.writeString(categoria);
    }

    public String getId_Moveis() {
        return id_Moveis;
    }

    public void setId_Moveis(String id_Moveis) {
        this.id_Moveis = id_Moveis;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_ong() {
        return id_ong;
    }

    public void setId_ong(String id_ong) {
        this.id_ong = id_ong;
    }

    public String getTipo_Moveis() {
        return tipo_Moveis;
    }

    public void setTipo_Moveis(String tipo_Moveis) {
        this.tipo_Moveis = tipo_Moveis;
    }

    public String getQtd_Moveis() {
        return qtd_Moveis;
    }

    public void setQtd_Moveis(String qtd_Moveis) {
        this.qtd_Moveis = qtd_Moveis;
    }

    public String getCondicao_Moveis() {
        return condicao_Moveis;
    }

    public void setCondicao_Moveis(String condicao_Moveis) {
        this.condicao_Moveis = condicao_Moveis;
    }

    public String getDescricao_Moveis() {
        return descricao_Moveis;
    }

    public void setDescricao_Moveis(String descricao_Moveis) {
        this.descricao_Moveis = descricao_Moveis;
    }

    public String getImgUrl1_Moveis() {
        return imgUrl1_Moveis;
    }

    public void setImgUrl1_Moveis(String imgUrl1_Moveis) {
        this.imgUrl1_Moveis = imgUrl1_Moveis;
    }

    public String getImgUrl2_Moveis() {
        return imgUrl2_Moveis;
    }

    public void setImgUrl2_Moveis(String imgUrl2_Moveis) {
        this.imgUrl2_Moveis = imgUrl2_Moveis;
    }

    public String getImgUrl3_Moveis() {
        return imgUrl3_Moveis;
    }

    public void setImgUrl3_Moveis(String imgUrl3_Moveis) {
        this.imgUrl3_Moveis = imgUrl3_Moveis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnica_ou_campanha() {
        return unica_ou_campanha;
    }

    public void setUnica_ou_campanha(String unica_ou_campanha) {
        this.unica_ou_campanha = unica_ou_campanha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
