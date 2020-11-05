package com.example.tcc.Entities;

import android.os.Parcel;
import android.os.Parcelable;

public class DoacaoLivro implements Parcelable {

    String id_Livro;
    String id_user;
    String id_ong;
    String tipo_Livro;
    String qtd_Livro;
    String condicao_Livro;
    String descricao_Livro;
    String imgUrl1_Livro;
    String imgUrl2_Livro;
    String imgUrl3_Livro;
    String status;
    String unica_ou_campanha;
    String categoria;

    public DoacaoLivro(String id_Livro, String id_user, String id_ong, String tipo_Livro, String qtd_Livro, String condicao_Livro, String descricao_Livro, String imgUrl1_Livro, String imgUrl2_Livro, String imgUrl3_Livro, String status, String unica_ou_campanha, String categoria){
        this.id_Livro = id_Livro;
        this.id_user = id_user;
        this.id_ong = id_ong;
        this.tipo_Livro = tipo_Livro;
        this.qtd_Livro = qtd_Livro;
        this.condicao_Livro = condicao_Livro;
        this.descricao_Livro = descricao_Livro;
        this.imgUrl1_Livro = imgUrl1_Livro;
        this.imgUrl2_Livro = imgUrl2_Livro;
        this.imgUrl3_Livro = imgUrl3_Livro;
        this.status = status;
        this.unica_ou_campanha = unica_ou_campanha;
        this.categoria = categoria;
    }

    public DoacaoLivro(Parcel in) {
        id_Livro = in.toString();
        id_user = in.toString();
        id_ong = in.toString();
        tipo_Livro = in.toString();
        qtd_Livro = in.toString();
        condicao_Livro = in.toString();
        descricao_Livro = in.toString();
        imgUrl1_Livro = in.toString();
        imgUrl2_Livro = in.toString();
        imgUrl3_Livro = in.toString();
        status = in.toString();
        unica_ou_campanha = in.toString();
        categoria = in.toString();

    }

    public static final Creator<DoacaoLivro> CREATOR = new Creator<DoacaoLivro>() {
        @Override
        public DoacaoLivro createFromParcel(Parcel in) {
            return new DoacaoLivro(in);
        }

        @Override
        public DoacaoLivro[] newArray(int size) {
            return new DoacaoLivro[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id_Livro);
        parcel.writeString(tipo_Livro);
        parcel.writeString(qtd_Livro);
        parcel.writeString(condicao_Livro);
        parcel.writeString(descricao_Livro);
        parcel.writeString(imgUrl1_Livro);
        parcel.writeString(imgUrl2_Livro);
        parcel.writeString(imgUrl3_Livro);
        parcel.writeString(status);
        parcel.writeString(unica_ou_campanha);
        parcel.writeString(categoria);
    }

    public String getId_Livro() {
        return id_Livro;
    }

    public void setId_Livro(String id_Livro) {
        this.id_Livro = id_Livro;
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

    public String getTipo_Livro() {
        return tipo_Livro;
    }

    public void setTipo_Livro(String tipo_Livro) {
        this.tipo_Livro = tipo_Livro;
    }

    public String getQtd_Livro() {
        return qtd_Livro;
    }

    public void setQtd_Livro(String qtd_Livro) {
        this.qtd_Livro = qtd_Livro;
    }

    public String getCondicao_Livro() {
        return condicao_Livro;
    }

    public void setCondicao_Livro(String condicao_Livro) {
        this.condicao_Livro = condicao_Livro;
    }

    public String getDescricao_Livro() {
        return descricao_Livro;
    }

    public void setDescricao_Livro(String descricao_Livro) {
        this.descricao_Livro = descricao_Livro;
    }

    public String getImgUrl1_Livro() {
        return imgUrl1_Livro;
    }

    public void setImgUrl1_Livro(String imgUrl1_Livro) {
        this.imgUrl1_Livro = imgUrl1_Livro;
    }

    public String getImgUrl2_Livro() {
        return imgUrl2_Livro;
    }

    public void setImgUrl2_Livro(String imgUrl2_Livro) {
        this.imgUrl2_Livro = imgUrl2_Livro;
    }

    public String getImgUrl3_Livro() {
        return imgUrl3_Livro;
    }

    public void setImgUrl3_Livro(String imgUrl3_Livro) {
        this.imgUrl3_Livro = imgUrl3_Livro;
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
