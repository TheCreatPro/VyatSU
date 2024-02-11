using System;
internal class Program
{
    private static void Main(string[] args)
    {
        long Factorial(int n)
        {
            if (n == 1) return 1;
            return n * Factorial(n - 1);
        }
        int Denominator(int n)
        {
            int result = 1, counter = 1;
            while (counter <= 3 * n - 2)
            {
                result *= counter;
                counter += 3;
            }
            return result;
        }
        Console.WriteLine("Самостоятельная работа 4. Вариант 11.");
        Console.WriteLine("    Задание 1");
        Console.Write("Введите точность. Eps = ");
        string inputEps = Console.ReadLine();
        Console.Write("Введите максимальную величину. g = ");
        string inputG = Console.ReadLine();
        bool isNumber = double.TryParse(inputEps, out double eps) & int.TryParse(inputG, out int g);
        if (!isNumber)
        {
            Console.WriteLine("Введите число! Программа завершена. Нажмите любую кнопку, чтобы закрыть это окно");
            Console.ReadKey();
            return;  // Досрочный выход из программы
        }
        double sum = 0, element = 0;
        int n = 1;
        do
        {
            element = Factorial(2 * n + 1) / Denominator(n);
            sum += element;
            Console.WriteLine("Итерация: " + n + ". Элемент: " + element);
            n += 1;
        } while (Math.Abs(element) > eps && Math.Abs(element) < g);
        Console.WriteLine("\nСумма элементов последовательности: " + sum);
        if (Math.Abs(element) < eps) Console.Write("Ряд сходится. ");  // Проверка на сходимость ряда
        else Console.Write("Ряд сходиться. ");
        Console.WriteLine("Сумма ряда: " + sum);
        Console.WriteLine("Программа завершена. Нажмите любую кнопку, чтобы закрыть это окно.");
        Console.ReadKey();
    }
}
