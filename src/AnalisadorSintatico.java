import java.util.ArrayList;

public class AnalisadorSintatico {
    private RegrasSintaticas regras;
    private ArrayList<ClassificacaoLexica> todosTokens; // toda a saída do léxico

    public AnalisadorSintatico(ArrayList<ClassificacaoLexica> vetorLexico) {
        this.regras = new RegrasSintaticas();
        this.todosTokens = vetorLexico;
    }

    public void AnaliseSintatica() {
        if (!regras.INICIO(todosTokens)) {
            System.err.println("ERRO SINTÁTICO na LINHA 1: início inválido.");
            return;
        }
        System.out.println("INÍCIO OK");

        int ultimaLinha = todosTokens.get(todosTokens.size() - 1).Linha;
        boolean semErros = true;
        int erros = 0;

        for (int linha = 2; linha <= ultimaLinha; linha++) {
            ArrayList<ClassificacaoLexica> tokensDaLinha = obterTokensDaLinha(linha);

            if (tokensDaLinha.isEmpty() || isSomenteComentario(tokensDaLinha)) {
                continue;
            }

            boolean valida = processarLinha(tokensDaLinha, linha);

            if (!valida) {
                semErros = false;
                erros++;
            }
        }

        if (semErros) {
            System.out.println("Análise sintática completa sem erros.");
        } else {
            System.err.println("Análise sintática com " + erros + " erro(s).");
        }
        // debug feito por mim
        // System.out.println(
        // "\n\n\n\n\n************************************* A PARTIR DAQUI FEITO PELO
        // PAI PRA VER O QUE CHEGA *************************************");
        // regras.printArrayList(todosTokens);
    }

    private ArrayList<ClassificacaoLexica> obterTokensDaLinha(int linha) {
        ArrayList<ClassificacaoLexica> tokensDaLinha = new ArrayList<>();
        for (ClassificacaoLexica tok : todosTokens) {
            if (tok.Linha == linha) {
                tokensDaLinha.add(tok);
            }
        }
        return tokensDaLinha;
    }

    private boolean isSomenteComentario(ArrayList<ClassificacaoLexica> tokensDaLinha) {
        for (ClassificacaoLexica tok : tokensDaLinha) {
            if (tok.Token != Token.COMENTARIO) {
                return false;
            }
        }
        return true;
    }

