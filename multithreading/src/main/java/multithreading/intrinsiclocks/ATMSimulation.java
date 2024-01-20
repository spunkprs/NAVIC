package multithreading.intrinsiclocks;

public class ATMSimulation {

    /*
    * Entire point of doing this exercise is to make sure no two customers access ATM at the same time
    * Practising intrinsic/monitor lock via this exercise
    * Ideally ATM shall be entertaining all customer requests in FIFO basis
    * */
    public static void main(String[] args) {
        ATM atm = new ATM();
        Thread customerOne = new Thread(new Customer("Prateek", 1000, atm));
        Thread customerTwo = new Thread(new Customer("Piyush", 1500, atm));
        Thread customerThree = new Thread(new Customer("Naina", 1800, atm));

        customerOne.start();
        customerTwo.start();
        customerThree.start();
    }

    static class Customer implements Runnable {

        private String customerName;
        private double amountTobeWithdrawn;
        private ATM atm;

        public Customer(String customerName, double amountTobeWithdrawn, ATM atm) {
            this.customerName = customerName;
            this.amountTobeWithdrawn = amountTobeWithdrawn;
            this.atm = atm;
        }

        private void useATM() {
            atm.checkBalance(this.customerName);
            atm.withdrawAmount(this.customerName, this.amountTobeWithdrawn);
        }

        @Override
        public void run() {
            useATM();
        }
    }

    static class ATM {
        public synchronized void checkBalance(String customerName) {
            System.out.println(customerName + " is proceeding with balance check !!");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Balance shown for " + customerName);
        }

        public synchronized void withdrawAmount(String customerName, double amount) {
            System.out.println(customerName + " is proceeding with withdrawing amount " + amount + " !!");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(amount + " amount withdrawn by " + customerName);
        }
    }
}
