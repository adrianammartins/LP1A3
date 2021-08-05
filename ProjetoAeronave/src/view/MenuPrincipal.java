package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Aviao;
import model.Voo;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = -5949155172702869161L;

	JDesktopPane desktopPane = new JDesktopPane();
	
	private List<Aviao> listAviao = new ArrayList<>();
	private List<Voo> listVoo = new ArrayList<>();
	
	CadastroVoo cadastroVoo;
	CadastroAeronave cadastroAeronave;
	CadastroReserva cadastroReserva;
	ConsultarLugares consultarLugares;
	
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
		setTitle("Trabalho de LP1A3");
		//TODO retirar o mock no final do projeto
		mockAviao();
		mockVoo();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1155, 571);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnParametrosDoSistema = new JMenu("Parametros do sistema");
		menuBar.add(mnParametrosDoSistema);
		
		JMenuItem mntmCadastrarAeronave = new JMenuItem("Cadastrar Aeronave");
		mntmCadastrarAeronave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastroAeronave = new CadastroAeronave(listAviao);
				desktopPane.add(cadastroAeronave);
				cadastroAeronave.setVisible(true);
			}
		});
		mnParametrosDoSistema.add(mntmCadastrarAeronave);
		
		JMenuItem mntmCadastrarVoo = new JMenuItem("Cadastrar Voo");
		mntmCadastrarVoo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastroVoo = new CadastroVoo(listVoo, listAviao);
				desktopPane.add(cadastroVoo);
				cadastroVoo.setVisible(true);
			}
		});
		mnParametrosDoSistema.add(mntmCadastrarVoo);
		
		JMenuItem mntmVoltar = new JMenuItem("Voltar");
		mntmVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastroAeronave.setVisible(false);
				cadastroVoo.setVisible(false);
			}
		});
		mnParametrosDoSistema.add(mntmVoltar);
		
		JMenu mnReservaDePassagens = new JMenu("Reserva de Passagens");
		menuBar.add(mnReservaDePassagens);
		
		JMenuItem mntmFazerReserva = new JMenuItem("Fazer Reserva");
		mntmFazerReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastroReserva = new CadastroReserva(listVoo);
				desktopPane.add(cadastroReserva);
				cadastroReserva.setVisible(true);
				
			}
		});
		mnReservaDePassagens.add(mntmFazerReserva);
		
		JMenuItem mntmConsultarLugaresVazios = new JMenuItem("Consultar Lugares Vazios");
		mntmConsultarLugaresVazios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultarLugares = new ConsultarLugares(listVoo);
				desktopPane.add(consultarLugares);
				consultarLugares.setVisible(true);
			}
		});
		mnReservaDePassagens.add(mntmConsultarLugaresVazios);
		
		JMenuItem mntmConsultarReservasRealizadas = new JMenuItem("Consultar Reservas Realizadas");
		mntmConsultarReservasRealizadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultarLugares = new ConsultarLugares(listVoo);
				desktopPane.add(consultarLugares);
				consultarLugares.setVisible(true);
			}
		});
		mnReservaDePassagens.add(mntmConsultarReservasRealizadas);
		
		JMenuItem mntmVoltar_1 = new JMenuItem("Voltar");
		mntmVoltar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultarLugares.setVisible(false);
			}
		});
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
		
		Panel panel = new Panel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 1113, 472);
		desktopPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/aviao.jpg")));
		panel.add(lblNewLabel);
		
	}
	
	private void mockAviao() {
		for (int i = 1; i < 11; i++) {
			Aviao aviao = new Aviao("Aeronave " + i, (i*2), (i*2));
			listAviao.add(aviao);
		}
	}
	
	private void mockVoo() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
		
		for (Aviao aviao : listAviao) {
			for (int i = 0; i < 3; i++) {
				cal.add(Calendar.DAY_OF_MONTH, i);
				cal.add(Calendar.HOUR_OF_DAY, i);
				Voo voo = new Voo(aviao, i, sdf.format(cal.getTime()), sdfHora.format(cal.getTime()));
				listVoo.add(voo);
			}
		}
	}
}
