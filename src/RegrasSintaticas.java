import java.util.ArrayList;

public class RegrasSintaticas {

    // o size do vetor é a quantidade de tokens dentro desse bloco

    boolean INICIO(ArrayList<ClassificacaoLexica> vetorAnaliseLexica) {
        /*-------- REGRA SINTATICA --------
        
        INICIO -> VOID MAIN ABRE_PARENTES FECHA_PARENTES ABRE_CHAVE PROGRAMA
        FECHA_CHAVE
        
        */

        int Tamanho = vetorAnaliseLexica.size();
        if ((vetorAnaliseLexica.get(0).Token == Token.INT) &&
                (vetorAnaliseLexica.get(1).Token == Token.MAIN) &&
                (vetorAnaliseLexica.get(2).Token == Token.ABRE_PARENTES) &&
                (vetorAnaliseLexica.get(3).Token == Token.FECHA_PARENTES) &&
                (vetorAnaliseLexica.get(4).Token == Token.ABRE_CHAVE) &&
                (vetorAnaliseLexica.get(Tamanho - 1).Token == Token.FECHA_CHAVE))
            return true;
        else
            return false;
    }

    // esse printf estava funcionando pro codigo 1 e 2
    // public boolean COMANDO_PRINTF(ArrayList<ClassificacaoLexica> v) {
    // int n = v.size();
    // // mínimo: printf("texto");
    // if (n < 5)
    // return false;
    // // checa começo e fim
    // if (v.get(0).Token != Token.PRINTF)
    // return false;
    // if (v.get(1).Token != Token.ABRE_PARENTES)
    // return false;
    // if (v.get(2).Token != Token.STRING)
    // return false;
    // if (v.get(n - 2).Token != Token.FECHA_PARENTES)
    // return false;
    // if (v.get(n - 1).Token != Token.PONTO_VIRGULA)
    // return false;
    // // agora verifica os grupos opcionais: , VARIAVEL
    // // eles devem estar em posições 3,4 (começando em 3) até n-3
    // for (int i = 3; i < n - 2; i += 2) {
    // if (v.get(i).Token != Token.VIRGULA)
    // return false;
    // if (v.get(i + 1).Token != Token.VARIAVEL)
    // return false;
    // }
    // return true;
    // }

    boolean COMANDO_PRINTF(ArrayList<ClassificacaoLexica> v) {
        // mínimo 5 tokens: printf ( STRING ) ;
        if (v.size() < 5)
            return false;

        if (v.get(0).Token != Token.PRINTF)
            return false;
        if (v.get(1).Token != Token.ABRE_PARENTES)
            return false;
        if (v.get(2).Token != Token.STRING)
            return false;
        if (v.get(v.size() - 2).Token != Token.FECHA_PARENTES)
            return false;
        if (v.get(v.size() - 1).Token != Token.PONTO_VIRGULA)
            return false;

        // Se só tem esses 5 tokens, é um printf simples com só string
        if (v.size() == 5)
            return true;

        // Agora trata a lista de argumentos entre índice 3 e penúltimo - 2
        // deve começar com uma vírgula
        if (v.get(3).Token != Token.VIRGULA)
            return false;

        // Percorre os tokens de argumentos
        // índices para os argumentos: 4 até v.size()-3
        // A estrutura geral:
        // VARIAVEL [ ABRE_COLCHETE VARIAVEL FECHA_COLCHETE ]* (para cada argumento)
        // Seguido possivelmente de vírgula (exceto o último argumento)

        int i = 4;
        while (i < v.size() - 2) {
            // Espera um argumento — começa com VARIAVEL
            if (v.get(i).Token != Token.VARIAVEL)
                return false;
            i++;

            // Pode ter zero ou mais índices [ VARIAVEL ]
            while (i + 2 < v.size() - 2 &&
                    v.get(i).Token == Token.ABRE_COLCHETE &&
                    v.get(i + 1).Token == Token.VARIAVEL &&
                    v.get(i + 2).Token == Token.FECHA_COLCHETE) {
                i += 3;
            }

            // Depois do argumento pode ter uma vírgula, se não for o último argumento
            if (i == v.size() - 2) {
                // chegou no FECHA_PARENTES, ok
                break;
            }

            // Se não for o último argumento, deve ser vírgula
            if (v.get(i).Token != Token.VIRGULA)
                return false;
            i++;
        }

        // Se percorreu tudo sem problemas, é válido
        return true;
    }

