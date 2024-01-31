package com.prog3.walletapp.functionnality;

import com.prog3.walletapp.entity.TransfertHistory;
import com.prog3.walletapp.repository.AccountCrudOperation;
import com.prog3.walletapp.repository.CurrencyValueOperation;
import com.prog3.walletapp.repository.TransactionCrudOperation;
import com.prog3.walletapp.repository.TransfertHistoryOperation;


import com.prog3.walletapp.entity.Transaction;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BalanceHistory {
    TransactionCrudOperation transactionCrudOperation=new TransactionCrudOperation();
    public Double getSoldByDate(int idAccount, LocalDateTime dateTime){
        List<Transaction> transactions=transactionCrudOperation.allTransactoinByIdAccount(idAccount);

        transactions.sort(Comparator.comparing(Transaction::getDate));
        Double amount=0.0;
        for(Transaction transaction:transactions){
            if(transaction.getDate().isAfter(dateTime) || transaction.getDate().equals(dateTime)){
                break;
            }
            if(transaction.getType().equals("debit"))
                amount-=transaction.getAmount();
            else
                amount+=transaction.getAmount();
        }
        return amount;
    }

    public List<Double> accountBalanceHistory(int idAccount,LocalDateTime beginDateTime,LocalDateTime endDateTime){
        List<Transaction> transactions=transactionCrudOperation.allTransactoinByIdAccount(idAccount);
        transactions.sort(Comparator.comparing(Transaction::getDate));
        Double amount=0.0;
        List<Double> allSold=new ArrayList<>();
        for(Transaction transaction:transactions){
            if(transaction.getDate().isBefore(beginDateTime))
                continue;
            if(transaction.getDate().isAfter(endDateTime))
                break;
            if(transaction.getType().equals("debit"))
                amount-=transaction.getAmount();
            else
                amount+=transaction.getAmount();

            allSold.add(amount);
        }
    return allSold;
    }

// the current sold of the account which receives transfert
    TransfertHistoryOperation transfert=new TransfertHistoryOperation();
    CurrencyValueOperation currencyValueOperation=new CurrencyValueOperation();
    public double receveirAccountCurrentSold(int idAccountReceiver){
       List<TransfertHistory> transfertHistories=transfert.getTransfertHistoryByIdCreditor(idAccountReceiver);
        AccountCrudOperation account=new AccountCrudOperation();
       Double totalSold= getSoldByDate(idAccountReceiver,LocalDateTime.now());
        for(TransfertHistory transfertHistory:transfertHistories){
           LocalDateTime transfertDate= transfertHistory.getDateTime();
          double CurrencyValueAmount=currencyValueOperation.getByDate(transfertDate).getAmount();
          double convertedSold=CurrencyValueAmount*transfertHistory.getAmount();

          totalSold+=convertedSold;
        }
        return totalSold;
    }

}
