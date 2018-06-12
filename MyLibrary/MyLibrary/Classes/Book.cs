using System;

namespace MyLibrary
{
    public class Book
    {
        public string isbn { get; set; }
        public string title { get; set; }
        public string author { get; set; }
        public string description { get; set; }
        public string image { get; set; }
        public string isAvailable { get; set; } = "Yes";
        public string friend { get; set; }
        public string contact { get; set; }
        public DateTime? borrowDate { get; set; }
        public DateTime? returnDate { get; set; }

    }
}
