﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MyLibraryApp
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class BorrowBook : ContentPage
	{
        internal DateTime Date { get; set; }
        internal int Days { get; set; } = 0;

        Book book;
        Friend friend;

        public BorrowBook (Book book)
		{
			InitializeComponent ();
            BindingContext = this.book = book;
            Date = DateTime.Now;
        }

        private async void Button_Clicked(object sender, EventArgs e)
        {
            if(Pkr_fndlst.SelectedItem == null)
            {
                await DisplayAlert("Alert", "Please select a friend.", "OK");
                return;
            }

            book.BorrowDate = Date.ToLongDateString();
            if (!string.IsNullOrEmpty(Ent_numofdays.Text))
            {
                Days = int.Parse(Ent_numofdays.Text);
            }
            book.ReturnDate = Date.AddDays(Days).ToLongDateString();

            //friend = (from f in App._friendlist where f.Phone.Contains((Pkr_fndlst.SelectedItem as Friend).Phone) select f).FirstOrDefault<Friend>();
            friend.BookList.Add(book.Isbn);
            book.Friend = friend.FirstName + " " + friend.LastName;
            book.Contact = friend.Phone;
            book.Availability = "No";

            await Navigation.PopAsync();
        }

        private void ContentPage_Appearing(object sender, EventArgs e)
        {
            Pkr_fndlst.ItemsSource = App._friendlist;
            Lbl_currdate.Text = Date.ToLongDateString();
            Lbl_bktitle.Text = book.Title;
            Lbl_bkauth.Text = book.Author;

            Slt_frndetail.IsVisible = false;
        }

        private void Pkr_fndlst_SelectedIndexChanged(object sender, EventArgs e)
        {
            if(Pkr_fndlst.SelectedItem == null)
            {
                return;
            }

            friend = (from f in App._friendlist where f.Phone.Contains((Pkr_fndlst.SelectedItem as Friend).Phone) select f).FirstOrDefault<Friend>();
            Slt_frndetail.IsVisible = true;
            Lbl_contact.Text = friend.Phone;

        }

        private void ToolbarItem_Clicked(object sender, EventArgs e)
        {
            Pkr_fndlst.SelectedItem = null;
            Ent_numofdays.Text = string.Empty;
            Slt_frndetail.IsVisible = false;
        }
    }
}