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
                .Add("2 - Editar", menuEditar)
                .Add("3 - Borrar", menuBorrar)
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

        private static void menuEditar() {

            Menu.Create("\nMenú Editar\n")
                .Add("1 - Listar los artículos", modificar)
                .ExitWhen("2 - Volver al menú artículo")
                .Loop();
        }

        private static void menuBorrar() {

            Menu.Create("\nMenú Borrar\n")
                .Add("1 - Listar los artículos", borrar)
                .ExitWhen("2 - Volver al menú artículo")
                .Loop();
        }

        private static void modificar() {

            string seleccionado = "0";
            int idSelected = 0;

            mostrar();
            IDbCommand dbCommand = dbConnection.CreateCommand();

            Console.WriteLine("Selecciona un artículo: ");
            seleccionado = Console.ReadLine();
            idSelected = Int32.Parse(seleccionado);

            Console.Write("Introduce el nombre del artículo: ");
            string nombre = Console.ReadLine();
            Console.Write("Introduce el precio de " + nombre + ": ");
            string precio = Console.ReadLine();
            Console.Write("Introduce la categoria de " + nombre + ": ");
            string categoria = Console.ReadLine();

            //Actualización de datos
            dbCommand.CommandText = "UPDATE articulo SET nombre = @nombre, precio = @precio, categoria = @categoria WHERE id = @idSelected;";
            DbCommandHelper.AddParameter(dbCommand, "idSelected", idSelected);
            DbCommandHelper.AddParameter(dbCommand, "nombre", nombre);
            DbCommandHelper.AddParameter(dbCommand, "precio", precio);
            DbCommandHelper.AddParameter(dbCommand, "categoria", categoria);
            dbCommand.ExecuteNonQuery();

        }

        private static void borrar() {

            string seleccionado = "0";
            int idSelected = 0;

            mostrar();

            IDbCommand dbCommand = dbConnection.CreateCommand();

            Console.WriteLine("Selecciona un artículo: ");
            seleccionado = Console.ReadLine();
            idSelected = Int32.Parse(seleccionado);

            dbCommand.CommandText = "DELETE FROM articulo WHERE id = @idSelected;";
            DbCommandHelper.AddParameter(dbCommand, "idSelected", idSelected);
            dbCommand.ExecuteNonQuery();

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

        public static void comprobar(int buscaId) {
            IDbCommand dbCommand = dbConnection.CreateCommand();
            dbCommand.CommandText = "select id from articulo where id = buscaId";
            IDataReader dataReader = dbCommand.ExecuteReader();


        

        //public static int ReadInteger(string label) {
        //    while (true) {
        //        Console.Write(label);
        //        string linea = Console.ReadLine();
        //        try {
        //            return Int32.Parse(linea);
        //        }
        //        catch {
        //            Console.WriteLine("Formato inválido. Vuelve a introducir.");
        //        }
        //    };

        //}

    }
}