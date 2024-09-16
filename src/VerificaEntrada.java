import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class VerificaEntrada {

    GerenciaVariavel gerenciaVariavel = new GerenciaVariavel();
    ConversorExpressao conversorExpressao = new ConversorExpressao();
    VerificaExpressao verificaExpressao = new VerificaExpressao(1024);
    Fila fila = new Fila();

    private boolean gravacao = false;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");


    // verifica se o input corresponde a algum dos comandos válidos
    public void isValid(String expressao)
    {
        if (expressao.equals("REC"))
        {
            fila.iniciarGravacao();
            gravacao = true;
        } else if (expressao.equals("STOP"))
        {
            fila.pararGravacao();
            gravacao = false;
        } else if (expressao.equals("PLAY") && !gravacao)
        {
            try
            {
                String[] comandos = fila.retornaFila();

                for(int i=0; i<comandos.length;i++)
                {
                   if(comandos[i] == null)
                   {
                       return;
                   }else
                   {
                       if(comandos[i].matches(".*[+\\-*/^].*"))
                       {
                          System.out.println(comandos[i]);
                       }
                       isValid(comandos[i]);
                   }
                }
            }catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }

        } else if (expressao.equals("ERASE"))
        {
            fila.apagar();
        } else if (gravacao)
        {
            fila.gravarComando(expressao);
        } else if (expressao.matches(".*[+\\-*/^].*"))
        {
            processarExpressaoMatematica(expressao);
        } else if (expressao.matches("[A-Z]\\s*=\\s*\\d+(\\.\\d+)?"))
        {
            definirVariavel(expressao);
        } else if (expressao.matches("[A-Z]"))
        {
            obterValorVariavel(expressao);
        } else {
            verifica_comandos(expressao);
        }
    }

    private void processarExpressaoMatematica(String expressao)
    {
        int quantidadeVar = gerenciaVariavel.quantidadeVariaveis();
        List<Character> variaveisNaoDefinidas = new ArrayList<>();

        for (char exp : expressao.toCharArray())
        {
            if (Character.isLetter(exp))
            {
                if (gerenciaVariavel.obterValor(exp) == -1)
                {
                    if (!variaveisNaoDefinidas.contains(exp))
                    {
                        variaveisNaoDefinidas.add(exp);
                    }
                }
            }
        }

        if (!variaveisNaoDefinidas.isEmpty())
        {
            for (char var : variaveisNaoDefinidas)
            {
                System.out.println("Erro: variável " + var + " não definida.");
            }
        } else if (quantidadeVar == 0)
        {
            System.out.println("Erro: operação inválida");
        } else
        {
            try
            {
                String expressaoPosFixa = conversorExpressao.convertorPosFixa(expressao);

                var resultado_final = verificaExpressao.avaliar(expressaoPosFixa, gerenciaVariavel);
                System.out.println(decimalFormat.format(resultado_final));

            } catch (RuntimeException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void definirVariavel(String expressao)
    {
        String[] partes = expressao.split("=");
        String varStr = partes[0].trim();
        char var = varStr.charAt(0);
        double valor = Double.parseDouble(partes[1].trim());

        gerenciaVariavel.definirValor(var, valor);
        System.out.println(var + " = " + decimalFormat.format(valor));
    }

    private void obterValorVariavel(String expressao)
    {
        char var = expressao.charAt(0);
        double valor = gerenciaVariavel.obterValor(var);

        if (valor == -1)
        {
            System.out.println("Erro: variável " + var + " não definida.");
        } else
        {
            System.out.println("X = " + decimalFormat.format(valor));
        }
    }

    private void verifica_comandos(String comando)
    {
        switch (comando)
        {
            case "EXIT":
                System.out.println("\nMuito obrigado por utilizar nossa calculadora!");
                System.exit(0);
                break;
            case "VARS":
                gerenciaVariavel.listarVariaveis();
                break;
            case "RESET":
                gerenciaVariavel.reset();
                break;
            default:
                System.out.println("Erro: comando inválido.");
        }
    }
}