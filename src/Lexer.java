import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    public TabelaDeSimbolos objTabelaDeSimbolos = new TabelaDeSimbolos();
    public ArrayList<ClassificacaoLexica> ArrayListAnaliseLexica = new ArrayList<>();

    boolean TokenVOID(String Lexema) {
        if (Lexema.equals("void"))
            return true;
        else
            return false;
    }

    boolean TokenMain(String Lexema) {
        if (Lexema.equals("main"))
            return true;
        else
            return false;
    }

    boolean TokenTipoComandoFor(String Lexema) {
        if (Lexema.equals("for"))
            return true;
        else
            return false;
    }

    boolean TokenNumeroDecimal(String Lexema) {
        // aceita opcionalmente sinal -, dígitos, ponto, dígitos
        Lexema = Lexema.trim();
        return Lexema.matches("-?\\d+\\.\\d+");
    }

    boolean TokenTipoComandoIf(String Lexema) {
        if (Lexema.equals("if")) {
            return true;
        } else {
            return false;
        }
    }

    boolean TokenTipoComandoElse(String Lexema) {
        if (Lexema.equals("else"))
            return true;
        else
            return false;
    }

    boolean TokenTipoInteiro(String Lexema) {
        if (Lexema.equals("int"))
            return true;
        else
            return false;
    }

    boolean TokenTipoChar(String Lexema) {
        if (Lexema.equals("char")) {
            return true;
        } else {
            return false;

        }

    }

    boolean TokenTipoFloat(String Lexema) {
        // if (Lexema.matches("\\d+\\.\\d+")) { // Pelo menos um ponto decimal pois se
        // usasse apenas \\d+ qualquer numero
        // // viraria float com um parse!
        // // Java verifica se a string inteira corresponde ao padrão fornecido pela
        // // expressão regular (regex).
        // return true;
        // } else {
        // return false;
        // }
        if (Lexema.equals("float"))
            return true;
        else
            return false;
    }

    boolean TokenTipoPrintln(String Lexema) {
        if (Lexema.equals("println"))
            return true;
        else
            return false;
    }

    boolean TokenTipoScanf(String Lexema) {
        if (Lexema.equals("scanf"))
            return true;
        else
            return false;
    }

    boolean TokenTipoAtribuicao(String Lexema) {
        if (Lexema.equals("="))
            return true;
        else
            return false;
    }

    boolean TokenPontoVirgula(String Lexema) {
        if (Lexema.equals(";"))
            return true;
        else
            return false;
    }

    boolean TokenAbreChave(String Lexema) {
        if (Lexema.equals("{"))
            return true;
        else
            return false;
    }

    boolean TokenFechaChave(String Lexema) {
        if (Lexema.equals("}"))
            return true;
        else
            return false;
    }

    boolean TokenAbreParenteses(String Lexema) {
        if (Lexema.equals("("))
            return true;
        else
            return false;
    }

    boolean TokenFechaParenteses(String Lexema) {
        if (Lexema.equals(")"))
            return true;
        else
            return false;
    }

    boolean TokenAdicao(String Lexema) {
        if (Lexema.equals("+"))
            return true;
        else
            return false;
    }

    boolean TokenVirgula(String Lexema) {
        if (Lexema.equals(","))
            return true;
        else
            return false;
    }

    boolean EhDigito(char caracter) {
        if ((caracter == '0') || (caracter == '1') || (caracter == '2') || (caracter == '3') || (caracter == '4')
                || (caracter == '5') || (caracter == '6') || (caracter == '7') || (caracter == '8')
                || (caracter == '9') == false)
            return true;
        else
            return false;
    }

    boolean EhDigito_(char str) {
        // verifica se a str é uma caracter de 0 a 9
        if (Character.isDigit(str)) {
            return true;
        } else {
            return false;
        }
    }

    boolean EhLetra(char str) {
        // verifica se a str é uma letra de 'A' a 'Z' ou 'a' a 'z'
        if (Character.isLetter(str)) {
            return true;
        } else {
            return false;
        }
    }

    boolean TokenNumeroInteiro(String Lexema) {
        // int tamanho = Lexema.length();
        // // verifica se o lexema é um número inteiro
        // for (int i = 0; i < tamanho; i++) {
        // char pedacoLexema = Lexema.charAt(i);
        // if (EhDigito_(pedacoLexema) == false) {
        // return false;
        // }
        // }
        // return true;
        Lexema = Lexema.trim();
        return Lexema.matches("-?\\d+");

    }

    boolean TokenComandoCOUT(String Lexema) {
        if (Lexema.equals("cout"))
            return true;
        else
            return false;
    }

    boolean TokenComandoCIN(String Lexema) {
        if (Lexema.equals("cin"))
            return true;
        else
            return false;
    }

    boolean TokenMultiplicacao(String Lexema) {
        if (Lexema.equals("*")) {
            return true;
        } else {
            return false;
        }
    }

    boolean TokenOperadorStream(String Lexema) {
        if (Lexema.equals("<<") || Lexema.equals(">>")) {
            return true;
        } else {
            return false;
        }
    }

    boolean TokenString(String Lexema) {
        // qualquer sequência (inclusive vazia) de caracteres que não sejam " entre
        // aspas duplas
        return Lexema.matches("\"[^\"]*\"");
    }

    boolean TokenDoisPontos(String Lexema) {
        if (Lexema.equals(":")) {
            return true;
        } else {
            return false;
        }
    }

    boolean TokenAspas(String Lexema) {
        if (Lexema.equals("\"")) {
            return true;
        } else {
            return false;
        }
    }

    boolean TokenSubtracao(String Lexema) {
        if (Lexema.equals("-")) {
            return true;
        } else {
            return false;
        }
    }

    boolean TokenMenor(String Lexema) {
        if (Lexema.equals("<")) {
            return true;
        } else {
            return false;
        }
    }

    boolean TokenConstanteChar(String Lexema) {
        return Lexema.matches("'[^']'");
    }

    boolean TokenMaior(String Lexema) {
        if (Lexema.equals(">")) {
            return true;
        } else {
            return false;
        }
    }

    boolean TokenEComercial(String Lexema) {
        return Lexema.matches("&");
    }

    boolean TokenVariavel(String Lexema) {
        int tamanho = Lexema.length();
        // como em qualquer lugar de uma variavel ou lexema nao pode conter virugla
        // entao:
        if (Lexema.contains(",") || Lexema.contains("cin") || Lexema.contains("cout") || Lexema.contains("char")
                || Lexema.contains("float") || Lexema.contains("int") || Lexema.contains("void")
                || Lexema.contains("if") || Lexema.contains("else") || Lexema.contains("for")) {
            return false;
        }
        // verifica se o lexema é uma variável
        for (int i = 0; i < tamanho; i++) {
            char pedacoLexema = Lexema.charAt(i);

            if (i == 0)// primeiro caracter
            {
                if (((pedacoLexema == '_') || (EhLetra(pedacoLexema))) == false) {
                    return false;
                } else if ((EhLetra(pedacoLexema) || EhDigito(pedacoLexema) || (pedacoLexema == '_')
                        || pedacoLexema == ',') == false) {
                    return false;
                }
            }
        }
        return true;
    }

    void classificaLemexa(String Lexema, int linha) {
        if (TokenVOID(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.VOID, linha));
            return;
        }
        if (TokenMain(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.MAIN, linha));
            return;
        }
        if (TokenConstanteChar(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.CONSTANTE_CHAR, linha));
            return;
        }
        if (TokenTipoChar(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.CHAR, linha));
            return;
        }
        if (TokenNumeroDecimal(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.DECIMAL, linha));
            return;
        }
        if (TokenTipoInteiro(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.INT, linha));
            return;
        }
        if (TokenTipoFloat(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.FLOAT, linha));
            return;
        }
        if (TokenTipoPrintln(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.PRINTLN, linha));
            return;
        }
        if (TokenTipoScanf(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.SCANF, linha));
            return;
        }
        if (TokenTipoAtribuicao(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ATRIBUICAO, linha));
            return;
        }
        if (TokenPontoVirgula(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.PONTO_VIRGULA, linha));
            return;
        }
        if (TokenAbreChave(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ABRE_CHAVE, linha));
            return;
        }
        if (TokenFechaChave(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.FECHA_CHAVE, linha));
            return;
        }
        if (TokenAbreParenteses(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ABRE_PARENTES, linha));
            return;
        }
        if (TokenFechaParenteses(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.FECHA_PARENTES, linha));
            return;
        }
        if (TokenAdicao(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ADICAO, linha));
            return;
        }
        if (TokenVirgula(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.VIRGULA, linha));
            return;
        }
        if (TokenNumeroInteiro(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.NUMERO_INTEIRO, linha));
            return;
        }
        if (TokenTipoComandoFor(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.COMANDO_FOR, linha));
            return;
        }
        if (TokenTipoComandoIf(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.COMANDO_IF, linha));
            return;
        }
        if (TokenTipoComandoElse(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.COMANDO_ELSE, linha));
            return;
        }
        if (TokenMultiplicacao(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.MULTIPLICACAO, linha));
            return;
        }
        if (TokenOperadorStream(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.OPERADOR_STREAM, linha));
            return;
        }
        if (TokenDoisPontos(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.DOIS_PONTOS, linha));
            return;
        }
        if (TokenAspas(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ASPAS, linha));
            return;
        }
        if (TokenSubtracao(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.SUBTRACAO, linha));
            return;
        }
        if (TokenMenor(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.MENOR, linha));
            return;
        }
        if (TokenMaior(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.MAIOR, linha));
            return;
        }
        if (TokenComandoCIN(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.COMANDO_CIN, linha));
            return;
        }
        if (TokenComandoCOUT(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.COMANDO_COUT, linha));
            return;
        }
        if (TokenEComercial(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.E_COMERCIAL, linha));
            return;
        }
        if (TokenVariavel(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.VARIAVEL, linha));
            return;
        }
        if (TokenString(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.STRING, linha));
            return;
        } else {
            // se nao conseguiu classificar em nenhum dos tokens, mostra a mensagem de Token
            // nao reconhecido
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ERRO_DESCONHECIDO, linha));
            return;
        }
    }

    void GerarTabelaDeSimbolos() {
        objTabelaDeSimbolos.adicionarSimbolo(Token.VOID, "COMANDO VOID");
        objTabelaDeSimbolos.adicionarSimbolo(Token.MAIN, "COMANDO MAIN");
        objTabelaDeSimbolos.adicionarSimbolo(Token.INT, "COMANDO INT");
        objTabelaDeSimbolos.adicionarSimbolo(Token.CHAR, "COMANDO CHAR");
        objTabelaDeSimbolos.adicionarSimbolo(Token.FLOAT, "COMANDO FLOAT");
        objTabelaDeSimbolos.adicionarSimbolo(Token.PRINTLN, "COMANDO PRINTLN");
        objTabelaDeSimbolos.adicionarSimbolo(Token.SCANF, "COMANDO SCANF");
        objTabelaDeSimbolos.adicionarSimbolo(Token.ATRIBUICAO, "ATRIBUICAO");
        objTabelaDeSimbolos.adicionarSimbolo(Token.PONTO_VIRGULA, "PONTO_VIRGULA");
        objTabelaDeSimbolos.adicionarSimbolo(Token.ABRE_CHAVE, "ABRE_CHAVE");
        objTabelaDeSimbolos.adicionarSimbolo(Token.FECHA_CHAVE, "FECHA_CHAVE");
        objTabelaDeSimbolos.adicionarSimbolo(Token.ABRE_PARENTES, "ABRE_PARENTES");
        objTabelaDeSimbolos.adicionarSimbolo(Token.FECHA_PARENTES, "FECHA_PARENTES");
        objTabelaDeSimbolos.adicionarSimbolo(Token.ADICAO, "ADICAO");
        objTabelaDeSimbolos.adicionarSimbolo(Token.VIRGULA, "VIRGULA");
        objTabelaDeSimbolos.adicionarSimbolo(Token.NUMERO_INTEIRO, "NUMERO_INTEIRO");
        objTabelaDeSimbolos.adicionarSimbolo(Token.VARIAVEL, "VARIAVEL");
        objTabelaDeSimbolos.adicionarSimbolo(Token.ERRO_DESCONHECIDO, "ERRO_DESCONHECIDO");
        objTabelaDeSimbolos.adicionarSimbolo(Token.STRING, "STRING");
        objTabelaDeSimbolos.adicionarSimbolo(Token.MENOR, "MENOR");
        objTabelaDeSimbolos.adicionarSimbolo(Token.MAIOR, "MAIOR");
        objTabelaDeSimbolos.adicionarSimbolo(Token.COMANDO_FOR, "COMANDO FOR");
        objTabelaDeSimbolos.adicionarSimbolo(Token.COMANDO_IF, "COMANDO IF");
        objTabelaDeSimbolos.adicionarSimbolo(Token.COMANDO_ELSE, "COMANDO ELSE");
        objTabelaDeSimbolos.adicionarSimbolo(Token.MULTIPLICACAO, "MULTIPLICACAO");
        objTabelaDeSimbolos.adicionarSimbolo(Token.OPERADOR_STREAM, "OPERADOR STREAM");
        objTabelaDeSimbolos.adicionarSimbolo(Token.DOIS_PONTOS, "DOIS PONTOS");
        objTabelaDeSimbolos.adicionarSimbolo(Token.ASPAS, "ASPAS");
        objTabelaDeSimbolos.adicionarSimbolo(Token.SUBTRACAO, "SUBTRACAO");
        objTabelaDeSimbolos.adicionarSimbolo(Token.COMANDO_COUT, "COMANDO COUT");
        objTabelaDeSimbolos.adicionarSimbolo(Token.COMANDO_CIN, "COMANDO CIN");
        objTabelaDeSimbolos.adicionarSimbolo(Token.CONSTANTE_CHAR, "CONSTANTE CHAR");
        objTabelaDeSimbolos.adicionarSimbolo(Token.E_COMERCIAL, "E_COMERCIAL");
        objTabelaDeSimbolos.adicionarSimbolo(Token.DECIMAL, "DECIMAL");

    }

    void GerarAnaliseLexica() {
        // varrer todo o arraylist ArrayListAnaliseLexica
        // classificar os lexemas da linha lida do arquivo
        for (ClassificacaoLexica obj : ArrayListAnaliseLexica) {
            String Lexema = obj.Lexema;
            int linha = obj.Linha;
            int codigoToken = obj.Token;
            // String simbolo = objTabelaDeSimbolos.buscarValor(codigoToken);

            // mostrar a classificacao lexica na tela
            System.out.println("Linha: " + linha + " | Lexema: " + Lexema + // " | Simbolo: " + simbolo
                    " | " + objTabelaDeSimbolos.buscarValor(codigoToken) + " | Token: " + codigoToken);
        }

    }

    void AnalisadorLexico(File selectedFile) throws FileNotFoundException {
        // criar a tabela de simbolos
        GerarTabelaDeSimbolos();

        // abrir o arquivo selecionado
        Scanner LeituraArquivo = new Scanner(selectedFile);
        String TextoArquivoAnalisado;

        System.out.println("\n\n**********ANALISE LEXICA**********\n\n");

        // comecar a leitura de cada uma das linhas
        int linha = 1;
        while (LeituraArquivo.hasNextLine()) {
            TextoArquivoAnalisado = LeituraArquivo.nextLine();

            // Expressão regular para identificar strings entre aspas e palavras
            Pattern p = Pattern.compile(
                    // 1) Literais de string: "abc"
                    "\"(?:\\\\.|[^\"])*\"" +
                            "|" +
                            // 2) Literais de char: 'c'
                            "'(?:\\\\.|[^\\\\'])'" +
                            "|" +
                            // 3) Operadores compostos (>> << == != <= >= && ||) e símbolos únicos
                            ">>|<<|==|!=|<=|>=|&&|\\|\\||" +
                            "[\\[\\]{}(),;:+\\-*/%<>=&]" +
                            "|" +
                            // 4) Identificadores (letra ou _ seguido de alfanuméricos/_)
                            "[A-Za-z_][A-Za-z0-9_]*" +
                            "|" +
                            // 5) Números (inteiros, decimais, negativos)
                            "-?\\d+\\.\\d+|-?\\d+");

            Matcher m = p.matcher(TextoArquivoAnalisado);

            // classificar os lexemas da linha lida do arquivo
            while (m.find()) {
                String Lexema = m.group();
                if (!Lexema.trim().isEmpty()) { // Evitar lexemas vazios
                    classificaLemexa(Lexema, linha);
                }
            }

            // incrementar o numero da linha
            linha++;
        }

        // fechar a leitura do arquivo
        LeituraArquivo.close();

        // mostrar a analise lexica na tela e gerar um arquivo com a classificacao dos
        // lexemas
        GerarAnaliseLexica();
    }

}