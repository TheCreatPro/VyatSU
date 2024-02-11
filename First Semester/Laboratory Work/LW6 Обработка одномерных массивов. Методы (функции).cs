using System;
internal class ProgramLW6
{
    private static void Main(string[] args)
    {
        void PrintAverage(int n, int[] array)  // Среднее арифметическое
        {
            double result = 0;
            for (int i = 0; i <= n - 1; i++)
            {
                result += array[i];
            }
            Console.WriteLine("\nСреднее арифметическое массива: " + (result / n));
        }
        Console.WriteLine("Лабораторная работа 6. Вариант 11.");
        Console.Write("    Введите длину массива: n = ");
        string inputN = Console.ReadLine();
        int n = int.Parse(inputN);  // Длина массива
        Console.Write("    Введите количество цепочки возрастающих смежных элементов: ");
        string inputK = Console.ReadLine();
        int k = int.Parse(inputK);  // Кол-во цепочки возрастающих смежных элементов
        int[] array = new int[n];
        int[] resultArray = new int[k];
        int counter = 0;
        bool flag = true;
        for (int i = 0; i < n; i++)
        {
            Console.Write("Введите " + (i + 1) +"-й массива: ");
            string inputEl = Console.ReadLine();
            array[i] = int.Parse(inputEl);
        }
        for (int i = 0; i < n - k + 1; i++)
        {
            do
            {
                if (array[i + counter] < array[i + counter + 1]) { counter++; flag = true; }  // Проверка соседних элементов на возрастание
                else { flag = false; break; }  // Если не подходит - переходим к следующему i-му элементу
            } while (counter < k - 1);
            if (flag) { resultArray = array[i..(i+counter+1)]; break; }  // Если за проверку флаг остался, то берём подходящий диапазон из изначального списка
            counter = 0;
        }
        // Вывод полученных данных на экран
        Console.Write("\nИсходный массив:");
        for (int i = 0; i < n; i++) { Console.Write(" " + array[i]); }
        PrintAverage(n, array);
        if (flag)
        {
            Console.Write("\nСформированный массив:");
            for (int i = 0; i < k; i++) { Console.Write(" " + resultArray[i]); }
            PrintAverage(k, resultArray);
        }
        else { Console.WriteLine("Нет подходящих элементов для второго массива"); }
        Console.WriteLine("\nПрограмма завершена. Нажмите любую кнопку, чтобы закрыть это окно.");
        Console.ReadKey();
    }
}