    boolean COMANDO_SCANF(ArrayList<ClassificacaoLexica> v) {
        // scanf ( "%d" , & x ) ;
        if (v.size() == 8 &&
                v.get(0).Token == Token.SCANF &&
                v.get(1).Token == Token.ABRE_PARENTES &&
                v.get(2).Token == Token.STRING &&
                v.get(3).Token == Token.VIRGULA &&
                v.get(4).Token == Token.E_COMERCIAL &&
                v.get(5).Token == Token.VARIAVEL &&
                v.get(6).Token == Token.FECHA_PARENTES &&
                v.get(7).Token == Token.PONTO_VIRGULA) {
            return true;
        }

        // scanf ( "%d" , & vetor [ i ] ) ;
        if (v.size() == 11 &&
                v.get(0).Token == Token.SCANF &&
                v.get(1).Token == Token.ABRE_PARENTES &&
                v.get(2).Token == Token.STRING &&
                v.get(3).Token == Token.VIRGULA &&
                v.get(4).Token == Token.E_COMERCIAL &&
                v.get(5).Token == Token.VARIAVEL &&
                v.get(6).Token == Token.ABRE_COLCHETE &&
                v.get(7).Token == Token.VARIAVEL &&
                v.get(8).Token == Token.FECHA_COLCHETE &&
                v.get(9).Token == Token.FECHA_PARENTES &&
                v.get(10).Token == Token.PONTO_VIRGULA) {
            return true;
        }

        // scanf ( "%d" , & matriz [ i ] [ j ] ) ;
        if (v.size() == 14 &&
                v.get(0).Token == Token.SCANF &&
                v.get(1).Token == Token.ABRE_PARENTES &&
                v.get(2).Token == Token.STRING &&
                v.get(3).Token == Token.VIRGULA &&
                v.get(4).Token == Token.E_COMERCIAL &&
                v.get(5).Token == Token.VARIAVEL &&
                v.get(6).Token == Token.ABRE_COLCHETE &&
                v.get(7).Token == Token.VARIAVEL &&
                v.get(8).Token == Token.FECHA_COLCHETE &&
                v.get(9).Token == Token.ABRE_COLCHETE &&
                v.get(10).Token == Token.VARIAVEL &&
                v.get(11).Token == Token.FECHA_COLCHETE &&
                v.get(12).Token == Token.FECHA_PARENTES &&
                v.get(13).Token == Token.PONTO_VIRGULA) {
            return true;
        }

        return false;
    }

    // 1) if (condição)
    boolean COMANDO_IF(ArrayList<ClassificacaoLexica> v) {
        if (v.size() < 4)
            return false;
        if (v.get(0).Token != Token.COMANDO_IF)
            return false;
        if (v.get(1).Token != Token.ABRE_PARENTES)
            return false;
        // procurar o FECHA_PARENTES no fim
        if (v.get(v.size() - 1).Token != Token.FECHA_PARENTES)
            return false;
        // tudo entre 2 e size-2 é parte da condição (aceitamos libermente aqui)
        return true;
    }

    // 2) else if (condição)
    boolean COMANDO_ELSE_IF(ArrayList<ClassificacaoLexica> v) {
        if (v.size() < 6)
            return false;
        if (v.get(0).Token != Token.COMANDO_ELSE)
            return false;
        if (v.get(1).Token != Token.COMANDO_IF)
            return false;
        if (v.get(2).Token != Token.ABRE_PARENTES)
            return false;
        if (v.get(v.size() - 1).Token != Token.FECHA_PARENTES)
            return false;
        return true;
    }

