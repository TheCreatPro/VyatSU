using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Basic_Part
{
    struct Book
    {
        public string BookID;  // Код книги
        public string TicketNumber;  // Номер читательского билета
        public DateTime DateStart;  // Дата выдачи
        public DateTime DateEnd;  // Срок выдачи
        public Book(string f, string p, DateTime d, DateTime deg)  // конструктор
        {
            BookID = f;
            TicketNumber = p;
            DateStart = d;
            DateEnd = deg;
        }
    }
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            this.FormBorderStyle = FormBorderStyle.FixedSingle;
        }
        private void Form1_Load(object sender, EventArgs e)
        {
            dataGridView1.RowHeadersVisible = false;
            dataGridView1.ColumnCount = 4;
            dataGridView1.Columns[0].HeaderText = "Код книги";
            dataGridView1.Columns[1].HeaderText = "Номер читательского билета";
            dataGridView1.Columns[2].HeaderText = "Дата выдачи";
            dataGridView1.Columns[3].HeaderText = "Срок выдачи";
            dataGridView2.ColumnCount = 4;
            dataGridView2.Columns[0].HeaderText = "Код книги";
            dataGridView2.Columns[1].HeaderText = "Номер читательского билета";
            dataGridView2.Columns[2].HeaderText = "Дата выдачи";
            dataGridView2.Columns[3].HeaderText = "Срок выдачи";
            dataGridView2.RowHeadersVisible = false;
            dataGridView2.AutoResizeColumns();
        }
        Book[] library = new Book[0];
        int count = 0;
        private void button1_Click(object sender, EventArgs e)  // Добавление элемента
        {
            Array.Resize(ref library, library.Length + 1);
            library[count].BookID = textBox1.Text;
            library[count].TicketNumber = textBox2.Text;
            library[count].DateStart = dateTimePicker1.Value;
            library[count].DateEnd = dateTimePicker2.Value;
            dataGridView1.Rows.Add(library[count].BookID, library[count].TicketNumber,
                library[count].DateStart, library[count].DateEnd);
            count++;
        }
        private void button2_Click(object sender, EventArgs e)  // Поиск элемента
        {
            dataGridView2.Rows.Clear();
            DateTime inputdDateEnd = dateTimePicker3.Value;

            /*for (int i = 0; i < client.Length - 1; i++)
            {
                if (client[i].Date_End < select1) dataGridView2.Rows.Add(client[i].Book_ID, client[i].Ticket_Number, client[i].Date_Start, client[i].Date_End);
            }*/
            foreach (Book element in library)
            {
                if (element.DateEnd < inputdDateEnd)
                    dataGridView2.Rows.Add(element.BookID, element.TicketNumber, element.DateStart, element.DateEnd);
            }
        }
        
        private void добавитьДанныеToolStripMenuItem_Click_1(object sender, EventArgs e)
        {
            button1_Click(sender, e);
        }

        private void выполнитьЗапросToolStripMenuItem_Click_1(object sender, EventArgs e)
        {
            button2_Click(sender, e);
        }

        private void выходToolStripMenuItem_Click_1(object sender, EventArgs e)
        {
            Close();
        }
    }
}