using System;

internal class Program
{
    private static void Main(string[] args)
    {
        Console.WriteLine("Вариант 11");
        Console.WriteLine("Введите значение a");
        string input_a = Console.ReadLine();
        Console.WriteLine("Введите значение b");
        string input_b = Console.ReadLine();
        Console.WriteLine("Введите значение c");
        string input_c = Console.ReadLine();

        double a = double.Parse(input_a);  // str -> double
        double b = double.Parse(input_b);
        double c = double.Parse(input_c);
        double numerator = Math.Pow(Math.Sin(Math.Pow((a + b), 2)), 3);   // числитель
        double denominator = a * a * (b + c);   // знаменатель

        bool is_zero = denominator == 0;
        if (is_zero)
        {
            Console.WriteLine("Знаменатель равен нулю! Выход из программы...");
            Environment.Exit(0);  // Досрочный выход из программы
        }

        double result = numerator / denominator;
        Console.WriteLine("Ответ: " + result);
    }
}
