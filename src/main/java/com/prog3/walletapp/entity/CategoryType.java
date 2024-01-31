package com.prog3.walletapp.entity;

public enum CategoryType {
    nourritureEtBoisson("nourriture et boisson"),
    achatEtBoutiqueEnLigne("achat et boutique en ligne"),
    logment("logement"),
    transports("transports"),
    vehicule("vehicule"),
    loisirs("loisirs"),
    multimediaInformatique("multimedia,Informatique"),
    financialExpenses("financial expenses"),
    investments("investments"),
    inCome("income"),
    other("other");



    private final String value;

    CategoryType(String value) {
    this.value=value;
    }

    public String getValue() {
        return value;
    }
}
