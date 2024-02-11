using System;
internal class Program
{
    private static void Main(string[] args)
    {
        Console.WriteLine("Самостоятельная работа 4. Вариант 11");
        double a, b, c, denominator = 0;
        do
        {
            Console.Write("Введите значение a: ");
            string inputA = Console.ReadLine();
            Console.Write("Введите значение b: ");
            string inputB = Console.ReadLine();
            Console.Write("Введите значение c: ");
            string inputC = Console.ReadLine();

            a = double.Parse(inputA);  // str -> double
            b = double.Parse(inputB);
            c = double.Parse(inputC);
            denominator = a * a * (b + c);   // знаменатель

            if (denominator == 0)
            {
                Console.WriteLine("\nЗнаменатель равен нулю! Введите корректные значения!");
            }
        } while (denominator == 0);
        double numerator = Math.Pow(Math.Sin(Math.Pow((a + b), 2)), 3);   // числитель
        double result = numerator / denominator;
        Console.WriteLine("Ответ: " + result);
        Console.WriteLine("Работа программы завершена. Нажмите любую кнопку для выхода...");
        Console.ReadKey();
    }
}
