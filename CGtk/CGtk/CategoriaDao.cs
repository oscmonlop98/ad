using System;
using System.Collections;
using System.Collections.Generic;
using System.Data;
using Serpis.Ad;
namespace CGtk
{
    public class CategoriaDao
    {

        private static string selectAll = "select * from articulo order by id";
        public static IEnumerable GetAll() {
            IList<Categoria> categorias = new List<Categoria>();

            IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
            dbCommand.CommandText = selectAll;
            IDataReader dataReader = dbCommand.ExecuteReader();
            while (dataReader.Read()) {
                //opción 1
                categorias.Add(new Categoria((ulong)dataReader["id"], (string)dataReader["nombre"]));

                //opción 2
                //Categoria categoria = new Categoria();
                //categoria.Id = (ulong)dataReader["id"];
                //categoria.Nombre = (string)dataReader["nombre"];
                //categorias.Add(categoria);

                //opción 3
                //Type type = typeof(Categoria);
                //Categoria categoria = Activator.CreateInstance<Categoria>();
                //type.GetProperty("Id").SetValue(categoria, dataReader["id"]);
                //type.GetProperty("Nombre").SetValue(categoria, dataReader["nombre"]);
                //categorias.Add(categoria);
            }
            dataReader.Close();
            return categorias;
        }

        private static string selectSql= "select * from articulo where id = @id";
        public static Categoria Load(object id) {
            Categoria categoria = new Categoria();
            IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
            dbCommand.CommandText = selectSql;
            DbCommandHelper.AddParameter(dbCommand, "id", id);
            IDataReader dataReader = dbCommand.ExecuteReader();
            dataReader.Read();
            categoria.Id = (ulong)id;
            categoria.Nombre = (string)dataReader["nombre"];
            dataReader.Close();
            return categoria;
        }

        public static void Save(Categoria categoria) {
            if (categoria.Id == 0)
                insert(categoria);
            else
                update(categoria);
        }

        private static string insertSql = "insert into articulo (nombre) values (@nombre)";
        private static void insert(Categoria categoria) {
            IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
            dbCommand.CommandText = insertSql;
            DbCommandHelper.AddParameter(dbCommand, "nombre", categoria.Nombre);
            dbCommand.ExecuteNonQuery();
        }

        private static string updateSql = "update articulo set nombre=@nombre where id=@id";
        private static void update(Categoria categoria) {
            IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
            dbCommand.CommandText = updateSql;
            DbCommandHelper.AddParameter(dbCommand, "nombre", categoria.Nombre);
            DbCommandHelper.AddParameter(dbCommand, "id", categoria.Id);
            dbCommand.ExecuteNonQuery();
        }

        private static string deleteSql = "delete from articulo where id=@id";
        public static void Delete(object id) {
            Categoria categoria = new Categoria();
            IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
            dbCommand.CommandText = deleteSql;
            DbCommandHelper.AddParameter(dbCommand, "id", id);
            dbCommand.ExecuteNonQuery();
        }
    }
}