    // private boolean processarLinha(ArrayList<ClassificacaoLexica> tokensDaLinha,
    // int linha) {
    // ArrayList<ArrayList<ClassificacaoLexica>> blocos =
    // dividirPorChaveOuElse(tokensDaLinha);
    // // adicionado como debug
    // // System.out.println("DEBUG | Tokens da linha " + linha + ":");
    // // for (ClassificacaoLexica token : tokensDaLinha) {
    // // System.out.println(" " + token.Lexema + " | " + token.Token);
    // // }
    // for (ArrayList<ClassificacaoLexica> bloco : blocos) {
    // if (bloco.isEmpty())
    // continue;
    // StringBuilder linhaCompleta = new StringBuilder();
    // // adicionado como debug
    // // System.out.print("| Verificando COMANDO_PRINTF com: ");
    // // for (ClassificacaoLexica token : bloco) {
    // // System.out.print(token.Token + " ");
    // // }
    // // System.out.println();
    // if (regras.COMANDO_PRINTF(bloco)) {
    // linhaCompleta.setLength(0);
    // for (ClassificacaoLexica token : bloco) {
    // linhaCompleta.append(token.Lexema).append(" ");
    // }
    // System.out.println("COMANDO_PRINTF - " + linha + " | " +
    // linhaCompleta.toString().trim());
    // } else if (regras.COMANDO_SCANF(bloco)) {
    // linhaCompleta.setLength(0);
    // for (ClassificacaoLexica token : bloco) {
    // linhaCompleta.append(token.Lexema).append(" ");
    // }
    // System.out.println("COMANDO_SCANF - " + linha + " | " +
    // linhaCompleta.toString().trim());
    // // } else if (regras.COMANDO_IF_COMPLETO(bloco)) {
    // // linhaCompleta.setLength(0);
    // // for (ClassificacaoLexica token : bloco) {
    // // linhaCompleta.append(token.Lexema).append(" ");
    // // }
    // // System.out.println("COMANDO_IF - " + linha + " | " +
    // // linhaCompleta.toString().trim());
    // }
    // if (regras.COMANDO_ELSE_IF(bloco)) {
    // linhaCompleta.setLength(0);
    // for (ClassificacaoLexica token : bloco) {
    // linhaCompleta.append(token.Lexema).append(" ");
    // }
    // System.out.println("COMANDO_ELSE_IF - " + linha + " | " +
    // linhaCompleta.toString().trim());
    // } else if (regras.COMANDO_ELSE(bloco)) {
    // linhaCompleta.setLength(0);
    // for (ClassificacaoLexica token : bloco) {
    // linhaCompleta.append(token.Lexema).append(" ");
    // }
    // System.out.println("COMANDO_ELSE - " + linha + " | " +
    // linhaCompleta.toString().trim());
    // } else if (regras.COMANDO_IF(bloco)) {
    // linhaCompleta.setLength(0);
    // for (ClassificacaoLexica token : bloco) {
    // linhaCompleta.append(token.Lexema).append(" ");
    // }
    // System.out.println("COMANDO_IF - " + linha + " | " +
    // linhaCompleta.toString().trim());
    // } else if (regras.ESTRUTURA_DE_REPETICAO(bloco)) {
    // linhaCompleta.setLength(0);
    // for (ClassificacaoLexica token : bloco) {
    // linhaCompleta.append(token.Lexema).append(" ");
    // }
    // System.out.println("ESTRUTURA_DE_REPETICAO - " + linha + " | " +
    // linhaCompleta.toString().trim());
    // } else if (regras.COMANDO_DO(bloco)) {
    // linhaCompleta.setLength(0);
    // for (ClassificacaoLexica token : bloco) {
    // linhaCompleta.append(token.Lexema).append(" ");
    // }
    // System.out.println("COMANDO_DO - " + linha + " | " +
    // linhaCompleta.toString().trim());
    // } else if (regras.DECLARACAO_VARIAVEL(bloco)) {
    // linhaCompleta.setLength(0);
    // for (ClassificacaoLexica token : bloco) {
    // linhaCompleta.append(token.Lexema).append(" ");
    // }
    // System.out.println("DECLARACAO_VARIAVEL - " + linha + " | " +
    // linhaCompleta.toString().trim());
    // } else if (regras.ATRIBUICAO_DE_VARIAVEL(bloco)) {
    // linhaCompleta.setLength(0);
    // for (ClassificacaoLexica token : bloco) {
    // linhaCompleta.append(token.Lexema).append(" ");
    // }
    // System.out.println("ATRIBUICAO_VARIAVEL - " + linha + " | " +
    // linhaCompleta.toString().trim());
    // } else if (regras.DECLARACAO_DE_VETOR(bloco)) {
    // linhaCompleta.setLength(0);
    // for (ClassificacaoLexica token : bloco) {
    // linhaCompleta.append(token.Lexema).append(" ");
    // }
    // System.out.println("DECLARACAO_DE_VETOR - " + linha + " | " +
    // linhaCompleta.toString().trim());
    // } else if (regras.COMANDO_RETURN(bloco)) {
    // linhaCompleta.setLength(0);
    // for (ClassificacaoLexica token : bloco) {
    // linhaCompleta.append(token.Lexema).append(" ");
    // }
    // System.out.println("COMANDO_RETURN - " + linha + " | " +
    // linhaCompleta.toString().trim());
    // } else if (regras.COMENTARIO(bloco)) {
    // linhaCompleta.setLength(0);
    // for (ClassificacaoLexica token : bloco) {
    // linhaCompleta.append(token.Lexema).append(" ");
    // }
    // System.out.println("COMENTARIO - " + linha + " | " +
    // linhaCompleta.toString().trim());
    // } else if (regras.ABRE_BLOCO(bloco)) {
    // System.out.println("ABRE_BLOCO - " + linha);
    // } else if (regras.FECHA_BLOCO(bloco)) {
    // System.out.println("FECHA_BLOCO - " + linha);
    // } else {
    // linhaCompleta.setLength(0);
    // for (ClassificacaoLexica token : bloco) {
    // linhaCompleta.append(token.Lexema).append(" ");
    // }
    // System.out.println("NAO_RECONHECIDO - " + linha + " | " +
    // linhaCompleta.toString().trim());
    // } // adicionado por mim como debug
    // }
    // return true;
    // }

