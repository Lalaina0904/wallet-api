package com.prog3.walletapp.repository;

import com.prog3.walletapp.entity.Currency;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeviseCrudOperation implements CrudOperation<Currency> {

    public List<Currency> findAll() {
        String sql="select * from currency";
        List <Currency> currencies =new ArrayList<>();
        try {
            PreparedStatement preparedStatement= ConnectionDB
                    .getConnection().getConnectionInstance().prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                currencies.add(
                        new Currency(
                                resultSet.getInt("id_currency"),
                                resultSet.getString("name"),
                                resultSet.getString("code")
                        )
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return currencies;
    }

    @Override
    public Currency findById(int id) {
        String sql="select * from devise where id_currency=?";
        Currency currency =null;
        try {
            PreparedStatement preparedStatement= ConnectionDB
                    .getConnection().getConnectionInstance().prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){

                        new Currency(
                                resultSet.getInt("id_currency"),
                                resultSet.getString("name"),
                                resultSet.getString("code")
                        );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return currency;
    }

    @Override
    public Currency save(Currency toSave) {
        String sql="INSERT INTO currency VALUES" +
                "(?,?,?) ON CONFLICT DO NOTHING";
        try{
            PreparedStatement preparedStatement=ConnectionDB.getConnection()
                    .getConnectionInstance().prepareStatement(sql);
            preparedStatement.setInt(1,toSave.getId());
            preparedStatement.setString(2,toSave.getName());
            preparedStatement.setString(3,toSave.getCode());

            int rows=preparedStatement.executeUpdate();
            System.out.println(rows>0 ? "inserted succefully" : "this currency already exists");
        }catch (Exception e){
            e.printStackTrace();
        }
        return toSave;
    }

    @Override
    public List<Currency> saveAll(List<Currency> toSave) {
    List<Currency> currencies=new ArrayList<>();
    for(Currency currency:toSave){
        currencies.add(save(currency));
    }
        return toSave;
    }

    @Override
    public Currency update(Currency toUpdate) {
        return null;
    }
}
