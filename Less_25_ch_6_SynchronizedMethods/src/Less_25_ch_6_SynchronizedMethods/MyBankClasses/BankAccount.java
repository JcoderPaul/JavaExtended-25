package Less_25_ch_6_SynchronizedMethods.MyBankClasses;

public class BankAccount {
    private int account_balance = 10000;

    public void put_money_to_deposit(int amount){
           account_balance += amount;
    }

    public void get_money_from_deposit(int amount){
        account_balance -= amount;
    }

    public int getAccount_balance(){
        return account_balance;
    }

    public static void transfer_money(BankAccount account_1, BankAccount account_2, int amount){
        account_1.get_money_from_deposit(amount);
        account_2.put_money_to_deposit(amount);
    }
}
