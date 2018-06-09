using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Data;

namespace MyLibrary
{
    public class IsbnToTitleConverter : IValueConverter
    {
        public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
        {
            ObservableCollection<string> titleList = new ObservableCollection<string>();

            foreach (string element in (ObservableCollection<string>)value)
            {
                Book book = (from b in MainWindow.books where b.isbn.Contains(element) select b).FirstOrDefault<Book>();
                titleList.Add(book.title);
            }

            return titleList;
        }

        public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
        {
            throw new NotImplementedException();
        }
    }
}
