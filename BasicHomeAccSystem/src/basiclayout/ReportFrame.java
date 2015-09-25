/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basiclayout;

import static basiclayout.SQLHandler.*;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;

/**
 *
 * @author jaina_000
 */


public class ReportFrame extends javax.swing.JFrame {

    String tableC = "categorymaster", tableE = "expenseincomemaster";

    public ReportFrame() {
        
       initComponents();
       setVisible(true);
       setTitle("Report Frame");
       setResizable(false);
    }

    int final_total, sub_amt;
    DefaultTableModel report_model;
    Object [] rep_row = new Object[5];
    Document document;
    PdfPTable table;
    
    public ReportFrame(DefaultTableModel subtotal_model, String st1_date, String e1_date) throws DocumentException, IOException {
        
        this();
        report_model =  (DefaultTableModel) reportTable.getModel();
        reportTable.setRowSelectionAllowed(true);                
        reportTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        document = new Document() ;
        
        
        try {

            
                initialize_connection();

                PdfWriter.getInstance((com.itextpdf.text.Document) document, new FileOutputStream("C:\\Users\\jaina_000\\Desktop\\test.pdf"));
                document.open();
                
                table = new PdfPTable(5);
                table.setWidthPercentage(300/3f);
                table.setWidths(new int[]{3, 3, 3, 3, 3});
                
                PdfPCell cell;
        cell = new PdfPCell(new Phrase("Category"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Title"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Date"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Amount"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Details"));
        table.addCell(cell);

        cell.setRowspan(5);
                
                final_total = 0;

                for( int i=0; i < subtotal_model.getRowCount(); i++){

                    String subt_cat = subtotal_model.getValueAt(i, 0).toString();

                    resultset1 = statement1.executeQuery("Select * from "+tableC+" WHERE CategoryName='"+subt_cat+"'");
                    resultset1.next();

                    resultset2 = statement2.executeQuery("Select * from "+tableE+" WHERE CategoryId= '"+resultset1.getString(1)+"' "
                        + "AND (Date BETWEEN '"+st1_date+"' AND '"+e1_date+"')");

                    sub_amt = 0;

                    System.out.print("\n\nCategory: " +resultset1.getString(2));
                    rep_row[0] = resultset1.getString(2);
                    
                    for(int z=1; z<5; z++)
                    {
                        rep_row[z] = null;
                    }
                    
                    report_model.addRow(rep_row);

                    table.addCell(new PdfPCell(new Phrase((String) rep_row[0])));
                    table.addCell(new PdfPCell(new Phrase("")));
                    table.addCell(new PdfPCell(new Phrase("")));
                    table.addCell(new PdfPCell(new Phrase("")));
                    table.addCell(new PdfPCell(new Phrase("")));
                                                            
                    cell.setRowspan(5);
                    
                    while(resultset2.next()){             

                            rep_row[0] = null;
                            System.out.print("\nTitle : "+resultset2.getString(5));          // Title
                                rep_row[1] = resultset2.getString(5);

                            System.out.print("\tDate : "+resultset2.getString(2));           // Date
                                rep_row[2] = resultset2.getString(2);

                            System.out.print("\tAmount: "+Math.abs(Integer.parseInt(resultset2.getString(4))));
                                rep_row[3] = Math.abs(Integer.parseInt(resultset2.getString(4)));

                            System.out.print("\tDescription : "+resultset2.getString(6));    // Description
                                rep_row[4] = resultset2.getString(6);

                            table.addCell(new PdfPCell(new Phrase((String) rep_row[0])));
                            table.addCell(new PdfPCell(new Phrase((String) rep_row[1])));
                            table.addCell(new PdfPCell(new Phrase((String) rep_row[2])));
                            table.addCell(new PdfPCell(new Phrase( String.valueOf(rep_row[3]) )));
                            table.addCell(new PdfPCell(new Phrase((String) rep_row[4])));    
                                

                            sub_amt += Integer.parseInt(resultset2.getString(4));

                            report_model.addRow(rep_row);
                            
                            cell.setRowspan(5);
                    }

                    final_total += sub_amt;
                    for(int z=0; z<5; z++)
                    {
                        rep_row[z] = null;
                    }
                    
                    rep_row[2] = "Subtotal" ;
                    rep_row[3] = Math.abs(sub_amt);
                    System.out.println("\nCategory Subtotal : "+Math.abs(sub_amt));

                    table.addCell(new PdfPCell(new Phrase((String) rep_row[0])));
                    table.addCell(new PdfPCell(new Phrase((String) rep_row[1])));
                    table.addCell(new PdfPCell(new Phrase((String) rep_row[2])));
                    table.addCell(new PdfPCell(new Phrase( String.valueOf(rep_row[3]) )));
                    table.addCell(new PdfPCell(new Phrase((String) rep_row[4])));    
                    
                    report_model.addRow(rep_row);
                    
                    cell.setRowspan(5);
                }                
                    
                for(int z=0; z<5; z++)
                    {
                        rep_row[z] = null;
                    }
                report_model.addRow(rep_row);
                report_model.addRow(rep_row);
                
                System.out.print("\n\nFinal total: "+Math.abs(final_total));
                rep_row[2] = "Final Total";
                rep_row[3] =  Math.abs(final_total) ;

                
                if( final_total < 0){
                    System.out.println("  (Expense)");
                    rep_row[4] = "(Expense)";
                }
                    
                else{
                    System.out.println("  (Income)");
                    rep_row[4] = "(Income)";
                }
                    
                
                table.addCell(new PdfPCell(new Phrase((String) rep_row[0])));
                table.addCell(new PdfPCell(new Phrase((String) rep_row[1])));
                table.addCell(new PdfPCell(new Phrase((String) rep_row[2])));
                table.addCell(new PdfPCell(new Phrase( String.valueOf(rep_row[3]) )));
                table.addCell(new PdfPCell(new Phrase((String) rep_row[4]))); 
                
                
                report_model.addRow(rep_row);
                
                cell.setRowspan(5);
                
                
                
            }catch( ClassNotFoundException | SQLException e){

            } catch (FileNotFoundException ex) {
            Logger.getLogger(ReportFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

            finally {

                close_connection();

            }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ReportPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        reportTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        moveBack = new javax.swing.JButton();
        generatepdf = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        reportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Category", "Title", "Date", "Amount", "Details"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(reportTable);

        jLabel1.setText("Report Generation Table");

        moveBack.setText("Move Back");
        moveBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveBackActionPerformed(evt);
            }
        });

