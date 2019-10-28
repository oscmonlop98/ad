using Gtk;
using System;
using System.Collections.Generic;
using System.Data;
using System.Reflection;

using MySql.Data.MySqlClient;

using CGtk;

using Serpis.Ad;

public partial class MainWindow : Gtk.Window
{
    public MainWindow() : base(Gtk.WindowType.Toplevel) {
        Build();

        App.Instance.DbConnection = new MySqlConnection(
            "server=localhost;database=dbprueba;user=root;password=sistemas;ssl-mode=none"
        );
        App.Instance.DbConnection.Open();


        TreeViewHelper.Fill(treeView, new string[] { "Id", "Nombre" }, CategoriaDao.GetAll());

        newAction.Activated += (sender, e) => {
            Categoria categoria = new Categoria();
            new CategoriaWindow(categoria);

            TreeViewHelper.Fill(treeView, new string[] { "Id", "Nombre" }, CategoriaDao.GetAll());
            RefreshStateActions();
        };

        editAction.Activated += (sender, e) => {
            object id = TreeViewHelper.GetId(treeView);
            Categoria categoria = CategoriaDao.Load(id);
            new CategoriaWindow(categoria);

            TreeViewHelper.Fill(treeView, new string[] { "Id", "Nombre" }, CategoriaDao.GetAll());
            RefreshStateActions();
        };

        deleteAction.Activated += (sender, e) => {
            object id = TreeViewHelper.GetId(treeView);
            CategoriaDao.Delete(id);

            TreeViewHelper.Fill(treeView, new string[] { "Id", "Nombre" }, CategoriaDao.GetAll());
            RefreshStateActions();
        };

        quitAction.Activated += (sender, e) => {
            Application.Quit();
        };

        refreshAction.Activated += (sender, e) =>
            TreeViewHelper.Fill(treeView, new string[] { "Id", "Nombre" }, CategoriaDao.GetAll());

        RefreshStateActions();
        treeView.Selection.Changed += (sender, e) => RefreshStateActions();
    }



    protected void OnDeleteEvent(object sender, DeleteEventArgs a) {
        Application.Quit();
        a.RetVal = true;
    }

    private void RefreshStateActions() {
        bool hasSelectedRows = treeView.Selection.CountSelectedRows() > 0;
        editAction.Sensitive = hasSelectedRows;
        deleteAction.Sensitive = hasSelectedRows;
    }
}
