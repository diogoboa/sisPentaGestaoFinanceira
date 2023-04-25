package penta.sisPenta.gestaoFinanceira.Util;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public final class FuncoesDeValidacaoDeDados {





    public static String normaliza_caixa_alta_ou_vazio(String texto, boolean aplicar_trim)
    {

        try{
            if(aplicar_trim)
                texto=texto.trim();
            texto = texto.toUpperCase();

        }catch(Exception e)
        {
            texto = "";
        }
        return texto;
    }

    public static String normaliza_caixa_baixa_ou_vazio(String texto, boolean aplicar_trim)
    {

        try{
            if(aplicar_trim)
                texto=texto.trim();
            texto = texto.toLowerCase();

        }catch(Exception e)
        {
            texto = "";
        }
        return texto;
    }



    public static String autoCompletar(String texto, String caracteres_para_preencher, int tamanho_minimo, boolean para_esquerda)
    {

            int quantidade_faltante = tamanho_minimo - texto.length();

            if(quantidade_faltante>0)
            {
                StringBuilder texto_acrescentar = new StringBuilder();
                while(quantidade_faltante>0)
                {
                    texto_acrescentar.append(caracteres_para_preencher);
                    quantidade_faltante -= caracteres_para_preencher.length();
                }

                String saida;
                if(para_esquerda)
                    return texto_acrescentar.toString()+texto;
                else
                    return texto+texto_acrescentar.toString();
            }
            else {
               return texto;
            }


    }



    public static String somenteNumero(String str) {



        String strFinal = "";
        if(str==null)
            return "";

        for (int x = 0; x < str.length(); x++) {
            if (str.charAt(x) >= '0' && str.charAt(x) <= '9') {
                strFinal += str.charAt(x);
            }
        }

        return strFinal;
    }

    public static String somenteLetrasEespacos(String str) {



        String strFinal = "";
        if(str==null)
            return "";

        for (int x = 0; x < str.length(); x++) {
            if ( (str.charAt(x) >= 'a' && str.charAt(x) <= 'z') || (str.charAt(x) >= 'A' && str.charAt(x) <= 'Z') || str.charAt(x) == ' ') {
                strFinal += str.charAt(x);
            }
        }

        return strFinal;
    }


    public static String somenteLetrasNumerosEespacos(String str) {



        String strFinal = "";
        if(str==null)
            return "";

        for (int x = 0; x < str.length(); x++) {
            if ( (str.charAt(x) >= '0' && str.charAt(x) <= '9') || (str.charAt(x) >= 'a' && str.charAt(x) <= 'z') || (str.charAt(x) >= 'A' && str.charAt(x) <= 'Z') || str.charAt(x) == ' ') {
                strFinal += str.charAt(x);
            }
        }

        return strFinal;
    }



    public static String validacaoPlacaDeCarro(String str) {

        if(str == null)
            str = "";

        str = somenteLetrasNumerosEespacos(str).replaceAll(" ", "");

        String placa;
        char[] arr = str.toCharArray();
        if(str.length() == 7)
        {
           placa = (arr[0]+"")+(arr[1]+"")+(arr[2]+"")+"-"+arr[3]+(arr[4]+"")+arr[5]+arr[6];
        }
        else
        {
           placa = str;
        }

        return placa.toUpperCase();
    }




    public static String padraoParaNome(String nome, boolean aplicar_caixa_alta) {


        if(nome==null)
            nome = "";

        nome = nome.trim();

        if(aplicar_caixa_alta)
            nome=nome.toUpperCase();

        return nome.toUpperCase();
    }


    public static String padrao_quantidade_un_ou_decimal(BigDecimal quantidade, Integer quantidade_de_casas) {


        if(quantidade == null)
        {
            quantidade= BigDecimal.ZERO;
        }


        BigDecimal parte_fracionaria = quantidade.remainder( BigDecimal.ONE );
        BigInteger parte_inteira = quantidade.toBigInteger();

        quantidade = quantidade.setScale(quantidade_de_casas, RoundingMode.HALF_UP);

        if(parte_fracionaria.compareTo(BigDecimal.ZERO) == 0)
            return parte_inteira.toString();
        else
        {

            return quantidade.toPlainString();
        }


    }

    public static String padrao_monetario_sem_ponto_brl(BigDecimal valor) {


        try {
            Locale localeBR = new Locale( "pt", "BR" );
            NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);
            String saida = (dinheiroBR.format(valor.doubleValue()).replaceAll("R\\$.", ""));
            return saida;

        }catch (Exception ex)
        {
            throw new RuntimeException("Erro ao converter moeda");
        }


    }



    public static String instant_to_utc_brl_string(Instant instant)
    {

        String formatted;
        try
        {
            Date dt = Date.from(instant);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);


            formatted = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(cal.getTime());
        }catch (Exception exception)
        {
            formatted= "";
        }


        return formatted;
    }

    public static String instant_to_utc_brl_somente_dia_string(Instant instant)
    {

        String formatted;
        try
        {
            Date dt = Date.from(instant);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);


            formatted = new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime());
        }catch (Exception exception)
        {
            formatted= "";
        }


        return formatted;
    }

    public static String instant_to_utc_brl_somente_mes_ano_string(Instant instant)
    {

        String formatted;
        try
        {
            Date dt = Date.from(instant);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);


            formatted = new SimpleDateFormat("MM/yyyy").format(cal.getTime());
        }catch (Exception exception)
        {
            formatted= "";
        }


        return formatted;
    }

    public static String instant_seconds_to_utc_brl_string(Instant instant)
    {

        String formatted;
        try
        {
            Date dt = Date.from(instant);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);


            formatted = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(cal.getTime());
        }catch (Exception exception)
        {
            formatted= "";
        }


        return formatted;
    }


}
