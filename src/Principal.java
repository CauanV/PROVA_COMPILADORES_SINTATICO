import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;

public class Principal {
    public static void main(String[] args) throws FileNotFoundException {

        // cria um objeto do tipo Lexer
        Lexer objetoAnalisadorLexico = new Lexer();

        // objeto para caixa de dialogo de selecao dos arquivos
        JFileChooser fileChooser = new JFileChooser();

        // define o filtro para mostrar apenas arquivos .txt
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de Texto (*.txt)", "txt");
        fileChooser.setFileFilter(filter);

        // abre a janela de dialogo para selecionar o arquivo
        int returnValue = fileChooser.showOpenDialog(null);

        // se o usuario selecionou um arquivo
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // pega o arquivo selecionado
            File selectedFile = fileChooser.getSelectedFile();

            // pega o caminho do arquivo selecionado
            System.out.println("\n =====> Arquivo selecionado:" + selectedFile.getAbsolutePath());

            // chamar o Lexer para fazer a Analise lexica
            objetoAnalisadorLexico.AnalisadorLexico(selectedFile);
        } else {
            System.out.println("Nenhum arquivo selecionado.");
        }
    }
}
