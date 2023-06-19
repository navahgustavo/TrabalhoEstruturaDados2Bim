package com.mycompany.trabalhoestruturadados;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Gustavo H. Nava
 */
public class TrabalhoEstruturaDados {

    public static void main(String[] args) {
        
        int tamanho = definirTamanhoVetor();
        int[] vetorInteiro = new int[tamanho];
        Random random = new Random();
        
        for (int i = 0; i < tamanho; i++) {
            vetorInteiro[i] = random.nextInt();
        }
        
        menuPrincipal(vetorInteiro);        
    }
    
    public static int definirTamanhoVetor() {
        
        boolean entradaCorreta = false;       
        int n = 0;
        
        while (!entradaCorreta) {            
            String tamanhoVetor = JOptionPane.showInputDialog(null, "Informe o tamanho do vetor:");
            try {
                n = Integer.parseInt(tamanhoVetor);
                JOptionPane.showMessageDialog(null, "O tamanho do vetor será: " + n);
                entradaCorreta = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida! Digite um número inteiro válido.");
                entradaCorreta = false;
            }
        }
        
        return n;
    }
    
    public static void menuPrincipal(int[] vetorInteiro) {
        
        String[] opcoesVetor = {"Métodos de Ordenação", "Exibir Vetores", "Pesquisar Elementos", "Sair" };
        int escolha;
        
        txtVetor(vetorInteiro, "VetorInicial");

        do {
            escolha = JOptionPane.showOptionDialog(null, "Tamanho do vetor: " + vetorInteiro.length + "\nSelecione uma opção:", "Menu", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, opcoesVetor, opcoesVetor[0]);

            switch (escolha) {
                case 0:
                    menuVetores(vetorInteiro);
                    break;
                case 1:
                    String[] opcoesExibicao = {"Vetor Inicial", "Vetor Final"};
                    int escolhaExibicao = JOptionPane.showOptionDialog(null, "Tamanho do vetor: " + vetorInteiro.length + "\nSelecione uma opção:", "Menu", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE, null, opcoesExibicao, opcoesExibicao[0]);
                    if (escolhaExibicao == 0) {
                        exibicaoVetores("VetorInicial.txt");
                    } else if (escolhaExibicao == 1) {
                        exibicaoVetores("VetorFinal.txt");
                    }
                    break;
                case 2:
                    exibicaoPesquisa();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Saindo.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida");
                    break;
            }
        } while (escolha != 3);
    }
    
    public static void menuVetores(int[] vetorInteiro) {
        
        int[] vetor = vetorInteiro;
        String[] opcoesVetor = {"Ordenação por Insercao", "Ordenação por Seleção", "Ordenação Bolha", "Voltar" };
        int escolha;

        do {
            escolha = JOptionPane.showOptionDialog(null, "Tamanho do vetor: " + vetor.length + "\nSelecione uma opção:", "Menu de Ordenação do Vetor", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, opcoesVetor, opcoesVetor[0]);

            switch (escolha) {
                case 0:
                    ordenacaoInsercao(vetorInteiro);
                    break;
                case 1:
                    ordenacaoSelecao(vetorInteiro);
                    break;
                case 2:
                    ordenacaoBolha(vetorInteiro);
                    break;
                case 3:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida");
                    break;
            }
        } while (escolha != 3);
    }
    
    public static void ordenacaoInsercao(int[] vetorInteiro) {
        
        long inicioExecucao = System.currentTimeMillis();
        for (int i = 1; i < vetorInteiro.length; i++) {
            int chave = vetorInteiro[i];
            int j = i - 1;
            
            while (j >= 0 && vetorInteiro[j] > chave) {
                vetorInteiro[j + 1] = vetorInteiro[j];
                j--;
            }
            
            vetorInteiro[j + 1] = chave;
        }
        long finalExecucao = System.currentTimeMillis();
        
        double tempoExecucao = (finalExecucao - inicioExecucao) / 1000;
        txtVetor(vetorInteiro, "VetorFinal");
        
        JOptionPane.showMessageDialog(null, "Tempo de execucao do loop: " + tempoExecucao + " segundos.");
    }
    
