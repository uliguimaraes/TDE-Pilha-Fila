
class Pilha {
    private String[] elementos;
    private int tamanho;

    public Pilha(int capacidade) {
        this.elementos = new String[capacidade];
        this.tamanho = 0;
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public boolean estaCheia() {
        return tamanho == elementos.length;
    }

    public boolean empilhar(String e) {
        if (!estaCheia()) {
            this.elementos[tamanho] = e;
            tamanho++;
            return true;
        }
        return false;
    }

    public String desempilhar() {
        if (!estaVazia()) {
            tamanho--;
            return this.elementos[tamanho];
        }
        return null;
    }

    public String espiar() {
        if (!estaVazia()) {
            return this.elementos[tamanho - 1];
        }
        return null;
    }
}


class Fila {
    private String[] elementos;
    private int tamanho;

    public Fila(int cap) {
        this.elementos = new String[cap];
        this.tamanho = 0;
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public boolean estaCheia() {
        return tamanho == elementos.length;
    }

    public boolean enfileirar(String e) {
        if (!estaCheia()) {
            this.elementos[tamanho] = e;
            tamanho++;
            return true;
        }
        return false;
    }

    public String desenfileirar() {
        if (!estaVazia()) {
            String elementoRemovido = this.elementos[0];
            for (int i = 1; i < tamanho; i++) {
                elementos[i - 1] = elementos[i];
            }
            tamanho--;
            return elementoRemovido;
        }
        return null;
    }

    public String espiar() {
        if (!estaVazia()) {
            return this.elementos[0];
        }
        return null;
    }
}

// Classe Principal com a execução de todos os exercícios
public class Main {
    public static void main(String[] args) {
        System.out.println("=== EXERCÍCIO 1: Par e Ímpar ===");
        exercicio1();

        System.out.println("\n=== EXERCÍCIO 2: Desafio do Palíndromo ===");
        exercicio2();

        System.out.println("\n=== EXERCÍCIO 3: Parênteses Balanceados ===");
        exercicio3();

        System.out.println("\n=== EXERCÍCIO 4: Fila de Atendimento Bancário ===");
        exercicio4();

        System.out.println("\n=== EXERCÍCIO 5: Fila de Impressão ===");
        exercicio5();
    }

    public static void exercicio1() {
        int[] numeros = {12, 7, 0, 4, 15, 8, 3, 0, 9, 2};
        Pilha par = new Pilha(10);
        Pilha impar = new Pilha(10);

        for (int num : numeros) {
            if (num == 0) {
                if (par.estaVazia() || impar.estaVazia()) {
                    System.out.println("Erro: Uma das pilhas está vazia ao tentar desempilhar!");
                } else {
                    par.desempilhar();
                    impar.desempilhar();
                }
            } else if (num % 2 == 0) {
                par.empilhar(String.valueOf(num));
            } else {
                impar.empilhar(String.valueOf(num));
            }
        }

        System.out.println("--- Desempilhando Pilha PAR ---");
        while (!par.estaVazia()) System.out.println(par.desempilhar());

        System.out.println("--- Desempilhando Pilha ÍMPAR ---");
        while (!impar.estaVazia()) System.out.println(impar.desempilhar());
    }

    public static void exercicio2() {
        String palavra = "AABCCBAA";
        Pilha pilha = new Pilha(palavra.length());

        for (int i = 0; i < palavra.length(); i++) {
            pilha.empilhar(String.valueOf(palavra.charAt(i)));
        }

        boolean ehPalindromo = true;
        for (int i = 0; i < palavra.length(); i++) {
            String letra = pilha.desempilhar();
            if (!letra.equals(String.valueOf(palavra.charAt(i)))) {
                ehPalindromo = false;
                break;
            }
        }
        System.out.println(palavra + " - " + (ehPalindromo ? "sim" : "não"));
    }

    public static void exercicio3() {
        String expressao = "((5 + 3) * (2 + 4))";
        Pilha pilha = new Pilha(expressao.length());
        boolean balanceado = true;

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                pilha.empilhar(String.valueOf(c));
            } else if (c == ')' || c == ']' || c == '}') {
                if (pilha.estaVazia()) {
                    balanceado = false;
                    break;
                }
                String topo = pilha.desempilhar();
                if ((c == ')' && !topo.equals("(")) ||
                        (c == ']' && !topo.equals("[")) ||
                        (c == '}' && !topo.equals("{"))) {
                    balanceado = false;
                    break;
                }
            }
        }
        if (!pilha.estaVazia()) balanceado = false;

        System.out.println(expressao + " -> " + (balanceado ? "Balanceado" : "Não balanceado"));
    }

    public static void exercicio4() {
        Fila nomes = new Fila(5);
        Fila tempos = new Fila(5);

        nomes.enfileirar("Ana"); tempos.enfileirar("5");
        nomes.enfileirar("Pedro"); tempos.enfileirar("3");
        nomes.enfileirar("João"); tempos.enfileirar("8");

        int totalTempo = 0;
        int totalClientes = 0;

        while (!nomes.estaVazia()) {
            String cliente = nomes.desenfileirar();
            int tempo = Integer.parseInt(tempos.desenfileirar());

            System.out.println("Atendendo: " + cliente + " | Tempo: " + tempo + " minutos");
            totalTempo += tempo;
            totalClientes++;
        }

        System.out.println("------------------------------------");
        System.out.println("Total de tempo gasto: " + totalTempo + " minutos");
        System.out.println("Total de clientes atendidos: " + totalClientes);
    }

    public static void exercicio5() {
        Fila documentos = new Fila(5);
        Fila paginas = new Fila(5);

        documentos.enfileirar("Relatorio.pdf"); paginas.enfileirar("12");
        documentos.enfileirar("Contrato.docx"); paginas.enfileirar("5");
        documentos.enfileirar("Livro.pdf"); paginas.enfileirar("150");
        documentos.enfileirar("Apresentacao.pptx"); paginas.enfileirar("25");

        int impressoes = 0;
        int totalPaginas = 0;
        String docMaior = "";
        int maxPaginas = -1;

        while (!documentos.estaVazia()) {
            String doc = documentos.desenfileirar();
            int numPaginas = Integer.parseInt(paginas.desenfileirar());

            System.out.println("Imprimindo: " + doc + " (" + numPaginas + " páginas)");

            totalPaginas += numPaginas;
            impressoes++;

            if (numPaginas > maxPaginas) {
                maxPaginas = numPaginas;
                docMaior = doc;
            }

            if (impressoes % 2 == 0 && !documentos.estaVazia()) {
                System.out.println(">> [Aviso] Próximo documento na fila: " + documentos.espiar() + " (" + paginas.espiar() + " páginas)");
            }
        }

        System.out.println("------------------------------------");
        System.out.println("Documento com maior número de páginas: " + docMaior + " (" + maxPaginas + " páginas)");
        System.out.println("Total de páginas impressas: " + totalPaginas);
    }
}