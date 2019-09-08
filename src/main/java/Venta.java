import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;

public class Venta {

    static ArrayList<String> pedidos = new ArrayList<>();

    public static void main(String[]args){
        desplegarMenuPrincipal();
    }


    /**
     * Desplega el menu principal y llama a elegirOpcion()
     */
    public static void desplegarMenuPrincipal(){

        int opcion=1;
        while(opcion!=4) {

            System.out.println("------Menu------");
            System.out.println("[1] Mostrar Listado");
            System.out.println("[2] Hacer Pedido");
            System.out.println("[3] Eliminar Pedido");
            System.out.println("[4] Salir");
            opcion = recibirOpcion(4);
            elegirOpcion(opcion);
        }
    }


    /**
     * Intenta recibir una opcion numerica y captura una excpecion
     * @param nroOpciones cantidad de opciones en el menú
     * @return el numero de la opcion elegida
     */
    public static int recibirOpcion(int nroOpciones){
        int opcion;

        try{
            opcion= tratarRecibirNro(nroOpciones);
        }
        catch (InputMismatchException ime){
            System.out.println("Debe ingresar un numero!");

            opcion=recibirOpcion(nroOpciones);
        }
        return opcion;
    }

    /**
     *Recibe un numero y lo valida dentro de un rango
     * @param nroOpciones rango de opciones para validar
     * @return opcion elegida
     */
    public static int tratarRecibirNro(int nroOpciones){
        Scanner scan = new Scanner(System.in);

        int opcion;
        do{
            opcion = scan.nextInt();
        }
        while(!validarOpcion(opcion,nroOpciones));
        return opcion;
    }

    /**
     * Valida que un numero este dentro de un rango
     * @param opcion el numero para validar
     * @param nroOpciones tope superior del rango
     * @return True si el numero está dentro del rango
     */
    public static boolean validarOpcion(int opcion,int nroOpciones){
        boolean flag=false;
        if (opcion>0 && opcion<=nroOpciones){
            flag=true;
            //System.out.println("bien validado, opcion:"+opcion);
        }
        if (opcion<=0||opcion>nroOpciones){
            System.out.println("Opcion no valida!");

        }
        return flag;
    }


    /**
     * Llama a la funcion elegida en el menu
     * @param opcion opcion elegida
     */
    private static void elegirOpcion(int opcion) {
        if (opcion==1){
            //System.out.println("mostrar");
            desplegarlistado();
        }
        if (opcion==2){
            //System.out.println("hacer");
            hacerPedido();
        }
        if (opcion==3){
            //System.out.println("eliminar");
            eliminarPedido();
        }
        if (opcion==4){
            System.out.println("Salir");
        }
    }

    /**
     * Muestra todos los pedidos
     */
    public static void desplegarlistado(){
        if(pedidos.size()!=0) {
            String[] partes = new String[3];
            System.out.println("Pedido\tTipo\t\tTalla\tEstampado");
            for (int i = 0; i < pedidos.size(); i++) {
                partes = pedidos.get(i).split(",");
                System.out.println((i + 1) + "\t\t" + partes[0] + "\t\t" + partes[1] + "\t\t" + partes[2]);
            }
        }
        else{
            System.out.println("No hay elementos!");
        }
    }

    /**
     * Elegir cada una de las caracteristicas del pedido
     */
    public static void hacerPedido(){
        elegirTipo();
        elegirTalla();
        elegirEstampado();
    }

    /**
     * El usuario selecciona el tipo
     */
    public static void elegirTipo() {
        System.out.println("Eliga el tipo");
        String[] menuTipo={"Algodón", "polyester", "spandex"};
        mostrarMenu(menuTipo);
        int opcion = recibirOpcion(menuTipo.length);
        pedidos.add(menuTipo[opcion-1]);
    }

    /**
     * El usuario selecciona la talla
     */
    public static void elegirTalla(){
        System.out.println("Eliga la talla");
        String[] menuTalla={"S","M","L","XL"};
        mostrarMenu(menuTalla);
        int opcion = recibirOpcion(menuTalla.length);
        int ultimo=pedidos.size()-1;
        pedidos.set(ultimo,pedidos.get(ultimo)+","+menuTalla[opcion-1]);
    }

    /**
     * El usuario selecciona estampado
     */
    public static void elegirEstampado(){
        System.out.println("Estampado?");
        String[] menuEstampado={"Si","No"};
        mostrarMenu(menuEstampado);
        int opcion=recibirOpcion(menuEstampado.length);
        int ultimo = pedidos.size()-1;
        pedidos.set(ultimo,pedidos.get(ultimo)+","+menuEstampado[opcion-1]);
    }

    /**
     * Muestra un menu
     * @param menu lista de elementos del menu
     */
    public static  void mostrarMenu(String[] menu){
        for(int i =0;i<menu.length;i++){
            System.out.println("["+(i+1)+"] "+menu[i]);
        }
    }

    /**
     * Intenta eliminar un pedido y captura excepciones
     */
    public static  void eliminarPedido(){
        try{
            tratarEliminar();
        }
        catch (java.lang.IndexOutOfBoundsException iobe){
            System.out.println("No existe ese elemento!");

            eliminarPedido();
        }
        catch (InputMismatchException ime){
            System.out.println("Debes ingresar numeros!");

            eliminarPedido();
        }

    }

    /**
     * Elimina el pedido elegido por el ususario
     * @throws InputMismatchException si el ususario no ingresa un numero
     * @throws IndexOutOfBoundsException si el usuario ingresa un elemento que no existe
     */
    public static void tratarEliminar()throws InputMismatchException, IndexOutOfBoundsException{
        if(pedidos.size()!=0) {
            int eliminando = elegirEliminar();
            pedidos.remove(eliminando - 1);
        }
        else{System.out.println("No hay pedidos!");}
    }

    /**
     * El usuario elige el pedido a eliminar
     * @return numero del pedido a eliminar
     * @throws InputMismatchException si el usuario no ingresa un numero
     */
    public static int elegirEliminar()throws InputMismatchException{
        int elegido;
        Scanner scan = new Scanner(System.in);
        desplegarlistado();
        System.out.println("Cual");
        elegido=scan.nextInt();
        return elegido;
    }
}
