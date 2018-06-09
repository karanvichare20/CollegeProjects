using Microsoft.Win32;
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
            SPn_new_friend.DataContext = friend;
        }

        private void Btn_save_friend_Click(object sender, RoutedEventArgs e)
        {
            MainWindow.friends.Add(friend);
            MyStorage.WriteXML<ObservableCollection<Friend>>("friends.xml", MainWindow.friends);
            MessageBox.Show("New friend has been added");
            this.Close();
        }

        private void Btn_close_friend_Click(object sender, RoutedEventArgs e)
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
