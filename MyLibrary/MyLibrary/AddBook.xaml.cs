using Microsoft.Win32;
using System;
using System.Collections.ObjectModel;
using System.Linq;
using System.Windows;
using System.Windows.Media.Imaging;

namespace MyLibrary
{
    /// <summary>
    /// Interaction logic for Add.xaml
    /// </summary>
    public partial class AddBook : Window
    {
        Book book = new Book();

        public AddBook()
        {
            InitializeComponent();
            
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            g_add_book.DataContext = book;
        }

        private void Btn_image_browse_Click(object sender, RoutedEventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            openFileDialog.Filter = "Image files (*.png;*.jpeg)|*.png;*.jpeg|All files (*.*)|*.*";
            if (openFileDialog.ShowDialog() == true)
            {
                string path = System.IO.Path.GetFullPath(openFileDialog.FileName);
                TBx_image_path.Text = path;
                book.image = path;
                book_image.Source = new BitmapImage(new Uri(path));
            }
                
        }

        private void Btn_save_book_Click(object sender, RoutedEventArgs e)
        {
            if (book.isbn == null)
            {
                MessageBox.Show("Please enter ISBN.");
                return;
            }

            Book bList = (from b in MainWindow.books where b.isbn.Contains(book.isbn) select b).FirstOrDefault<Book>();
            if (bList != null)
            {
                MessageBox.Show("The book already exists in database. Please try another.");
                return;
            }

            MainWindow main = (MainWindow)Application.Current.MainWindow;
            MainWindow.books.Add(book);
            main.availableCount++;
            MyStorage.WriteXML<ObservableCollection<Book>>("books.xml", MainWindow.books);
            MessageBox.Show("New book has been added");
            this.Close();
        }

        private void Btn_close_book_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

    }
}
