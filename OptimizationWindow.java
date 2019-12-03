/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group.financialcomputing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yongzhaohuang
 */
public class OptimizationWindow extends javax.swing.JFrame implements ActionListener {
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton3;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField5;
	private java.awt.ScrollPane scrollPane1;
	private DefaultListModel portfolioMode = new DefaultListModel();
	private JList portfolioJlist = new JList();
	private List<String> showPortfolio = new ArrayList<>();
	private int numberOfStock;
	private String pyPath;
	// End of variables declaration
	/**
	 *
	 * Creates new form OptimazationFrame
	 */
	public OptimizationWindow() {
		initComponents();
	}

	public OptimizationWindow(MainWindow mainWindow) {
		this.pyPath =mainWindow.getPyPath();
		this.numberOfStock = mainWindow.getAllStock().size();
		initComponents();
	}

	public OptimizationWindow(StockManagementWindow stockManagementWindow) {
		this.pyPath = stockManagementWindow.getPyPath();
		this.numberOfStock = stockManagementWindow.getPickedStocks().size();
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField();
		jTextField3 = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jLabel7 = new javax.swing.JLabel();
		jTextField5 = new javax.swing.JTextField();
		scrollPane1 = new java.awt.ScrollPane();
		portfolioMode.addAll(showPortfolio);
		portfolioJlist.setModel(portfolioMode);
		scrollPane1.add(portfolioJlist);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setText("Risk Free Interest Rate:");

		jTextField1.addActionListener(this);

		jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
		jLabel2.setText("Optimization Results");

		jLabel3.setText("Expected Return:");

		jLabel4.setText("Volatility:");

		jLabel6.setText("Stock Portfolio:");

		jTextField2.addActionListener(this);

		jButton1.setText("Optimize Portfolio");
		jButton1.addActionListener(this);

		jButton3.setText("Cancel");
		jButton3.addActionListener(this);

		jLabel7.setText("Fix Return:");

		jTextField5.addActionListener(this);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(50, 50, 50)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addComponent(jLabel3)
												.addGap(18, 18, 18)
												.addComponent(jTextField2)
												.addGap(180, 180, 180))
										.addGroup(layout.createSequentialGroup()
												.addComponent(jLabel4)
												.addGap(18, 18, 18)
												.addComponent(jTextField3)
												.addGap(180, 180, 180))
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(layout.createSequentialGroup()
																.addComponent(jLabel6)
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
														.addGroup(layout.createSequentialGroup()
																.addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addGap(15, 15, 15)))
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
														.addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addContainerGap())
										.addGroup(layout.createSequentialGroup()
												.addGap(0, 0, Short.MAX_VALUE)
												.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(180, 180, 180))))
						.addGroup(layout.createSequentialGroup()
								.addGap(41, 41, 41)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addComponent(jLabel7)
												.addGap(18, 18, 18)
												.addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
										.addComponent(jLabel2)
										.addComponent(jLabel1))
								.addGap(180, 180, 180))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(40, 40, 40)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel1)
										.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel7)
										.addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(55, 55, 55)
								.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel3)
										.addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(11, 11, 11)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel4)
										.addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addGroup(layout.createSequentialGroup()
												.addGap(59, 59, 59)
												.addComponent(jButton1)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButton3))
										.addGroup(layout.createSequentialGroup()
												.addComponent(jLabel6)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		pack();
	}// </editor-fold>

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getActionCommand().equals(jButton1.getText())){
			//double riskFree = Double.parseDouble(jTextField1.getText());
			portfolioMode.clear();
			try {
				String[] command = new String[]{"python3", this.pyPath + "/portfolio.py", jTextField1.getText()};
				Process proc;
				proc = Runtime.getRuntime().exec(command);

				BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				String line = null;
				String[] token = null;
				String[][] portfolioOfStock = new String[(numberOfStock + 4)][2];
				int count = 0;

				while ((line = in.readLine()) != null) {
					token = line.split(" ");
					portfolioOfStock[count][0] = token[0];
					for(int i = 1; i < token.length; i++) {
						if (!token[i].equals("")) {
							portfolioOfStock[count][1] = token[i];
							break;
						}
					}
					count++;
				}
				in.close();
				proc.waitFor();
				jTextField2.setText(portfolioOfStock[numberOfStock][1]);   //expected return
				jTextField3.setText(portfolioOfStock[numberOfStock + 1][1]);   //volatility
				for(int i = 0 ; i < portfolioOfStock.length - 4 ; i++) {
					portfolioMode.addElement(portfolioOfStock[i][0] + "\t" + " " + portfolioOfStock[i][1]);
				}
			}catch (IOException e){
				e.printStackTrace();
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		else if (actionEvent.getActionCommand().equals(jButton3.getText())) {
			this.dispose();
		}
	}
}
