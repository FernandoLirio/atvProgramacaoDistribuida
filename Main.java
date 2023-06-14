import java.util.concurrent.locks.ReentrantLock;

class  Evento{
    private int bilhetesDispo;
    private final ReentrantLock lock;

    public Evento(int bilhetesDisp){
        this.bilhetesDispo = bilhetesDisp;
        this.lock = new ReentrantLock();
    }

    public void comprasBilhete(){
        lock.lock();
        try{
            if (bilhetesDispo > 0){
                bilhetesDispo--;
                System.out.println("Ingressos comprado com sucesso");
            }else {
                System.out.println("Ingressos esgotados!");
            }
        }finally {
            lock.unlock();
        }
    }
}

class Cliente implements Runnable{
    private final Evento evento;

    public Cliente(Evento evento){
        this.evento = evento;
    }
    @Override
    public void run(){
        evento.comprasBilhete();
    }
}

public class Main{
    public static void main(String[] args){
        int totalIngressos = 10;
        Evento evento = new Evento(totalIngressos);

        int totalClientes = 15;
        for (int i = 0; i < totalClientes; i++){
            new Thread(new Cliente(evento)).start();
        }
    }
}