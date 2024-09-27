/*
Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046
 */

import java.text.DecimalFormat;
// Classe para verificar expressao informada pelo usuário no input
public class VerificaEntrada {

    // Instância das classes
    GerenciaVariavel gerenciaVariavel = new GerenciaVariavel();
    ConversorExpressao conversorExpressao = new ConversorExpressao();
    VerificaExpressao verificaExpressao = new VerificaExpressao(1024);
    Fila fila = new Fila();
    Menu menu = new Menu();

    // Declaração de variáveis
    private boolean gravando = false;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");


    // verifica se o input corresponde a algum dos comandos válidos
    public void isValid(String expressao)
    {
        if (expressao.equals("REC"))
        {
            fila.iniciarGravacao();
            gravando = true;
        } else if (expressao.equals("STOP"))
        {
            fila.pararGravacao();
            gravando = false;
        } else if (expressao.equals("PLAY") && !gravando)
        {
            try
            {
                String[] comandos = fila.retornaFila();
                System.out.println("Reproduzindo gravação...");
                for(int i=0; i<comandos.length;i++)
                {
                   if(comandos[i] == null)
                   {
                       return;
                   }else
                   {
                       if(comandos[i].matches(".*[+\\-*/^%].*"))
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

        } else if (expressao.equals("ERASE") && !gravando)
        {
            fila.apagar();
        } else if (gravando)
        {
            if (!fila.isFull())
            {
                fila.gravarComando(expressao);
            } else
            {
                System.out.println("Erro: Limite de gravação atingido. Caso queira gravar novos comandos apague a fila!");
                gravando = false;
            }

        }else if (expressao.matches("[A-Z]\\s*=\\s*[-]?\\d+(\\.\\d+)?"))// Caso seja uma atribuição de variável
        {
            definirVariavel(expressao);
        }else if (expressao.matches(".*[+\\-*/^%].*")) // Caso seja uma expressao matématica
        {
            processarExpressaoMatematica(expressao);
        }
        else if (expressao.matches("[A-Z]")) // Caso queira saber o valor da variável
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

        // Caso algum operador informado seja inválido
        if (expressao.matches(".*%.*"))
        {
           System.out.println("Erro: operador inválido.");
           return;
        }

        // Caso a expressão comece ou termine com algum operador
        if (expressao.matches("^[+\\-*/].*|.*[+\\-*/]$"))
        {
            System.out.println("Erro: expressão inválida.");
            return;
        }

        // Caso a expressao seja algo como 2+2
        if (!expressao.matches("[0-9+\\-*/()a-zA-Z\\s]*") || expressao.matches(".*[a-zA-Z]{2,}.*"))
        {
            System.out.println("Erro: expressão inválida.");
            return;
        }

        // Caso exista variaveis seguidas de números, Ex: x2+x
        if (expressao.matches(".*\\d+[\\+\\-*/]\\d+.*"))
        {
            System.out.println("Erro: expressão inválida.");
            return;
        }

        // Itera sobre a expressão
        for (char exp : expressao.toCharArray())
        {
            if (Character.isLetter(exp))
            {
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
            }else if(Character.isDigit(exp))
            {
                System.out.println("Erro: expressão inválida.");
                return;
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
            System.out.println("Erro: operação inválida.");
        } else
        {
            try
            {
                // Chama as rotinas de conversão da expressao e de cálculo
                String expressaoPosFixa = conversorExpressao.convertorPosFixa(expressao);
                var resultado_final = verificaExpressao.avaliar(expressaoPosFixa, gerenciaVariavel);
                System.out.println(decimalFormat.format(resultado_final));

            } catch (RuntimeException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }

    // Define o valor da variável
    private void definirVariavel(String expressao)
    {
        String[] partes = expressao.split("=");
        String varStr = partes[0].trim();
        char var = varStr.charAt(0);
        double valor = Double.parseDouble(partes[1].trim());

        gerenciaVariavel.definirValor(var, valor);
        System.out.println(var + " = " + decimalFormat.format(valor));
    }

    // Obtem o valor da variável solicitada
    private void obterValorVariavel(String expressao)
    {
        char var = expressao.charAt(0);
        double valor = gerenciaVariavel.obterValor(var);

        if (valor == -1)
        {
            System.out.println("Erro: variável " + var + " não definida.");
        } else
        {
            System.out.println(decimalFormat.format(valor));
        }
    }

    // Verificação de outros possíveis comandos
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
            case "HELP":
                menu.exibeMenu();
                break;
            default:
                System.out.println("Erro: comando inválido.");
        }
    }
}