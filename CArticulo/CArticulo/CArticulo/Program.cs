using System;
using System.Data;
using MySql.Data.MySqlClient;

using Serpis.Ad;

namespace CArticulo
{
    class MainClass
    {
        private static IDbConnection dbConnection;

        public static void Main(string[] args) {

            // Acceso a la Base de datos
            dbConnection = new MySqlConnection(
                "server=localhost;database=dbprueba;user=root;password=sistemas;ssl-mode=none"
            );
        
            dbConnection.Open();

            Menu.Create("Menú Artículo\n")
                .Add("1 - Nuevo", nuevo)
                .Add("2 - Editar", editar)
                .Add("3 - Borrar", borrar)
                .Add("4 - Mostrar", mostrar)
                .ExitWhen("0 - Salir")
                .Loop();

        }

        private static void nuevo() {

            Console.WriteLine("\n Ha entrado en nuevo\n");
            IDbCommand dbCommand = dbConnection.CreateCommand();

            Console.Write("Introduce el nombre del artículo: ");
            string nombre = Console.ReadLine();
            Console.Write("Introduce el precio de " + nombre + ": ");
            string precio = Console.ReadLine();
            Console.Write("Introduce la categoria de " + nombre + ": ");
            string categoria = Console.ReadLine();

            //Inserción de datos
            dbCommand.CommandText = "insert into articulo (nombre, precio, categoria) values (@nombre, @precio, @categoria)";

            DbCommandHelper.AddParameter(dbCommand, "nombre", nombre);
            DbCommandHelper.AddParameter(dbCommand, "precio", precio);
            DbCommandHelper.AddParameter(dbCommand, "categoria", categoria);

            dbCommand.ExecuteNonQuery();

        }

        private static void editar() {

            Menu.Create("\nMenú Editar\n")
                .Add("1 - Listar los artículos", modificar)
                .Add("2 - Volver al menú artículo", editar)
                .Loop();

        }

        private static void modificar() {

            mostrar();

            Console.WriteLine("Selecciona un artículo: ");
            string seleccionado = Console.ReadLine();



        }

        private static void borrar() {
            Console.WriteLine("Ha entrado en borrar");
            Console.ReadLine();

        }

        public static int ReadInteger(string label) {
            while (true) {
                Console.Write(label);
                string linea = Console.ReadLine();
                try {
                    return Int32.Parse(linea);
                }
                catch {
                    Console.WriteLine("Formato inválido. Vuelve a introducir.");
                }
            };

        }

        public static void mostrar() {
            IDbCommand dbCommand = dbConnection.CreateCommand();
            dbCommand.CommandText = "select * from articulo";
            IDataReader dataReader = dbCommand.ExecuteReader();

            while (dataReader.Read()) {
                Console.WriteLine("id={0} nombre={1}", dataReader["id"], dataReader["nombre"]);
            }

            dataReader.Close();
        }

        //public static int ReadOption(string label, string options) {
        //    while (true) {
        //        Console.Write(label);
        //        string option = Console.ReadLine();
        //        if (option == "0")
        //            return 0;
        //    }
        //}
    }
}