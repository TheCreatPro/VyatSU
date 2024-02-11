using System;

internal class Program
{
    private static void Main(string[] args)
    {
        Console.WriteLine("Вариант 11. Введите радиус окружности:");
        string input = Console.ReadLine();
        
        bool is_number = double.TryParse(input, out double radius);
        if (!is_number)
        {
            Console.WriteLine("Это не число! Выход из программы...");
            Environment.Exit(0);  // Досрочный выход из программы
        }
        if (radius < 0)
        {
            Console.WriteLine("Радиус меньше нуля! Выход из программы...");
            Environment.Exit(0);
        }
        double square_circle = Math.PI * radius * radius;
        Console.WriteLine("Площадь фигуры: " + (square_circle / 8 + square_circle / 2));
        double perimeter_circle = 2 * Math.PI * radius;
        Console.WriteLine("Периметр фигуры: " + ((perimeter_circle / 8 + 2 * radius) + (perimeter_circle / 2 + 2 * radius)));
    }
}
