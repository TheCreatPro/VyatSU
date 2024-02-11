using System;
internal class Program
{
    private static void Main(string[] args)
    {
        Console.WriteLine("Вариант 11. Вывод количества не равных нулю чисел");
        Console.WriteLine("Введите значение a");
        string input_a = Console.ReadLine();
        Console.WriteLine("Введите значение b");
        string input_b = Console.ReadLine();
        Console.WriteLine("Введите значение c");
        string input_c = Console.ReadLine();

        bool isNumber = double.TryParse(input_a, out double a) & double.TryParse(input_b, out double b) & double.TryParse(input_c, out double c);
        if (!isNumber)
        {
            Console.WriteLine("Ошибка ввода! Выход из программы...");
            Console.ReadKey();
            Environment.Exit(0);  // Досрочный выход из программы
        }

        // 1-й способ
        if (a == 0.0 && b == 0.0 && c == 0.0)
        {
            Console.WriteLine("Первый способ. Не равных нулю чисел нет");
        }
        else
        {
            if (a != 0.0 && b != 0 && c != 0)
            {
                Console.WriteLine("Первый способ. Ответ: 3");
            }
            else
            {
                if ((a == 0.0 && b == 0.0 && c != 0.0) || (a != 0.0 && b == 0.0 && c == 0.0) || (a == 0.0 && b != 0.0 && c == 0.0))
                {
                    Console.WriteLine("Первый способ. Ответ: 1");
                }
                else
                {
                    Console.WriteLine("Первый способ. Ответ: 2");
                }
            }
        }

        // 2-й способ
        int result = 0;
        if (a != 0) ++result;
        if (b != 0) ++result;
        if (c != 0) ++result;
        Console.WriteLine("Второй способ. Ответ: " + result);
        Console.ReadKey();
    }
}
