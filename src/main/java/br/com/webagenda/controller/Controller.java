package br.com.webagenda.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import br.com.webagenda.model.DAO;
import br.com.webagenda.model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();

	public Controller() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Teste de conxao
		// dao.testeConexao();
		// Requisicao ao servidor
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			novoContato(request, response);
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else if (action.equals("/update")) {
			editarContato(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	// listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que ira receber os Dados JavaBEans
		ArrayList<JavaBeans> lista = dao.listarContatos();

		// Encaminhar a lista ao documento webAgenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("webAgenda.jsp");
		rd.forward(request, response);
		// Teste de recebimento da lista
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).getIdcon());
			System.out.println(lista.get(i).getNome());
			System.out.println(lista.get(i).getFone());
			System.out.println(lista.get(i).getEmail());
		}
	}

	// Novo contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// teste de recebimento dos dados do formulï¿½rio
		// System.out.println(request.getParameter("nome"));
		// System.out.println(request.getParameter("fone"));
		// System.out.println(request.getParameter("email"));

		// Setar as variaveis JavaBeans
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// invocar o metodo inserir contato , passando o objeto contato
		dao.inserirContato(contato);

		// Redirecionar para o documento agenda.jsp
		response.sendRedirect("main");

	}

	// Editar contato =====================================================================/
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idcon = request.getParameter("idcon");
		// System.out.println(idcon);

		// Setar a variavel javaBeans
		contato.setIdcon(idcon);
		// Executar o método selecionarContato (DAO)
		dao.selecionarContato(contato);

		// Setar os ATRIBUTOS DO FORMULARIO COM OS ATRIBUTOS JAVAbEANS
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());
		// Encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setar as variaveis JavaBeans
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		//Executar o método alterarContato
		dao.alterarContato(contato);
		// Redirecionar para o documento webAgenda.jsp (Atualizando as alterações nos contatos)
		response.sendRedirect("main");
	}
	
}
