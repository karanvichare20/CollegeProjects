using System;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Xml.Serialization;

namespace MyLibrary
{
    class MyStorage
    {
        internal static void WriteXML<T>(string file, T data)
        {
            FileStream stream;
            XmlSerializer xmlSer = new XmlSerializer(typeof(T));
            stream = new FileStream(file, FileMode.Create);
            xmlSer.Serialize(stream, data);
            stream.Close();
        }

        internal static T ReadXML<T>(string file)
        {
            try
            {
                using (StreamReader sr = new StreamReader(file))
                {
                    XmlSerializer xmlSer = new XmlSerializer(typeof(T));
                    return (T)xmlSer.Deserialize(sr);
                }
            }
            catch
            {
                return default(T);
            }
        }
    }
}
