using System;
using System.Collections.ObjectModel;
using System.Linq;
using System.Windows;
using System.Windows.Controls;

namespace MyLibrary
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public static ObservableCollection<Book> books;
        public static ObservableCollection<Friend> friends;


        public int availableCount
        {
            get { return (int)GetValue(availableCountProperty); }
            set { SetValue(availableCountProperty, value); }
        }

        // Using a DependencyProperty as the backing store for availableCount.  This enables animation, styling, binding, etc...
        public static readonly DependencyProperty availableCountProperty =
            DependencyProperty.Register("availableCount", typeof(int), typeof(MainWindow), new PropertyMetadata(0));



        public MainWindow()
        {
            InitializeComponent();

        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            if(MyStorage.ReadXML<ObservableCollection<Book>>("books.xml") == null)
            {
                books = new ObservableCollection<Book>();
            }
            else
            {
                books = MyStorage.ReadXML<ObservableCollection<Book>>("books.xml");
            }

            if (MyStorage.ReadXML<ObservableCollection<Friend>>("friends.xml") == null)
            {
                friends = new ObservableCollection<Friend>();
            }
            else
            {
                friends = MyStorage.ReadXML<ObservableCollection<Friend>>("friends.xml");
            }

            LBx_book.ItemsSource = books;
            LBx_friend.ItemsSource = friends;
            TBk_bookCount.DataContext = this;

            foreach (Book element in books)
            {
                if (element.isAvailable == "Yes")
                    availableCount++;
            }
            
        }

        private void Window_Closed(object sender, EventArgs e)
        {
            MyStorage.WriteXML<ObservableCollection<Book>>("books.xml", books);
            MyStorage.WriteXML<ObservableCollection<Friend>>("friends.xml", friends);
        }

        //--------------------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------BOOKS-------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------------------

        private void TBx_filter_book_TextChanged(object sender, TextChangedEventArgs e)
        {
            var bList = from b in books where b.title.ToLower().Contains(TBx_book_filter.Text.ToLower()) select b;
            LBx_book.ItemsSource = bList;
        }

        private void Btn_add_book_Click(object sender, RoutedEventArgs e)
        {
            AddBook addBookWin = new AddBook();
            addBookWin.Owner = this;
            addBookWin.Show();
        }

        private void Btn_remove_book_Click(object sender, RoutedEventArgs e)
        {
            if (LBx_book.SelectedItem == null)
            {
                MessageBox.Show("Please select a book to be deleted...");
                return;
            }

            books.Remove(LBx_book.SelectedItem as Book);
            availableCount--;
        }

        private void Btn_borrow_book_Click(object sender, RoutedEventArgs e)
        {
            if (LBx_book.SelectedItem == null)
            {
                MessageBox.Show("Please select a book to be borrowed...");
                return;
            }

            Book book = LBx_book.SelectedItem as Book;

            if (book.isAvailable.Equals("No"))
            {
                MessageBox.Show("The selected book is not available for borrowing...");
                return;
            }

            Borrow borrowWin = new Borrow(book);
            borrowWin.Owner = this;
            borrowWin.Show();
        }

        private void Btn_return_book_Click(object sender, RoutedEventArgs e)
        {
            if (LBx_book.SelectedItem == null)
            {
                MessageBox.Show("Please select a book to be returned...");
                return;
            }

            Book book = LBx_book.SelectedItem as Book;

            if (book.isAvailable.Equals("No"))
            {
                Friend friend = (from f in MainWindow.friends where f.phone.Contains(book.contact) select f).FirstOrDefault<Friend>();
                friend.bookList.Remove(book.isbn);
                friend.history.Add(book.isbn);

                book.isAvailable = "Yes";
                book.friend = null;
                book.borrowDate = null;
                book.returnDate = null;
                book.contact = null;
                availableCount++;

                MessageBox.Show("The book is now returned...");
                return;
            }

            MessageBox.Show("The book is already returned...");

        }

        //--------------------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------FRIENDS-----------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------------------

        private void TBx_filter_friend_TextChanged(object sender, TextChangedEventArgs e)
        {
            var fList = from f in friends where f.firstName.ToLower().Contains(TBx_friend_filter.Text.ToLower()) select f;
            LBx_friend.ItemsSource = fList;
        }

        private void Btn_add_friend_Click(object sender, RoutedEventArgs e)
        {
            AddFriend addFriendWin = new AddFriend();
            addFriendWin.Owner = this;
            addFriendWin.Show();
        }

        private void Btn_remove_friend_Click(object sender, RoutedEventArgs e)
        {
            if (LBx_friend.SelectedItem == null)
            {
                MessageBox.Show("Please select a friend to be deleted...");
                return;
            }

            friends.Remove(LBx_friend.SelectedItem as Friend);
        }

        
    }
}
