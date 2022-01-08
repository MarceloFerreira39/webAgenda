package br.com.webagenda.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Result;

public class DAO {

	// PARAMETROS DE CONEXAO
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/webagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "0146";

	// Metodos de conexao

	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;

		} catch (Exception e) {
			System.out.println(e);
			return null;

		}
	}

	/** CRUS CREATE **/
	public void inserirContato(JavaBeans contato) {
		String insert = "insert INTO contatos (nome, Fone, email) values (?,?,?)";
		try {
			// Abrir a conexão com o DB
			Connection con = conectar();

			// Preparar a query para execução no DB
			PreparedStatement pst = con.prepareStatement(insert);

			// Subistituir os parametros (?) pelo conteudo das variaves JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());

			// Executar a query
			pst.executeUpdate();

			// Encerrar a conexão com o Banco
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/** CRUD READ **/
	public ArrayList<JavaBeans> listarContatos() {
		// Criando objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();

		String read = "select * from contatos order by Idcon";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// O laço abaixo sera executado enquanto houver contatos
			while (rs.next()) {
				// Variaveis de apoio que recebem os dados do DB
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// Populando o array list
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			con.close();
			return contatos;

			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	
			
	}
	
		/**CRUD UPDATE**/
		public void selecionarContato(JavaBeans contato) {
			String read2 = "select * from contatos where idcon = ?";
			try {
				Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(read2);
				pst.setString(1, contato.getIdcon());
				ResultSet rs = pst.executeQuery();
				while(rs.next()) {
					contato.setIdcon(rs.getString(1));
					contato.setNome(rs.getString(2));
					contato.setFone(rs.getString(3));
					contato.setEmail(rs.getString(4));
				}
				
			} catch (Exception e) {
				System.out.println(e);
			}
					
	}

}
