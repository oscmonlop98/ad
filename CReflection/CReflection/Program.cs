using System;
using System.Reflection;

namespace CReflection
{
    class MainClass
    {
        public static void Main(string[] args) {

            Categoria categoria = new Categoria(5, "Cat 5");

            //Console.WriteLine("c.Id = {0} c.Nombre = {1}", c.Id, c.Nombre);
            show(categoria, "Nombre" );

        }

        //private static void show(object obj) {
        //    Type type = obj.GetType();
        //    Console.WriteLine("obj.GetType() = {0}", type);
        //    foreach (PropertyInfo propertyInfo in type.GetProperties())
        //        Console.WriteLine("propertyInfo.Name = {0} propertyInfo.GetValue ={1}", 
        //        propertyInfo.Name, propertyInfo.GetValue(obj));
        //}

        private static void show(object obj, params string[] propertyNames) {
            foreach (string propertyName in propertyNames)
                Console.WriteLine("propertyName = {0} Value ={1}",
                propertyName, obj.GetType().GetProperty(propertyName).GetValue(obj));
        }
    }
}
