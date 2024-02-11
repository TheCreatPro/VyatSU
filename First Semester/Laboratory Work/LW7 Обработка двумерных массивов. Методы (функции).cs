using System;
internal class ProgramLW7
{
    private static void Main(string[] args)
    {
        double GetAverage(int row, int coloumn, int[,] array)  // Среднее арифметическое
        {
            double result = 0;
            for (int i = 0; i < row; i++)
            {
                for (int j = 0; j < coloumn; j++) result += array[i, j];
            }
            return result / (row * coloumn);
        }
        void PrintArray(int row, int coloumn, int[,] array)
        {
            for (int i = 0; i < row; i++)
            {
                for (int j = 0; j < coloumn; j++) Console.Write(array[i, j] + "\t");
                Console.WriteLine();
            }
        }
        Console.WriteLine("Лабораторная работа 7. Вариант 11.");
        Console.Write("    Введите число строк: m = ");
        string inputM = Console.ReadLine();
        int row = int.Parse(inputM);  // Число строк
        Console.Write("    Введите число столбцов: n = ");
        string inputN = Console.ReadLine();
        int column = int.Parse(inputN);  // Число столбцов
        Console.Write("    Введите столбец k (счёт начинается с 1): k = ");
        string inputK = Console.ReadLine();
        int k = int.Parse(inputK);  // Число К
        int[,] inputArray = new int[row, column];
        int[,] resultArray = new int[row, column + 1];
        for (int i = 0; i < row; i++)
        {
            Console.Write("Введите " + (i + 1) + "-ю строчку массива (разделитель - пробел): ");
            string inputRow = Console.ReadLine();
            // Считываем из консоли строку, разделяем её по пробелам, для каждой подстроки
            // вызываем int.Pars и приводим полученную последовательность к типу массив:
            int[] numbers = inputRow.Split(' ').Select(row => int.Parse(row)).ToArray();
            for (int j = 0; j < column; j++) inputArray[i, j] = numbers[j];
        }
        int[] rowSum = new int[row];
        for (int i = 0; i < row; i++)
        {
            int sum = 0;
            for (int j = 0; j < column; j++) sum += inputArray[i, j];
            rowSum[i] = sum;
        }
        for (int i = 0; i < row; i++)  // по строкам
        {
            Array.Copy(inputArray, i * column, resultArray, i * (column + 1), k);
            resultArray[i, k] = rowSum[i];
            Array.Copy(inputArray, k + (column * i), resultArray, (k + (column * i)) + (i + 1), column - k);  // Такая запись, т.к. идёт смещение
        }
        Console.WriteLine("\nИсходный массив:");
        PrintArray(row, column, inputArray);
        Console.WriteLine("Среднее арифметическое исходного массива: " + GetAverage(row, column, inputArray));
        Console.WriteLine("\nСформированный массив:");
        PrintArray(row, column + 1, resultArray);  // coloumn + 1 т.к. столбцов больше
        Console.WriteLine("Среднее арифметическое сформированного массива: " + GetAverage(row, column + 1, resultArray));
        Console.WriteLine("\nПрограмма завершена. Нажмите любую кнопку, чтобы закрыть это окно.");
        Console.ReadKey();
    }
}
