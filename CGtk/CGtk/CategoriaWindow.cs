using System;
using System.Data;

using Serpis.Ad;

namespace CGtk
{
    public partial class CategoriaWindow : Gtk.Window
    {
        public CategoriaWindow(Categoria categoria) : base(Gtk.WindowType.Toplevel) {
            this.Build();

            entryNombre.Text = categoria.Nombre ?? "";

            buttonOk.Clicked += (sender, e) => {
                categoria.Nombre = entryNombre.Text;

                CategoriaDao.Save(categoria);
                Destroy();
            };

            buttonCancel.Clicked += (sender, e) => Destroy();
        }

        public string GetNombre() {
            return entryNombre.Text;
        }
    }
}
