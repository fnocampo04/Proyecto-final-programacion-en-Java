package BatallaNaval;


import java.security.InvalidParameterException;

import java.util.*;


public class Tablero {
    final int tamano, numBarcos;

    public int[][] getMiTablero() {
        return miTablero;
    }

    int[][] miTablero;

    public int[][] getTableroPines() {
        return tableroPines;
    }

    int[][] tableroPines;



    public Tablero(int tamano, int numBarcos) {

        if (tamano <= 0)
            throw new InvalidParameterException("Tamaño de tablero invalido! No puede ser <= 0");
        if (numBarcos <= 0)
            throw new InvalidParameterException("Numero de barcos invalido! No puede ser <= 0");

        this.tamano = tamano;
        this.numBarcos = numBarcos;

        crearMiTablero();
        crearTableroPines();
    }
    public void crearMiTablero() {
        miTablero = new int[tamano][tamano];
    }

    public void crearTableroPines(){
        tableroPines = new int[tamano][tamano];
    }
    public void mostrarMiTablero(){

        System.out.println("            "+ ConsoleColors.CYAN_BOLD_BRIGHT + "MI TABLERO" + ConsoleColors.RESET);

        char ch = 'A';
        System.out.printf(ConsoleColors.CYAN_BACKGROUND + "   " + ConsoleColors.RESET);
        for(int i = 1; i <= 10; i++)
        {
            System.out.printf( ConsoleColors.BLACK_BACKGROUND + " " + i + " " + ConsoleColors.RESET);
        }

        System.out.println();
        //System.out.println(ConsoleColors.BLACK_BACKGROUND +"                                   " + ConsoleColors.RESET); // Separador
        for (int i = 0; i < miTablero.length; i++) {
            System.out.printf(ConsoleColors.BLACK_BACKGROUND + " " + ch + " " + ConsoleColors.RESET); // Pone la letra en la fila

            for (int j = 0; j < miTablero[i].length; j++) {
                if(miTablero[i][j] == 0){
                    System.out.print(ConsoleColors.BLUE_BACKGROUND + " " + miTablero[i][j] + " " + ConsoleColors.RESET);
                } else if (miTablero[i][j] == 1) {
                    System.out.print(ConsoleColors.WHITE_BACKGROUND_BRIGHT + " " + miTablero[i][j] + " " + ConsoleColors.RESET);
                } else if (miTablero[i][j] == 2) {
                    System.out.print(ConsoleColors.RED_BACKGROUND_BRIGHT + " " + miTablero[i][j] + " " + ConsoleColors.RESET);
                } else if (miTablero[i][j] == 3) {
                    System.out.print(ConsoleColors.GREEN_BACKGROUND_BRIGHT + " " + miTablero[i][j] + " " + ConsoleColors.RESET);
                }
                //System.out.print(miTablero[i][j] + "  ");
            }
            ch++; // Siguiente letra
            System.out.println();
        }
        System.out.println();
    }
    public void posicionarBarcos(){

        for (int numBarco = 2; numBarco<=numBarcos; numBarco++){
            boolean satisfactorio = false;
            while (satisfactorio == false){
                mostrarMiTablero();
        int posicionX = 0;
        int posicionY = 0;

        System.out.println("Ubica el barco de tamaño: "+ numBarco);
        boolean bool = true;
        boolean bool2 = true;
        while (bool){
            int coordenadaX = 0;
            System.out.println("Por favor ingrese la letra correspondiente a la fila (A - J): ");
            Scanner sc = new Scanner(System.in);
            String sc1 = sc.nextLine();
            if (sc1.length() <2){

            char c = sc1.charAt(0);

            for (char ch = 'A'; ch <='J'; ch++){
                if (c == ch){
                    posicionX = coordenadaX;
                    bool = false;
                }
                coordenadaX++;
            }
            }else{
                bool = true;
            }
        }
        while (bool2){
            int coordenadaY = 0;

            Scanner sc = new Scanner(System.in);
            boolean esNumero = false;
            int sn = 0;
            while (esNumero == false){
                try{
                    System.out.println("Por favor ingrese el número correspondiente a la columna (1 - 10): ");

                String s = sc.nextLine();
                esNumero = s.chars().allMatch( Character::isDigit );
                if (esNumero == true){
                    sn=Integer.parseInt(s);
                }else{
                    System.out.println("¡Debe ser un número!");
                    System.out.println("Digite la columna nuevamente");
                }
                }catch (NumberFormatException e){
                    esNumero = false;
                }
            }

            for (int i = 1; i <= 10; i++){
                if (sn == i){
                    posicionY = coordenadaY;
                    bool2 = false;
                }
                coordenadaY++;
            }
        }
        if (miTablero[posicionX][posicionY] == 0){
        miTablero[posicionX][posicionY] = 1;
        mostrarMiTablero();
        System.out.println("Ingrese la dirección en que quiere el barco");
        System.out.println("A: Arriba | B: Abajo | D: Derecha | I: Izquierda");
        Scanner sc = new Scanner(System.in);
        try {
        String direccion = sc.nextLine();
            switch (direccion) {

                case "A":
                    // Arriba

                    int contador = 1;
                    for (int i = posicionX-1;contador<numBarco; i--){
                        if (miTablero[i][posicionY]==0){
                            satisfactorio= true;
                        }else{
                            satisfactorio= false;
                            System.out.println("Posicion inválida");
                            miTablero[posicionX][posicionY] = 0;
                            break;
                        }
                        contador++;
                    }
                    contador = 1;
                    if(satisfactorio){
                        for (int i = posicionX-1;contador<numBarco; i--){
                                miTablero[i][posicionY] = 1;
                            contador++;
                        }
                    }
                    break;
                case "B":
                    // Abajo

                    contador = 1;
                    for (int i = posicionX+1;contador<numBarco; i++){
                        if (miTablero[i][posicionY]==0){
                            satisfactorio= true;
                        }else{
                            satisfactorio= false;
                            System.out.println("Posicion inválida");
                            miTablero[posicionX][posicionY] = 0;
                            break;
                        }
                        contador++;
                    }
                    contador = 1;
                    if(satisfactorio){
                        for (int i = posicionX+1;contador<numBarco; i++){
                            miTablero[i][posicionY] = 1;
                            contador++;
                        }
                    }
                    break;

                case "I" :
                    // Izquierda

                    contador = 1;
                    for (int i = posicionY-1;contador<numBarco; i--){
                        if (miTablero[posicionX][i]==0){
                            satisfactorio= true;
                        }else{
                            satisfactorio= false;
                            System.out.println("Posicion inválida");
                            miTablero[posicionX][posicionY] = 0;
                            break;
                        }
                        contador++;
                    }
                    contador = 1;
                    if(satisfactorio){
                        for (int i = posicionY-1;contador<numBarco; i--){
                            miTablero[posicionX][i] = 1;
                            contador++;
                        }
                    }
                    break;

                case "D" :
                    // Derecha

                    contador = 1;
                    for (int i = posicionY+1;contador<numBarco; i++){
                        if (miTablero[posicionX][i]==0){
                            satisfactorio= true;
                        }else{
                            satisfactorio= false;
                            System.out.println("Posicion inválida");
                            miTablero[posicionX][posicionY] = 0;
                            break;
                        }
                        contador++;
                    }
                    contador = 1;
                    if(satisfactorio){
                        for (int i = posicionY+1;contador<numBarco; i++){
                            miTablero[posicionX][i] = 1;
                            contador++;
                        }
                    }
                    break;

                default:
                    System.out.println("Dirección no válida, ingrese nuevamente");
                    miTablero[posicionX][posicionY]=0;
                    satisfactorio= false;
                    break;
                    // Default secuencia de sentencias.
            }
        }catch (ArrayIndexOutOfBoundsException e){ // Este catch para cuando se sale de la matriz
            System.out.println("Dirección no válida, ingrese nuevamente");
            miTablero[posicionX][posicionY]=0;
            satisfactorio= false;

        }

        }else{
            System.out.println("La posición ya está ocupada intente nuevamente");
            satisfactorio = false;
        }
        }
        }
    }



