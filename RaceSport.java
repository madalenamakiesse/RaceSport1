/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racesport;

import java.io.File;
import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * @author Madalena Makiessse
 * @author Márcia Buienga
 * @author Omar
 * @author Pinto Soque
 */
public class RaceSport {

    /**
     * @param args the command line arguments
     */
    
    static int i;
    /* Essa função auxilia no somatório dos pontos de cada piloto , 
    pegando assim o vector de registro o nome do piloto e a equipa */
    static ClassCampeonato[] Pontuar(ClassCampeonato[] listPilot, String nome, int ponto) {
        int cont;
        for (cont = 0; cont < listPilot.length; cont++) {
            if (listPilot[cont].nome.equalsIgnoreCase(nome)) {
                listPilot[cont].pontos = listPilot[cont].pontos + ponto;
                return listPilot;
            }
        }
        return listPilot;
    }

    /* Essa função auxilia na ordenação dos Pilotos em relação aos somatório dos pontos de cada piloto, 
    recebendo assim, o vector de registro e ordenando segundo o ponto de cada equipe*/
    static ClassCampeonato[] OrdenarPorPonto(ClassCampeonato[] listPilot) {
        int cont1, cont2, auxPont;
        String auxNome, auxEquip;
        for (cont1 = 0; cont1 < listPilot.length; cont1++) {
            for (cont2 = 0; cont2 < listPilot.length - 1; cont2++) {
                if (listPilot[cont2].pontos < listPilot[cont2 + 1].pontos) {
                    auxPont = listPilot[cont2].pontos;
                    auxNome = listPilot[cont2].nome;
                    auxEquip = listPilot[cont2].equipa;
                    listPilot[cont2].pontos = listPilot[cont2 + 1].pontos;
                    listPilot[cont2].nome = listPilot[cont2 + 1].nome;
                    listPilot[cont2].equipa = listPilot[cont2 + 1].equipa;
                    listPilot[cont2 + 1].pontos = auxPont;
                    listPilot[cont2 + 1].nome = auxNome;
                    listPilot[cont2 + 1].equipa = auxEquip;

                }
            }
        }

        return listPilot;
    }

    /* Essa função auxilia na ordenação dos Pilotos em relação aos somatório dos pontos de cada piloto, 
    caso os pilotos estejam com os mesmos pontos, ela ordene em ordem alfabética comparando cada letra dos nomes dos pilotos
    Também ordena a lista de equipes que estejam com o mesmo ponto comparando assim letra por letra de cada equipa*/
    static ClassCampeonato[] OrdenarPorPontoIguais(ClassCampeonato[] listPilot) {
        int cont1, cont2, auxPont, auxCont = 0;
        String auxNome, auxEquip;
        for (cont1 = 0; cont1 < listPilot.length; cont1++) {
            for (cont2 = 0; cont2 < listPilot.length - 1; cont2++) {
                if (listPilot[cont2].pontos == listPilot[cont2 + 1].pontos) {
                    while (auxCont < listPilot[cont2].nome.length() && auxCont < listPilot[cont2 + 1].nome.length() && listPilot[cont2].nome.charAt(auxCont) == listPilot[cont2 + 1].nome.charAt(auxCont)) {
                        auxCont++;
                    }
                    if (listPilot[cont2].nome.charAt(auxCont) > listPilot[cont2 + 1].nome.charAt(auxCont)) {
                        auxPont = listPilot[cont2].pontos;
                        auxNome = listPilot[cont2].nome;
                        auxEquip = listPilot[cont2].equipa;
                        listPilot[cont2].pontos = listPilot[cont2 + 1].pontos;
                        listPilot[cont2].nome = listPilot[cont2 + 1].nome;
                        listPilot[cont2].equipa = listPilot[cont2 + 1].equipa;
                        listPilot[cont2 + 1].pontos = auxPont;
                        listPilot[cont2 + 1].nome = auxNome;
                        listPilot[cont2 + 1].equipa = auxEquip;
                    }
                    auxCont = 0;
                }
            }
            auxCont = 0;
        }
        return listPilot;
    }

    /*Função para destribuir pontos por cada equipa
    Fazendo assim o somatório dos pontos de cada equipe*/
    static ClassCampeonato[] DestPontoEquip(ClassCampeonato[] listPilot) {
        int cont1, cont2, auxPont = 0, auxCont = 0, somarPont = 0, i;
        String auxNome, auxEquip;

        //Este ciclo Conta quantas equipas tem ignorando a repetição valores tem sem repetição
        for (cont1 = 0; cont1 < listPilot.length; cont1++) {
            for (cont2 = 0; cont2 < cont1; cont2++) {
                if (listPilot[cont1].equipa.equals(listPilot[cont2].equipa)) {
                    auxCont++;
                }
            }
            if (auxCont == 0) {
                auxPont++;
            }
            auxCont = 0;
        }

        ClassCampeonato[] ccamp = new ClassCampeonato[auxPont];

        auxPont = 0;

        //Este ciclo destribui as equipas no vector de registro sem repetição
        for (cont1 = 0; cont1 < listPilot.length; cont1++) {
            for (cont2 = 0; cont2 < cont1; cont2++) {
                if (listPilot[cont1].equipa.equals(listPilot[cont2].equipa)) {
                    auxCont++;
                }
            }
            if (auxCont == 0) {
                ccamp[auxPont] = new ClassCampeonato();
                ccamp[auxPont].nome = listPilot[cont1].equipa;
                ccamp[auxPont].pontos = 0;
                auxPont++;
            }
            auxCont = 0;
        }
        //Este ciclo faz o somatório dos pontos das equipas cujo tem um ou mais pilotos
        for (cont1 = 0; cont1 < ccamp.length; cont1++) {
            for (cont2 = 0; cont2 < listPilot.length; cont2++) {
                if (ccamp[cont1].nome.equals(listPilot[cont2].equipa)) {

                    ccamp[cont1].pontos += listPilot[cont2].pontos;
                }
            }
        }

        ccamp = OrdenarPorPonto(ccamp);
        return ccamp;
    }

