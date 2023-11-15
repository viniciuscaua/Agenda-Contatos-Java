package persistencia;
import dominio.Contato;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ContatoDAO {

    private static Conexao conexao;

    public static void setConexao(Conexao con) {
        conexao = con;
    }

    public static void inserirContato(Contato contato) {
        try {
            conexao.conectar();

            PreparedStatement instrucao = conexao.getCon().prepareStatement("INSERT INTO contato (nome, telefone, email) VALUES (?, ?, ?)");
            instrucao.setString(1, contato.getNome());
            instrucao.setString(2, contato.getTelefone());
            instrucao.setString(3, contato.getEmail());

            int linhasAfetadas = instrucao.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Inserção realizada com sucesso.");
            } else {
                System.out.println("Nenhuma linha afetada durante a inserção.");
            }

        } catch (SQLException e) {
            System.out.println("ERRO NO METODO: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }
    }

    public static Contato buscarID(int id) {
        Contato contato = null;
        try {
            conexao.conectar();

            String sql = "SELECT * FROM contato WHERE id = ?";
            PreparedStatement instrucao = conexao.getCon().prepareStatement(sql);
            instrucao.setInt(1, id);

            ResultSet rs = instrucao.executeQuery();

            if (rs.next()) {
                int contatoId = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");

                contato = new Contato(contatoId, nome, telefone, email);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar o contato: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }
        return contato;
    }

    public static ArrayList<Contato> buscarNome(String nomeB) {
        ArrayList<Contato> listaContatos = new ArrayList<>();
    
        try {
            conexao.conectar();
            String sql = "SELECT * FROM contato WHERE nome ILIKE ?";
            PreparedStatement instrucao = conexao.getCon().prepareStatement(sql);
            instrucao.setString(1, "%" + nomeB + "%");
            ResultSet rs = instrucao.executeQuery();
    
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
    
                Contato c1 = new Contato(id, nome, telefone, email);
    
                listaContatos.add(c1);
            }
            conexao.desconectar();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar o contato: " + e.getMessage());
        }
    
        return listaContatos;
    }   

    public static void alterarContato(int id, Contato contato) {
        try {
            conexao.conectar();
    
            String sql = "UPDATE contato SET nome = ?, telefone = ?, email = ? WHERE id = ?";
            PreparedStatement instrucao = conexao.getCon().prepareStatement(sql);
            
            instrucao.setString(1, contato.getNome());
            instrucao.setString(2, contato.getTelefone());
            instrucao.setString(3, contato.getEmail());
            instrucao.setInt(4, id);
    
            int linhasAfetadas = instrucao.executeUpdate();
    
            if (linhasAfetadas > 0) {
                System.out.println("Alteração realizada com sucesso.");
            } else {
                System.out.println("Nenhuma linha afetada durante a alteração.");
            }
    
        } catch (SQLException e) {
            System.out.println("ERRO NO METODO: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }
    }
    
    public static void deletarContato(int id) {
        try {
            conexao.conectar();
    
            String sql = "DELETE FROM contato WHERE id = ?";
            PreparedStatement instrucao = conexao.getCon().prepareStatement(sql);
            
            instrucao.setInt(1, id);
    
            int linhasAfetadas = instrucao.executeUpdate();
    
            if (linhasAfetadas > 0) {
                System.out.println("Alteração realizada com sucesso.");
            } else {
                System.out.println("Nenhuma linha afetada durante a alteração.");
            }
    
        } catch (SQLException e) {
            System.out.println("ERRO NO METODO: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }
    }
    
    
    public static ArrayList<Contato> exibirContatos() {
        ArrayList<Contato> listaContatos = new ArrayList<>();
    
        try {
            conexao.conectar();
            String sql = "SELECT * FROM contato";
            PreparedStatement instrucao = conexao.getCon().prepareStatement(sql);
            ResultSet rs = instrucao.executeQuery();
    
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
    
                Contato c1 = new Contato(id, nome, telefone, email);
    
                listaContatos.add(c1);
            }
            conexao.desconectar();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar o contato: " + e.getMessage());
        }
    
        return listaContatos;
    }   
}