    public void mostrarTableroPines(){

        System.out.println("          "+ ConsoleColors.RED_BOLD_BRIGHT + "TABLERO ATAQUE" + ConsoleColors.RESET);

        char ch = 'A';
        System.out.printf(ConsoleColors.RED_BACKGROUND + "   " + ConsoleColors.RESET);
        for(int i = 1; i <= 10; i++)
        {
            System.out.printf( ConsoleColors.BLACK_BACKGROUND + " " + i + " " + ConsoleColors.RESET);
        }

        System.out.println();

        for (int i = 0; i < tableroPines.length; i++) {
            System.out.printf(ConsoleColors.BLACK_BACKGROUND + " " + ch + " " + ConsoleColors.RESET); // Pone la letra en la fila

            for (int j = 0; j < tableroPines[i].length; j++) {
                if(tableroPines[i][j] == 0){
                    System.out.print(ConsoleColors.BLUE_BACKGROUND + " " + tableroPines[i][j] + " " + ConsoleColors.RESET);
                } else if (tableroPines[i][j] == 1) {
                    System.out.print(ConsoleColors.WHITE_BACKGROUND_BRIGHT + " " + tableroPines[i][j] + " " + ConsoleColors.RESET);
                } else if (tableroPines[i][j] == 2) {
                    System.out.print(ConsoleColors.RED_BACKGROUND_BRIGHT + " " + tableroPines[i][j] + " " + ConsoleColors.RESET);
                } else if (tableroPines[i][j] == 3) {
                    System.out.print(ConsoleColors.GREEN_BACKGROUND_BRIGHT + " " + tableroPines[i][j] + " " + ConsoleColors.RESET);
                }

            }
            ch++; // Siguiente letra
            System.out.println();
        }
        System.out.println();}

    public boolean hayBarcos() {
        for (int i = 0; i < miTablero.length; i++) {
            for (int j = 0; j < miTablero[i].length; j++) {
                if (miTablero[i][j] == 1){
                    return true;
                }

            }

    }
        return false;
    }




}
