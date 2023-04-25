/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penta.sisPenta.gestaoFinanceira.Util;

/**
 *
 * @author Diogo
 */
public final class ValidadorCPFeCNPJ {
   private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
   private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

   private static int calcularDigito(String str, int[] peso) {
      int soma = 0;
      for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
         digito = Integer.parseInt(str.substring(indice,indice+1));
         soma += digito*peso[peso.length-str.length()+indice];
      }
      soma = 11 - soma % 11;
      return soma > 9 ? 0 : soma;
   }

   public static boolean isValidCPF(String cpf) {
      if ((cpf==null) || (cpf.length()!=11)) return false;

      Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
      Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
      return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
   }

   public static boolean isValidCNPJ(String cnpj) {

      if ((cnpj==null)||(cnpj.length()!=14)) return false;

      Integer digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
      Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
      return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
   }


   public static String formatarEAutoCompletarPadraoCpfCnpj(String texto)
   {


      String cpf_cnpj = FuncoesDeString.somenteNumero(texto.toString());


      if(cpf_cnpj.length() < 11)
         cpf_cnpj = FuncoesDeString.autoCompletar(cpf_cnpj, "0", 11, true);
      if(cpf_cnpj.length() < 14 && cpf_cnpj.length() != 11)
         cpf_cnpj = FuncoesDeString.autoCompletar(cpf_cnpj, "0", 14, true);
      return formatarPadraoCpfCnpj(cpf_cnpj);

   }


   public static String formatarPadraoCpfCnpj(String texto) {

      texto = FuncoesDeString.somenteNumero(texto);

      if (texto.length() == 11) {

            texto = texto.substring(0, 3) + "." + texto.substring(3, 6) + "." + texto.substring(6, 9) + "-" + texto.substring(9, 11);

      } else if (texto.length() == 14) {

            texto = String.format(texto.substring(0, 2) + "." + texto.substring(2, 5) + "." + texto.substring(5, 8) + "/" + texto.substring(8, 12) + "-" + texto.substring(12, 14));


      } else {
         texto = "";
      }

      return texto;
   }




}
