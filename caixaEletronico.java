import java.util.concurrent.locks.ReentrantLock;

class Caixa {
    private final ReentrantLock lock = new ReentrantLock();

    public void retirarDinheiro(int quantiaEmDinheiro) {
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "está sacando R$" + quantiaEmDinheiro);
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

class Clientes implements Runnable {
    private Caixa caixa;
    private int quantiaSacar;

    public Clientes(Caixa caixa, int quantiaSacar){
        this.caixa = caixa;
        this.quantiaSacar = quantiaSacar;
    }
    @Override
    public void run(){
        caixa.retirarDinheiro(quantiaSacar);
    }
}


public class caixaEletronico {
    public static void main(String[] args) {
        Caixa caixa = new Caixa();
        for (int i = 0; i < 5; i++){
            new Thread(new Clientes(caixa,100), "Cliente" + (i + 1)).start();
        }
    }
}