    public static void ordenacaoSelecao(int[] vetorInteiro) {
        
        long inicioExecucao = System.currentTimeMillis();
        int n = vetorInteiro.length;

        for (int i = 0; i < n - 1; i++) {
            int menorIndice = i;
            for (int j = i + 1; j < n; j++) {
                if (vetorInteiro[j] < vetorInteiro[menorIndice]) {
                    menorIndice = j;
                }
            }

            int numSalvo = vetorInteiro[menorIndice];
            vetorInteiro[menorIndice] = vetorInteiro[i];
            vetorInteiro[i] = numSalvo;
        }
        long finalExecucao = System.currentTimeMillis();
        
        double tempoExecucao = (finalExecucao - inicioExecucao) / 1000;
        
        JOptionPane.showMessageDialog(null, "Tempo de execucao do loop: " + tempoExecucao + " segundos.");
    }
    
    public static void ordenacaoBolha(int[] vetorInteiro) {
        
        long inicioExecucao = System.currentTimeMillis();
        int n = vetorInteiro.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (vetorInteiro[j] > vetorInteiro[j + 1]) {
                    int numSalvo = vetorInteiro[j];
                    vetorInteiro[j] = vetorInteiro[j + 1];
                    vetorInteiro[j + 1] = numSalvo;
                }
            }
        }
        long finalExecucao = System.currentTimeMillis();
        
        double tempoExecucao = (finalExecucao - inicioExecucao) / 1000;
        
        JOptionPane.showMessageDialog(null, "Tempo de execucao do loop: " + tempoExecucao + " segundos.");
    }
    
    public static void exibicaoVetores(String nomeArquivo) {
        List<Integer> listaInteiros = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                int n = Integer.parseInt(linha);
                listaInteiros.add(n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Elementos do arquivo:\n");

        for (int n : listaInteiros) {
            mensagem.append(n);
            mensagem.append("\n");
        }
        
        JTextArea janela = new JTextArea(mensagem.toString());
        JScrollPane barrinha = new JScrollPane(janela);
        
        JFrame frame = new JFrame("Exibição do Vetor");
        frame.setSize(300, 800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(barrinha);
        frame.setVisible(true);
    }
    
    public static void exibicaoPesquisa() {
        List<Integer> listaInicial = new ArrayList<>();
        List<Integer> listaFinal = new ArrayList<>();
        
        boolean entradaCorreta = false;       
        int elemento = 0;
        
        while (!entradaCorreta) {            
            String elementoPesquisado = JOptionPane.showInputDialog(null, "Informe o elemento a ser pesquisado:");
            try {
                elemento = Integer.parseInt(elementoPesquisado);
                entradaCorreta = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida! Digite um número inteiro válido.");
            }
        }
        
        try (BufferedReader leitorInicial = new BufferedReader(new FileReader("VetorInicial.txt"))) {
            String linha;
            while ((linha = leitorInicial.readLine()) != null) {
                int n = Integer.parseInt(linha);
                listaInicial.add(n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try (BufferedReader leitorFinal = new BufferedReader(new FileReader("VetorFinal.txt"))) {
            String linha;
            while ((linha = leitorFinal.readLine()) != null) {
                int n = Integer.parseInt(linha);
                listaFinal.add(n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Posicões em que o " + elemento + " aparece.\n\n Vetor Inicial:\n");
        
        for (int i = 0; i < listaInicial.size(); i++) {
            if (listaInicial.get(i) == elemento) {
                mensagem.append(i);
                mensagem.append("\n");
            }
        }
        
        mensagem.append("\n Vetor Final:\n");
        
        for (int i = 0; i < listaFinal.size(); i++) {
            if (listaFinal.get(i) == elemento) {
                mensagem.append(i);
                mensagem.append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, mensagem.toString());
    }
    
    public static void txtVetor(int[] vetorInteiro, String nomeArquivo) {
        
        nomeArquivo += ".txt";
        try {
            FileWriter escrever = new FileWriter(nomeArquivo);
            
            for (int n : vetorInteiro) {
                escrever.write(String.valueOf(n));
                escrever.write("\n");
            }
            
            escrever.close();
            File arquivo = new File(nomeArquivo);
            JOptionPane.showMessageDialog(null, "Arquivo com os números do vetor salvo em: " + arquivo.getAbsolutePath());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o arquivo " + nomeArquivo);
            e.printStackTrace();
        }
    }
}