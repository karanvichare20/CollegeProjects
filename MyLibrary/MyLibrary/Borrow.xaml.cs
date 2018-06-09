using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
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
    /// Interaction logic for Borrow.xaml
    /// </summary>
    public partial class Borrow : Window
    {
        Book selectedBook;
        Friend selectedFriend = new Friend();

        public DateTime date { get; set; }
        public double days { get; set; }
        //public DateTime returnDate { get; set; }

        public Borrow(Book book)
        {
            InitializeComponent();
            selectedBook = book;
            date = DateTime.Now;
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            //SPx_book_details.DataContext = selectedBook;
            g_book_details.DataContext = selectedBook;
            //g_friend_detail.DataContext = friend;
            //SPx_friend_details.DataContext = friend;
            SPx_date.DataContext = this;
            SPx_count_days.DataContext = this;
            LBx_friend.ItemsSource = MainWindow.friends;
        }

        private void Btn_save_Click(object sender, RoutedEventArgs e)
        {
            if(LBx_friend.SelectedItem == null)
            {
                MessageBox.Show("Please select a friend ...");
                return;
            }

            selectedFriend = LBx_friend.SelectedItem as Friend;
            Friend friend = (from f in MainWindow.friends where f.phone.Contains(selectedFriend.phone) select f).FirstOrDefault<Friend>();

            selectedBook.isAvailable = "No";
            selectedBook.friend = friend.firstName +" "+ friend.lastName;
            selectedBook.contact = friend.phone;
            selectedBook.borrowDate = date;
            selectedBook.returnDate = date.AddDays(days);
            friend.bookList.Add(selectedBook.isbn);
            //MainWindow.friends.Add(friend);
            MyStorage.WriteXML<ObservableCollection<Friend>>("friends.xml", MainWindow.friends);
            MessageBox.Show("The book has been borrowed");
            this.Close();
        }

        private void Btn_close_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void TBx_filter_friend_TextChanged(object sender, TextChangedEventArgs e)
        {
            var list = from s in MainWindow.friends where s.firstName.ToLower().Contains(TBx_friend_filter.Text.ToLower()) select s;
            LBx_friend.ItemsSource = list;
        }
    }
}
