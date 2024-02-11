using System;
internal class Program
{
    private static void Main(string[] args)
    {
        // Задание 1
        Console.WriteLine("Вариант 11. Задание 1");
        Console.WriteLine("Введите значение x");
        string input_x = Console.ReadLine();

        bool isNumber = double.TryParse(input_x, out double x);
        if (!isNumber)
        {
            Console.WriteLine("Введите число! Выход из программы...");
            Console.ReadKey();
            return;  // Досрочный выход из программы
        }

        if (x <= 0) Console.WriteLine("Ветвь 1. Ответ: y = " + x);
        else if (0 < x & x <= 11) Console.WriteLine("Ветвь 2. Ответ: y = " + 1 / x);
        else if (x > 11) Console.WriteLine("Ветвь 3. Ответ: y = " + 11*x);

        // Задание 2
        Console.WriteLine("----------\nДля запуска Задания 2 (в задачнике 4.136(б), нажмите на любую кнопку...");
        Console.ReadKey();
        Console.WriteLine("Введите k, день года (от 1 до 365)");
        string input_day = Console.ReadLine();
        Console.WriteLine("Введите d, день, с которого начинается год (от 1 до 7)");
        string input_d = Console.ReadLine();

        bool isCorrect = int.TryParse(input_day, out int day) & int.TryParse(input_d, out int d);
        if (!isCorrect)
        {
            Console.WriteLine("Ошибка ввода! Выход из программы...");
            Console.ReadKey();
            return;  // Досрочный выход из программы
        }
        if (day < 1 || day > 365 || d < 1 || d > 7)
        {
            Console.WriteLine("Введите значение из диапазона! Выход из программы...");
            Console.ReadKey();
            return;  // Досрочный выход из программы
        }
        switch ((day + (d - 1)) % 7)
        {
            case 0: Console.WriteLine(day + " день года это воскресенье"); break;
            case 6: Console.WriteLine(day + " день года это суббота"); break;
            default: Console.WriteLine(day + " день года это рабочий день"); break;
        }
        Console.ReadKey();
    }
}
