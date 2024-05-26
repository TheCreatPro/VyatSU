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

        private void button1_Click(object sender, EventArgs e)
        {
            button1.Enabled = false;
            double x1, y1, x2, y2, r2, x3, y3, r3, h3, x4, y4, r4, h4;
            x1 = Convert.ToDouble(textBox1.Text);
            y1 = Convert.ToDouble(textBox2.Text);
            x2 = Convert.ToDouble(textBox3.Text);
            y2 = Convert.ToDouble(textBox4.Text);
            r2 = Convert.ToDouble(textBox5.Text);
            x3 = Convert.ToDouble(textBox6.Text);
            y3 = Convert.ToDouble(textBox7.Text);
            r3 = Convert.ToDouble(textBox8.Text);
            //h3 = Convert.ToDouble(textBox10.Text);
            x4 = Convert.ToDouble(textBox9.Text);
            y4 = Convert.ToDouble(textBox10.Text);
            r4 = Convert.ToDouble(textBox11.Text);
            h4 = Convert.ToDouble(textBox12.Text);
            Point tochka = new Point();
            tochka.pointX = x1;
            tochka.pointY = y1;
            textBox18.Text += "Точка" + Environment.NewLine;
            textBox18.Text += "x= " + tochka.pointX + "\t";
            textBox18.Text += "y= " + tochka.pointY + Environment.NewLine;
            textBox18.Text += Environment.NewLine + Environment.NewLine;
            textBox18.Text += "Круг" + Environment.NewLine;
            textBox18.Text += "Невозможно создать экземпляр абстрактного класса Circle "
           + Environment.NewLine;
            textBox18.Text += Environment.NewLine + Environment.NewLine;
            Scope roll = new Scope(x3, y3, r3);
            textBox18.Text += "Цилиндр" + Environment.NewLine;
            textBox18.Text += "x= " + roll.pointX + "\t";
            textBox18.Text += "x= " + roll.pointY + "\t";
            textBox18.Text += "r= " + roll.Rad + "\t";
            textBox18.Text += "Площадь основания цилиндра = " + string.Format("{0:F3}",
           roll.square()) + Environment.NewLine;
            textBox18.Text += "Объем цилиндра = " + string.Format("{0:F3}", roll.volume()) +
           Environment.NewLine;
            textBox18.Text += "Площадь поверхности цилиндра = " + string.Format("{0:F3}",
           roll.squareFull()) + Environment.NewLine;
            textBox18.Text += Environment.NewLine + Environment.NewLine;

            Cone bell = new Cone(x4, y4, r4);
            bell.Hei = h4;
            textBox18.Text += "Конус" + Environment.NewLine;
            textBox18.Text += "x= " + bell.pointX + "\t";
            textBox18.Text += "x= " + bell.pointY + "\t";
            textBox18.Text += "r= " + bell.Rad + "\t";
            textBox18.Text += "h= " + bell.Hei + Environment.NewLine;
            textBox18.Text += "Площадь основания конуса = " + string.Format("{0:F3}",
           bell.square()) + Environment.NewLine;
            textBox18.Text += "Объем конуса = " + string.Format("{0:F3}", bell.volume()) +
           Environment.NewLine;
            textBox18.Text += "Площадь поверхности конуса = " + string.Format("{0:F3}",
           bell.squareFull()) + Environment.NewLine + Environment.NewLine + Environment.NewLine;
            Snowman night_King = new Snowman();
            night_King.Bucket.Hei = Convert.ToDouble(textBox13.Text);
            night_King.Bucket.Rad = Convert.ToDouble(textBox14.Text);
            night_King.Head.Rad = Convert.ToDouble(textBox15.Text);
            night_King.Average_body.Rad = Convert.ToDouble(textBox16.Text);
            night_King.Lower_body.Rad = Convert.ToDouble(textBox17.Text);
            textBox18.Text += "Объем снеговика-ходока = " + string.Format("{0:F3}",
           night_King.volume()) + Environment.NewLine;
            textBox18.Text += "Площадь поверхности снеговика-ходока = " +
           string.Format("{0:F3}", night_King.squareFull) + Environment.NewLine;
        }
        private void button2_Click(object sender, EventArgs e)
        {
            textBox18.Clear();
            button1.Enabled = true;
        }
        private void button3_Click(object sender, EventArgs e)
        {
            Close();
        }
    }
}
