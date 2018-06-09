using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MyLibrary
{
    public class Book
    {
        public string isbn { get; set; }
        public string title { get; set; }
        public Author author { get; set; }
        public string description { get; set; }
        public string image { get; set; }
        public string isAvailable { get; set; } = "Yes";

        public string friend { get; set; }
        public string contact { get; set; }
        public DateTime? borrowDate { get; set; }
        public DateTime? returnDate { get; set; }

    }
}
