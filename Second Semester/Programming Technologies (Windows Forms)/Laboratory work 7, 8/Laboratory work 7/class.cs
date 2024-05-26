using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laboratory_work_7
{
    internal class @class
    {
    }

    interface IOperations
    {
        //double Value();  // значение
        void Addition(double input);
        void Multiply(double input);
    }

    class Variable : IOperations // Переменная
    {
        public Variable() { }
        private double element;  // поле
        
        public double Value  // это свойство
        {
            get { return element; }
            set { element = value; }
        }
        public virtual void Addition(double input)  // Сложить
        {
            element += input;
        }
        public virtual void Multiply(double input)  // Умножить
        {
            element *= input;
        }
    }
    class Massive : IOperations
    {
        public Massive() {  }
        private double[] massive;
        public double[] Value
        {
            get { return massive; }
            set { massive = value; }
        }
        public virtual void Addition(double inputNum)  // Сложить
        {
            for (int i = 0; i < massive.Length; i++) massive[i] += inputNum;
        }
        public virtual void Multiply(double inputNum)  // Умножить
        {
            for (int i = 0; i < massive.Length; i++) massive[i] *= inputNum;
        }
        public virtual void Sorted()
        {
            Array.Sort(massive);
        }
    }
    class Matrix: Massive
    {
        public Matrix() { }
        private double[,] matrix;
        public new double[,] Value
        {
            get { return matrix; }
            set { matrix = value; }
        }
        public override void Addition(double inputNum)  // Сложить
        {
            for (int i = 0; i < matrix.GetLength(0); i++)  
            {
                for (int j = 0; j < matrix.GetLength(1); j++)
                {
                    matrix[i, j] += inputNum;
                }
            }
        }
        public override void Multiply(double inputNum)  // Умножить
        {
            for (int i = 0; i < matrix.GetLength(0); i++)
            {
                for (int j = 0; j < matrix.GetLength(1); j++)
                {
                    matrix[i, j] *= inputNum;
                }
            }
        }
        public override void Sorted()
        {
            int rows = matrix.GetLength(0);
            int cols = matrix.GetLength(1);

            // Конвертируем матрицу в одномерный массив:
            double[] flatArray = new double[rows * cols];
            int index = 0;
            for (int i = 0; i < rows; i++)
            {
                for (int j = 0; j < cols; j++)
                {
                    flatArray[index++] = matrix[i, j];
                }
            }

            Array.Sort(flatArray);  // Сортируем одномерный массив

            // Обратно конвертируем одномерный массив в матрицу:
            index = 0;
            for (int i = 0; i < rows; i++)
            {
                for (int j = 0; j < cols; j++)
                {
                    matrix[i, j] = flatArray[index++];
                }
            }

        }
    }
}
