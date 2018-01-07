package dev.mrodriguezul.mismascotas.presentador;


public interface IPerfilFragmentPresenter {
    public void mostrarFotos();
    public void obtenerUsuariosInstagramBySearch(String username);
    public void obtenerMediaUsuario(String userId);
    public void mostrarResultado();
}