    // 3) else (sem if)
    boolean COMANDO_ELSE(ArrayList<ClassificacaoLexica> v) {
        // pode ser só 'else' ou 'else {'
        if (v.size() == 1 && v.get(0).Token == Token.COMANDO_ELSE) {
            return true;
        }
        if (v.size() == 2
                && v.get(0).Token == Token.COMANDO_ELSE
                && v.get(1).Token == Token.ABRE_CHAVE) {
            return true;
        }
        return false;
    }

    boolean DECLARACAO_VARIAVEL(ArrayList<ClassificacaoLexica> v) {
        /*
         * DECLARACAO_VARIAVEL → TIPO VARIAVEL (VIRGULA VARIAVEL)* PONTO_VIRGULA
         * TIPO: INT | FLOAT | CHAR
         */

        int n = v.size();
        if (n < 3)
            return false; // precisa no mínimo: TIPO VARIAVEL ;

        int tTipo = v.get(0).Token;
        if (!(tTipo == Token.INT || tTipo == Token.FLOAT || tTipo == Token.CHAR)) {
            return false;
        }

        // depois do tipo, precisa de pelo menos um VARIAVEL
        if (v.get(1).Token != Token.VARIAVEL)
            return false;

        int i = 2;

        // parseia pares (VIRGULA VARIAVEL) opcionais
        while (i < n - 1) {
            if (v.get(i).Token != Token.VIRGULA)
                return false;
            i++;
            if (i >= n - 1)
                return false;
            if (v.get(i).Token != Token.VARIAVEL)
                return false;
            i++;
        }

        // último token deve ser PONTO_VIRGULA
        if (v.get(n - 1).Token != Token.PONTO_VIRGULA)
            return false;

        return true;
    }

    boolean ATRIBUICAO_DE_VARIAVEL(ArrayList<ClassificacaoLexica> v) {
        /*
         * ATRIBUICAO_DE_VARIAVEL → VARIAVEL ATRIBUICAO EXPRESSAO PONTO_VIRGULA
         * EXPRESSAO → qualquer sequência válida entre ATRIBUICAO e PONTO_VIRGULA
         */

        if (v.size() < 4)
            return false;

        // Verifica início: VARIAVEL =
        if (v.get(0).Token != Token.VARIAVEL)
            return false;

        if (v.get(1).Token != Token.ATRIBUICAO)
            return false;

        // Verifica fim: ;
        if (v.get(v.size() - 1).Token != Token.PONTO_VIRGULA)
            return false;

        // Verifica se os tokens entre v[2] e v[n-2] formam uma expressão válida
        for (int i = 2; i < v.size() - 1; i++) {
            int token = v.get(i).Token;

            if (!(token == Token.VARIAVEL ||
                    token == Token.NUMERO_INTEIRO ||
                    token == Token.DECIMAL ||
                    token == Token.CONSTANTE_CHAR ||
                    token == Token.STRING ||
                    token == Token.ABRE_PARENTES ||
                    token == Token.FECHA_PARENTES ||
                    token == Token.ADICAO ||
                    token == Token.SUBTRACAO ||
                    token == Token.MULTIPLICACAO ||
                    token == Token.DIVISAO ||
                    token == Token.MAIOR ||
                    token == Token.MENOR ||
                    token == Token.MAIOR_IGUAL ||
                    token == Token.MENOR_IGUAL ||
                    token == Token.OPERADOR_IGUAL ||
                    token == Token.DIFERENTE ||
                    token == Token.OPERADOR_AND ||
                    token == Token.OPERADOR_OU ||
                    token == Token.VIRGULA)) {
                return false; // Token inválido na expressão
            }
        }

        return true;
    }

