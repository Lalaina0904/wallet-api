package com.prog3.walletapp.repository;


import com.prog3.walletapp.entity.CurrencyValue;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;


public class CurrencyValueOperation {
    Connection connection=ConnectionDB.getConnection().getConnectionInstance();
    String idCurrencyValueColumn="id_currency_value";
    String idCurrencySourceColumn="id_currency_source";
    String idCurrencyDestinationColumn="id_currency_destination";
    String valueColumn="value";
    String dateColumn="date";
    public CurrencyValue getLastCurrencyValue(){
        String sql="select * from currency_value order by date DESC limit 1";
        CurrencyValue currencyValue=null;
      try {
          Statement statement=connection.createStatement();
          ResultSet resultSet= statement.executeQuery(sql);
          while (resultSet.next()){
              currencyValue=
                      new CurrencyValue(

                              resultSet.getInt(idCurrencyValueColumn),
                              resultSet.getInt(idCurrencySourceColumn),
                              resultSet.getInt(idCurrencyDestinationColumn),
                              resultSet.getDouble(idCurrencyValueColumn),
                              resultSet.getTimestamp(dateColumn).toLocalDateTime()
                      );

          }

      }catch (Exception e){
          e.printStackTrace();
      }
      return currencyValue;
    }



    public CurrencyValue getByDate(LocalDateTime date){
        String sql="select * from currency_value where date=?";

        CurrencyValue currencyValue=null;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(date));
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                currencyValue=
                        new CurrencyValue(

                                resultSet.getInt(idCurrencyValueColumn),
                                resultSet.getInt(idCurrencySourceColumn),
                                resultSet.getInt(idCurrencyDestinationColumn),
                                resultSet.getDouble(idCurrencyValueColumn),
                                resultSet.getTimestamp(dateColumn).toLocalDateTime()
                        );

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return currencyValue;
    }
    public List<CurrencyValue> groupByDate(LocalDate date){
        String sql = "SELECT * FROM currency_value WHERE DATE_TRUNC('day', date::DATE) = DATE_TRUNC('day', ?::DATE)";
        Timestamp timestamp = Timestamp.valueOf(date.atStartOfDay());
        List<CurrencyValue> currencyValuesByDate=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setTimestamp(1,timestamp);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
               currencyValuesByDate.add(
                       new CurrencyValue(

                               resultSet.getInt(idCurrencyValueColumn),
                               resultSet.getInt(idCurrencySourceColumn),
                               resultSet.getInt(idCurrencyDestinationColumn),
                               resultSet.getDouble(idCurrencyValueColumn),
                               resultSet.getTimestamp(dateColumn).toLocalDateTime()
                       )
               );

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return currencyValuesByDate;
    }
}
