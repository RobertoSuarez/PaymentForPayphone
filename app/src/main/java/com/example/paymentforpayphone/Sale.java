package com.example.paymentforpayphone;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class Sale {
    public String phoneNumber;
    public String countryCode;
    public String reference;
    public int amount;
    public int amountWithoutTax;
    public String clientTransactionId;
    public String currency;

    public Sale() {
    }

    // convierte un Sale a Object Json
    public JSONObject getJson() throws JSONException {
        Gson gson = new Gson();
        return new JSONObject(gson.toJson(this));
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmountWithoutTax() {
        return amountWithoutTax;
    }

    public void setAmountWithoutTax(int amountWithoutTax) {
        this.amountWithoutTax = amountWithoutTax;
    }

    public String getClientTransactionId() {
        return clientTransactionId;
    }

    public void setClientTransactionId(String clientTransactionId) {
        this.clientTransactionId = clientTransactionId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
