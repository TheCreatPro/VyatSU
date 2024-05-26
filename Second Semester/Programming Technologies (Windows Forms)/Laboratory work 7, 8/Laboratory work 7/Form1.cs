using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;

namespace Laboratory_work_7
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            this.FormBorderStyle = FormBorderStyle.FixedSingle;
            

        }

        void PrintArray(double[] arr)
        {
            textBox18.Text += "{ ";
            for (int i = 0; i < arr.Length; i++)
            {
                textBox18.Text += arr[i] + "; ";
            }
            textBox18.Text += " }";
        }
        void PrintMatrix(double[,] matrix)
        {
            textBox18.Text += "{ " + Environment.NewLine;
            for (int i = 0; i < matrix.GetLength(0); i++)
            {
                textBox18.Text += "\t{ ";
                for (int j = 0; j < matrix.GetLength(1); j++)
                {
                    textBox18.Text += matrix[i, j] + "\t";
                }
                textBox18.Text += " }" + "\r\n";
            }
            textBox18.Text += " }";
        }


        private void ButtonCalculation_Click(object sender, EventArgs e)
        {
            buttonCalculation.Enabled = false;
            int n, m;
            double num1, num2, arrayNum, matrixNum;
            double[] array;
            double[,] matrix;
            try
            {
                num1 = Convert.ToDouble(inputNum1.Text);
                num2 = Convert.ToDouble(inputNum2.Text);

                array = inputArray.Text.Split(' ').Select(Double.Parse).ToArray();  // Получаем массив строк и переводим его в массив чисел
                arrayNum = Convert.ToDouble(inputArrayNum.Text);

                n = (int)inputN.Value; //  кол-во строк матрицы
                m = (int)inputM.Value; //  кол-во столбцов матрицы
                matrix = new double[n, m]; //создаем матрицу
                for (int i = 0; i < n; i++)
                {
                    for (int j = 0; j < m; j++)
                    {
                        matrix[i, j] = Convert.ToDouble(inputMatrix.Rows[i].Cells[j].Value); //cчитываем данные
                    }
                }
                matrixNum = Convert.ToDouble(inputMatrixNum.Text);
                
            }
            catch { textBox18.Text += "Ошибка ввода данных. Введите корректные значения!" + Environment.NewLine; return; }



        // Числовые переменные:
            Variable Number = new Variable();
            textBox18.Text += "####### Операции с числовыми переменнами #######" + Environment.NewLine;
            // Сложение:
            Number.Value = num1;
            textBox18.Text += Number.Value + " + ";
            Number.Addition(num2);
            textBox18.Text += num2 + " = " + Number.Value + ";" + Environment.NewLine;
            // Умножение:
            Number.Value = num1;
            textBox18.Text += Number.Value + " * ";
            Number.Multiply(num2);
            textBox18.Text += num2 + " = " + Number.Value + ";" + Environment.NewLine;
            textBox18.Text += Environment.NewLine;

        // Массив:
            Massive Arr = new Massive();
            textBox18.Text += "####### Операции с массивом чисел #######" + Environment.NewLine;
            // Сложение:
            Arr.Value = array;
            PrintArray(Arr.Value);
            textBox18.Text += " + " + arrayNum + " = ";
            Arr.Addition(arrayNum);
            PrintArray(Arr.Value);
            textBox18.Text += ";" + Environment.NewLine;
            // Умножение:
            //Arr.Value = array;
            PrintArray(Arr.Value);
            textBox18.Text += " * " + arrayNum + " = ";
            Arr.Multiply(arrayNum);
            PrintArray(Arr.Value);
            textBox18.Text += ";" + Environment.NewLine;
            // Сортировка (по возрастанию):
            //Arr.Value = array;
            Arr.Sorted();
            textBox18.Text += "Окончательный массив отсортирован по возрастанию элементов: ";
            PrintArray(Arr.Value);
            textBox18.Text += ";" + Environment.NewLine + Environment.NewLine;


        // Матрица:
            Matrix Matr = new Matrix();
            textBox18.Text += "####### Операции с матрицей #######" + Environment.NewLine;
            // Сложение:
            Matr.Value = matrix;
            PrintMatrix(Matr.Value);
            textBox18.Text += " + " + matrixNum + " = ";
            Matr.Addition(matrixNum);
            PrintMatrix(Matr.Value);
            textBox18.Text += ";" + Environment.NewLine;
            // Умножение:
            Matr.Value = matrix;
            PrintMatrix(Matr.Value);
            textBox18.Text += " * " + matrixNum + " = ";
            Matr.Multiply(matrixNum);
            PrintMatrix(Matr.Value);
            textBox18.Text += ";" + Environment.NewLine;
            // Сортировка:
            Matr.Sorted();
            textBox18.Text += "Окончательная матрица отсортирована по возрастанию элементов: ";
            PrintMatrix(Matr.Value);
            textBox18.Text += ";";
        }
        private void ButtonClear_Click(object sender, EventArgs e)
        {
            textBox18.Clear();
            buttonCalculation.Enabled = true;
            // addNumBtn.Enabled = true;
        }
        private void ButtonClose_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void ChangeColoumn(object sender, EventArgs e)  // столбцы
        {
            inputMatrix.ColumnCount = (int)inputM.Value;
        }
        private void ChangeRow(object sender, EventArgs e)  // строки
        {
            inputMatrix.RowCount = (int)inputN.Value;
        }              
    }
}
