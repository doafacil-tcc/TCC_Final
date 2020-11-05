package com.example.tcc.Entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Doacao implements Parcelable {

    String id;
    String id_user;
    String id_ong;
    String tipo;
    String qtd;
    String tamanho;
    String condicao;
    String descricao;
    String imgUrl1;
    String imgUrl2;
    String imgUrl3;
    String status;
    String unica_ou_campanha;
    String categoria;
    String origem;

    public Doacao(){ }


    public Doacao(String id, String id_user, String id_ong, String tipo, String qtd, String tamanho,
                  String condicao, String descricao, String imgUrl1, String imgUrl2, String imgUrl3,
                  String status, String unica_ou_campanha, String categoria, String origem){
        this.id = id;
        this.id_user = id_user;
        this.id_ong = id_ong;
        this.tipo = tipo;
        this.qtd = qtd;
        this.tamanho = tamanho;
        this.condicao = condicao;
        this.descricao = descricao;
        this.imgUrl1 = imgUrl1;
        this.imgUrl2 = imgUrl2;
        this.imgUrl3 = imgUrl3;
        this.status = status;
        this.unica_ou_campanha = unica_ou_campanha;
        this.categoria = categoria;
        this.origem = origem;

    }

    public Doacao(Parcel in) {
        id = in.toString();
        id_user = in.toString();
        id_ong = in.toString();
        tipo = in.toString();
        qtd = in.toString();
        tamanho = in.toString();
        condicao = in.toString();
        descricao = in.toString();
        imgUrl1 = in.toString();
        imgUrl2 = in.toString();
        imgUrl3 = in.toString();
        status = in.toString();
        unica_ou_campanha = in.toString();
        categoria = in.toString();
        origem = in.toString();

    }

    public static final Creator<Doacao> CREATOR = new Creator<Doacao>() {
        @Override
        public Doacao createFromParcel(Parcel in) {
            return new Doacao(in);
        }

        @Override
        public Doacao[] newArray(int size) {
            return new Doacao[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(tipo);
        parcel.writeString(qtd);
        parcel.writeString(tamanho);
        parcel.writeString(condicao);
        parcel.writeString(descricao);
        parcel.writeString(imgUrl1);
        parcel.writeString(imgUrl2);
        parcel.writeString(imgUrl3);
        parcel.writeString(status);
        parcel.writeString(unica_ou_campanha);
        parcel.writeString(categoria);
        parcel.writeString(origem);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getQtd() {
        return qtd;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImgUrl1() {
        return imgUrl1;
    }

    public void setImgUrl1(String imgUrl1) {
        this.imgUrl1 = imgUrl1;
    }

    public String getImgUrl2() {
        return imgUrl2;
    }

    public void setImgUrl2(String imgUrl2) {
        this.imgUrl2 = imgUrl2;
    }

    public String getImgUrl3() {
        return imgUrl3;
    }

    public void setImgUrl3(String imgUrl3) {
        this.imgUrl3 = imgUrl3;
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

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }
}
