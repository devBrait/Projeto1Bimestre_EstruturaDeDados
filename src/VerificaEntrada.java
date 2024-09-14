import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class VerificaEntrada {

    GerenciaVariavel gerenciaVariavel = new GerenciaVariavel();
    ConversorExpressao conversorExpressao = new ConversorExpressao();
    VerificaExpressao verificaExpressao = new VerificaExpressao(1024);
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    // verifica se o input corresponde a algum dos comandos válidos
    public void isValid(String expressao) {
        if(expressao.matches(".*[+\\-*/^].*"))
        {

            int quantidadeVar = gerenciaVariavel.quantidadeVariaveis();
            List<Character> variaveisNaoDefinidas = new ArrayList<>();

            // Itera sobre os caracteres da expressão
            for (char exp : expressao.toCharArray()) {
                if (Character.isLetter(exp)) { // verifica se o caracter é uma letra
                    if (gerenciaVariavel.obterValor(exp) == -1)
                    {
                        if (!variaveisNaoDefinidas.contains(exp))
                        {
                            variaveisNaoDefinidas.add(exp);
                        }
                    }
                }
            }
            // Caso as variaveis ainda não tenham sido iniciadas
            if (!variaveisNaoDefinidas.isEmpty()) {
                for (char var : variaveisNaoDefinidas)
                {
                    System.out.println("Erro: variável " + var + " não definida.");
                }
            }else if(quantidadeVar == 0)
            {
                System.out.println("Erro: operação inválida");
            }else
            {
                try
                {
                    String expressaoPosFixa = conversorExpressao.convertorPosFixa(expressao);
                    System.out.println(expressaoPosFixa);

                    var resultado_final = verificaExpressao.avaliar(expressaoPosFixa, gerenciaVariavel);
                    System.out.println(decimalFormat.format(resultado_final));

                }catch (RuntimeException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }

        }
        else if (expressao.matches("[A-Z]\\s*=\\s*\\d+(\\.\\d+)?"))
        {
            String[] partes = expressao.split("=");
            String varStr = partes[0].trim();
            char var = varStr.charAt(0);
            double valor = Double.parseDouble(partes[1].trim());

            gerenciaVariavel.definirValor(var, valor);
            System.out.println(var + " = " + decimalFormat.format(valor));

        }else if(expressao.matches("[A-Z]"))
        {
            char var = expressao.charAt(0);
            double valor = gerenciaVariavel.obterValor(var);

            if(valor == -1){
                System.out.println("Erro: variável " + var + " não definida.");
            }else{
                System.out.println("X = " + decimalFormat.format(valor));
            }

        }else
        {
            verifica_comandos(expressao);
        }
    }

    private void verifica_comandos(String comando) {
            switch (comando) {
                case "EXIT":
                    System.exit(0); // Encerra a aplicação
                    break;
                case "VARS":
                    gerenciaVariavel.listarVariaveis();
                    break;
                case "RESET":
                    gerenciaVariavel.reset();
                    break;
                case "REC":
                   // recorder.startRecording();
                    break;
                case "STOP":
                 //   recorder.stopRecording();
                    break;
                case "PLAY":
                  //  recorder.playCommands(this);
                    break;
                case "ERASE":
                  //  recorder.eraseCommands();
                    break;
                default:
                    System.out.println("Erro: comando inválido.");
            }
    }
}
