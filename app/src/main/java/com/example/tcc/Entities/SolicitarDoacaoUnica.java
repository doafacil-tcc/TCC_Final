package com.example.tcc.Entities;

import android.os.Parcel;
import android.os.Parcelable;

public class SolicitarDoacaoUnica implements Parcelable {

    String id_roupa;
    String id_user;
    String id_ong;
    String tipo;
    String quantidade;
    String tamanho;
    String condicao;
    String descricao;
    String status;
    String unica_ou_campanha;
    String categoria;
    String origem;

    public SolicitarDoacaoUnica(String id_roupa, String id_user, String id_ong, String tipo_roupa, String quantidade, String tamanho,
                                String condicao, String descricao, String status, String unica_ou_campanha, String categoria, String origem){
        this.id_roupa = id_roupa;
        this.id_user = id_user;
        this.id_ong = id_ong;
        this.tipo = tipo_roupa;
        this.quantidade = quantidade;
        this.tamanho = tamanho;
        this.condicao = condicao;
        this.descricao = descricao;
        this.status = status;
        this.unica_ou_campanha = unica_ou_campanha;
        this.categoria = categoria;
        this.origem = origem;
    }

    public SolicitarDoacaoUnica(Parcel in) {
        id_roupa = in.toString();
        id_user = in.toString();
        id_ong = in.toString();
        tipo = in.toString();
        quantidade = in.toString();
        tamanho = in.toString();
        condicao = in.toString();
        descricao = in.toString();
        status = in.toString();
        unica_ou_campanha = in.toString();
        categoria = in.toString();
        origem = in.toString();


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
        parcel.writeString(tipo);
        parcel.writeString(quantidade);
        parcel.writeString(tamanho);
        parcel.writeString(condicao);
        parcel.writeString(descricao);
        parcel.writeString(status);
        parcel.writeString(unica_ou_campanha);
        parcel.writeString(categoria);
        parcel.writeString(origem);
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
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
