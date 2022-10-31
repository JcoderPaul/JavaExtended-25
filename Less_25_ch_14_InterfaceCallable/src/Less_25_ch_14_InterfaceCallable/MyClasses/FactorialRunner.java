package Less_25_ch_14_InterfaceCallable.MyClasses;

import Less_25_ch_14_InterfaceCallable.Less_25_RunnableFault_Step1;

public class FactorialRunner implements Runnable{
    int f;

    public FactorialRunner(int f) {
        this.f = f;
    }

    @Override
    public void run() {
        int result = 1;
        // Проверяем правильность введенного числа, оно должно быть больше или равно 0
        if(f <= 0) {
            System.out.println("You cannot enter 0 or less!");
            return;
        } else {
            for(int i = 1; i <= f; i++){
            result *= i;
            }
        }
        // Передаем результат расчетов в статическую переменную основного кода
        Less_25_RunnableFault_Step1.factorialResult = result;
    }
}
