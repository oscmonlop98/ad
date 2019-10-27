using System;
namespace CGtk
{
    public class Categoria
    {
        public Categoria() {
        }

        public Categoria(ulong id, string nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        private ulong id;
        private string nombre;

        public ulong Id {
            get { return id; }
            set { id = value; }
        }

        public string Nombre {
            get { return nombre; }
            set { nombre = value; }
        }

        public override string ToString() {
            return String.Format("{0} {1}", id, nombre); 
        }

    }
}