    boolean ESTRUTURA_DE_REPETICAO(ArrayList<ClassificacaoLexica> v) {
        if (v.size() != 13) // Corrigir também o tamanho: são 13 tokens!
            return false;

        return v.get(0).Token == Token.COMANDO_FOR &&
                v.get(1).Token == Token.ABRE_PARENTES &&
                v.get(2).Token == Token.VARIAVEL &&
                v.get(3).Token == Token.ATRIBUICAO &&
                v.get(4).Token == Token.NUMERO_INTEIRO &&
                v.get(5).Token == Token.PONTO_VIRGULA &&
                v.get(6).Token == Token.VARIAVEL &&
                (v.get(7).Token == Token.MENOR || v.get(7).Token == Token.MAIOR ||
                        v.get(7).Token == Token.MENOR_IGUAL || v.get(7).Token == Token.MAIOR_IGUAL)
                &&
                v.get(8).Token == Token.NUMERO_INTEIRO &&
                v.get(9).Token == Token.PONTO_VIRGULA &&
                v.get(10).Token == Token.VARIAVEL &&
                (v.get(11).Token == Token.INCREMENTO || v.get(11).Token == Token.DECREMENTO) &&
                v.get(12).Token == Token.FECHA_PARENTES;
    }

    boolean COMANDO_DO(ArrayList<ClassificacaoLexica> v) {
        /*
         * COMANDO_DO → COMANDO_DO
         * (linha contendo apenas a palavra-chave 'do')
         */
        return v.size() == 1
                && v.get(0).Token == Token.COMANDO_DO;
    }

    boolean DECLARACAO_DE_VETOR(ArrayList<ClassificacaoLexica> v) {
        /*
         * DECLARACAO_DE_VETOR → TIPO VARIAVEL [ NUMERO ] ... [ NUMERO ] ;
         * Exemplo válido: int matriz[3][3];
         */

        if (v.size() < 6)
            return false;

        // Primeiro token deve ser um tipo
        int t0 = v.get(0).Token;
        if (!(t0 == Token.INT || t0 == Token.FLOAT || t0 == Token.CHAR))
            return false;

        // Segundo token deve ser uma variável
        if (v.get(1).Token != Token.VARIAVEL)
            return false;

        // Depois disso, esperamos pares: [ NUMERO ]
        int i = 2;
        int tamanho = v.size();

        while (i + 2 < tamanho - 1) {
            if (v.get(i).Token != Token.ABRE_COLCHETE)
                return false;
            if (v.get(i + 1).Token != Token.NUMERO_INTEIRO) // ou se quiser reconhecer DECIMAL, mude aqui ->
                                                            // Token.VARIAVEL
                return false;
            if (v.get(i + 2).Token != Token.FECHA_COLCHETE)
                return false;
            i += 3;
        }

        // Último token deve ser ponto e vírgula
        if (i != tamanho - 1)
            return false;

        if (v.get(i).Token != Token.PONTO_VIRGULA)
            return false;

        return true;
    }

    boolean COMENTARIO(ArrayList<ClassificacaoLexica> vetorAnaliseLexica) {
        /*
         * COMENTARIO -> COMENTARIO
         */
        if (vetorAnaliseLexica.size() != 1)
            return false;

        return vetorAnaliseLexica.get(0).Token == Token.COMENTARIO;
    }

    boolean COMANDO_RETURN(ArrayList<ClassificacaoLexica> v) {
        /*
         * COMANDO_RETURN → RETURN (VALOR)? PONTO_VIRGULA
         * VALOR → VARIAVEL | NUMERO_INTEIRO | DECIMAL | CONSTANTE_CHAR | STRING
         */
        int n = v.size();
        // pode ser apenas "return;" (2 tokens) ou "return VALOR;" (3 tokens)
        if (n == 2) {
            // apenas RETURN e ;
            return v.get(0).Token == Token.RETURN
                    && v.get(1).Token == Token.PONTO_VIRGULA;
        } else if (n == 3) {
            // RETURN, VALOR e ;
            if (v.get(0).Token != Token.RETURN)
                return false;
            int t1 = v.get(1).Token;
            if (!(t1 == Token.VARIAVEL
                    || t1 == Token.NUMERO_INTEIRO
                    || t1 == Token.DECIMAL
                    || t1 == Token.CONSTANTE_CHAR
                    || t1 == Token.STRING)) {
                return false;
            }
            return v.get(2).Token == Token.PONTO_VIRGULA;
        } else {
            return false;
        }
    }

    boolean ABRE_BLOCO(ArrayList<ClassificacaoLexica> v) {
        /*
         * ABRE_BLOCO → ABRE_CHAVE
         * (uma linha só com '{')
         */
        return v.size() == 1
                && v.get(0).Token == Token.ABRE_CHAVE;
    }

