package com.prog3.walletapp.service;

import com.prog3.walletapp.repository.ConnectionDB;

import java.sql.*;
import java.time.LocalDate;

public class MapSQLFunction {
Connection connection= ConnectionDB.getConnection().getConnectionInstance();

public  String entryAndExpenseSum(int accountId, LocalDate startDate, LocalDate endDate){
   String response="";
   try {
       String sql="SELECT * FROM credit_and_debit_amount_sum(?,?,?)";
       PreparedStatement preparedStatement=connection.prepareStatement(sql);
       preparedStatement.setInt(1,accountId);
       preparedStatement.setDate(2, Date.valueOf(startDate));
       preparedStatement.setDate(3,Date.valueOf(endDate));
       ResultSet resultSet=preparedStatement.executeQuery();
       while (resultSet.next()){
          int entry= resultSet.getInt("entry");
          int expense=resultSet.getInt("expense");

        response+="entry="+entry+" expnse="+expense;
       }
   }catch (Exception e){
e.printStackTrace();
   }
   return response;
}


public String categorieTotalAmount(int accountId, LocalDate startDate, LocalDate endDate){
    String response="";
    try {
        String sql="SELECT * FROM  category_amount_sum(?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,accountId);
        preparedStatement.setDate(2, Date.valueOf(startDate));
        preparedStatement.setDate(3,Date.valueOf(endDate));
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            String type= resultSet.getString("category_type");
            int amountSum=resultSet.getInt("category_amount_sum");

            response+="\ncategorieType="+type+" categoryAmountSum="+amountSum;
        }
    }catch (Exception e){
        e.printStackTrace();
    }
    return response;
}

}