        generatepdf.setText("Generate PDF document");
        generatepdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generatepdfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ReportPanelLayout = new javax.swing.GroupLayout(ReportPanel);
        ReportPanel.setLayout(ReportPanelLayout);
        ReportPanelLayout.setHorizontalGroup(
            ReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReportPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(334, 334, 334))
            .addGroup(ReportPanelLayout.createSequentialGroup()
                .addGroup(ReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ReportPanelLayout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addComponent(moveBack)
                        .addGap(61, 61, 61)
                        .addComponent(generatepdf))
                    .addGroup(ReportPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ReportPanelLayout.setVerticalGroup(
            ReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReportPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moveBack)
                    .addComponent(generatepdf))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ReportPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ReportPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void moveBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveBackActionPerformed

        NewJFrame.n.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_moveBackActionPerformed

    private void generatepdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generatepdfActionPerformed

        try{
                document.add(table);
                document.close();

                File fo = new File("C:\\Users\\jaina_000\\Desktop\\test.pdf");
                Desktop.getDesktop().open(fo);
        }catch(DocumentException | IOException e){
            
        }
    }//GEN-LAST:event_generatepdfActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ReportPanel;
    private javax.swing.JButton generatepdf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton moveBack;
    private javax.swing.JTable reportTable;
    // End of variables declaration//GEN-END:variables

    

    
}
