package PR_TimeLifeApp;

import PR_Usuario.Usuario;
import Paineis.Footer;
import PR_PlayerControle.PlayerControle;

public class TimeLifeApp {
	public static Usuario 		 _usuario;
	public static PlayerControle _playercontrol;
	public static Footer         _footer;
	
	//LINKS SERVIDORES
	public static String _logar 	   	 		 = "https://saladocodigo.000webhostapp.com/Funcionalidades/Usuario/Logar.php";
	public static String _Seguidores   	 		 = "https://saladocodigo.000webhostapp.com/Funcionalidades/Usuario/Seguidores.php";
	public static String _EnviarMusica   		 = "https://saladocodigo.000webhostapp.com/Funcionalidades/Usuario/setMusica.php";
	public static String _ReceberMusicas         = "https://saladocodigo.000webhostapp.com/Funcionalidades/Usuario/getMusica.php";
	public static String _ReceberTodasAsMusicas  = "https://saladocodigo.000webhostapp.com/Funcionalidades/Sistema/getMusicasShow.php";
	public static String _PlayListFavoritos      = "https://saladocodigo.000webhostapp.com/Funcionalidades/Sistema/Metodos_Playlist/getFavoritePlayList.php";
	public static String _CadastrarUsuario       = "https://saladocodigo.000webhostapp.com/Funcionalidades/Sistema/cadastrarUsuario.php";
	public static String _FavoritarMusica 		 = "https://saladocodigo.000webhostapp.com/Funcionalidades/Sistema/Metodos_Playlist/AdicionarMusicaPlayList.php";
}
