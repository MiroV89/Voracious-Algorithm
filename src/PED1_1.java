/* 
 * Nombre: Miroslav Krasimirov Vladimirov
 * email: mkrasimir4@alumno.uned.es / miro.kv89@gmail.com
 * NIE: X4780953N
 * tlfn: 676867565   
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class PED1_1{
	int num = 0; // Variable utilizada para el numero de clientes
    int total=0; // Variable utilizada para almacenar el tiempo total
    String cadena=""; // Variable utilizada para guardar el contenido del vector e imprimirlo
    String miTraza=""; // Variable utilizada para la traza
	private int sumatorio;
    
    public PED1_1(String select, String f_entrada, String f_salida){
    	if(!select.trim().equals("-t") && !select.trim().equals("-T") && !select.trim().equals("-h") && !select.trim().equals("-H")){
        	f_salida=f_entrada;
        	f_entrada=select;
        	}
    	else if(f_entrada.isEmpty())System.out.println("Seleccione archivo de entrada");
        //Solo se permitiran archivos con formato *.txt, en caso de que no se ponga expresamente se añadira por el programa.
        else if(!f_entrada.endsWith(".txt"))f_entrada=f_entrada+".txt";
        else if(!f_salida.isEmpty() && !f_salida.endsWith(".txt"))f_salida=f_salida+".txt";
        
        //(-h)OPCION HELP o AYUDA
        if (select.trim().equals("-h") || select.trim().equals("-H")){
            System.out.println("SINTAXIS:");
            System.out.println("servicio [-t] [-h] [fichero_entrada] [fichero_salida]");
            System.out.println("-t\t\t\t Traza la selección de clientes");
            System.out.println("-h\t\t\t Muestra esta ayuda");
            System.out.println("fichero_entrada\t\t Nombre del fiechero de entrada");
            System.out.println("fichero_salida\t\t Nombre del fichero de salida");
        }
        
        	//Caso con archivo de entrada
            if(!f_entrada.isEmpty()){
            	//Se utilizan 2 ArrayList, uno para el vector original y otro para el auxiliar que ordenadremos
                ArrayList<Integer> mont = new ArrayList<Integer>();
                ArrayList<Integer> aux = new ArrayList<Integer>();
                try {
                	//Lectura del fichero de entrada
					 FileReader entrada = new FileReader(f_entrada);
                     BufferedReader lector = new BufferedReader(entrada);
                     String linea = lector.readLine();
                     num=Integer.parseInt(linea.trim());
                     mont = new ArrayList<Integer>(num);
                     linea = lector.readLine();
                     String[] cadena = linea.split(" ");
                     //Se rellenan los ArrayList con los valores de entrada
                     for(String numero:cadena){
                         mont.add(Integer.parseInt(numero));
                         aux.add(Integer.parseInt(numero));
                     }
                     lector.close();
                     //Se ordena el vector auxiliar utilizando algoritmo de ordenacion quicksort
                     Collections.sort(aux);
                }
                catch (Exception e){System.out.println("Ha ocurrido un error no previsto");}
                //Se calcula el tiempo total de espera de los clientes con coste lineal: n
            	//for(int n=0;n<num;n++){total=total+aux.get(n)*(num-n);}
            	//Se rellena la cadena de salida utilizando el orden de llegada con coste: n^2
                ArrayList<Integer> suma = new ArrayList<Integer>();
                for(int j=0; j<num; j++){
                	total=total+aux.get(j)*(num-j);
                	if(j==0){suma.add(aux.get(j));}
                	if (j>0){
                		sumatorio = 0;
                		for(int k=0;k<suma.size();k++){
                			sumatorio+=suma.get(k);}
                		suma.add(suma.get(j-1)+sumatorio+aux.get(j));
                	}
            		for(int i=1; i<num+1; i++){
            			if(mont.get(i-1)==aux.get(j)){
            				cadena+=(i+" ");
            				System.out.println(suma);
            				miTraza=miTraza+"Paso: "+(j+1)+"\n"+"Datos: "+cadena+"\n"+"Tiempo: "+total+"\n--------------------\n";
            			}
                	}
                }
                //Casos de salida de fichero
                try {
                	//Sin archivo de salida, imprimimos por pantalla
                    if(f_salida.isEmpty()){
                        System.out.println(total);
                        System.out.println(cadena);
                    }
                    //Guardamos el contenido en el archivo de salida
                    else if(f_salida!=null || !f_salida.isEmpty()) {
                        File nuevo=new File(f_salida);
                        String ruta=nuevo.getAbsolutePath();
                        File archivo=new File(ruta);
                        if(archivo.exists()){
                            System.out.println("Error, no se permite sobreescribrir.");
                        }
                        else if(!archivo.exists()){
                            FileWriter escribir=new FileWriter(archivo,true);
                            escribir.write(total+"");
                            escribir.write("\r\n");
                            escribir.write(cadena);
                            escribir.close();
                        }  
                    }
                }
                catch(Exception e){System.out.println("Ha ocurrido un error no previsto");}
            }
            else{System.out.println("Comando incorrecto");}
          //(-t)OPCION TRAZA
        if (select.trim().equals("-t") || select.trim().equals("-T")){
        	System.out.println("\nTraza:\n--------------------\n"+miTraza);
        	        }
    }    

    
    public static void main(String[] args) {
    	String[] datos=new String[3];
    	for(int i=0;i<3;i++) datos[i]="";
    	for(int i=0;i<args.length;i++){datos[i]=args[i];}
        new PED1_1(datos[0],datos[1],datos[2]);
    	//new PED1_1("-t", "doc_e","");
    }
}