    // private ArrayList<ArrayList<ClassificacaoLexica>>
    // dividirPorChaveOuElse(ArrayList<ClassificacaoLexica> tokens) {
    // ArrayList<ArrayList<ClassificacaoLexica>> blocos = new ArrayList<>();
    // ArrayList<ClassificacaoLexica> atual = new ArrayList<>();
    // for (int i = 0; i < tokens.size(); i++) {
    // ClassificacaoLexica token = tokens.get(i);
    // if (token.Token == Token.FECHA_CHAVE) {
    // if (!atual.isEmpty()) {
    // blocos.add(new ArrayList<>(atual));
    // atual.clear();
    // }
    // ArrayList<ClassificacaoLexica> blocoChave = new ArrayList<>();
    // blocoChave.add(token);
    // blocos.add(blocoChave);
    // } else if (token.Token == Token.COMANDO_ELSE) {
    // if (!atual.isEmpty()) {
    // blocos.add(new ArrayList<>(atual));
    // atual.clear();
    // }
    // ArrayList<ClassificacaoLexica> blocoElse = new ArrayList<>();
    // blocoElse.add(token);
    // i++;
    // while (i < tokens.size()) {
    // ClassificacaoLexica prox = tokens.get(i);
    // blocoElse.add(prox);
    // if (prox.Token == Token.PONTO_VIRGULA || prox.Token == Token.ABRE_CHAVE)
    // break;
    // i++;
    // }
    // blocos.add(blocoElse);
    // } else {
    // atual.add(token);
    // }
    // }
    // if (!atual.isEmpty()) {
    // blocos.add(atual);
    // }
    // return blocos;
    // }

