import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    public TabelaDeSimbolos objTabelaDeSimbolos = new TabelaDeSimbolos();
    public static ArrayList<ClassificacaoLexica> ArrayListAnaliseLexica = new ArrayList<>();

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

    boolean TokenComandoWhile(String Lexema) {
        if (Lexema.equals("while"))
            return true;
        else
            return false;
    }

    boolean TokenComandoReturn(String Lexema) {
        if (Lexema.equals("return")) {
            return true;
        } else {
            return false;
        }
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

    boolean TokenTipoComandoSCANF(String Lexema) {
        if (Lexema.equals("scanf"))
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

    boolean TokenIncremento(String Lexema) {
        if (Lexema.equals("++")) {
            return true;
        } else {
            return false;
        }
    }

    boolean TokenDecremento(String Lexema) {
        if (Lexema.equals("--")) {
            return true;
        } else {
            return false;
        }
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

    boolean TokenTipoPrintf(String Lexema) {
        if (Lexema.equals("printf"))
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

    boolean TokenAbreColchetes(String Lexema) {
        if (Lexema.equals("["))
            return true;
        else
            return false;
    }

    boolean TokenFechaColchetes(String Lexema) {
        if (Lexema.equals("]"))
            return true;
        else
            return false;
    }

    boolean TokenComandoSwitch(String Lexema) {
        if (Lexema.equals("switch"))
            return true;
        else
            return false;
    }

    boolean TokenComandoCase(String Lexema) {
        if (Lexema.equals("case"))
            return true;
        else
            return false;
    }

    boolean TokenComandoBreak(String Lexema) {
        if (Lexema.equals("break"))
            return true;
        else
            return false;
    }

    boolean TokenComandoDefault(String Lexema) {
        if (Lexema.equals("default"))
            return true;
        else
            return false;
    }

    boolean TokenComandoDo(String Lexema) {
        if (Lexema.equals("do"))
            return true;
        else
            return false;
    }

    boolean TokenComandoSystem(String Lexema) {
        if (Lexema.equals("System"))
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

    boolean TokenMenorIgual(String Lexema) {
        if (Lexema.equals("<=")) {
            return true;
        } else {
            return false;
        }
    }

    boolean TokenMaiorIgual(String Lexema) {
        if (Lexema.equals(">=")) {
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

    boolean TokenDivisao(String Lexema) {
        if (Lexema.equals("/")) {
            return true;
        } else {
            return false;
        }
    }

    boolean TokenDiferente(String Lexema) {
        if (Lexema.equals("!=")) {
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

    boolean TokenOperadorAnd(String Lexema) {
        if (Lexema.equals("&&")) {
            return true;
        } else {
            return false;
        }
    }

    boolean TokenOperadorIgual(String Lexema) {
        if (Lexema.equals("==")) {
            return true;
        } else {
            return false;
        }
    }

    boolean TokenOperadorOu(String Lexema) {
        if (Lexema.equals("||")) {
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

    boolean TokenComentario(String Lexema) {
        return Lexema.matches("//.*");
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
        if (TokenIncremento(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.INCREMENTO, linha));
            return;
        }
        if (TokenDecremento(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.DECREMENTO, linha));
            return;
        }
        if (TokenTipoChar(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.CHAR, linha));
            return;
        }
        if (TokenComandoReturn(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.RETURN, linha));
            return;
        }
        if (TokenComandoSystem(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.COMANDO_SYSTEM, linha));
            return;
        }
        if (TokenComandoSwitch(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.COMANDO_SWITCH, linha));
            return;
        }
        if (TokenComandoCase(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.COMANDO_CASE, linha));
            return;
        }
        if (TokenComandoBreak(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.COMANDO_BREAK, linha));
            return;
        }
        if (TokenComandoDefault(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.COMANDO_DEFAULT, linha));
            return;
        }
        if (TokenComandoDo(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.COMANDO_DO, linha));
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
        if (TokenDiferente(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.DIFERENTE, linha));
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
        if (TokenTipoPrintf(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.PRINTF, linha));
            return;
        }
        if (TokenTipoComandoSCANF(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.SCANF, linha));
            return;
        }
        if (TokenTipoScanf(Lexema)) {// comando scanf
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.PRINTF, linha));
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
        if (TokenComentario(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.COMENTARIO, linha));
            return;
        }
        if (TokenMenorIgual(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.MENOR_IGUAL, linha));
            return;
        }
        if (TokenMaiorIgual(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.MAIOR_IGUAL, linha));
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
        if (TokenAbreColchetes(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ABRE_COLCHETE, linha));
            return;
        }
        if (TokenFechaColchetes(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.FECHA_COLCHETE, linha));
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
        if (TokenComandoWhile(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.COMANDO_WHILE, linha));
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
        if (TokenDivisao(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.DIVISAO, linha));
            return;
        }
        if (TokenOperadorStream(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.OPERADOR_STREAM, linha));
            return;
        }
        if (TokenOperadorAnd(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.OPERADOR_AND, linha));
            return;
        }
        if (TokenOperadorIgual(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.OPERADOR_IGUAL, linha));
            return;
        }
        if (TokenOperadorOu(Lexema)) {
            ArrayListAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.OPERADOR_OU, linha));
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
        objTabelaDeSimbolos.adicionarSimbolo(Token.PRINTF, "PRINTF");
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
        objTabelaDeSimbolos.adicionarSimbolo(Token.COMENTARIO, "COMENTARIO");
        objTabelaDeSimbolos.adicionarSimbolo(Token.OPERADOR_AND, "OPERADOR AND");
        objTabelaDeSimbolos.adicionarSimbolo(Token.OPERADOR_IGUAL, "OPERADOR IGUAL");
        objTabelaDeSimbolos.adicionarSimbolo(Token.OPERADOR_OU, "OPERADOR OU");
        objTabelaDeSimbolos.adicionarSimbolo(Token.RETURN, "RETURN");
        objTabelaDeSimbolos.adicionarSimbolo(Token.ABRE_COLCHETE, "ABRE COLCHETE");
        objTabelaDeSimbolos.adicionarSimbolo(Token.FECHA_COLCHETE, "FECHA COLCHETE");
        objTabelaDeSimbolos.adicionarSimbolo(Token.SCANF, "SCANF");
        objTabelaDeSimbolos.adicionarSimbolo(Token.COMANDO_WHILE, "COMANDO WHILE");
        objTabelaDeSimbolos.adicionarSimbolo(Token.COMANDO_SWITCH, "COMANDO SWITCH");
        objTabelaDeSimbolos.adicionarSimbolo(Token.COMANDO_CASE, "COMANDO CASE");
        objTabelaDeSimbolos.adicionarSimbolo(Token.COMANDO_BREAK, "COMANDO BREAK");
        objTabelaDeSimbolos.adicionarSimbolo(Token.COMANDO_DEFAULT, "COMANDO DEFAULT");
        objTabelaDeSimbolos.adicionarSimbolo(Token.COMANDO_DO, "COMANDO DO");
        objTabelaDeSimbolos.adicionarSimbolo(Token.COMANDO_SYSTEM, "COMANDO SYSTEM");
        objTabelaDeSimbolos.adicionarSimbolo(Token.MENOR_IGUAL, "MENOR IGUAL");
        objTabelaDeSimbolos.adicionarSimbolo(Token.MAIOR_IGUAL, "MAIOR IGUAL");
        objTabelaDeSimbolos.adicionarSimbolo(Token.DIVISAO, "DIVISAO");
        objTabelaDeSimbolos.adicionarSimbolo(Token.DIFERENTE, "DIFERENTE");
        objTabelaDeSimbolos.adicionarSimbolo(Token.INCREMENTO, "INCREMENTO");
        objTabelaDeSimbolos.adicionarSimbolo(Token.DECREMENTO, "DECREMENTO");
    }

    boolean GerarAnaliseLexica() {
        boolean ResultadoAnaliseLexica = true;
        // varrer todo o ArrayListAnaliseLexica
        // Classificar os lexemas da linha lida do arquivo
        for (ClassificacaoLexica obj : ArrayListAnaliseLexica) {
            String Lexema = obj.Lexema;
            int linha = obj.Linha;
            int codigoToken = obj.Token;
            if (obj.Token == Token.ERRO_DESCONHECIDO) {

                ResultadoAnaliseLexica = false;
            }

            // mostrar a classificacao lexica na tela
            System.out.println("Linha: " + linha + " | Lexema: " + Lexema +
                    " | " + objTabelaDeSimbolos.buscarValor(codigoToken) + " | Token: " + codigoToken);
        }
        return ResultadoAnaliseLexica; // TODOS OS LEXEMAS FORAM RECONHECIDOS
    }

    boolean AnalisadorLexico(File selectedFile) throws FileNotFoundException {
        // criar a tabela de simbolos
        GerarTabelaDeSimbolos();

        // abrir o arquivo selecionado
        Scanner LeituraArquivo = new Scanner(selectedFile);
        String TextoArquivoAnalisado;

        System.out.println("\n\n**********ANALISE LEXICA**********\n\n");

        // Expressão regular para identificar strings, operadores, números, etc.
        // Pattern p = Pattern.compile(
        // "//.*" + // Comentários de linha primeiro
        // "|" +
        // "\"(?:\\\\.|[^\"])*\"" + // Strings
        // "|" +
        // "'(?:\\\\.|[^\\\\'])'" + // Char
        // "|" +
        // ">>|<<|==|!=|<=|>=|&&|\\|\\||" + // Operadores compostos
        // "[\\[\\]{}(),;:+\\-*/%<>=&]" + // Símbolos
        // "|" +
        // "[A-Za-z_][A-Za-z0-9_]*" + // Identificadores
        // "|" +
        // "-?\\d+\\.\\d+|-?\\d+" // Números
        // );

        Pattern p = Pattern.compile(
                "//.*" + // Comentários
                        "|" + "\"(?:\\\\.|[^\"])*\"" + // Strings
                        "|" + "'(?:\\\\.|[^\\\\'])'" + // Chars
                        "|" + "\\+\\+|--|>>|<<|==|!=|<=|>=|&&|\\|\\|" + // <- Corrigido: inclui ++ e --
                        "|" + "[\\[\\]{}(),;:+\\-*/%<>=&]" + // Símbolos simples
                        "|" + "[A-Za-z_][A-Za-z0-9_]*" + // Identificadores
                        "|" + "-?\\d+\\.\\d+|-?\\d+" // Números
        );

        // Começar a leitura das linhas
        int linha = 1;
        while (LeituraArquivo.hasNextLine()) {
            TextoArquivoAnalisado = LeituraArquivo.nextLine();

            // Se a linha contém comentário
            int posComentario = TextoArquivoAnalisado.indexOf("//"); // indexOf("//") é usado para detectar comentários
                                                                     // de linha. Se encontrar //
                                                                     // você sabe que o restante da linha é um
                                                                     // comentário, e pode separá-lo do código anterior.

            if (posComentario != -1) { // Se o conteúdo buscado não for encontrado, ele retorna -1.
                String antesDoComentario = TextoArquivoAnalisado.substring(0, posComentario); // substring retorna uma
                                                                                              // parte da string
                String comentario = TextoArquivoAnalisado.substring(posComentario); // inclui "//"

                // Analisa apenas o código antes do comentário
                Matcher m1 = p.matcher(antesDoComentario);
                while (m1.find()) {
                    String Lexema = m1.group(); // group Retorna o conteúdo que corresponde ao padrão encontrado.
                    if (!Lexema.trim().isEmpty()) { // trim remove espaços em branco do início e do fim da string.
                        classificaLemexa(Lexema, linha);
                    }
                }

                // Trata o comentário inteiro como um único lexema
                classificaLemexa(comentario, linha);
            } else {
                // Linha sem comentário: processa normalmente
                Matcher m1 = p.matcher(TextoArquivoAnalisado);
                while (m1.find()) {
                    String Lexema = m1.group();
                    if (!Lexema.trim().isEmpty()) {
                        classificaLemexa(Lexema, linha);
                    }
                }
            }

            // Incrementa número da linha
            linha++;
        }

        LeituraArquivo.close(); // Boas práticas: fechar scanner

        // mostrar a analise lexica na tela e gerar um arquivo com a classificacao dos
        // lexemas
        return GerarAnaliseLexica();
    }

}