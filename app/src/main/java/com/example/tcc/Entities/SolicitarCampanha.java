package com.example.tcc.Entities;

import android.os.Parcel;
import android.os.Parcelable;

public class SolicitarCampanha implements Parcelable{

    String id_campanha;
    String id_user;
    String id_ong;
    String titulo;
    int qtd_arrecadado;
    int qtd_limite;
    String objetivo_campanha;
    String descricao_breve;
    String imgUrl1_campanha;
    String status;
    String unica_ou_campanha;
    String categoria;

    public SolicitarCampanha(String id_campanha, String id_user, String id_ong, String titulo, int qtd_arrecadado,
                             int qtd_limite, String objetivo_campanha, String descricao_breve, String imgUrl1_campanha,
                             String status, String unica_ou_campanha, String categoria) {

        this.id_campanha = id_campanha;
        this.id_user = id_user;
        this.id_ong = id_ong;
        this.titulo = titulo;
        this.qtd_arrecadado = qtd_arrecadado;
        this.qtd_limite = qtd_limite;
        this.objetivo_campanha = objetivo_campanha;
        this.descricao_breve = descricao_breve;
        this.imgUrl1_campanha = imgUrl1_campanha;
        this.status = status;
        this.unica_ou_campanha = unica_ou_campanha;
        this.categoria = categoria;
    }

    public SolicitarCampanha() {}

    public SolicitarCampanha(Parcel in) {
        id_campanha = in.toString();
        id_user = in.toString();
        id_ong = in.toString();
        titulo = in.toString();
//        qtd_arrecadado = in.toString();
//        qtd_limite = ();
        objetivo_campanha = in.toString();
        descricao_breve = in.toString();
        imgUrl1_campanha = in.toString();
        status = in.toString();
        unica_ou_campanha = in.toString();
        categoria = in.toString();

    }

    public static final Parcelable.Creator<DoacaoRoupa> CREATOR = new Parcelable.Creator<DoacaoRoupa>() {
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
        parcel.writeString(id_campanha);
        parcel.writeString(titulo);
        parcel.writeInt(qtd_arrecadado);
        parcel.writeInt(qtd_limite);
        parcel.writeString(objetivo_campanha);
        parcel.writeString(descricao_breve);
        parcel.writeString(imgUrl1_campanha);
        parcel.writeString(status);
        parcel.writeString(unica_ou_campanha);
        parcel.writeString(categoria);
    }

    public String getId_campanha() {
        return id_campanha;
    }

    public void setId_campanha(String id_campanha) {
        this.id_campanha = id_campanha;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getQtd_arrecadado() {
        return qtd_arrecadado;
    }

    public void setQtd_arrecadado(int qtd_arrecadado) {
        this.qtd_arrecadado = qtd_arrecadado;
    }

    public int getQtd_limite() {
        return qtd_limite;
    }

    public void setQtd_limite(int qtd_limite) {
        this.qtd_limite = qtd_limite;
    }

    public String getObjetivo_campanha() {
        return objetivo_campanha;
    }

    public void setObjetivo_campanha(String objetivo_campanha) {
        this.objetivo_campanha = objetivo_campanha;
    }

    public String getImgUrl1_campanha() {
        return imgUrl1_campanha;
    }

    public void setImgUrl1_campanha(String imgUrl1_campanha) {
        this.imgUrl1_campanha = imgUrl1_campanha;
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

    public String getDescricao_breve() {
        return descricao_breve;
    }

    public void setDescricao_breve(String descricao_breve) {
        this.descricao_breve = descricao_breve;
    }
}
