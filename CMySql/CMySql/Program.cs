using System;
using System.Data;
using MySql.Data.MySqlClient;

using Serpis.Ad;

namespace CMySql
{
    class MainClass
    {
        private static IDbConnection dbConnection;
        public static void Main(string[] args)
        {
            Console.WriteLine("Acceso a dbprueba");
            dbConnection = new MySqlConnection(
                 "server=localhost;database=dbprueba;user=root;password=sistemas;ssl-mode=none"
            );
            dbConnection.Open();

            InsertValue();
            ShowAll();
            ShowMetaInfo();

            dbConnection.Close();
        }

        public static void ShowAll() {
            IDbCommand dbCommand = dbConnection.CreateCommand();
            dbCommand.CommandText = "select * from categoria";
            IDataReader dataReader = dbCommand.ExecuteReader();

            while (dataReader.Read()) {
                Console.WriteLine("id={0} nombre={1}", dataReader["id"], dataReader["nombre"]);
            }

            dataReader.Close();
        }

        public static void InsertValue() {
            IDbCommand dbCommand = dbConnection.CreateCommand();
            //string nombre = "nuevo " + DateTime.Now;
            Console.Write("Nombre: ");
            string nombre = Console.ReadLine();
            dbCommand.CommandText = "insert into categoria (nombre) values (@nombre)";

            //IDbDataParameter dbDataParameter = dbCommand.CreateParameter();
            //dbDataParameter.ParameterName = "nombre";
            //dbDataParameter.Value = nombre; //"cat. 33" 
            //dbCommand.Parameters.Add(dbDataParameter);
            DbCommandHelper.AddParameter(dbCommand, "nombre", nombre);

            dbCommand.ExecuteNonQuery();
        }

        public static void ShowMetaInfo() {
            IDbCommand dbCommand = dbConnection.CreateCommand();
            dbCommand.CommandText = "select * from categoria";
            IDataReader dataReader = dbCommand.ExecuteReader();

            Console.WriteLine("FieldCount={0}", dataReader.FieldCount);
            for (int index = 0; index < dataReader.FieldCount; index++)
                Console.WriteLine("Field {0,3} = {1,-15} Type= {2}", index, dataReader.GetName(index), 
                  dataReader.GetFieldType(index));

            dataReader.Close();
        }
    }
}
