package penta.sisPenta.gestaoFinanceira.Util;



import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class FuncoesDeString {


    public static String regexFinder(String regex, String texto, int idGroup)
    {
        String retorno="";

        try{
            Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
            Matcher matcher = pattern.matcher(texto);
            if(matcher.find())
            {
                retorno = matcher.group(idGroup);
            }
            else
            {
                retorno = "";
            }
        }catch(Exception e)
        {
            retorno = "";
        }
        return retorno;
    }


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


    public static String[] regexFinderVector(String regex, String texto)
    {
        String retorno[]=new String[0];
        try
        {
            Vector<String> vetorMatches = new Vector<>();

            Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
            Matcher matcher = pattern.matcher(texto);

            if(matcher.find())
            {
                do
                {
                    vetorMatches.add(matcher.group(0));
                }while(matcher.find());

                retorno = new String[vetorMatches.size()];
                for (int i = 0; i < vetorMatches.size(); i++) {
                    retorno[i] = vetorMatches.get(i);
                }

            }
            else
            {
                retorno = new String[0];
            }
        }
        catch(Exception e)
        {
            retorno = new String[0];

            return retorno;
        }
        return retorno;
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
                    quantidade_faltante-= caracteres_para_preencher.length();
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



}
