package com.example.tcc.Entities;

import android.os.Parcel;
import android.os.Parcelable;

public class DoacaoBrinquedo implements Parcelable {

    String id_Brinquedo;
    String id_user;
    String id_ong;
    String tipo_Brinquedo;
    String qtd_Brinquedo;
    String condicao_Brinquedo;
    String descricao_Brinquedo;
    String imgUrl1_Brinquedo;
    String imgUrl2_Brinquedo;
    String imgUrl3_Brinquedo;
    String status;
    String unica_ou_campanha;
    String categoria;

    public DoacaoBrinquedo(String id_Brinquedo, String id_user, String id_ong, String tipo_Brinquedo, String qtd_Brinquedo, String condicao_Brinquedo, String descricao_Brinquedo, String imgUrl1_Brinquedo, String imgUrl2_Brinquedo, String imgUrl3_Brinquedo, String status, String unica_ou_campanha, String categoria){
        this.id_Brinquedo = id_Brinquedo;
        this.id_user = id_user;
        this.id_ong = id_ong;
        this.tipo_Brinquedo = tipo_Brinquedo;
        this.qtd_Brinquedo = qtd_Brinquedo;
        this.condicao_Brinquedo = condicao_Brinquedo;
        this.descricao_Brinquedo = descricao_Brinquedo;
        this.imgUrl1_Brinquedo = imgUrl1_Brinquedo;
        this.imgUrl2_Brinquedo = imgUrl2_Brinquedo;
        this.imgUrl3_Brinquedo = imgUrl3_Brinquedo;
        this.status = status;
        this.unica_ou_campanha = unica_ou_campanha;
        this.categoria = categoria;
    }

    public DoacaoBrinquedo(Parcel in) {
        id_Brinquedo = in.toString();
        id_user = in.toString();
        id_ong = in.toString();
        tipo_Brinquedo = in.toString();
        qtd_Brinquedo = in.toString();
        condicao_Brinquedo = in.toString();
        descricao_Brinquedo = in.toString();
        imgUrl1_Brinquedo = in.toString();
        imgUrl2_Brinquedo = in.toString();
        imgUrl3_Brinquedo = in.toString();
        status = in.toString();
        unica_ou_campanha = in.toString();
        categoria = in.toString();

    }

    public static final Creator<DoacaoBrinquedo> CREATOR = new Creator<DoacaoBrinquedo>() {
        @Override
        public DoacaoBrinquedo createFromParcel(Parcel in) {
            return new DoacaoBrinquedo(in);
        }

        @Override
        public DoacaoBrinquedo[] newArray(int size) {
            return new DoacaoBrinquedo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id_Brinquedo);
        parcel.writeString(tipo_Brinquedo);
        parcel.writeString(qtd_Brinquedo);
        parcel.writeString(condicao_Brinquedo);
        parcel.writeString(descricao_Brinquedo);
        parcel.writeString(imgUrl1_Brinquedo);
        parcel.writeString(imgUrl2_Brinquedo);
        parcel.writeString(imgUrl3_Brinquedo);
        parcel.writeString(status);
        parcel.writeString(unica_ou_campanha);
        parcel.writeString(categoria);
    }

    public String getId_Brinquedo() {
        return id_Brinquedo;
    }

    public void setId_Brinquedo(String id_Brinquedo) {
        this.id_Brinquedo = id_Brinquedo;
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

    public String getTipo_Brinquedo() {
        return tipo_Brinquedo;
    }

    public void setTipo_Brinquedo(String tipo_Brinquedo) {
        this.tipo_Brinquedo = tipo_Brinquedo;
    }

    public String getQtd_Brinquedo() {
        return qtd_Brinquedo;
    }

    public void setQtd_Brinquedo(String qtd_Brinquedo) {
        this.qtd_Brinquedo = qtd_Brinquedo;
    }

    public String getCondicao_Brinquedo() {
        return condicao_Brinquedo;
    }

    public void setCondicao_Brinquedo(String condicao_Brinquedo) {
        this.condicao_Brinquedo = condicao_Brinquedo;
    }

    public String getDescricao_Brinquedo() {
        return descricao_Brinquedo;
    }

    public void setDescricao_Brinquedo(String descricao_Brinquedo) {
        this.descricao_Brinquedo = descricao_Brinquedo;
    }

    public String getImgUrl1_Brinquedo() {
        return imgUrl1_Brinquedo;
    }

    public void setImgUrl1_Brinquedo(String imgUrl1_Brinquedo) {
        this.imgUrl1_Brinquedo = imgUrl1_Brinquedo;
    }

    public String getImgUrl2_Brinquedo() {
        return imgUrl2_Brinquedo;
    }

    public void setImgUrl2_Brinquedo(String imgUrl2_Brinquedo) {
        this.imgUrl2_Brinquedo = imgUrl2_Brinquedo;
    }

    public String getImgUrl3_Brinquedo() {
        return imgUrl3_Brinquedo;
    }

    public void setImgUrl3_Brinquedo(String imgUrl3_Brinquedo) {
        this.imgUrl3_Brinquedo = imgUrl3_Brinquedo;
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
