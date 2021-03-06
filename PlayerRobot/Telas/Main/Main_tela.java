package Main;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import Paineis.Footer;
import Paineis.NavBar;
import Paineis.UsuarioPerfil;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import Botaos.BotaoMusica;
import Botaos.BotaoPerfil;
import PR_Metodos_Musica.PegarMP3Servidor;
import PR_Metodos_Musica.PegarTodasAsMusicas;
import PR_Metodos_Usuario.MetodosUsuarioController;
import PR_Musica.Musica;
import PR_PlayerControle.PlayerControle;
import PR_TimeLifeApp.TimeLifeApp;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.awt.Dimension;

public class Main_tela {

	public JFrame frame;
	private JPanel Body;
	private JPanel MenuMusicas;
	private Footer footer = new Footer();
	PlayerControle pc = new PlayerControle();
	int x, y;
	/**	
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_tela window = new Main_tela();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public Main_tela() throws Exception {
		initialize();
		this.CarregarMusicas();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 */
	private void initialize() throws Exception {
		
		TimeLifeApp._playercontrol = this.pc;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 834, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		NavBar nav = new NavBar();
		nav.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}
		});
		nav.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				int xx = arg0.getXOnScreen();
				int yy = arg0.getYOnScreen();
				frame.setLocation(xx - x, yy - y);
			}
		});
		frame.getContentPane().add(nav);

		Body = new JPanel();
		Body.setBounds(0, 37, 840, 483);
		Body.setBackground(new Color(28,28,28));
		Body.setLayout(new CardLayout(0, 0));
		frame.getContentPane().add(Body);
		
		
		
		
		JPanel Main = new JPanel();
		Main.setBounds(0, 0, 840, 520);
				
		Body.add(Main, "main");
		Main.setLayout(null);
		
		MenuMusicas = new JPanel();
		MenuMusicas.setAutoscrolls(true);
		MenuMusicas.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		MenuMusicas.setBounds(10, 23, 820, 449);
		MenuMusicas.setBackground(new Color(23,23,23));
		
		
		MenuMusicas.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		Main.add(MenuMusicas);
				
		JLabel Main_body = new JLabel("");
		Main_body.setBounds(0, 0, 840, 560);
		Main.add(Main_body);
		Main_body.setIcon(new ImageIcon(Main_tela.class.getResource("/Libraries/img/main.png")));
				
		//Panel Footer = new Panel();
		footer.setBounds(0, 519, 840, 41);
		frame.getContentPane().add(footer);
		TimeLifeApp._footer = footer;
		
		BotaoPerfil btnPerfil = new BotaoPerfil(this);
		btnPerfil.setBorderPainted(false);
		btnPerfil.setBorder(null);
		btnPerfil.setAlignmentX(0.5f);
		btnPerfil.setBounds(785, 2, 40, 40);
		TimeLifeApp._footer.add(btnPerfil);
		
		btnPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) 
			{	
				try 
				{
					UsuarioPerfil perfil = new UsuarioPerfil(TimeLifeApp._usuario);;
					Body.add(perfil,"perfil");
					
						MetodosUsuarioController  muc = new MetodosUsuarioController();
						muc.AtualizarSeguidores(TimeLifeApp._usuario.getId());
						muc.PegarPlayListFavoritos(TimeLifeApp._usuario.getId());
						CardLayout c = (CardLayout)(Body.getLayout());
						c.show(Body, "perfil");
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		
		JButton btnMain = new JButton();
		btnMain.setToolTipText("Seu perfil");
		btnMain.setBorderPainted(false);
		btnMain.setBorder(null);
		btnMain.setAlignmentX(0.5f);
		btnMain.setBounds(685, 2, 40, 40);
		ImageIcon imgmain = new ImageIcon(this.getClass().getResource("/Libraries/img/btnmain.png"));
		btnMain.setIcon(imgmain);
		TimeLifeApp._footer.add(btnMain);
		
		btnMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CardLayout c = (CardLayout)(Body.getLayout());
				c.show(Body,"main");
			}
			
			public void teste() {}
		});
		
		
		
	}
	public void CarregarMusicas() throws Exception 
	{
		try 
		{
			PegarTodasAsMusicas getMusicas = new PegarTodasAsMusicas();
			ArrayList<Musica> musicas = getMusicas.RetornarTodasAsMusicas();
			for(int i = 0; i < musicas.size(); i++) {
				Musica ms = musicas.get(i);
				
				BotaoMusica btnMusica = new BotaoMusica(ms.getNome());
				btnMusica.setIcon(ms.getImagem());
				btnMusica.setPreferredSize(new Dimension(120, 120));
				
				//JButton btnMusica = new JButton(ms.getNome());
				//btnMusica.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				//btnMusica.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				//btnMusica.setBorder(null);
				
				btnMusica.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent me) {
						PegarMP3Servidor getMP3 = new PegarMP3Servidor(); 
						try {
							if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {
								
								JPopupMenu MenuMusica = new JPopupMenu();
								addPopup(MenuMusicas, MenuMusica);
								
								JMenuItem mntmTocar = new JMenuItem("Tocar");
								MenuMusica.add(mntmTocar);
								
								JMenuItem mntmFavoritar = new JMenuItem("Favoritar");
								MenuMusica.add(mntmFavoritar);
								
								mntmTocar.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										try {
											ms.setArquivoMP3(getMP3.getMP3DoServidor(ms.getCodigoMp3Servidor()));
											TimeLifeApp._playercontrol.addMusica(ms);
											TimeLifeApp._playercontrol.play();
										} catch (Exception e1) {
											JOptionPane.showMessageDialog(null, e1.getMessage());
										}
										}
								});
								
								
								mntmFavoritar.addActionListener(new ActionListener(){
									public void actionPerformed(ActionEvent ae) {
										MetodosUsuarioController cmd = new MetodosUsuarioController();
										try {
											cmd.FavoritarMusica(ms.getId(), TimeLifeApp._usuario.getFavorito().getId());
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								});
								MenuMusica.show(btnMusica, me.getX(), me.getY());
							}else 
							{
								ms.setArquivoMP3(getMP3.getMP3DoServidor(ms.getCodigoMp3Servidor()));
								TimeLifeApp._playercontrol.addMusica(ms);
								TimeLifeApp._playercontrol.play();	
							}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					}
				});
				this.MenuMusicas.add(btnMusica);
			}
		}catch(Exception e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
