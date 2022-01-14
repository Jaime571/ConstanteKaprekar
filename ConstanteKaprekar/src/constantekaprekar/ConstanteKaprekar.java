package constantekaprekar;

import java.util.Arrays;
import java.util.Scanner;

public class ConstanteKaprekar 
{
    public static void main(String[] args) 
    {
        Scanner dato = new Scanner(System.in);//Se crea el objeto desde Scanner para poder leer datos desde consola.
        System.out.println("Ingrese un numero: ");
        int numeroUsuario = dato.nextInt();//Asignamos el objeto a una variable. ¿PODRÍA CAMBIAR A STRING?
        int noIteracion = 0;
        
        //int a = VerificarDato(numeroUsuario);
        if(VerificarDato(numeroUsuario))
        {
            System.out.println("Iteracion.\tNumero Ingresado.\tDato Mayor.\tDato Menor\tResultado.");
            procesarDato(numeroUsuario, noIteracion);
        }
        else
            System.out.println("El numero ingresado tiene mas de 2 numeros iguales, vuelva a intentar.");
    }

    private static boolean VerificarDato(int numeroUsuario)//Este método lo utilizo solo para continuar o no en el primer if del main
    { 
        if(0 <= numeroUsuario && numeroUsuario <= 9999) //Verificamos que la cifra no contenga mas de 4 numeros y sea entera.
        {
            if(numeroUsuario == 0000)
                return false;
            
            int excepciones = 1111; //Variable que comineza con la primera excepcion de todos los digitos iguales.
            
            do
            {
                if(excepciones%numeroUsuario != 0)//Verifica que el residuo sea 0.
                    excepciones += 1111;//Incrementa de 1111 en 1111 para poder comparar todos los valores hasta 9999.
                else
                    return false;//El dato fue encontrado como incompatible.
            }
            while(excepciones < 9999);//Lo hacemos hasta que excepciones alcance el valor de 9999.   
        }
        return true;//Decimos que el dato no tuvo ningún problema.
    }
    
    private static int procesarDato(int numeroVerificado, int noIteracion)//Procesa el dato vaciando cada caracter a un arreglo.
    {
        String conversion = String.valueOf(numeroVerificado);//Se transforma a cadena el número enviado para poder agregarlo al arreglo.
        String numeroACadena = CompletarDatos(conversion);
        char [] datoArregloMayor = new char [4]; //Arreglo de 4 lugares para poder almacenar los datos del String sin ser valor ASCII
        char [] datoArregloMenor = new char [4];
        for(int i = 0; i < numeroACadena.length(); i++)
        {
            datoArregloMayor[i] = numeroACadena.charAt(i); //Se va agregando al arreglo cada dato.
            datoArregloMenor[i] = numeroACadena.charAt(i);
        }
        /*-----------------------------Ordenamiento de los arreglos-----------------------------------*/
        Arrays.sort(datoArregloMenor);//Ordena de menor a mayor
        for (int i = 0 ; i < datoArregloMayor.length - 1 ; i++) //Ordena de mayor a menor. Recorre todo el arreglo
        {
            int max = i;
            for (int j = i + 1 ; j < datoArregloMayor.length ; j++)  //Busca el mayor número
                if (datoArregloMayor[j] > datoArregloMayor[max])
                    max = j;    //Encuentra el mayor número
            
            if (i != max) //Se permutan los valores
            {
                char aux = datoArregloMayor[i];
                datoArregloMayor[i] = datoArregloMayor[max];
                datoArregloMayor[max] = aux;
            }
        }
        int nuevoNumero = Kaprekar(numeroVerificado, datoArregloMayor, datoArregloMenor, noIteracion);
        return nuevoNumero; //Kaprekar(mayor, menor);//Este método regresa el resultado de la resta de los números ordenados.
    }
    
    public static int Kaprekar(int numeroVerificado, char[] datoArregloMayor, char[] datoArregloMenor, int noIteracion)//Hacemos la resta de los números ordenados para obtener el nuevo número.
    {
        //Hago enteros los arreglos de caracteres
        int datoMenor = Integer.parseInt(new String(datoArregloMenor));
        int datoMayor = Integer.parseInt(new String(datoArregloMayor));
        
        int diferenciaMayorMenor = datoMayor - datoMenor;//Realiza la resta de los datos ordenados.
        noIteracion ++;//Aumenta el número de iteración.
        
        String conversion = String.valueOf(diferenciaMayorMenor);//Convierto la resta a String para poder completarla en caso de que sea necesario.
        String resta = CompletarDatos(conversion);//Asigno el valor de retorno del m[etodo a una variable para poder imprimirla.
        System.out.println(noIteracion + "\t\t" + numeroVerificado + "\t\t\t" + String.valueOf(datoArregloMayor) + "\t\t" + String.valueOf(datoArregloMenor) + "\t\t" + resta);//Se imprimen los datos.
        
        if(diferenciaMayorMenor != 6174)//En caso de que la diferencia de datoMayor y datoMenor sea diferente de la constante de Kaprekar, envía el nuevo dato a ser procesado nuevamente.
            return procesarDato(diferenciaMayorMenor, noIteracion);//Manda el dato resultante a ser tratado y reordenado otra vez.
        else
            return diferenciaMayorMenor;//En caso de que sea el número, lo retornamos.
    }
    
    public static String CompletarDatos (String datoACompletar)
    {
        //Se revisa la longitud del arreglo, en caso de que no sea 
        if(datoACompletar.length() == 3)
            datoACompletar = "0"+datoACompletar;
        if(datoACompletar.length() == 2)
            datoACompletar = "00"+datoACompletar;
        if(datoACompletar.length() == 1)
            datoACompletar = "00"+datoACompletar;
        return datoACompletar;
    }
}