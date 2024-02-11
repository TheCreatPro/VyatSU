using System;
internal class Program
{
    private static void Main(string[] args)
    {
        Console.WriteLine("Лабораторная работа 4. Вариант 11.");
        Console.Write("Введите значение x: ");
        string inputX = Console.ReadLine();
        Console.Write("Введите значение n: ");
        string inputN = Console.ReadLine();

        bool isNumber = double.TryParse(inputX, out double x) & double.TryParse(inputN, out double n);
        if (!isNumber)
        {
            Console.WriteLine("Введите число! Выход из программы...");
            Console.ReadKey();
            return;  // Досрочный выход из программы
        }

        double factorial = 1;
        double sum = 0;
        for (double k = 1; k <= n; ++k) 
        {
            factorial *= k;
            sum += (Math.Pow(x, k - 1)) / factorial;
            if (k % 4 == 0) Console.WriteLine("Сумма = " + sum + ". При k = " + k);
        }
        Console.WriteLine("Программа завершена. Нажмите любую кнопку.");
        Console.ReadKey();
    }
}
