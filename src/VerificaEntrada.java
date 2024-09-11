import java.util.Arrays;

public class VerificaEntrada {
    //private static final String[] comandos = {"EXIT", "VARS", "RESET", "REC", "STOP", "PLAY", "ERASE"};
    GerenciaVariavel gerenciaVariavel = new GerenciaVariavel();

    // verifica se o input corresponde a algum dos comandos válidos
    public void is_valid(String input) {

        if (input.matches("[A-Z]\\s*=\\s*\\d+(\\.\\d+)?"))
        {
            String[] partes = input.split("=");
            String varStr = partes[0].trim();
            char var = varStr.charAt(0);
            int valor = Integer.parseInt(partes[1].trim());

            gerenciaVariavel.definirValor(var, valor);
            System.out.println(var + " = " + valor);

        }else if(input.matches("[A-Z]"))
        {

            char var = input.charAt(0);
            int valor = gerenciaVariavel.obterValor(var);
            System.out.println(var + " = " + valor);

        }else
        {
//            if (Arrays.asList(comandos).contains(input))
//            {
                verifica_comandos(input);
//            }
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
