package visao;

import dominio.*;
import persistencia.Conexao;
import persistencia.ContatoDAO;

import java.util.Scanner;
import java.util.ArrayList;

public class Principal {
	public static void main(String[] args) {
		int opcao, opcao2, confirm, buscaID;
		String nome, telefone, email, buscaNome;
		ArrayList<Contato> listaContatos = new ArrayList<Contato>();
		Conexao conexao = new Conexao();
		ContatoDAO.setConexao(conexao);
		Contato contato = new Contato();

		try (Scanner teclado = new Scanner(System.in)) {
            do {
            	System.out.println("\tMENU PRINCIPAL");
            	System.out.println("1 - Incluir Contato");
            	System.out.println("2 - Buscar Contato");
            	System.out.println("3 - Alterar Contato");
            	System.out.println("4 - Deletar Contato");
            	System.out.println("5 - Relatorio de Contatos");
            	System.out.println("6 - Sair do Sistema");
            	System.out.println("\nDigite a opcao que deseja:");

            	opcao = teclado.nextInt();
            	teclado.nextLine();

            	switch (opcao) {
            		case 1:
                        System.out.println("Digite o nome do contato: ");
						nome = teclado.nextLine();
						System.out.println("Digite o telefone do contato: ");
						telefone = teclado.nextLine();
						System.out.println("Digite o email do contato: ");
						email = teclado.nextLine();
						contato.setNome(nome);
						contato.setTelefone(telefone);
						contato.setEmail(email);
						ContatoDAO.inserirContato(contato);
						break;

					case 2:
						do {
            				System.out.println("\tMENU SECUNDARIO");
            				System.out.println("1 - Buscar pelo ID");
            				System.out.println("2 - Buscar Pelo Nome");
            				System.out.println("3 - Voltar ao Menu Principal");
            				System.out.println("\nDigite a opcão que deseja");

            				opcao2 = teclado.nextInt();
            				teclado.nextLine();

            				switch (opcao2) {
            					case 1:
									System.out.println("Digite o id que deseja buscar: ");
									buscaID = teclado.nextInt();
									teclado.nextLine();
            						contato = ContatoDAO.buscarID(buscaID);
									if (contato != null) {
										System.out.println("Nome: " + contato.getNome());
										System.out.println("Telefone: " + contato.getTelefone());
										System.out.println("Email: " + contato.getEmail());
									} else{
										System.out.println("Contato não encontrado!");
									}
            						break;
            				
								case 2:
                                    boolean achou = false;
									System.out.println("Digite o nome que deseja buscar: ");
									buscaNome = teclado.nextLine();
									listaContatos = ContatoDAO.buscarNome(buscaNome);
									for (int i = 0; i < listaContatos.size(); i++) {
                                        achou = true;
										System.out.println("Id: " + listaContatos.get(i).getId());
										System.out.println("Nome: " + listaContatos.get(i).getNome());
										System.out.println("Telefone: " + listaContatos.get(i).getTelefone());
										System.out.println("Email: " + listaContatos.get(i).getEmail());
									}
                                    if(!achou){
                                        System.out.println("Contato não encontrado!");
                                    }
									break;

								case 3:
									System.out.println("Saindo do Menu Secundário");
									break;
            					default:
									System.out.println("Opção inválida!");
            						break;
            				}
            			} while (opcao2 != 3);
            			break;

					case 3:
						System.out.println("Digite o id que deseja buscar: ");
						buscaID = teclado.nextInt();
						teclado.nextLine();
						contato = ContatoDAO.buscarID(buscaID);
						if (contato != null) {
							System.out.println("Nome: " + contato.getNome());
							System.out.println("Telefone: " + contato.getTelefone());
							System.out.println("Email: " + contato.getEmail() + "\n");

							System.out.println("Digite o novo nome do contato: ");
							nome = teclado.nextLine();
							System.out.println("Digite o nome telefone do contato: ");
							telefone = teclado.nextLine();
							System.out.println("Digite o novo email do contato:: ");
							email = teclado.nextLine();
							contato.setNome(nome);
							contato.setTelefone(telefone);
							contato.setEmail(email);
							ContatoDAO.alterarContato(buscaID, contato);

							System.out.println("\nContato alterado com sucesso!");

						} else {
							System.out.println("Contato não encontrado!");
						}
						break;

					case 4:
						System.out.println("Digite o ID que deseja deletar: ");
                        buscaID = teclado.nextInt();
                        System.out.println("Tem certeza que deseja deletar? (1 - Sim / 2 - Não)");
                        confirm = teclado.nextInt();
						teclado.nextLine();
                        if(confirm == 1){
                            ContatoDAO.deletarContato(buscaID);
                            System.out.println("Contato deletado com sucesso!");
                        } else {
                            System.out.println("Contato não encontrado");
                        }
						break;
					
					case 5:
						listaContatos = ContatoDAO.exibirContatos();
						for (int i = 0; i < listaContatos.size(); i++) {
							System.out.println("Id: " + listaContatos.get(i).getId());
							System.out.println("Nome: " + listaContatos.get(i).getNome());
							System.out.println("Telefone: " + listaContatos.get(i).getTelefone());
							System.out.println("Email: " + listaContatos.get(i).getEmail());
						}
						break;
					case 6:
						System.out.println("Encerrando o programa...");
						break;
            		default:
						System.out.println("Opção inválida!");
            			break;
            	}
            } while (opcao != 6);
        }
	}
}