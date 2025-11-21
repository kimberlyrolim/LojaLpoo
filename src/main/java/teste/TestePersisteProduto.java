package teste;

import model.Produto;
import model.dao.PersistenciaJPA;

public class TestePersisteProduto {
    public static void main(String[] args) {
        PersistenciaJPA jpa = new PersistenciaJPA();

        if (jpa.conexaoAberta()) {
            System.out.println("Conexão aberta com sucesso!");
            
            Produto p = new Produto();
            p.setProdNome("MOUSE");
            p.setProdTipo("Periférico");
            p.setProdValor(50.0);

            try {
                jpa.persist(p);
                System.out.println("Produto salvo no banco!");
            } catch (Exception e) {
                System.out.println("Erro ao salvar: " + e.getMessage());
            }

            jpa.fecharConexao();
        } else {
            System.out.println("Falha ao abrir conexão.");
        }
    }
}
