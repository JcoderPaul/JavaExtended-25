package Less_25_ch_6_SynchronizedMethods.MyBankClasses;

import java.util.Random;

public class BankRunnerOperation {
    BankAccount account_first = new BankAccount();
    BankAccount account_second = new BankAccount();
    Random random_transfer_amount = new Random();

    public void firstTread(){
        for(int i = 0; i < 10000; i++){
            BankAccount.transfer_money(account_first, account_second, random_transfer_amount.nextInt(100));
        }
    }
    public void secondTread(){
        for(int i = 0; i < 10000; i++){
            BankAccount.transfer_money(account_second, account_first, random_transfer_amount.nextInt(100));
        }
    }
    public void treadFinished(){
        System.out.println(account_first.getAccount_balance());
        System.out.println(account_second.getAccount_balance());
        System.out.println("Общий баланс двух счетов : "
                + (account_first.getAccount_balance() + account_second.getAccount_balance()));
    }
}
