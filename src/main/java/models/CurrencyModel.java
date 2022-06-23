package models;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CurrencyModel {
    public float UsdBuying;
    public float UsdSelling;
    public float EuroBuying;
    public float EuroSelling;
    public float ExchangeRate;
    public String Gun;

    public CurrencyModel() {
    }

    public CurrencyModel(float usdBuying, float usdSelling, float euroBuying, float euroSelling) {
        UsdBuying = usdBuying;
        UsdSelling = usdSelling;
        EuroBuying = euroBuying;
        EuroSelling = euroSelling;
        //DateTime Formatting
        ZonedDateTime zonedDate = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //DateTime Formatting
        Gun= zonedDate.format(formatter);
        //for decimal and parse float
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat= new DecimalFormat("",otherSymbols);
        decimalFormat.setMaximumFractionDigits(4);
        // for decimal and parse float
        ExchangeRate = Float.parseFloat(decimalFormat.format(this.EuroSelling/this.UsdSelling));

    }


}