    // Função de auxílio na busca do tempo para o nome do ficheiro
    static String nomeFileSaida() {
        //Pega o tempo actual
        Calendar c = Calendar.getInstance();
        String ano = String.valueOf(c.get(Calendar.YEAR));
        String mes = String.valueOf(c.get(Calendar.MONTH) + 1);
        String dia = String.valueOf(c.get(Calendar.DAY_OF_MONTH));

        String hora = String.valueOf(c.get(Calendar.HOUR));
        String minuto = String.valueOf(c.get(Calendar.MINUTE));
        String segundo = String.valueOf(c.get(Calendar.SECOND));
        String fileSaida = ano + mes + dia + hora + minuto + segundo + ".txt";

        return fileSaida;
    }

    /*
    Esta função guarda os dados no ficheiro
     */
    static void guardarFile(String nomeCamp, int totProvas, ClassCampeonato[] cPilot, ClassCampeonato[] cEquipas, int totPilot) {

        String nomeArquivo = nomeFileSaida();
        FileWriter fwArquivo;
        BufferedWriter fsaida;
        try {
            File arquivo = new File(nomeArquivo);

            //Se o arquivo já existir, então abrir para concatenação, caso contrário criar novo arquivo
            fwArquivo = new FileWriter(arquivo, arquivo.exists());
            fsaida = new BufferedWriter(fwArquivo);
//
            fsaida.write(nomeCamp + "\n");
            fsaida.newLine();
            fsaida.write("Classificados apos " + totProvas + " provas realizadas\n");
            fsaida.newLine();
            fsaida.write("Pilotos\n");

            for (i = 0; i < totPilot; i++) {
                fsaida.write((i + 1) + ". " + cPilot[i].nome + ", " + cPilot[i].equipa + ", " + cPilot[i].pontos + "\n");
            }

            fsaida.newLine();
            fsaida.write("Equipas\n");

            for (i = 0; i < cEquipas.length; i++) {
                fsaida.write((i + 1) + ". " + cEquipas[i].nome + ", " + cEquipas[i].pontos + "\n");
            }

            fsaida.newLine();

            fsaida.close();
            fwArquivo.close();

        } catch (IOException e) {
            System.err.println("Erro ao tentar escrever no arquivo: " + e.toString());
        }
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner tecla = new Scanner(System.in);
        int op, numCamp, cont = 0, numPilot;
        String lerString, fileName, nomCamp, fileNameSaida;

        System.out.println("\t\tBem Vindo Ao Campeonato de Corridas Race Sport XPTO\n\n");
        do {
            System.out.println("1-Ler Via Ficheiro\n2-Ler Via Teclado");
            System.out.print("Escolhe uma opção: ");
            op = tecla.nextInt();
            if (op != 1 && op != 2) {
                System.out.println("Opção Incorrecta");
            }
        } while (op != 1 && op != 2);
        if (op == 1) {
            //Leituras d ficheiros de entrada
            tecla.nextLine();
            System.out.print("Digite o nome do Ficheiro : ");
            fileName = tecla.nextLine();
            fileName = fileName.concat(".txt");

            BufferedReader f = null;
            BufferedWriter fsaida = null;
            try {
                f = new BufferedReader(new FileReader(fileName));
            } catch (IOException e) {
                System.out.println("Não é possível abrir o ficheiro " + fileName);
                System.out.println("Exit");
                return;
            }

            lerString = f.readLine();
            numCamp = Integer.parseInt(lerString);
            lerString = f.readLine();

            //Ciclo para Destribuir pontos nos pilotos e nas equipas
            do {
                //Verifica se o ficheiro está vazio
                if (lerString.length() == 0) {
                    lerString = f.readLine();
                }

                nomCamp = lerString;

                /*
                lerString é responsável por pegar os valores do ficheiro como via teclado e destribuir noutras variáveis
                
                 */
                lerString = f.readLine();

                lerString = f.readLine();
                //numPilot é o número total de pilotos em cada corrida
                numPilot = Integer.parseInt(lerString);

                ClassCampeonato[] cc = new ClassCampeonato[numPilot];
                String[] VetPilot = new String[numPilot];

                lerString = f.readLine();
                /*
                 ciclo responsável por repartir a String em três parte, o nome do piloto, a equipa e os pontos
                 facilitando assim a distribuição dos pontos. 
                 */
                for (i = 0; i < numPilot; i++) {

                    cc[i] = new ClassCampeonato();

                    VetPilot[i] = lerString;

                    String[] vet = lerString.split(", ");

                    cc[i].nome = vet[0];
                    cc[i].equipa = vet[1];

                    cc[i].pontos = 0;
                    lerString = f.readLine();
                }

                lerString = f.readLine();

                String[] vet = lerString.split(" ");
                int[] listPontos = new int[vet.length - 1];
                int PonDist = Integer.parseInt(vet[0]);

                //Ciclo que distribui os pontos num vector
                for (i = 0; i < listPontos.length; i++) {
                    listPontos[i] = Integer.parseInt(vet[i + 1]);
                }

                lerString = f.readLine();
                lerString = f.readLine();

                String nomeProva;
                int contProvas = 0, numProva = Integer.parseInt(lerString);
                lerString = f.readLine();

                do {

                    lerString = f.readLine();

                    nomeProva = lerString;

                    lerString = f.readLine();

                    /*ciclo responsável por destribuir pontos em cada equipe segundo as provas e o posicionamento dos pilotos       
                     */
                    for (i = 0; i < PonDist; i++) {
                        String[] separStr = lerString.split(", ");
                        cc = Pontuar(cc, separStr[0], listPontos[i]);
                        lerString = f.readLine();
                    }

                    /*ciclo usado para continuar a contagem dos pilotos depois de terminar as pontuções da prova*/
                    while (i != numPilot) {
                        i++;
                        lerString = f.readLine();
                    }

                    contProvas++;

                } while (contProvas != numProva);

                cc = OrdenarPorPonto(cc);
                cc = OrdenarPorPontoIguais(cc);

                ClassCampeonato[] cEquip = DestPontoEquip(cc);
                cEquip = OrdenarPorPontoIguais(cEquip);

                guardarFile(nomCamp, numProva, cc, cEquip, numPilot);

                cont++;
                if (numCamp != cont) {
                    lerString = f.readLine();
                }
            } while (numCamp != cont);

            f.close();

        } 
        else {

            tecla.nextLine();

            //Lituras d ficheiros de entrada
            do{
                System.out.print("Número de campeonatos: ");
                numCamp = tecla.nextInt();
            }while(numCamp<=0);
            System.out.println("");
            do {

                System.out.print("Nome do campeonato, seu ano de realização: ");
                lerString = tecla.nextLine();

                //nomCamp abreviatura d nome do campionato, variávl que guarda o nome do campeonato
                nomCamp = lerString;

                System.out.println("");
                System.out.print("Número de pilotos: ");
                lerString = tecla.nextLine();

                numPilot = Integer.parseInt(lerString);

                ClassCampeonato[] cc = new ClassCampeonato[numPilot];
                String[] VetPilot = new String[numPilot];
                for (i = 0; i < numPilot; i++) {
                    System.out.print("Nome do piloto, nome da sua equipa: ");
                    lerString = tecla.nextLine();
                    cc[i] = new ClassCampeonato();
                    VetPilot[i] = lerString;
                    String[] vet = lerString.split(", ");
                    cc[i].nome = vet[0];
                    cc[i].equipa = vet[1];
                    cc[i].pontos = 0;
                }

                System.out.print("\n\nLista de pontos a destribuir: ");
                lerString = tecla.nextLine();
                String[] vet = lerString.split(" ");

                int[] listPontos = new int[vet.length - 1];

                int PonDist = Integer.parseInt(vet[0]);

                for (i = 0; i < listPontos.length; i++) {
                    listPontos[i] = Integer.parseInt(vet[i + 1]);
                }

                System.out.println("");
                System.out.print("Número provas: ");
                lerString = tecla.nextLine();
                String nomeProva;

                int contMinCamp = 0, numProvas = Integer.parseInt(lerString);

                do {
                    System.out.println("");
                    System.out.print("Nome da prova: ");
                    lerString = tecla.nextLine();
                    nomeProva = lerString;

                    for (i = 0; i < numPilot; i++) {
                        System.out.print("Nome do piloto, nome da sua equipa: ");
                        lerString = tecla.nextLine();
                        String[] separStr = lerString.split(", ");
                        if (i < listPontos.length) {
                            cc = Pontuar(cc, separStr[0], listPontos[i]);
                        } else {
                            cc = Pontuar(cc, separStr[0], listPontos[i]);
                        }
                    }
                    contMinCamp++;
                } while (contMinCamp != numProvas);

                cc = OrdenarPorPonto(cc);
                cc = OrdenarPorPontoIguais(cc);

                ClassCampeonato[] cEquip = DestPontoEquip(cc);
                cEquip = OrdenarPorPontoIguais(cEquip);
                guardarFile(nomCamp, numProvas, cc, cEquip, numPilot);
                cont++;
            } while (numCamp != cont);
        }
    }
}
