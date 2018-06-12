using System.Collections.ObjectModel;

namespace MyLibrary
{
    public class Friend
    {
        public string firstName { get; set; }
        public string lastName { get; set; }
        public string email { get; set; }
        public string phone { get; set; }
        public string address { get; set; }
        public string image { get; set; }
        public ObservableCollection<string> bookList { get; set; } = new ObservableCollection<string>();
        public ObservableCollection<string> history { get; set; } = new ObservableCollection<string>();

        public override string ToString()
        {
            return "";
        }
    }
}
