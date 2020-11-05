package com.example.tcc.Entities;

import android.os.Parcel;
import android.os.Parcelable;

public class DoacaoRoupa implements Parcelable {

    String id_roupa;
    String id_user;
    String id_ong;
    String tipo_roupa;
    String qtd_roupa;
    String tamanho_roupa;
    String condicao_roupa;
    String descricao_roupa;
    String imgUrl1_roupa;
    String imgUrl2_roupa;
    String imgUrl3_roupa;
    String status;
    String unica_ou_campanha;
    String categoria;

    public DoacaoRoupa(String id_roupa, String id_user, String id_ong, String tipo_roupa, String qtd_roupa, String tamanho_roupa, String condicao_roupa, String descricao_roupa, String imgUrl1_roupa, String imgUrl2_roupa, String imgUrl3_roupa, String status, String unica_ou_campanha, String categoria){
        this.id_roupa = id_roupa;
        this.id_user = id_user;
        this.id_ong = id_ong;
        this.tipo_roupa = tipo_roupa;
        this.qtd_roupa = qtd_roupa;
        this.tamanho_roupa = tamanho_roupa;
        this.condicao_roupa = condicao_roupa;
        this.descricao_roupa = descricao_roupa;
        this.imgUrl1_roupa = imgUrl1_roupa;
        this.imgUrl2_roupa = imgUrl2_roupa;
        this.imgUrl3_roupa = imgUrl3_roupa;
        this.status = status;
        this.unica_ou_campanha = unica_ou_campanha;
        this.categoria = categoria;
    }

    public DoacaoRoupa(Parcel in) {
        id_roupa = in.toString();
        id_user = in.toString();
        id_ong = in.toString();
        tipo_roupa = in.toString();
        qtd_roupa = in.toString();
        tamanho_roupa = in.toString();
        condicao_roupa = in.toString();
        descricao_roupa = in.toString();
        imgUrl1_roupa = in.toString();
        imgUrl2_roupa = in.toString();
        imgUrl3_roupa = in.toString();
        status = in.toString();
        unica_ou_campanha = in.toString();
        categoria = in.toString();

    }

    public static final Creator<DoacaoRoupa> CREATOR = new Creator<DoacaoRoupa>() {
        @Override
        public DoacaoRoupa createFromParcel(Parcel in) {
            return new DoacaoRoupa(in);
        }

        @Override
        public DoacaoRoupa[] newArray(int size) {
            return new DoacaoRoupa[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id_roupa);
        parcel.writeString(tipo_roupa);
        parcel.writeString(qtd_roupa);
        parcel.writeString(tamanho_roupa);
        parcel.writeString(condicao_roupa);
        parcel.writeString(descricao_roupa);
        parcel.writeString(imgUrl1_roupa);
        parcel.writeString(imgUrl2_roupa);
        parcel.writeString(imgUrl3_roupa);
        parcel.writeString(status);
        parcel.writeString(unica_ou_campanha);
        parcel.writeString(categoria);
    }

    public String getId_roupa() {
        return id_roupa;
    }

    public void setId_roupa(String id_roupa) {
        this.id_roupa = id_roupa;
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

    public String getTipo_roupa() {
        return tipo_roupa;
    }

    public void setTipo_roupa(String tipo_roupa) {
        this.tipo_roupa = tipo_roupa;
    }

    public String getQtd_roupa() {
        return qtd_roupa;
    }

    public void setQtd_roupa(String qtd_roupa) {
        this.qtd_roupa = qtd_roupa;
    }

    public String getTamanho_roupa() {
        return tamanho_roupa;
    }

    public void setTamanho_roupa(String tamanho_roupa) {
        this.tamanho_roupa = tamanho_roupa;
    }

    public String getCondicao_roupa() {
        return condicao_roupa;
    }

    public void setCondicao_roupa(String condicao_roupa) {
        this.condicao_roupa = condicao_roupa;
    }

    public String getDescricao_roupa() {
        return descricao_roupa;
    }

    public void setDescricao_roupa(String descricao_roupa) {
        this.descricao_roupa = descricao_roupa;
    }

    public String getImgUrl1_roupa() {
        return imgUrl1_roupa;
    }

    public void setImgUrl1_roupa(String imgUrl1_roupa) {
        this.imgUrl1_roupa = imgUrl1_roupa;
    }

    public String getImgUrl2_roupa() {
        return imgUrl2_roupa;
    }

    public void setImgUrl2_roupa(String imgUrl2_roupa) {
        this.imgUrl2_roupa = imgUrl2_roupa;
    }

    public String getImgUrl3_roupa() {
        return imgUrl3_roupa;
    }

    public void setImgUrl3_roupa(String imgUrl3_roupa) {
        this.imgUrl3_roupa = imgUrl3_roupa;
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
