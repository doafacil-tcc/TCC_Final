package com.example.tcc.Entities;

import android.os.Parcel;
import android.os.Parcelable;

public class DoacaoComMatch implements Parcelable {

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
    String data;
    String hora;
    String endereco;
    String tipoEntrega;



    public DoacaoComMatch(String id, String id_user, String id_ong, String tipo, String qtd, String tamanho,
                          String condicao, String descricao, String imgUrl1, String imgUrl2, String imgUrl3,
                          String status, String unica_ou_campanha, String categoria, String origem, String data,
                          String hora, String endereco, String tipoEntrega) {
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
        this.data = data;
        this.hora = hora;
        this.endereco = endereco;
        this.tipoEntrega = tipoEntrega;
    }

    protected DoacaoComMatch(Parcel in) {
        id = in.readString();
        id_user = in.readString();
        id_ong = in.readString();
        tipo = in.readString();
        qtd = in.readString();
        tamanho = in.readString();
        condicao = in.readString();
        descricao = in.readString();
        imgUrl1 = in.readString();
        imgUrl2 = in.readString();
        imgUrl3 = in.readString();
        status = in.readString();
        unica_ou_campanha = in.readString();
        categoria = in.readString();
        origem = in.readString();
        data = in.readString();
        hora = in.readString();
        endereco = in.readString();
        tipoEntrega = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(id_user);
        dest.writeString(id_ong);
        dest.writeString(tipo);
        dest.writeString(qtd);
        dest.writeString(tamanho);
        dest.writeString(condicao);
        dest.writeString(descricao);
        dest.writeString(imgUrl1);
        dest.writeString(imgUrl2);
        dest.writeString(imgUrl3);
        dest.writeString(status);
        dest.writeString(unica_ou_campanha);
        dest.writeString(categoria);
        dest.writeString(origem);
        dest.writeString(data);
        dest.writeString(hora);
        dest.writeString(endereco);
        dest.writeString(tipoEntrega);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DoacaoComMatch> CREATOR = new Creator<DoacaoComMatch>() {
        @Override
        public DoacaoComMatch createFromParcel(Parcel in) {
            return new DoacaoComMatch(in);
        }

        @Override
        public DoacaoComMatch[] newArray(int size) {
            return new DoacaoComMatch[size];
        }
    };

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }
}
