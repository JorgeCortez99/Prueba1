/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.generala;

/**
 *
 * @author Marcelo
 */
public class Generala {

    protected int[] dice;
    public Generala(int d1, int d2, int d3, int d4, int d5)
    {
        dice = new int[]{d1,d2,d3,d4,d5};
    }

    public static int chance(int d1, int d2, int d3, int d4, int d5)
    {
        int total = 0;
        int valorDados[] = {d1,d2,d3,d4,d5};
        for (int i = 0;i<valorDados.length;i++){
            total+=valorDados[i];
        }
        return total;
    }

    // '(int... dice)' es similar a tener public static int generala(int d1, int d2, int d3 , etc) pero permite realizar operaciones como -> for (int die : dice)
    //es una forma de decir que el metodo puede aceptar 1 o más parametros de tipo int ... lista de parametros dinamicos.
    public static int generala(int... dice)
    {
        int[] counts = new int[6];
        for (int die : dice)
            counts[die-1]++;
        for (int i = 0; i != 6; i++)
            if (counts[i] == 5)
                return 50;
        return 0;


    }
    public static int sumar(int [] dados, int numero){
        int sum = 0;
        for (int i = 0; i<dados.length;i++){
            if(dados[i]==numero){
                sum+=numero;
            }
        }
        return sum;
    }

    public static int ones(int d1, int d2, int d3, int d4, int d5) {
        int valorDados [] = {d1,d2,d3,d4,d5};
        return sumar(valorDados,1);
    }

    public static int twos(int d1, int d2, int d3, int d4, int d5) {
        int valorDados [] = {d1,d2,d3,d4,d5};
        return sumar(valorDados,2);
    }

    public static int threes(int d1, int d2, int d3, int d4, int d5) {
        int valorDados [] = {d1,d2,d3,d4,d5};
        return sumar(valorDados,3);
    }

    public int fours() {
        return sumar(dice,4);
    }

    public int fives() {
        return sumar(dice,5);
    }

    public int sixes() {
        return sumar(dice,6);
    }

    public static int score_pair(int d1, int d2, int d3, int d4, int d5)
    {
        int valorDados[] = {d1,d2,d3,d4,d5};
        int[] counts = new int[6];

        for (int i=0;i<valorDados.length;i++){
            counts[valorDados[i]-1]++;
        }
        return encontrarParesMayores(counts);
    }

    public static int encontrarParesMayores(int [] counts){
        int at;
        for (at = 0; at != 6; at++)
            if (counts[6-at-1] >= 2)
                return (6-at)*2;
        return 0;
    }

    public static int two_pair(int d1, int d2, int d3, int d4, int d5)
    {
        int [] valorDados = {d1,d2,d3,d4,d5};
        int[] counts = new int[6];
        for (int i=0;i<valorDados.length;i++){
            counts[valorDados[i]-1]++;
        }
        return encontrarPares(counts);
    }

    public static int encontrarPares(int [] counts){
        int n = 0;
        int score = 0;
        for (int i = 0; i < 6; i += 1)
            if (counts[6-i-1] >= 2) {
                n++;
                score += (6-i);
            }
        if (n == 2)
            return score * 2;
        else
            return 0;
    }

    public static int four_of_a_kind(int d1, int d2, int d3, int d4, int d5)
    {
        int [] valorDados = {d1,d2,d3,d4,d5};
        int[] tallies = new int[6];
        for (int i=0;i<valorDados.length;i++){
            tallies[valorDados[i]-1]++;
        }
        return numerosIguales(tallies,4);
    }

    public static int numerosIguales(int [] counts, int valorDado){
        for (int i = 0; i < 6; i++)
            if (counts[i] >= valorDado)
                return (i+1) * valorDado;
        return 0;
    }

    public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5)
    {
        int [] valorDados = {d1,d2,d3,d4,d5};
        int[] tallies = new int[6];
        for (int i=0;i<valorDados.length;i++){
            tallies[valorDados[i]-1]++;
        }
        return numerosIguales(tallies,3);
    }

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int [] valorDados = {d1,d2,d3,d4,d5};
        int[] tallies = new int[6];
        for(int i=0;i<valorDados.length;i++) {
            tallies[valorDados[i] - 1]++;
        }
        return encontrarValores(tallies,0, 15);
    }
    public static int encontrarValores(int [] tallies, int diferencia, int puntaje){
        for (int i=0;i<5;i++){
            if(tallies[i+diferencia]!=1){
                return 0;
            }
        }
        return puntaje;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int [] valorDados = {d1,d2,d3,d4,d5};
        int[] tallies = new int[6];
        for(int i=0;i<valorDados.length;i++){
            tallies[valorDados[i]-1]++;
        }
        return encontrarValores(tallies, 1, 20);
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5)
    {
        int [] valorDados={d1,d2,d3,d4,d5};
        int[] tallies;
        boolean esPar = false;
        int valorPar = 0;
        boolean esTrio = false;
        int valorTrio = 0;

        tallies = new int[6];
        for(int i=0;i< valorDados.length;i++){
            tallies[valorDados[i]-1] +=1;
        }

        esPar=tieneCategoria(tallies,2);
        esTrio=tieneCategoria(tallies,3);
        valorPar=buscarValor(tallies,2);
        valorTrio=buscarValor(tallies,3);

        if (esPar && esTrio)
            return valorPar * 2 + valorTrio * 3;
        else
            return 0;
    }

    public static int buscarValor(int [] tallies, int valorBuscado){
        int valorEncontrado = 0;
        for (int i = 0; i != 6; i += 1) {
            if (tallies[i] == valorBuscado) {
                valorEncontrado = i + 1;
                return valorEncontrado;
            }
        }
        return 0;
    }

    public static boolean tieneCategoria(int [] tallies, int valorBuscado) {
        boolean categoria = false;
        for (int i = 0; i != 6; i += 1){
            if (tallies[i] == valorBuscado) {
                categoria = true;
                return categoria;
            }
        }
        return false;
    }

}

