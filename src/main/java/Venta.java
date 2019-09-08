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
     *
     * @param nroOpciones
     * @return
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



    private static void elegirOpcion(int tecla) {
        if (tecla==1){
            //System.out.println("mostrar");
            desplegarlistado();
        }
        if (tecla==2){
            //System.out.println("hacer");
            hacerPedido();
        }
        if (tecla==3){
            //System.out.println("eliminar");
            eliminarPedido();
        }
        if (tecla==4){
            System.out.println("Salir");
        }
    }


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


    public static void hacerPedido(){
        elegirTipo();
        elegirTalla();
        elegirEstampado();
    }

    public static void elegirTipo() {
        System.out.println("Eliga el tipo");
        String[] menuTipo={"Algodón", "polyester", "spandex"};
        mostrarMenu(menuTipo);
        int opcion = recibirOpcion(menuTipo.length);
        pedidos.add(menuTipo[opcion-1]);
    }
    public static void elegirTalla(){
        System.out.println("Eliga la talla");
        String[] menuTalla={"S","M","L","XL"};
        mostrarMenu(menuTalla);
        int opcion = recibirOpcion(menuTalla.length);
        int ultimo=pedidos.size()-1;
        pedidos.set(ultimo,pedidos.get(ultimo)+","+menuTalla[opcion-1]);
    }
    public static void elegirEstampado(){
        System.out.println("Estampado?");
        String[] menuEstampado={"Si","No"};
        mostrarMenu(menuEstampado);
        int opcion=recibirOpcion(menuEstampado.length);
        int ultimo = pedidos.size()-1;
        pedidos.set(ultimo,pedidos.get(ultimo)+","+menuEstampado[opcion-1]);
    }

    public static  void mostrarMenu(String[] menu){
        for(int i =0;i<menu.length;i++){
            System.out.println("["+(i+1)+"] "+menu[i]);
        }
    }


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
    public static void tratarEliminar()throws InputMismatchException, IndexOutOfBoundsException{
        if(pedidos.size()!=0) {
            int eliminando = elegirEliminar();
            pedidos.remove(eliminando - 1);
        }
        else{System.out.println("No hay pedidos!");}
    }
    public static int elegirEliminar()throws InputMismatchException{
        int elegido;
        Scanner scan = new Scanner(System.in);
        desplegarlistado();
        System.out.println("Cual");
        elegido=scan.nextInt();
        return elegido;
    }






}
