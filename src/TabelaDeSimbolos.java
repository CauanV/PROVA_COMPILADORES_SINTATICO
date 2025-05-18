import java.util.HashMap;

public class TabelaDeSimbolos {

    private HashMap<Integer, String> tabela; // chave(key) --> valor(value)

    public TabelaDeSimbolos() {
        this.tabela = new HashMap<>();
    }

    public void adicionarSimbolo(int token, String simbolo) {
        tabela.put(token, simbolo);
    }

    public String buscarValor(Integer token) {
        return tabela.get(token);
    }

    public boolean contemToken(Integer token) {
        return tabela.containsKey(token);
    }

    public boolean contemSimbolo(String simbolo) {
        return tabela.containsValue(simbolo);
    }

    public void imprimirTabela() {
        System.out.println("Tabela de Símbolos:");
        for (Integer token : tabela.keySet()) {
            System.out.println("Token: " + token + ", Simbolo: " + tabela.get(token)); //tabela get retorna o value associado a chave
        }
    }
}
