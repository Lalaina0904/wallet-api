package com.prog3.walletapp.repository;

import com.prog3.walletapp.entity.Currency;
import com.prog3.walletapp.entity.Account;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class AccountCrudOperation implements CrudOperation<Account> {

    @Override
    public List<Account> findAll() {
      /*  String sql="select id,nom_du_compte,type_de_compte,montant_de_depart" +
                ",devise.* from compte inner join devise on devise=id_devise;";
        List<Account> accounts =new ArrayList<>();
        try {
            PreparedStatement preparedStatement=ConnectionDB
                    .getConnection().getConnectionInstance().prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                accounts.add(
                        new Account(
                                resultSet.getInt("id"),
                                resultSet.getString("nom_du_compte"),
                                resultSet.getString("type_de_compte"),
                                resultSet.getDouble("montant_de_depart"),
                                new Currency(
                                        resultSet.getInt("id_devise"),
                                        resultSet.getString("nom_devise"),
                                        resultSet.getString("code_iso")
                                )

                        )
                );
            }

        }catch (Exception e){
            e.printStackTrace();
        }*/
        return null;
    }

    @Override
    public Account findById(int id) {
        String sql="select id,account_name,sold,accounttype,id_currency,name,code from account " +
                "inner join currency on id_currency=currency where id=?;";
        Account account = null;
        try {
            PreparedStatement preparedStatement=ConnectionDB.getConnection()
                    .getConnectionInstance().prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                account =new Account(
                        resultSet.getInt("id"),
                        resultSet.getString("account_name"),
                        resultSet.getDouble("sold"),
                        new Currency(
                                resultSet.getInt("id_currency"),
                                resultSet.getString("name"),
                                resultSet.getString("code")
                        ),
                        resultSet.getString("accounttype")

                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public Account save(Account account) {
        String sql="INSERT INTO compte VALUES" +
                "(?,?,?,?,?) ON CONFLICT DO NOTHING";
        try{
            PreparedStatement preparedStatement=ConnectionDB.getConnection()
                    .getConnectionInstance().prepareStatement(sql);
            preparedStatement.setInt(1, account.getId());
            preparedStatement.setString(2, account.getName());
            preparedStatement.setDouble(3, account.getSold());
            preparedStatement.setInt(4, account.getCurrency().getId());
            preparedStatement.setString(5, account.getType());
            int rows=preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public List<Account> saveAll(List<Account> toSave) {

        try{
            for(Account account : toSave){
            save(account);
           }
        }catch (Exception e){
            e.printStackTrace();
        }
        return toSave;
    }

    @Override
    public Account update(Account toUpdate) {
        String sql="update account set sold=? where id=?";
        try {
            PreparedStatement preparedStatement=ConnectionDB
                    .getConnection().getConnectionInstance().prepareStatement(sql);
            preparedStatement.setDouble(1,toUpdate.getSold());
            preparedStatement.setInt(2,toUpdate.getId());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

        return findById(toUpdate.getId());
    }
}
