using System;
using System.Collections.ObjectModel;
using System.Data;
using System.Linq;
using System.Windows;
using System.Windows.Input;

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

        public Borrow(Book book)
        {
            InitializeComponent();
            selectedBook = book;
            date = DateTime.Now;
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            g_book_details.DataContext = selectedBook;
            SPx_date.DataContext = this;
            SPx_count_days.DataContext = this;
            CBx_friend.ItemsSource = MainWindow.friends;
        }

        private void CBx_friend_PreviewTextInput(object sender, TextCompositionEventArgs e)
        {
            var list = from s in MainWindow.friends where s.firstName.ToLower().Contains(CBx_friend.Text.ToLower()) select s;
            CBx_friend.ItemsSource = list;
            CBx_friend.IsDropDownOpen = true;
        }

        private void Btn_save_Click(object sender, RoutedEventArgs e)
        {
            MainWindow main = (MainWindow)Application.Current.MainWindow;
            if (CBx_friend.SelectedItem == null)
            {
                MessageBox.Show("Please select a friend ...");
                return;
            }

            selectedFriend = CBx_friend.SelectedItem as Friend;
            Friend friend = (from f in MainWindow.friends where f.phone.Contains(selectedFriend.phone) select f).FirstOrDefault<Friend>();

            selectedBook.isAvailable = "No";
            selectedBook.friend = friend.firstName + " " + friend.lastName;
            selectedBook.contact = friend.phone;
            selectedBook.borrowDate = date;
            selectedBook.returnDate = date.AddDays(days);
            friend.bookList.Add(selectedBook.isbn);
            main.availableCount--;
            MyStorage.WriteXML<ObservableCollection<Friend>>("friends.xml", MainWindow.friends);
            MessageBox.Show("The book has been borrowed");
            this.Close();
        }

        private void Btn_close_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }
    }
}
