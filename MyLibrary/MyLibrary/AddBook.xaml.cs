using Microsoft.Win32;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace MyLibrary
{
    /// <summary>
    /// Interaction logic for Add.xaml
    /// </summary>
    public partial class AddBook : Window
    {
        //Book book = new Book();
        Book book = new Book { author = new Author() };

        public AddBook()
        {
            InitializeComponent();

            //MainWindow.books.Add(book);
            
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            SPn_new_book.DataContext = book;

        }

        private void Btn_save_book_Click(object sender, RoutedEventArgs e)
        {
            MainWindow.books.Add(book);
            MyStorage.WriteXML<ObservableCollection<Book>>("books.xml", MainWindow.books);
            MessageBox.Show("New book has been added");
            this.Close();
        }

        private void Btn_close_book_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void Btn_image_browse_Click(object sender, RoutedEventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            openFileDialog.Filter = "Image files (*.png;*.jpeg)|*.png;*.jpeg|All files (*.*)|*.*";
            if (openFileDialog.ShowDialog() == true)
                TBx_image_path.Text = System.IO.Path.GetFullPath(openFileDialog.FileName);
        }
    }
}
