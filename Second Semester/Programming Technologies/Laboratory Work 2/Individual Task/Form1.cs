using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics.Tracing;
using System.Drawing;
using System.Linq;
using System.Reflection.Emit;
using System.Runtime.Remoting.Contexts;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Net.Mime.MediaTypeNames;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;
using static System.Windows.Forms.VisualStyles.VisualStyleElement.ProgressBar;

namespace Laboratory_Work_2
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            this.KeyPress += new KeyPressEventHandler(buttonPressed);
            this.FormBorderStyle = FormBorderStyle.FixedSingle;
        }
        int FindWord(String FWord, int beginValue, int n)  // beginValue значение с которого начинается поиск
        {
            int LenWord;
            String ComparText;
            LenWord = FWord.Length;
            for (int i = beginValue; i <= n - LenWord; i++)
            {
                ComparText = inputBox.Text.Substring(i, LenWord);
                if (ComparText == FWord)
                {
                    return i;
                }
            }
            return -1;
        }

        static List<List<string>> SplitText(string inputText)
        {
            List<List<string>> sentences = new List<List<string>>();
            // Разделители предложений:
            string[] sentenceSeparators = { ".", "!", "?" };  
            // Второй аргумент указывает что метод сплит должен удалить все пустые подстроки из результирующего массива:
            string[] inputSentences = inputText.Split(sentenceSeparators, StringSplitOptions.RemoveEmptyEntries);
            // Разделители слов, можно добавлять свои:
            string[] wordSeparators = { " ", ",", ";" };
            foreach (var sentence in inputSentences)
            {
                List<string> words = new List<string>();
                // Тоже самое, только для слов:
                string[] inputWords = sentence.Split(wordSeparators, StringSplitOptions.RemoveEmptyEntries);
                foreach (var word in inputWords)
                {
                    words.Add(word);
                }
                sentences.Add(words);
            }
            return sentences;
        }

        string MyFName = "";
        private void openFile_Click(object sender, System.EventArgs e)  // Открытие файла
        {
            try
            {
                string text = "";
                openFileDialog1.Filter = "Текстовые файлы (*.rtf; *.txt; *.dat) | *.rtf; *.txt; *.dat";
                if (openFileDialog1.ShowDialog() == DialogResult.OK)
                {
                    MyFName = openFileDialog1.FileName;
                    inputBox.LoadFile(MyFName);
                    text = inputBox.Text.ToString();
                }
                logBox.Text += $"Файл '{MyFName}' открыт." + Environment.NewLine + Environment.NewLine;
                // Подсчёт кол-ва предложений
                var sentences = SplitText(text);
                int countSentences = sentences.Count;
                logBox.Text += $"Всего предложений в файле: {countSentences}." + Environment.NewLine;
                for (int i = 0; i < countSentences; i++)
                {
                    logBox.Text += $"В {i + 1}-м предложении слов: {sentences[i].Count}." + Environment.NewLine;
                }
            }
            catch { logBox.Text += Environment.NewLine + $"Ошибка открытия файла!" + Environment.NewLine + Environment.NewLine; }
            
        }
        private void saveFile_Click(object sender, EventArgs e)  // Сохранение файла
        {
            if (MyFName != "")
            {
                inputBox.SaveFile(MyFName);
            }
            else
            {
                saveFileDialog1.Filter = "Текстовые файлы (*.rtf; *.txt; *.dat) | *.rtf; *.txt; *.dat";
                if (saveFileDialog1.ShowDialog() == DialogResult.OK)
                {
                    MyFName = saveFileDialog1.FileName;
                    inputBox.SaveFile(MyFName);
                }
            }
        }
        private void saveAsFile_Click(object sender, System.EventArgs e)
        {
            saveFileDialog1.Filter = "Текстовые файлы (*.rtf; *.txt; *.dat) | *.rtf *.txt; *.dat";
            if (saveFileDialog1.ShowDialog() == DialogResult.OK)
            {
                MyFName = saveFileDialog1.FileName;
                inputBox.SaveFile(MyFName);
            }
        }

        private void Form1_Load(object sender, EventArgs e)  // Загрузчик
        {
            System.Windows.Forms.ContextMenu contextMenu1;
            contextMenu1 = new System.Windows.Forms.ContextMenu();
            System.Windows.Forms.MenuItem menuItem1;
            menuItem1 = new System.Windows.Forms.MenuItem();
            System.Windows.Forms.MenuItem menuItem2;
            menuItem2 = new System.Windows.Forms.MenuItem();
            System.Windows.Forms.MenuItem menuItem3;
            menuItem3 = new System.Windows.Forms.MenuItem();
            contextMenu1.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] { menuItem1, menuItem2, menuItem3 });
            menuItem1.Index = 0;
            menuItem1.Text = "Открыть";
            menuItem2.Index = 1;
            menuItem2.Text = "Сохранить";
            menuItem3.Index = 2;
            menuItem3.Text = "Сохранить как";
            inputBox.ContextMenu = contextMenu1;
            menuItem1.Click += new System.EventHandler(this.openFile_Click);
            menuItem2.Click += new System.EventHandler(this.saveFile_Click);
            menuItem3.Click += new System.EventHandler(this.saveAsFile_Click);

            
        }

        private void statusStrip1_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {

        }

        int words, sentences, prevPosBeginWord, prevPosBeginSent = 0;
        private void buttonPressed(object sender, KeyPressEventArgs e)
        {
            try
            {
                int lenText = inputBox.Text.Length;
                var text = SplitText(inputBox.Text.ToString());  // список списков предложений и слов
                List<string> sentence = text[sentences];  // список слов в предложении

                inputBox.SelectionStart = 0;
                inputBox.SelectionLength = lenText;
                inputBox.SelectionBackColor = Color.White;

                int posBeginSent = FindWord(sentence[0], prevPosBeginSent, lenText);  // Позиция начала предложения
                int lenSent = FindWord(sentence.Last(), prevPosBeginSent, lenText) + sentence.Last().Length - posBeginSent - 1;  // Длина предложения
                int posBeginWord = FindWord(sentence[words], prevPosBeginWord, lenText);  // Позиция начала слова
                int lenWord = sentence[words].Length;  // Длина слова
            
                inputBox.SelectionStart = posBeginSent;
                inputBox.SelectionLength = lenSent + 1;  // +1 т.к. индекс не включает в себя последний символ
                inputBox.SelectionBackColor = Color.Yellow;

                inputBox.SelectionStart = posBeginWord;
                inputBox.SelectionLength = lenWord;
                inputBox.SelectionBackColor = Color.Green;
            
                if (words < sentence.Count() - 1) words++;
                else 
                { 
                    words = 0;
                    if (sentences < text.Count() - 1) 
                    {
                        sentences++; 
                        prevPosBeginSent = posBeginSent + lenSent;
                    } 
                    else sentences = 0;
                }
                prevPosBeginWord = posBeginWord + lenWord;
            }
            catch
            {
                words = sentences = prevPosBeginWord = prevPosBeginSent = 0;
                logBox.Text += Environment.NewLine + "Произведён сброс. Для продолжения нажмите любую клавишу..." + Environment.NewLine;
            }
        }

        private void clearButton_Click(object sender, EventArgs e)  // Сброс
        {
            logBox.Clear();
            inputBox.Clear();
        }

        private void inputBox_TextChanged(object sender, EventArgs e)
        {

        }
     }
}
