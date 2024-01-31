package com.prog3.walletapp.service;

import com.prog3.walletapp.entity.CurrencyValue;
import com.prog3.walletapp.repository.CurrencyValueOperation;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class CurrencyValueFunctionality {
    CurrencyValueOperation currencyValueOperation=new CurrencyValueOperation();
    public Double getCalculatedCurrencyByDate(LocalDate date,String calculType){
        double result=0;
        switch (calculType){
            case "average":
                result=calculateAverage(date);
                break;
            case "minimum":
                result=minimumValue(date);
                break;
            case "maximun":
                result=maximumValue(date);
                break;
            case "median":
                result=median(date);
                break;
        }
        return result;
    }

    public double calculateAverage(LocalDate date){
        List<CurrencyValue> currencies= currencyValueOperation.groupByDate(date);
        int coefficient=currencies.size();
        double average=0.0;
        double sum=0.0;
        for(CurrencyValue currencyValue:currencies){
            sum+=currencyValue.getAmount();
        }
        average=sum/coefficient;
        return average;
    }
    public double minimumValue(LocalDate date){
        List<CurrencyValue> currencies= currencyValueOperation.groupByDate(date);


        currencies.sort(Comparator.comparing(CurrencyValue::getAmount));
        CurrencyValue currencyValueMin=currencies.get(0);
        double minAmount=currencyValueMin.getAmount();
        return  minAmount;

    }

    public double maximumValue(LocalDate date){
        List<CurrencyValue> currencies= currencyValueOperation.groupByDate(date);


        currencies.sort(Comparator.comparing(CurrencyValue::getAmount));
        CurrencyValue currencyValueMax=currencies.get(currencies.size()-1);
        double maxAmount=currencyValueMax.getAmount();
        return  maxAmount;

    }
    public double median(LocalDate date){
        List<CurrencyValue> currencies= currencyValueOperation.groupByDate(date);
        currencies.sort(Comparator.comparing(CurrencyValue::getAmount));
        double median=0.0;

        int size=currencies.size();
        if(size%2==0){
            int mid=size/2;
            median=(currencies.get(mid-1).getAmount()+currencies.get(mid+1).getAmount())/2;
        }
        else{
            int mid=size/2;
            median=currencies.get(mid).getAmount();
        }
        return median;
    }
}
