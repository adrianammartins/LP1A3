package view;

import java.awt.EventQueue;
import java.text.ParseException;
import java.util.List;

import javax.swing.JInternalFrame;

import model.Aviao;
import model.Passageiro;
import model.Voo;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.text.MaskFormatter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroReserva extends JInternalFrame {
	
	private static List<Voo> listVoo;
	private JTextField nome;
	private JTextField cpf;
	private JTextField fileira;
	private JTextField assento;
	public JComboBox comboBoxVoo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroReserva frame = new CadastroReserva(listVoo);
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
	public CadastroReserva(List<Voo> listaVoo) {
		listVoo = listaVoo;
		
		setTitle("Cadastro de Reserva");
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 621, 421);
		getContentPane().setLayout(null);
		
		JLabel labelVoo = new JLabel("Vôo:");
		labelVoo.setBounds(37, 50, 103, 20);
		getContentPane().add(labelVoo);
		
		comboBoxVoo = new JComboBox();
		comboBoxVoo.setBounds(140, 47, 425, 26);
		getContentPane().add(comboBoxVoo);
		comboBoxVoo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Voo vooSelecionado = (Voo)comboBoxVoo.getSelectedItem();
				JOptionPane.showMessageDialog(null, "Existem " + exibeQuantidadeLugaresLivres(vooSelecionado) + " lugares vagos neste Vôo.");
			}
		});
		
		for (Voo voo : listVoo) {
			comboBoxVoo.addItem(voo);
		}
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(37, 113, 69, 20);
		getContentPane().add(lblNome);
		
		nome = new JTextField();
		nome.setBounds(140, 110, 425, 26);
		getContentPane().add(nome);
		nome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(37, 160, 69, 20);
		getContentPane().add(lblCpf);
		
		try {
			cpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		} catch (ParseException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Deu erro ao colocar mascara no campo CPF");
		}
		cpf.setBounds(140, 157, 425, 26);
		getContentPane().add(cpf);
		cpf.setColumns(10);
		
		JButton cadastrar = new JButton("Cadastrar");
		cadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//validações dos campos de tela
				if (comboBoxVoo.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Vôo não preenchido!");
					return;
				}
				
				if (nome.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nome não preenchido!");
					return;
				}
				
				if (cpf.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "CPF não preenchido!");
					return;
				}
				Integer linha = null;
				if (fileira.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Fileira não preenchida!");
					return;
				}else {
					try{
						linha = Integer.valueOf(fileira.getText().trim()).intValue();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Fileira inválida!");
						e.printStackTrace();
						return;
					}
				}
				Integer coluna = null;
				if (assento.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Assento não preenchido!");
					return;
				}else {
					try{
						coluna = Integer.valueOf(assento.getText().trim()).intValue();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Assento inválido!");
						e.printStackTrace();
						return;
					}
				}
				//fim as validações
				
				// Criando o objeto passageiro
				Passageiro passageiro = new Passageiro(nome.getText().trim(), cpf.getText().trim());
				Voo vooSelecionado = (Voo)comboBoxVoo.getSelectedItem();
				
				//verificando se o lugar escolhido nao esta ocupado
				try{
					if (vooSelecionado.getAeronave().verificaLugarOcupado((linha-1), (coluna-1))) {
						JOptionPane.showMessageDialog(null, "Assento já ocupado pelo passageiro: " + vooSelecionado.getAeronave().getPassageiro(linha, coluna).getNome());
						return;
					}else{
						vooSelecionado.getAeronave().setPassageiro((linha-1), (coluna-1), passageiro);
						JOptionPane.showMessageDialog(null, "Cadastro Realizado com Sucesso!");
					}
				} catch (Exception e){
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar Passageiro ao Vôo. Fileira/Assento inválido.");
				} finally {
					nome.setText("");
					cpf.setText("");
					fileira.setText("");
					assento.setText("");
				}
				
			}
		});
		cadastrar.setBounds(445, 305, 115, 29);
		getContentPane().add(cadastrar);
		
		JLabel lblFileira = new JLabel("Fileira");
		lblFileira.setBounds(37, 220, 69, 20);
		getContentPane().add(lblFileira);
		
		fileira = new JTextField();
		fileira.setBounds(140, 217, 146, 26);
		getContentPane().add(fileira);
		fileira.setColumns(10);
		
		JLabel lblAssento = new JLabel("Assento");
		lblAssento.setBounds(313, 220, 69, 20);
		getContentPane().add(lblAssento);
		
		assento = new JTextField();
		assento.setBounds(419, 217, 146, 26);
		getContentPane().add(assento);
		assento.setColumns(10);

	}
	
	private int exibeQuantidadeLugaresLivres(Voo vooSelecionado) {
		Passageiro[][] lugares = vooSelecionado.getAeronave().getLugares();
		int cont = 0;
		for (int i = 0; i < lugares.length; i++) {
			for (int j = 0; j < lugares[0].length; j++) {
				if (lugares[i][j] == null) {
					cont++;
				}
			}
		}
		return cont;
	}
}