    private boolean processarLinha(ArrayList<ClassificacaoLexica> tokensDaLinha, int linha) {
        ArrayList<ArrayList<ClassificacaoLexica>> blocos = dividirPorChaveOuElse(tokensDaLinha);

        // for (ArrayList<ClassificacaoLexica> bloco : blocos) {
        // if (bloco.isEmpty())
        // continue;

        // String texto = reconstruirLinha(bloco);

        // if (regras.COMANDO_PRINTF(bloco)) {
        // System.out.println("COMANDO_PRINTF | " + linha + " | " + texto);
        // continue;
        // } else if (regras.COMANDO_SCANF(bloco)) {
        // System.out.println("COMANDO_SCANF | " + linha + " | " + texto);
        // continue;
        // } else if (regras.COMANDO_ELSE_IF(bloco)) {
        // System.out.println("ELSE_IF | " + linha + " | " + texto);
        // continue;
        // } else if (regras.COMANDO_SWITCH(bloco)) {
        // System.out.println("COMANDO_SWITCH | " + linha + " | " + texto);
        // continue;
        // } else if (regras.COMANDO_CASE(bloco)) {
        // System.out.println("COMANDO_CASE | " + linha + " | " + texto);
        // continue;
        // } else if (regras.COMANDO_BREAK(bloco)) {
        // System.out.println("COMANDO_BREAK | " + linha + " | " + texto);
        // continue;
        // } else if (regras.COMANDO_ELSE(bloco)) {
        // System.out.println("ELSE | " + linha + " | " + texto);
        // continue;
        // } else if (regras.COMANDO_DEFAULT(bloco)) {
        // System.out.println("COMANDO_DEFAULT | " + linha + " | " + texto);
        // continue;
        // } else if (regras.COMANDO_IF(bloco)) {
        // System.out.println("COMANDO_IF | " + linha + " | " + texto);
        // continue;
        // } else if (regras.ESTRUTURA_DE_REPETICAO(bloco)) {
        // System.out.println("ESTRUTURA_DE_REPETICAO | " + linha + " | " + texto);
        // continue;
        // } else if (regras.COMANDO_DO(bloco)) {
        // System.out.println("COMANDO_DO | " + linha + " | " + texto);
        // continue;
        // } else if (regras.COMANDO_DO_WHILE(bloco)) {
        // System.out.println("COMANDO_DO_WHILE | " + linha + " | " + texto);
        // continue;
        // } else if (regras.DECLARACAO_VARIAVEL(bloco)) {
        // System.out.println("DECLARACAO_VARIAVEL | " + linha + " | " + texto);
        // continue;
        // } else if (regras.ATRIBUICAO_DE_VARIAVEL(bloco)) {
        // System.out.println("ATRIBUICAO_VARIAVEL | " + linha + " | " + texto);
        // continue;
        // } else if (regras.DECLARACAO_DE_VETOR(bloco)) {
        // System.out.println("DECLARACAO_DE_VETOR | " + linha + " | " + texto);
        // continue;
        // } else if (regras.COMANDO_RETURN(bloco)) {
        // System.out.println("COMANDO_RETURN | " + linha + " | " + texto);
        // continue;
        // } else if (regras.COMENTARIO(bloco)) {
        // System.out.println("COMENTARIO | " + linha + " | " + texto);
        // continue;
        // } else if (regras.ABRE_BLOCO(bloco)) {
        // System.out.println("ABRE_BLOCO | " + linha);
        // continue;
        // } else if (regras.FECHA_BLOCO(bloco)) {
        // System.out.println("FECHA_BLOCO | " + linha);
        // continue;
        // }

        // else {
        // // se nenhum comando bateu, marcamos como não reconhecido
        // System.out.println("NAO_RECONHECIDO | " + linha + " | " + texto);
        // return false;
        // }
        // }

        /************************************************************************************************************************* */
        for (ArrayList<ClassificacaoLexica> bloco : blocos) {
            if (bloco.isEmpty())
                continue;

            String texto = reconstruirLinha(bloco);

            if (regras.COMENTARIO(bloco)) {
                System.out.println("COMENTARIO       | " + linha + " | " + texto);
                continue;
            } else if (regras.ABRE_BLOCO(bloco)) {
                System.out.println("ABRE_BLOCO       | " + linha);
                continue;
            } else if (regras.FECHA_BLOCO(bloco)) {
                System.out.println("FECHA_BLOCO       | " + linha);
                continue;
            } else if (regras.COMANDO_DO_WHILE(bloco)) {
                System.out.println("COMANDO_DO_WHILE       | " + linha + " | " + texto);
                continue;
            } else if (regras.COMANDO_DO(bloco)) {
                System.out.println("COMANDO_DO       | " + linha + " | " + texto);
                continue;
            } else if (regras.ESTRUTURA_DE_REPETICAO(bloco)) {
                System.out.println("ESTRUTURA_DE_REPETICAO       | " + linha + " | " + texto);
                continue;
            } else if (regras.COMANDO_ELSE_IF(bloco)) {
                System.out.println("ELSE_IF       | " + linha + " | " + texto);
                continue;
            } else if (regras.COMANDO_IF(bloco)) {
                System.out.println("COMANDO_IF       | " + linha + " | " + texto);
                continue;
            } else if (regras.COMANDO_ELSE(bloco)) {
                System.out.println("ELSE       | " + linha + " | " + texto);
                continue;
            } else if (regras.COMANDO_SWITCH(bloco)) {
                System.out.println("COMANDO_SWITCH       | " + linha + " | " + texto);
                continue;
            } else if (regras.COMANDO_CASE(bloco)) {
                System.out.println("COMANDO_CASE       | " + linha + " | " + texto);
                continue;
            } else if (regras.COMANDO_DEFAULT(bloco)) {
                System.out.println("COMANDO_DEFAULT       | " + linha + " | " + texto);
                continue;
            } else if (regras.COMANDO_BREAK(bloco)) {
                System.out.println("COMANDO_BREAK       | " + linha + " | " + texto);
                continue;
            } else if (regras.COMANDO_PRINTF(bloco)) {
                System.out.println("COMANDO_PRINTF       | " + linha + " | " + texto);
                continue;
            } else if (regras.COMANDO_SCANF(bloco)) {
                System.out.println("COMANDO_SCANF       | " + linha + " | " + texto);
                continue;
            } else if (regras.DECLARACAO_DE_VETOR(bloco)) {
                System.out.println("DECLARACAO_DE_VETOR       | " + linha + " | " + texto);
                continue;
            } else if (regras.DECLARACAO_VARIAVEL(bloco)) {
                System.out.println("DECLARACAO_VARIAVEL       | " + linha + " | " + texto);
                continue;
            } else if (regras.ATRIBUICAO_DE_VARIAVEL(bloco)) {
                System.out.println("ATRIBUICAO_VARIAVEL       | " + linha + " | " + texto);
                continue;
            } else if (regras.COMANDO_RETURN(bloco)) {
                System.out.println("COMANDO_RETURN       | " + linha + " | " + texto);
                continue;
            } else {
                // se nenhum comando bateu, marcamos como não reconhecido
                System.out.println("NAO_RECONHECIDO      | " + linha + " |  " + texto);
                return false;
            }
        }

        // linha sem blocos relevantes (ex: só comentário), considera válida
        return true;
    }

