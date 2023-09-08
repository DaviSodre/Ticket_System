import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Ticket {
  private int id;
    private String descricao;
    private String estado;
    private String resposta; // Nova adição para a resposta do administrador

    public Ticket(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
        this.estado = "Aberto";
        this.resposta = ""; // Inicialmente vazio
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEstado() {
        return estado;
    }

    public String getResposta() {
        return resposta;
    }

    public void atualizarEstado(String novoEstado) {
        estado = novoEstado;
    }

    public void adicionarResposta(String resposta) {
        this.resposta = resposta;
        estado = "Respondido"; // Altera o estado para "Respondido"
    }



    public void responder(String resposta) {
        this.resposta = resposta;
        estado = "Fechado"; // Quando respondido, o ticket é marcado como "Fechado"
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Ticket> tickets = new ArrayList<>();
        int nextTicketId = 1;
        String senhaAdministrador = "senha123"; // Senha do administrador
        int tentativasSenha = 3;
        boolean isAdmin = false;

            while (true) {
                System.out.println("\n====== Sistema de Tickets ======\n");
                System.out.println("1. Criar um novo ticket");
                System.out.println("2. Entrar como Administrador.");
                System.out.println("3. Sair\n");

                if (isAdmin) {
                    System.out.println("=============================");
                    System.out.println("\t\tADMINISTRADOR");
                    System.out.println("=============================");
                    System.out.println("4. Ver todos os tickets");
                    System.out.println("5. Responder a um ticket\n");
                }
                int escolha;
                while (true) {
                    System.out.print("Escolha uma opção: ");
                    if (scanner.hasNextInt()) {
                        escolha = scanner.nextInt();
                        scanner.nextLine(); // Consumir a nova linha
                        if (escolha >= 1 && escolha <= 5) {
                            break; // Saia do loop se a escolha for válida
                        } else {
                            System.out.println("Opção inválida. Tente novamente.");
                        }
                    } else {
                        System.out.println("Opção inválida. Tente novamente.");
                        scanner.nextLine(); // Consumir entrada inválida
                    }
                }

                if (escolha == 1) {
                    System.out.print("Digite a descrição do problema: ");
                    String descricao = scanner.nextLine();
                    Ticket novoTicket = new Ticket(nextTicketId, descricao);
                    tickets.add(novoTicket);
                    nextTicketId++;
                    System.out.println("Ticket criado com sucesso. ID do Ticket: " + (nextTicketId - 1));

                }  else if (escolha == 4 && isAdmin) {
                    System.out.println("=== Todos os Tickets ===");
                    for (Ticket ticket : tickets) {
                        System.out.println("ID: " + ticket.getId());
                        System.out.println("Descrição: " + ticket.getDescricao());
                        System.out.println("Estado: " + ticket.getEstado());
                        System.out.println("Resposta: " + ticket.getResposta());
                        System.out.println("-----");
                    }
                    break;
                } else if (escolha == 5 && isAdmin) {
                    System.out.print("Qual ticket gostaria de responder? ");
                    int ticketId = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha

                    for (Ticket ticket : tickets) {
                        if (ticket.getId() == ticketId && ticket.getEstado().equals("Aberto")) {
                            System.out.print("Qual sua resposta? ");
                            String resposta = scanner.nextLine();
                            ticket.responder(resposta);
                            System.out.println("Ticket respondido com sucesso.");
                        }
                    }
                } else if (escolha == 3) {
                    break; // Encerra o programa se a opção for sair
                } else if (escolha == 0 || escolha == 2) {
                    // Solicitar senha do administrador apenas uma vez
                    System.out.print("Digite a senha do administrador: ");
                    String senhaDigitada = scanner.nextLine();
                    if (senhaDigitada.equals(senhaAdministrador)) {
                        isAdmin = true; // Entrar como administrador se a senha estiver correta
                    } else {
                        System.out.println("Senha incorreta. Tentativas restantes: " + (tentativasSenha - 1));
                        tentativasSenha--;
                    } if (tentativasSenha == 0){
                        System.out.println("Tentativas esgotadas encerrando programa.");
                        break;
                    }
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            }


        scanner.close();
    }
}
