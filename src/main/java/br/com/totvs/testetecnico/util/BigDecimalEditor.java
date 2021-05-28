package br.com.totvs.testetecnico.util;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class BigDecimalEditor extends PropertyEditorSupport {

    public void setAsText(String text) {
        setValue(formatarDinheiro(text));
    }
    
    private static BigDecimal formatarDinheiro(String dinheiro) {

        dinheiro = dinheiro.replaceAll("[^0-9.,]+", "").trim();

        BigDecimal dinheiroFomatado = new BigDecimal("0.00");

        if (dinheiro.isEmpty()) {
            return dinheiroFomatado;
        }

        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator(',');
        decimalFormatSymbols.setGroupingSeparator('.');

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###.00");
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setRoundingMode(RoundingMode.HALF_EVEN);
        decimalFormat.setParseBigDecimal(true);

        try {
            dinheiroFomatado = (BigDecimal) decimalFormat.parse(dinheiro);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dinheiroFomatado;
    }
}