    private String reconstruirLinha(ArrayList<ClassificacaoLexica> bloco) {
        StringBuilder sb = new StringBuilder();
        for (ClassificacaoLexica token : bloco) {
            sb.append(token.Lexema).append(" ");
        }
        return sb.toString().trim();
    }

    private ArrayList<ArrayList<ClassificacaoLexica>> dividirPorChaveOuElse(ArrayList<ClassificacaoLexica> tokens) {
        ArrayList<ArrayList<ClassificacaoLexica>> blocos = new ArrayList<>();
        ArrayList<ClassificacaoLexica> atual = new ArrayList<>();

        for (int i = 0; i < tokens.size(); i++) {
            ClassificacaoLexica tok = tokens.get(i);

            // Sempre que encontrar um '}', fecha o bloco atual e adiciona o bloco do '}'
            if (tok.Token == Token.FECHA_CHAVE) {
                if (!atual.isEmpty()) {
                    blocos.add(new ArrayList<>(atual));
                    atual.clear();
                }
                ArrayList<ClassificacaoLexica> soFecha = new ArrayList<>();
                soFecha.add(tok);
                blocos.add(soFecha);

                // Quando encontrar o 'else', separamos o 'else' e não "comemos" os próximos
                // tokens
            } else if (tok.Token == Token.COMANDO_ELSE) {
                // fecha bloco anterior
                if (!atual.isEmpty()) {
                    blocos.add(new ArrayList<>(atual));
                    atual.clear();
                }
                // bloco só com 'else'
                ArrayList<ClassificacaoLexica> blocoElse = new ArrayList<>();
                blocoElse.add(tok);
                blocos.add(blocoElse);

                // não incrementamos 'i' aqui: o próximo token (seja '{' ou outra coisa) vai
                // para o próximo bloco

            } else {
                // caso normal: acumula
                atual.add(tok);
            }
        }

        // garante o último bloco
        if (!atual.isEmpty()) {
            blocos.add(atual);
        }
        return blocos;
    }

}
