package com.example.paymentforpayphone;

public class HistorialPagoItem {
    public String Titulo;
    public String SubTitle;
    public String idTransaction;

    public HistorialPagoItem(String titulo, String subTitle, String idTransaction) {
        Titulo = titulo;
        SubTitle = subTitle;
        this.idTransaction = idTransaction;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getSubTitle() {
        return SubTitle;
    }

    public void setSubTitle(String subTitle) {
        SubTitle = subTitle;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }
}
