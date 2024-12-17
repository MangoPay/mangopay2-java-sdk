package com.mangopay.entities.subentities;

import com.google.gson.annotations.SerializedName;
import com.mangopay.core.Dto;
import com.mangopay.core.Money;
import com.mangopay.core.interfaces.PayInPaymentDetails;
import com.mangopay.entities.BankAccount;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing the BankWire type for mean of payment in PayIn entity.
 */
public class PayInPaymentDetailsBankWire extends Dto implements PayInPaymentDetails {

    /**
     * Declared debited funds.
     */
    @SerializedName("DeclaredDebitedFunds")
    private Money declaredDebitedFunds;

    /**
     * Declared fees.
     */
    @SerializedName("DeclaredFees")
    private Money declaredFees;

    /**
     * Bank account details.
     */
    @SerializedName("BankAccount")
    private BankAccount bankAccount;

    /**
     * Wire reference.
     */
    @SerializedName("WireReference")
    private String wireReference;

    /**
     * Transaction details for BankWire Pay-ins retrieval.
     */
    @SerializedName("TransactionDetails")
    private List<PayInTransactionDetailsBankWire> transactionDetails;

    public PayInPaymentDetailsBankWire(Money declaredDebitedFunds, Money declaredFees, BankAccount bankAccount, String wireReference, List<PayInTransactionDetailsBankWire> transactionDetails) {
        this.declaredDebitedFunds = declaredDebitedFunds;
        this.declaredFees = declaredFees;
        this.bankAccount = bankAccount;
        this.wireReference = wireReference;
        this.transactionDetails = transactionDetails;
    }

    public PayInPaymentDetailsBankWire() {
    }

    public Money getDeclaredDebitedFunds() {
        return declaredDebitedFunds;
    }

    public void setDeclaredDebitedFunds(Money declaredDebitedFunds) {
        this.declaredDebitedFunds = declaredDebitedFunds;
    }

    public Money getDeclaredFees() {
        return declaredFees;
    }

    public void setDeclaredFees(Money declaredFees) {
        this.declaredFees = declaredFees;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getWireReference() {
        return wireReference;
    }

    public void setWireReference(String wireReference) {
        this.wireReference = wireReference;
    }

    public List<PayInTransactionDetailsBankWire> getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(List<PayInTransactionDetailsBankWire> transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    /**
     * Gets map which property is an object and what type of object.
     *
     * @return Collection of field name-field type pairs.
     */
    @Override
    public Map<String, Type> getSubObjects() {

        HashMap<String, Type> result = new HashMap<>();

        result.put("DeclaredDebitedFunds", Money.class);
        result.put("DeclaredFees", Money.class);
        result.put("BankAccount", BankAccount.class);

        return result;
    }
}
