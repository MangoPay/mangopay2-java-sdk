package com.mangopay.core;

import com.mangopay.core.enumerations.CurrencyIso;
import com.mangopay.core.enumerations.PayoutMode;
import com.mangopay.entities.*;
import com.mangopay.entities.subentities.PayOutPaymentDetailsBankWire;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * IdempotencyApiImpl test methods.
 */
public class IdempotencyApiImplTest extends BaseTest {

    @Test
    public void idempotentRequest() {

        Calendar c = Calendar.getInstance();
        String key = Long.toString(c.getTimeInMillis());
        key = key + "abcdef";   // <- to make the key string at least 16 characters long

        PayOut payOut = null;

        // create bankwire
        try {
            Wallet wallet = this.getJohnsWallet();
            UserNatural user = this.getJohn();
            BankAccount account = this.getJohnsAccount();

            PayOut payOutPost = new PayOut();
            payOutPost.setAuthorId(user.getId());
            payOutPost.setDebitedWalletId(wallet.getId());
            Money debitedFunds = new Money();
            debitedFunds.setAmount(10);
            debitedFunds.setCurrency(CurrencyIso.EUR);
            payOutPost.setDebitedFunds(debitedFunds);
            Money fees = new Money();
            fees.setAmount(5);
            fees.setCurrency(CurrencyIso.EUR);
            payOutPost.setFees(fees);
            PayOutPaymentDetailsBankWire paymentDetails = new PayOutPaymentDetailsBankWire();
            paymentDetails.setBankAccountId(account.getId());
            paymentDetails.setBankWireRef("Johns bank wire ref");
            paymentDetails.setPayoutModeRequested(PayoutMode.STANDARD);
            payOutPost.setMeanOfPaymentDetails(paymentDetails);

            payOutPost.setTag("DefaultTag");
            payOutPost.setCreditedUserId(user.getId());

            payOut = this.api.getPayOutApi().create(key, payOutPost);
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }

        assertNotNull(payOut);

        // test existing key
        IdempotencyResponse result = null;
        try {
            result = this.api.getIdempotencyApi().get(key);
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }

        assertNotNull(result);

        // test not existing key
        try {
            this.api.getIdempotencyApi().get(key + "_no");

            // expecting a ResponseException to be thrown
            Assert.fail();
        } catch (ResponseException rex) {
            assertTrue(rex.getResponseHttpCode() == 400);
            assertTrue(rex.getType().equals("correlationid_not_found"));
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }

    }

}
