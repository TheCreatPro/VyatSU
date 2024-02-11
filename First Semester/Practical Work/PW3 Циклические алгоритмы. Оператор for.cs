using System;
internal class Program
{
    private static void Main(string[] args)
    {
        Console.WriteLine("Самостоятельная работа 3. Вариант 11.");
        Console.Write("Введите 1-й элемент последовательности. a1 = ");
        string inputA1 = Console.ReadLine();
        Console.Write("Введите 2-й элемент последовательности. a2 = ");
        string inputA2 = Console.ReadLine();
        Console.Write("Введите 3-й элемент последовательности. a3 = ");
        string inputA3 = Console.ReadLine();
        Console.Write("Введите количество элементов последовательности. n = ");
        string inputN = Console.ReadLine();
        bool isNumber = int.TryParse(inputA1, out int a1) & int.TryParse(inputA2, out int a2) & int.TryParse(inputA3, out int a3) & int.TryParse(inputN, out int n);
        if (!isNumber)
        {
            Console.WriteLine("Введите числа! Программа завершенаю Нажмите любую кнопку, чтобы закрыть это окно");
            Console.ReadKey();
            return;  // Досрочный выход из программы
        }

        int b = (a3 - a2) / (a2 - a1);
        int c = a2 - b * a1;
        int sum = a1;
        int element = a1;
        Console.Write("\nРекуррентная формула: a[i] = " + b + " * a[i-1] + " + c + "\nПоследовательность: " + element);
        for (int i = 0; i <= n - 2; i++)
        {
            element = b * element + c;
            sum += element;
            Console.Write(", " + element);
        }
        Console.WriteLine("\nСумма элементов последовательности: " + sum);

        Console.WriteLine("Программа завершена. Нажмите любую кнопку, чтобы закрыть это окно.");
        Console.ReadKey();
    }
}
