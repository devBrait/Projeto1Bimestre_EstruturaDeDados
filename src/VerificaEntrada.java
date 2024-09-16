/*
Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046
 */

import java.text.DecimalFormat;

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

        } else if (expressao.equals("ERASE") && !gravacao)
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

    private void processarExpressaoMatematica(String expressao) {
        int quantidadeVar = gerenciaVariavel.quantidadeVariaveis();
        char[] variaveisNaoDefinidas = new char[100];
        int countVariaveisNaoDefinidas = 0;

        // Itera sobre a expressão
        for (char exp : expressao.toCharArray()) {
            if (Character.isLetter(exp)) {
                if (gerenciaVariavel.obterValor(exp) == -1)
                {
                    // Verifica se a variável já foi adicionada
                    boolean jaAdicionada = false;
                    for (int i = 0; i < countVariaveisNaoDefinidas; i++)
                    {
                        if (variaveisNaoDefinidas[i] == exp)
                        {
                            jaAdicionada = true;
                            break;
                        }
                    }

                    // Adiciona a variável se não estiver presente
                    if (!jaAdicionada)
                    {
                        variaveisNaoDefinidas[countVariaveisNaoDefinidas++] = exp;
                    }
                }
            }
        }

        // Verifica se há variáveis não definidas
        if (countVariaveisNaoDefinidas > 0)
        {
            for (int i = 0; i < countVariaveisNaoDefinidas; i++)
            {
                System.out.println("Erro: variável " + variaveisNaoDefinidas[i] + " não definida.");
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