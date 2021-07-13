package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CadastroAeronave extends JInternalFrame {
	
	private static final long serialVersionUID = 5740693564680205033L;

	private JTextField txtModelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroAeronave frame = new CadastroAeronave();
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
	public CadastroAeronave() {
		setMaximizable(true);
		setClosable(true);
		setTitle("Cadastro de Aeronave");
		setBounds(100, 100, 541, 349);
		getContentPane().setLayout(null);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(15, 16, 57, 20);
		getContentPane().add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.setBounds(74, 13, 423, 26);
		getContentPane().add(txtModelo);
		txtModelo.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JOptionPane.showMessageDialog(null, "Cadastro Realizado com Sucesso!");
				txtModelo.setText("");
			}
		});
		
		btnCadastrar.setBounds(382, 55, 115, 29);
		getContentPane().add(btnCadastrar);

	}
}
