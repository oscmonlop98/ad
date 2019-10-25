using System;

namespace Serpis.Ad
{
    public class ConsoleHelper
    {
        public static int ReadInteger(string label) {
            while (true) {
                Console.Write(label);
                string linea = Console.ReadLine().ToUpper();
                try {
                    return Int32.Parse(linea);
                }
                catch {
                    Console.WriteLine("Formato inválido. Vuelve a introducir.");
                }
            };

        }


    }
}
