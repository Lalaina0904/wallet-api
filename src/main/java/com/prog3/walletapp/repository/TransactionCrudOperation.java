package com.prog3.walletapp.repository;

import com.prog3.walletapp.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TransactionCrudOperation{
       String idColumn="id";
        String labelColumn="label";
        String amountColumn="amount";
        String transactionDateColumn="transaction_date";
        String transactionTypeColumn="transaction_type";
        String idCategoryColumn="id_category";

    public List<Transaction> findAll() {
        String sql="select * from Transaction ";
        List <Transaction> Transactions=new ArrayList<>();
        AccountCrudOperation account=new AccountCrudOperation();
        try {
            PreparedStatement preparedStatement= ConnectionDB
                    .getConnection().getConnectionInstance().prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Transactions.add(
                        new Transaction(
                             resultSet.getInt(idColumn),
                                resultSet.getString(labelColumn),
                                resultSet.getDouble(amountColumn),
                                resultSet.getTimestamp(transactionDateColumn).toLocalDateTime(),
                                resultSet.getString(transactionTypeColumn),
                                resultSet.getInt(idCategoryColumn)
                        )
                );

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Transactions;
    }

    public Transaction findById(int id) {
        String sql="select * from Transaction ";
        Transaction transaction=null;
        AccountCrudOperation account=new AccountCrudOperation();
        try {
            PreparedStatement preparedStatement= ConnectionDB
                    .getConnection().getConnectionInstance().prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){

                transaction=new Transaction(
                        resultSet.getInt(idColumn),
                        resultSet.getString(labelColumn),
                        resultSet.getDouble(amountColumn),
                        resultSet.getTimestamp(transactionDateColumn).toLocalDateTime(),
                        resultSet.getString(transactionTypeColumn),
                        resultSet.getInt(idCategoryColumn)

                );


            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return transaction;
    }

    public Transaction save(Transaction toSave) {
        String sql="INSERT INTO Transaction VALUES" +
                "(?,?,?,?,?,?,?) ON CONFLICT DO NOTHING";
        try{
            PreparedStatement preparedStatement=ConnectionDB.getConnection()
                    .getConnectionInstance().prepareStatement(sql);
           preparedStatement.setInt(1,toSave.getId());
           preparedStatement.setString(2,toSave.getLabel());
           preparedStatement.setDouble(3,toSave.getAmount());
           preparedStatement.setTimestamp(5, Timestamp.valueOf(toSave.getDate()));
           preparedStatement.setInt(6,toSave.getCategory());
            preparedStatement.setInt(7,toSave.getCategory());

            int rows=preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }

        return toSave;
    }

public List<Transaction> allTransactoinByIdAccount(int idAccount){
    String sql="select * from Transaction where id_account=?";
    List <Transaction> Transactions=new ArrayList<>();
    AccountCrudOperation account=new AccountCrudOperation();
    try {
        PreparedStatement preparedStatement= ConnectionDB
                .getConnection().getConnectionInstance().prepareStatement(sql);
        preparedStatement.setInt(1,idAccount);
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            Transactions.add(
                    new Transaction(
                            resultSet.getInt(idColumn),
                            resultSet.getString(labelColumn),
                            resultSet.getDouble(amountColumn),
                            resultSet.getTimestamp(transactionDateColumn).toLocalDateTime(),
                            resultSet.getString(transactionTypeColumn),
                            resultSet.getInt(idCategoryColumn)
                    )
            );


        }
    }catch (Exception e){
        e.printStackTrace();
    }
    return Transactions;
}

    public List<Transaction> saveAll(List<Transaction> toSave) {
        List <Transaction> transactions=new ArrayList<>();
        for(Transaction transaction:toSave)
            transactions.add(save(transaction));
        return transactions;
    }


    public boolean saveTransactionByAccount(Transaction transaction,int idAccount){
        String sql="INSERT INTO Transaction VALUES" +
                "(?,?,?,?,?,?,?) ON CONFLICT DO NOTHING";
        int saved=0;
        try{
            PreparedStatement preparedStatement=ConnectionDB.getConnection()
                    .getConnectionInstance().prepareStatement(sql);
            preparedStatement.setInt(1,transaction.getId());
            preparedStatement.setString(2,transaction.getLabel());
            preparedStatement.setDouble(3,transaction.getAmount());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(transaction.getDate()));
            preparedStatement.setString(5,transaction.getType());
            preparedStatement.setInt(6,idAccount);
            preparedStatement.setInt(7,transaction.getCategory());


            saved=preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();

        }
        return saved!=0;
    }
}
