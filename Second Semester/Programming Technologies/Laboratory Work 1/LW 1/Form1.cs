using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace LW_1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void tabPage3_Click(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void label6_Click(object sender, EventArgs e)
        {

        }

        private void checkBox1_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void chart1_Click(object sender, EventArgs e)
        {

        }

        private void numericUpDown1_ValueChanged(object sender, EventArgs e)
        {

        }

        private void label8_Click(object sender, EventArgs e)
        {

        }

        private void Массивы_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void tabPage1_Click(object sender, EventArgs e)
        {

        }

        private void tabPage2_Click(object sender, EventArgs e)
        {

        }

        private void tabPage3_Click_1(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void label4_Click(object sender, EventArgs e)
        {

        }

        private void label5_Click(object sender, EventArgs e)
        {

        }

        private void label7_Click(object sender, EventArgs e)
        {

        }

        private void checkBox2_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        int i = 0;
        private void button1_Click(object sender, EventArgs e)
        {
            button1.Enabled = false;
            button2.Enabled = true;
            label8.Text = "";
            if (numericUpDown4.Value < numericUpDown5.Value)
            {
                label8.Text = "Максимальное значение не может быть меньше минимального значения!"; return; }
            int count, current = 0;
                count = (Convert.ToInt32(numericUpDown2.Value) -
               Convert.ToInt32(numericUpDown1.Value)) /
               Convert.ToInt32(numericUpDown3.Value) + 1;
                for (int n = Convert.ToInt32(numericUpDown1.Value); n <= Convert.ToInt32(numericUpDown2.Value); n += Convert.ToInt32(numericUpDown3.Value))
                {
                    int[] vptr = new int[n];
                    Random rand = new Random();
                    for (int j = 0; j < n; j++)
                    {
                        vptr[j] = rand.Next(Convert.ToInt32(numericUpDown5.Value), Convert.ToInt32(numericUpDown4.Value));
                    }
                    if (checkBox1.Checked)
                    {
                        dataGridView1.ColumnCount = n + 1;
                        dataGridView1.Rows.Add();
                        dataGridView1.Rows[i].Cells[0].Value = "Исходный массив";
                    for (int j = 0; j < n; j++)
                        {
                            dataGridView1.Rows[i].Cells[j + 1].Value = vptr[j];
                        }
                        i++;
                    }
                    sort(vptr, n);
                    current += 1;
                    progressBar1.Value = 100 * current / count;
                }
            }
            private void sort(int[] p, int n)
            {
                int k = 0, sr = 0, obm = 0, m = 0;
                for (int j = 0; j < n; j++)
                {
                    if (p[j] == 0) k++;
                    else p[j - k] = p[j];
                }
                n -= k;
                sr += n;
                if (n == 0)
                {
                    label8.Text = "В массиве одни нули"; 
                    return;
                }
                for (m = 0; m < n - 1; m++)
                    for (int j = m + 1; j < n; j++)
                    {
                        if (p[m] > 0 && p[j] > 0 && p[m] < p[j])
                        {
                            swap(ref p[m], ref p[j]); obm++;
                        }
                        if (p[m] < 0 && p[j] < 0 && p[m] > p[j])
                        {
                            swap(ref p[m], ref p[j]); obm++;
                        }
                        sr += 6;
                    }
                if (checkBox1.Checked)
                {
                    dataGridView1.AutoResizeColumns();
                    dataGridView1.Rows.Add();
                    dataGridView1.Rows[i].Cells[0].Value = "Получен массив";
                    for (int j = 0; j < n; j++)
                    { 
                        dataGridView1.Rows[i].Cells[j + 1].Value = p[j]; 
                    }
                    i++;
                }
                if (Convert.ToInt32(numericUpDown1.Value) == Convert.ToInt32(numericUpDown2.Value))
                {
                    label8.Text = "Количество сравнений = " + Convert.ToString(sr) + " Количество обменов=" + Convert.ToString(obm);
                }
                if (checkBox2.Checked)
                {
                    chart1.Series[0].Points.AddXY(n, sr);
                    chart2.Series[0].Points.AddXY(n, obm);
                }
            }

        void swap(ref int x, ref int y)
        {
            int z = x; x = y; y = z;
        }


        private void button2_Click(object sender, EventArgs e)
        {
            button2.Enabled = false;
            chart1.Series[0].Points.Clear();
            chart2.Series[0].Points.Clear();
            dataGridView1.Rows.Clear();
            dataGridView1.Columns.Clear();
            i = 0;
            button1.Enabled = true;

        }


        private void progressBar1_Click(object sender, EventArgs e)
        {

        }

        private void statusStrip1_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {

        }

        private void toolStripStatusLabel1_Click(object sender, EventArgs e)
        {

        }

        private void tabControl1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void tabPage4_Click(object sender, EventArgs e)
        {

        }

        private void chart2_Click(object sender, EventArgs e)
        {

        }

        private void numericUpDown2_ValueChanged(object sender, EventArgs e)
        {

        }

        private void numericUpDown3_ValueChanged(object sender, EventArgs e)
        {

        }

        private void numericUpDown4_ValueChanged(object sender, EventArgs e)
        {

        }

        private void numericUpDown5_ValueChanged(object sender, EventArgs e)
        {

        }

        private void label9_Click(object sender, EventArgs e)
        {

        }

        int n, m;
        private void button3_Click(object sender, EventArgs e)
        {

            int i, j, k, q, num;
            int maxVal = 0, maxCol = 0, minVal = 0, minCol;  // Макс и мин элемент матрицы, и их столбцы
            button3.Enabled = false;
            if (numericUpDown8.Value < numericUpDown7.Value)
            {
                label9.Text = "Макcимальное значение не может быть меньше минимального значения!"; return;
            }
            n = Convert.ToInt32(numericUpDown6.Value);
            m = Convert.ToInt32(numericUpDown6.Value);
            int[,] matrix;
            matrix = new int[m, n];
            Random rand = new Random();
            dataGridView2.AutoResizeColumns();
            dataGridView2.ColumnCount = n;

            // Создание матрицы и заполнение таблицы:
            for (i = 0; i < n; i++)
            {
                dataGridView2.Rows.Add();
                for (j = 0; j < m; j++)
                {
                    matrix[i, j] = rand.Next(Convert.ToInt32(numericUpDown7.Value),
                        Convert.ToInt32(numericUpDown8.Value));
                    dataGridView2.Rows[i].Cells[j].Value = matrix[i, j];
                }
            }

            // Поиск максимального и минимального значения
            for (i = 0; i < n; i++)
            {
                for (j = 0; j < m; j++)
                {
                    num = matrix[i, j];
                    if (num > maxVal) { maxVal = num; maxCol = j; }
                    else if (num < minVal) { minVal = num; minCol = j; }
                }
            }

            // Заполнение итоговой матрицы:
            // 1- сохранить исходный код. 2-создать массив со столбцами максимальных и минимальных элементов. 3-сделать проверку если вдруг все столбцы будут удалены. 4-в исх.

            /*
            q = 0;
            k = 0;
            if (matrix[n - 1, m - 1] < 0) k++;
            for (q = 0; q < n - 1; q++)
            {
                if (matrix[q, m - 1] < 0)
                {
                    k++;
                    for (i = q; i < n - 1; i++)
                    {
                        for (j = 0; j < m; j++) matrix[i, j] = matrix[i + 1, j];

                    }
                    q--;
                }
                if (k + q + 1 == n) { break; }
            }
            if (k == n)
            {
                label9.Text = "В матрице удалены все строки";
                return;
            }
            if (k == 0)
            {
                label9.Text = "В матрице нет удаленных строк";
                return;
            }
            label9.Text = "В матрице удалено " + (k - 1) + " строк(и)";
            for (j = 0; j < m; j++)
            {
                matrix[n - k, j] = 0;
                for (i = 0; i < n - k; i++)
                {
                    matrix[n - k, j] += matrix[i, j];
                }
            }
            dataGridView3.AutoResizeColumns();
            dataGridView3.ColumnCount = n;
            for (i = 0; i <= n - k; i++)
            {
                dataGridView3.Rows.Add();
                for (j = 0; j < m; j++)
                {
                    dataGridView3.Rows[i].Cells[j].Value = matrix[i, j];
                }
            } */
        }


        private void label15_Click(object sender, EventArgs e)
        {

        }

        private void button4_Click(object sender, EventArgs e)
        {
            dataGridView2.Rows.Clear();
            dataGridView2.Columns.Clear();
            dataGridView3.Rows.Clear();
            dataGridView3.Columns.Clear();
            button3.Enabled = true;
            label9.Text = "";
        }

        private void label16_Click(object sender, EventArgs e)
        {

        }

        private void dataGridView2_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void dataGridView3_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void statusStrip2_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {

        }
    }
}
