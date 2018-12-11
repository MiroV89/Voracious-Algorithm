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

public class servicio{
	int num = 0; // Variable utilizada para el numero de clientes
    int total=0; // Variable utilizada para almacenar el tiempo total
    String cadena=""; // Variable utilizada para guardar el contenido del vector e imprimirlo
    String miTraza=""; // Variable utilizada para guardar la traza
    
    public servicio(String select, String f_entrada, String f_salida){
    	if(select.isEmpty() || select==null) System.out.println("Introduzca datos.");
    	else{
    	boolean traza=(select.trim().equals("-t") || select.trim().equals("-T"));
        boolean help=(select.trim().equals("-h") || select.trim().equals("-H"));
    	if(!traza && !help){
        	f_salida=f_entrada;
        	f_entrada=select;
        	}
        if(f_entrada.isEmpty())System.out.println("Seleccione archivo de entrada");
        //Solo se permitiran archivos con formato *.txt, en caso de que no se ponga expresamente se añadira por el programa.
        if(!f_entrada.endsWith(".txt"))f_entrada=f_entrada+".txt";
        if(!f_salida.isEmpty() && !f_salida.endsWith(".txt"))f_salida=f_salida+".txt";
        
        //(-h)OPCION HELP o AYUDA
        if (help){
            System.out.println("SINTAXIS:");
            System.out.println("servicio [-t] [-h] [fichero_entrada] [fichero_salida]");
            System.out.println("-t\t\t\t Traza la selección de clientes");
            System.out.println("-h\t\t\t Muestra esta ayuda");
            System.out.println("fichero_entrada\t\t Nombre del fiechero de entrada");
            System.out.println("fichero_salida\t\t Nombre del fichero de salida");
        }
        
        	//Caso con archivo de entrada
        else if(!f_entrada.isEmpty()){
            	//Se utiliza 1 ArrayListde Clientes
                ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
                try {
                	//Lectura del fichero de entrada
					 FileReader entrada = new FileReader(f_entrada);
                     BufferedReader lector = new BufferedReader(entrada);
                     String linea = lector.readLine();
                     num=Integer.parseInt(linea.trim());
                     listaClientes = new ArrayList<Cliente>(num);
                     linea = lector.readLine();
                     String[] cadena = linea.split(" ");
                     //Se rellena el ArrayList con los clientes existentes
                     for(int i=0; i<num; i++){
                    	Cliente cl = new Cliente(i+1,Integer.parseInt(cadena[i]));
                    	listaClientes.add(cl);
                     }
                     lector.close();
                     //Se ordena el vector auxiliar utilizando algoritmo de ordenacion quicksort
                     Collections.sort(listaClientes);
                }
               catch (Exception e){System.out.println("Ha ocurrido un error no previsto");}
                //Se calcula el tiempo total de espera de los clientes con coste lineal: n
            	//Se rellena la cadena de salida utilizando el orden de llegada con coste: nlog(n)
            	int suma=0;
            	int total=0;
                for(int i=0;i<num;i++){
            			cadena+=(listaClientes.get(i).getPosicion()+" ");
            			suma=suma+listaClientes.get(i).getValor();
            			total=total+suma;
            			miTraza=miTraza+"\nPaso: "+(i+1)+"\n"+"Datos: "+cadena+"\n"+"Tiempo de espera: "
            			+total+"\n--------------------";
            	}
                //Casos de salida de fichero
                try {
                	//Sin archivo de salida, imprimimos por pantalla
                    if(f_salida.isEmpty()){
                        System.out.println(total);
                        System.out.println(cadena);
                      //(-t)OPCION TRAZA
                        if (traza){
                        	System.out.println("\nTraza:\n--------------------\n"+miTraza);
                        	        }
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
                            if(traza){escribir.write(miTraza);}
                            escribir.close();
                        }  
                    }
                }
                catch(Exception e){System.out.println("Ha ocurrido un error no previsto");}
            }
            else{System.out.println("Comando incorrecto");}
    	} 
    }
    
    public static void main(String[] args) {
    	String[] datos=new String[3];
    	for(int i=0;i<3;i++) datos[i]="";
    	for(int i=0;i<args.length;i++){datos[i]=args[i];}
        new servicio(datos[0],datos[1],datos[2]);
    	//new servicio("-t","doc_e","");
    }
}