package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Passageiro;
import model.Voo;

public class ConsultarLugares extends JInternalFrame {
	
	private static List<Voo> listVoo;
	private JTable table;
	public JComboBox comboBoxVoo;
	private JPanel painelFundo = null;
	private JScrollPane barraRolagem;
	private DefaultTableModel modelo = new DefaultTableModel();
	private boolean criouPanel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarLugares frame = new ConsultarLugares(listVoo);
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
	public ConsultarLugares(List<Voo> listaVoo) {
		listVoo = listaVoo;
		
		setTitle("Consultar Lugares");
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 880, 620);
		getContentPane().setLayout(null);
		
		JLabel lblVo = new JLabel("Vôo");
		lblVo.setBounds(15, 30, 69, 20);
		getContentPane().add(lblVo);
		
		comboBoxVoo = new JComboBox();
		comboBoxVoo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Voo vooSelecionado = (Voo)comboBoxVoo.getSelectedItem();
				JOptionPane.showMessageDialog(null, "Existem " + exibeQuantidadeLugaresLivres(vooSelecionado) + " lugares vagos neste Vôo.");
			}
		});
		comboBoxVoo.setBounds(61, 27, 570, 26);
		getContentPane().add(comboBoxVoo);
		
		for (Voo voo : listVoo) {
			comboBoxVoo.addItem(voo);
		}
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Voo vooSelecionado = (Voo)comboBoxVoo.getSelectedItem();
				
				criaJTable(vooSelecionado.getAeronave().getLugares());
				criaJanela();
				criouPanel = true;
			}
		});
		btnPesquisar.setBounds(673, 26, 115, 29);
		getContentPane().add(btnPesquisar);

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
	
	public void criaJanela() {
		//quando atualiza o panel e nao exibia a tabela correta para o outro registro, entao esta sendo removido o panel que nao é mais utilizado
		if (painelFundo != null) {
			getContentPane().remove(painelFundo);
		}
		barraRolagem = new JScrollPane(table);
		painelFundo = new JPanel();
		painelFundo.setBounds(15, 97, 773, 250);
		painelFundo.setLayout(new BorderLayout());
		painelFundo.add(BorderLayout.CENTER, barraRolagem);

		getContentPane().add(painelFundo);
		setSize(855, 425);
		setVisible(true);
	}

	private void criaJTable(Passageiro[][] lugares) {
		modelo = new DefaultTableModel();
		table = new JTable(modelo);
		
		int linha = lugares.length;
		int coluna = lugares[0].length;
		
		modelo.setNumRows(0);
		for (int i = 0; i < coluna; i++) {
			modelo.addColumn("Assento " + (i+1));
		}
		
		for (int i = 0; i < linha; i++) {
			String[] valoresLinha = new String[coluna];
			for (int j = 0; j < coluna; j++) {
				valoresLinha[j] = lugares[i][j] == null ? "Vazio" : "Ocupado";
			}
			modelo.addRow(valoresLinha);
		}

	}
	
}
