package com.group.financialcomputing;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yongzhaohuang
 */
public class StockPickWindow extends javax.swing.JFrame implements ActionListener {

	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JLabel jLabel1;
	private java.awt.ScrollPane scrollPane1;
	private Map<String, List<OneDayStock>> allStocks;
	private DefaultListModel defaultListModel = new DefaultListModel();
	private JList jList = new JList();
	private String pyPath;
	private String dataDirPath;
	private String input_type;
	// End of variables declaration

	/**
	 * Creates new form StockPick
	 */
	public StockPickWindow() {
		this.defaultListModel.addAll(allStocks.keySet());
		//this.pyPath = mainWindow.getPyPath();
		this.dataDirPath = "/Users/yongzhaohuang/Documents/python_project/teach/comp5513/assign/tdata";
		this.pyPath = "/Users/yongzhaohuang/IdeaProjects/FinancialComputingProject/out/artifacts/FinancialComputingProject_jar";
		initComponents();
	}

	public StockPickWindow(MainWindow mainWindow) {
		this.allStocks = mainWindow.getAllStock();
		this.defaultListModel.addAll(allStocks.keySet());
		this.pyPath = mainWindow.getPyPath();
		this.dataDirPath = mainWindow.getAbsPath();
		//this.pyPath = "/Users/yongzhaohuang/IdeaProjects/FinancialComputingProject/out/artifacts/FinancialComputingProject_jar";

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
		scrollPane1 = new java.awt.ScrollPane();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();


		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		jList.setModel(defaultListModel);
		scrollPane1.add(jList);

		jLabel1.setText("Ranked Stocks");

		jButton1.setText("MACD");
		jButton1.addActionListener(this);

		jButton2.setText("OK");
		jButton2.addActionListener(this);

		jButton3.setText("Cancel");
		jButton3.addActionListener(this);

		jButton4.setText("RISE");
		jButton4.addActionListener(this);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(35, 35, 35)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel1))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
												.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(jButton4)
												.addGap(17, 17, 17)))
								.addGap(16, 16, 16))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(43, 43, 43)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addComponent(jLabel1)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addContainerGap(36, Short.MAX_VALUE))
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jButton2)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(jButton3)
												.addGap(11, 11, 11))))
		);

		pack();
	}// </editor-fold>

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getActionCommand().equals(jButton1.getText())){  //MACD
			this.input_type = new String("1");

		}
		else if (actionEvent.getActionCommand().equals(jButton2.getText())) {  //OK button
			//System.out.println("hello world");
			try {
				//Based on MACD indicator, the stock is stronger as the MACD is greater.
				defaultListModel.clear();

				//System.out.println("enter in Ok button ");
				//System.out.println("enter type:" + this.input_type);
				String[] cmd = new String[]{"python3", this.pyPath +"/rank.py", input_type, this.dataDirPath};
				Process proc = Runtime.getRuntime().exec(cmd);
				BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				String line = null;

				String token = new String();
				while ((line = in.readLine()) != null) {
					token = line;
				}
				token = token.replaceAll("'|[\\[\\]]","");

//				System.out.print(token);

				String[] tokenArr = token.split(",");
				List<String> tokenList = new ArrayList<>();
				for(int i = 0; i < tokenArr.length; i++) {
					tokenList.add(tokenArr[i]);
				}


				in.close();
				proc.waitFor();

				defaultListModel.addAll(tokenList);
				scrollPane1.add(jList);

			} catch(IOException e) {
				e.printStackTrace();
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		else if (actionEvent.getActionCommand().equals(jButton3.getText())) {  //cancel button
			this.dispose();
		}
		else if (actionEvent.getActionCommand().equals(jButton4.getText())) {  //rise button
			this.input_type = new String("0");
		}
	}

}

