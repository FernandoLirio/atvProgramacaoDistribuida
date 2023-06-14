import java.util.ArrayList;
import java.util.List;

class Voo{
    private List<Integer> assentosDisp;

    public Voo(int assentos){
        assentosDisp = new ArrayList<>(assentos);
        for (int i = 1; i <= assentos; i++){
            assentosDisp.add(i);
        }
    }

    public synchronized boolean reserveAssentos(){
        if (assentosDisp.size() > 0){
            int assento = assentosDisp.remove(0);
            System.out.println(Thread.currentThread().getName() + "reservou o assento" + assento);
            return true;
        }else {
            System.out.println("Não há mais assentos disponiveis");
            return false;
        }
    }
}

class Passageiros implements Runnable{
    private Voo voo;

    public Passageiros(Voo voo){
        this.voo = voo;
    }

    @Override
    public void run(){
        voo.reserveAssentos();
    }
}

public class reservaVoo {
    public static void main(String[] args){
        Voo voo = new Voo(10);
        for (int i = 0; i < 15; i++){
            new Thread(new Passageiros(voo), "Passageiro" + (i+1)).start();
        }
    }
}
