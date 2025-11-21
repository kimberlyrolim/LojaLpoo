package view;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Produto;
import model.dao.ProdutoDAO;

public class ListaProdutoJF extends javax.swing.JFrame {

    ProdutoDAO dao;
    
    public ListaProdutoJF() {
        initComponents();
    
        dao = new ProdutoDAO();
        loadTabelaProduto();
        
        verificarDispinibilidade();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduto = new javax.swing.JTable();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnInfo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Produto", "Tipo", "Valor", "Disponível"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProduto.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tblProduto);
        tblProduto.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tblProduto.getColumnModel().getColumnCount() > 0) {
            tblProduto.getColumnModel().getColumn(2).setResizable(false);
        }

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnRemover.setText("Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnInfo.setText("Mais Informações");
        btnInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNovo)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar)
                        .addGap(18, 18, 18)
                        .addComponent(btnRemover)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnInfo)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnEditar)
                    .addComponent(btnRemover)
                    .addComponent(btnInfo))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        CadastroProdutoJD telaCadastro = new CadastroProdutoJD(this, rootPaneCheckingEnabled);
        telaCadastro.setVisible(true);
        
        Produto novoProduto = telaCadastro.getProduto();
        try {
            novoProduto.setDisponivel(true);
            
            dao.persist(novoProduto);
        } catch (Exception ex) {
            System.out.println("Erro ao cadastrar o produto "+novoProduto.toString()+" \n Erro: "+ex);
        }
        loadTabelaProduto();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoActionPerformed
        if(tblProduto.getSelectedRow() != -1){
            Produto obj_vendedor = (Produto) dao.buscarPorNome((String)tblProduto.getModel().getValueAt(tblProduto.getSelectedRow(), 0)).get();
            JOptionPane.showMessageDialog(rootPane, obj_vendedor.exibirDados());
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione um Produto!");
        }
    }//GEN-LAST:event_btnInfoActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        if (tblProduto.getSelectedRow() != -1) {
            Produto obj_produto = (Produto) dao.buscarPorNome((String) tblProduto.getModel().getValueAt(tblProduto.getSelectedRow(), 0)).get();
            int op_remover = JOptionPane.showConfirmDialog(rootPane, "Tem certeza que deseja remover " +obj_produto + "?");
            if (op_remover == JOptionPane.YES_OPTION) {
                try {
                    dao.remover(obj_produto);
                } catch (Exception ex) {
                    System.out.println("Erro ao remover produto " + obj_produto + "\n Erro: " + ex);
                }
                JOptionPane.showMessageDialog(rootPane, "Produto removido com sucesso... ");
                loadTabelaProduto();
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione um produto");
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (tblProduto.getSelectedRow() != -1) {
            Produto obj_produto = (Produto) dao.buscarPorNome((String)tblProduto.getModel().getValueAt(tblProduto.getSelectedRow(), 0)).get();            
            CadastroProdutoJD telaEdicao = new CadastroProdutoJD(this, rootPaneCheckingEnabled);
            telaEdicao.setProduto(obj_produto);

            telaEdicao.setVisible(true);

            try {
                dao.persist(telaEdicao.getProduto());
            } catch (Exception ex) {
                System.err.println("Erro ao editar produto\n Erro: " + ex);
            }

            loadTabelaProduto();

        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione um produto");
        }
        
    }//GEN-LAST:event_btnEditarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListaProdutoJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaProdutoJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaProdutoJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaProdutoJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaProdutoJF().setVisible(true);
            }
        });
    }
    
    public void loadTabelaProduto() {

        DefaultTableModel modelo = (DefaultTableModel) tblProduto.getModel();
        modelo.setNumRows(0);

        for (Produto obj : dao.listaProduto()) {
            Object[] linha = {
                obj.getProdNome(),
                obj.getProdTipo(),
                obj.getProdValor(),
                obj.getDisponivel().booleanValue()
            };
            modelo.addRow(linha);
        }

    }
    
    public void verificarDispinibilidade(){
        tblProduto.getModel().addTableModelListener(e -> {
        int row = e.getFirstRow();
        int col = e.getColumn();

        // COLUNA 3 é a coluna "Disponível"
        if (col == 3) {
            
            // 1. Captura o estado atual da UI e o nome do produto
            String nome = (String) tblProduto.getValueAt(row, 0);
            Boolean novoDisponivel = (Boolean) tblProduto.getValueAt(row, 3);

            // Tenta buscar o produto pelo nome
            dao.buscarPorNome(nome).ifPresent(p -> {
                int op_edt = JOptionPane.showConfirmDialog(rootPane, 
                        "Tem certeza que deseja alterar a disponibilidade do produto: " + nome + " para " + (novoDisponivel ? "DISPONÍVEL" : "INDISPONÍVEL") + "? ",
                        "Confirmação de Disponibilidade", JOptionPane.YES_NO_OPTION);
                
                if (op_edt == JOptionPane.YES_OPTION) {
                    // 2. Se o usuário confirmar, atualiza o OBJETO e tenta persistir
                    p.setDisponivel(novoDisponivel);
                    
                    try {
                        // TENTA SALVAR NO BANCO
                        dao.persist(p);
                        JOptionPane.showMessageDialog(rootPane, "Disponibilidade de '"+nome+"' alterada e salva com sucesso!");
                        
                    } catch (Exception ex) {
                        // SE FALHAR NO BANCO
                        System.err.println("🚨 ERRO AO PERSISTIR DISPONIBILIDADE 🚨\nProduto: " + p + "\nErro: " + ex);
                        JOptionPane.showMessageDialog(rootPane, "Erro ao salvar a disponibilidade no banco de dados! Consulte o console para mais detalhes.", "Erro de Persistência", JOptionPane.ERROR_MESSAGE);
                        
                        // Recarrega a tabela para reverter a alteração visual para o estado anterior (correto)
                        loadTabelaProduto(); 
                    }
                } else {
                    // 3. Se o usuário clicar em 'Não', REVERTE a alteração visual
                    // Recarrega a tabela para que o valor exibido volte a ser o valor real do DB
                    loadTabelaProduto();
                    JOptionPane.showMessageDialog(rootPane, "Alteração de disponibilidade cancelada.");
                }
            });
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnInfo;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnRemover;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProduto;
    // End of variables declaration//GEN-END:variables
}
