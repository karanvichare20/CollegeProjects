using Microsoft.Win32;
using System;
using System.Collections.ObjectModel;
using System.Linq;
using System.Windows;
using System.Windows.Media.Imaging;

namespace MyLibrary
{
    /// <summary>
    /// Interaction logic for AddFriend.xaml
    /// </summary>
    public partial class AddFriend : Window
    {
        Friend friend = new Friend();

        public AddFriend()
        {
            InitializeComponent();
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            g_add_friend.DataContext = friend;
        }

        private void Btn_image_browse_Click(object sender, RoutedEventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            openFileDialog.Filter = "Image files (*.png;*.jpeg)|*.png;*.jpeg|All files (*.*)|*.*";
            if (openFileDialog.ShowDialog() == true)
            {
                string path = System.IO.Path.GetFullPath(openFileDialog.FileName);
                TBx_image_path.Text = path;
                friend.image = path;
                friend_image.Source = new BitmapImage(new Uri(path));
            }
        }

        private void Btn_save_friend_Click(object sender, RoutedEventArgs e)
        {
            if(friend.phone == null)
            {
                MessageBox.Show("Please enter phone number.");
                return;
            }

            Friend fList = (from f in MainWindow.friends where f.phone.Contains(friend.phone) select f).FirstOrDefault<Friend>();
            if (fList != null)
            {
                MessageBox.Show("A friend with this phone number exists in database. Please try another.");
                return;
            }

            MainWindow.friends.Add(friend);
            MyStorage.WriteXML<ObservableCollection<Friend>>("friends.xml", MainWindow.friends);
            MessageBox.Show("New friend has been added");
            this.Close();
        }

        private void Btn_close_friend_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

    }
}
