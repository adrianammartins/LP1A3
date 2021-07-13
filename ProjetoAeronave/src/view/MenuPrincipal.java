package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.Color;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = -5949155172702869161L;

	JDesktopPane desktopPane = new JDesktopPane();
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1155, 571);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnParametrosDoSistema = new JMenu("Parametros do sistema");
		menuBar.add(mnParametrosDoSistema);
		
		JMenuItem mntmCadastrarAeronave = new JMenuItem("Cadastrar Aeronave");
		mntmCadastrarAeronave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroAeronave cadastroAeronave = new CadastroAeronave();
				desktopPane.add(cadastroAeronave);
				cadastroAeronave.setVisible(true);
			}
		});
		mnParametrosDoSistema.add(mntmCadastrarAeronave);
		
		JMenuItem mntmCadastrarVoo = new JMenuItem("Cadastrar Voo");
		mnParametrosDoSistema.add(mntmCadastrarVoo);
		
		JMenuItem mntmVoltar = new JMenuItem("Voltar");
		mnParametrosDoSistema.add(mntmVoltar);
		
		JMenu mnReservaDePassagens = new JMenu("Reserva de Passagens");
		menuBar.add(mnReservaDePassagens);
		
		JMenuItem mntmFazerReserva = new JMenuItem("Fazer Reserva");
		mnReservaDePassagens.add(mntmFazerReserva);
		
		JMenuItem mntmConsultarLugaresVazios = new JMenuItem("Consultar Lugares Vazios");
		mnReservaDePassagens.add(mntmConsultarLugaresVazios);
		
		JMenuItem mntmConsultarReservasRealizadas = new JMenuItem("Consultar Reservas Realizadas");
		mnReservaDePassagens.add(mntmConsultarReservasRealizadas);
		
		JMenuItem mntmVoltar_1 = new JMenuItem("Voltar");
		mnReservaDePassagens.add(mntmVoltar_1);
		
		JMenuItem mntmSairNovo = new JMenuItem("Sair");
		mntmSairNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		menuBar.add(mntmSairNovo);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane.setBackground(new Color(255, 255, 255));
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
	}

}