    boolean FECHA_BLOCO(ArrayList<ClassificacaoLexica> v) {
        /*
         * FECHA_BLOCO → FECHA_CHAVE
         * (uma linha só com '}')
         */
        return v.size() == 1
                && v.get(0).Token == Token.FECHA_CHAVE;
    }

    void printArrayList(ArrayList<ClassificacaoLexica> vetor) {
        for (ClassificacaoLexica cl : vetor) {
            System.out.println("Lexema: " + cl.Lexema + ", Token: " + cl.Token + ", Linha: " + cl.Linha);
        }
    }

    public boolean COMANDO_SWITCH(ArrayList<ClassificacaoLexica> v) {
        int n = v.size();
        // precisa ter pelo menos: switch ( X )
        if (n < 4)
            return false;
        return v.get(0).Token == Token.COMANDO_SWITCH &&
                v.get(1).Token == Token.ABRE_PARENTES &&
                v.get(n - 1).Token == Token.FECHA_PARENTES;
    }

    public boolean COMANDO_CASE(ArrayList<ClassificacaoLexica> v) {
        int n = v.size();
        // precisa ter pelo menos: case valor :
        if (n < 3)
            return false;

        // verifica o rótulo 'case'
        if (v.get(0).Token != Token.COMANDO_CASE)
            return false;

        // verifica o valor do case
        int t1 = v.get(1).Token;
        if (!(t1 == Token.NUMERO_INTEIRO
                || t1 == Token.DECIMAL
                || t1 == Token.CONSTANTE_CHAR
                || t1 == Token.STRING)) {
            return false;
        }

        // verifica o dois pontos
        if (v.get(2).Token != Token.DOIS_PONTOS)
            return false;

        // reconhece como case mesmo se houver comandos após o rótulo
        return true;
    }

    public boolean COMANDO_BREAK(ArrayList<ClassificacaoLexica> v) {
        /**
         * COMANDO_BREAK → COMANDO_BREAK PONTO_VIRGULA
         */

        // espera exatamente dois tokens: 'break' e ';'
        if (v.size() != 2)
            return false;

        // verifica o keyword 'break'
        if (v.get(0).Token != Token.COMANDO_BREAK)
            return false;

        // verifica o ponto‐e‐vírgula
        if (v.get(1).Token != Token.PONTO_VIRGULA)
            return false;

        return true;
    }

    public boolean COMANDO_DEFAULT(ArrayList<ClassificacaoLexica> v) {
        int n = v.size();
        // precisa ter pelo menos: default :
        if (n < 3)
            return false;

        // verifica o rótulo 'default'
        if (v.get(0).Token != Token.COMANDO_DEFAULT)
            return false;

        // verifica o dois pontos
        if (v.get(1).Token != Token.DOIS_PONTOS)
            return false;

        // reconhece como default mesmo se houver comandos após o rótulo
        return true;
    }

    public boolean COMANDO_WHILE(ArrayList<ClassificacaoLexica> v) {
        int n = v.size();
        // precisa ter pelo menos: while ( X )
        if (n < 4)
            return false;

        // verifica o token 'while'
        if (v.get(0).Token != Token.COMANDO_WHILE)
            return false;
        // abre parênteses
        if (v.get(1).Token != Token.ABRE_PARENTES)
            return false;
        // fecha parênteses
        if (v.get(n - 1).Token != Token.FECHA_PARENTES)
            return false;

        // tudo entre 2 e n-2 é a condição (aceitamos qualquer sequência aqui)
        return true;
    }

    boolean COMANDO_DO_WHILE(ArrayList<ClassificacaoLexica> bloco) {
        return bloco.size() >= 8 &&
                bloco.get(0).Token == Token.COMANDO_WHILE &&
                bloco.get(1).Token == Token.ABRE_PARENTES &&
                // ... condição ...
                bloco.get(bloco.size() - 2).Token == Token.FECHA_PARENTES &&
                bloco.get(bloco.size() - 1).Token == Token.PONTO_VIRGULA;
    }

}
