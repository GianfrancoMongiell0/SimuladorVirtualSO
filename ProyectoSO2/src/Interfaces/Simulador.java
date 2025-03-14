/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import Clases.Archivo;
import Clases.Directorio;
import Clases.SistemaArchivo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
/**
 *
 * @author LENOVO
 */
public class Simulador extends javax.swing.JFrame {
    SistemaArchivo sistema= new SistemaArchivo();
    /**
     * Creates new form SistemaArchivo
     */
    public Simulador() {
        initComponents();
        setTitle("Simulador Sistema de Archivos");
         // Configurar el JPanel donde se mostrarán los bloques
        
        // Inicializar el modelo de árbol para el JTree
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Raíz");
        DefaultTreeModel treeModel = new DefaultTreeModel(raiz);
        jTree1.setModel(treeModel);  // Asignamos el modelo al JTree
        
         
        configurarPanelSD();
        btnCrear.setVisible(false);
        btnEliminar.setVisible(false);
        btnCrear1.setVisible(false);
        btnModificar1.setVisible(false);
        btnEliminar1.setVisible(false);
    }
    
    private void configurarPanelSD() {
        // Establecer el Layout en el JPanel (suponiendo que se llame panelSD en el GUI)
        panelsd.setLayout(new GridLayout(10, 10)); // 10 filas x 10 columnas
        int totalBloques = 100; // Número de bloques en el sistema (10x10 en este caso)
        for (int i = 0; i < totalBloques; i++) {
            JPanel bloque = new JPanel();
            bloque.setPreferredSize(new Dimension(50, 50));
            bloque.setBackground(Color.GREEN); // Bloque libre por defecto

            // Agregar interacción (clic para marcar como ocupado/libre)
            bloque.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (bloque.getBackground() == Color.GREEN) {
                        bloque.setBackground(Color.RED); // Marcar como ocupado
                    } else {
                        bloque.setBackground(Color.GREEN); // Liberar bloque
                    }
                }
            });

            panelsd.add(bloque); // Agregar cada bloque al panel
        }

        // Refrescar la interfaz
        panelsd.revalidate();
        panelsd.repaint();
    }
    
    public void actualizarJTree() {
        DefaultMutableTreeNode raizNodo = new DefaultMutableTreeNode(sistema.getRaiz().getNombre());
        construirArbol(sistema.getRaiz(), raizNodo);

        DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();
        model.setRoot(raizNodo);
        model.reload();
    }

    // Método recursivo para construir el árbol
    private void construirArbol(Directorio dir, DefaultMutableTreeNode nodoPadre) {
        for (int i = 0; i < dir.getSubdirectorios().getLength(); i++) {
            Directorio subdir = dir.getSubdirectorios().get(i);
            DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(subdir); // Guardar el objeto Directorio
            nodoPadre.add(nodoHijo);
            construirArbol(subdir, nodoHijo);
        }

        for (int i = 0; i < dir.getArchivos().getLength(); i++) {
            Archivo archivo = dir.getArchivos().get(i);
            DefaultMutableTreeNode nodoArchivo = new DefaultMutableTreeNode(archivo); // Guardar el objeto Archivo
            nodoPadre.add(nodoArchivo);
        }
    }

   private void modificarNombreArchivo(Archivo archivo, DefaultMutableTreeNode nodo) {
        String nuevoNombre = JOptionPane.showInputDialog(this, "Ingrese el nuevo nombre para el archivo:", archivo.getNombre());

        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            archivo.setNombre(nuevoNombre);
            ((DefaultTreeModel) jTree1.getModel()).nodeChanged(nodo);
            JOptionPane.showMessageDialog(this, "Nombre del archivo modificado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarNombreDirectorio(Directorio directorio, DefaultMutableTreeNode nodo) {
        String nuevoNombre = JOptionPane.showInputDialog(this, "Ingrese el nuevo nombre para el directorio:", directorio.getNombre());

        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            directorio.setNombre(nuevoNombre);
            ((DefaultTreeModel) jTree1.getModel()).nodeChanged(nodo);
            JOptionPane.showMessageDialog(this, "Nombre del directorio modificado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        Modo = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        modoAdmi = new javax.swing.JRadioButton();
        modoUsuario = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panelsd = new javax.swing.JPanel();
        btnCrear = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCrear1 = new javax.swing.JButton();
        btnModificar1 = new javax.swing.JButton();
        btnEliminar1 = new javax.swing.JButton();
        btnCrearAleatorio = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setForeground(new java.awt.Color(153, 255, 102));

        Modo.add(modoAdmi);
        modoAdmi.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        modoAdmi.setText("Modo Administrador");
        modoAdmi.setNextFocusableComponent(modoAdmi);
        modoAdmi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modoAdmiActionPerformed(evt);
            }
        });

        Modo.add(modoUsuario);
        modoUsuario.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        modoUsuario.setText("Modo Usuario");
        modoUsuario.setNextFocusableComponent(modoAdmi);
        modoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modoUsuarioActionPerformed(evt);
            }
        });

        jTree1.setBackground(new java.awt.Color(208, 223, 239));
        jTree1.setBorder(null);
        jTree1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(jTree1);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel1.setText("Sistema de Archivos");

        jTable1.setBackground(new java.awt.Color(208, 223, 239));
        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre del fichero", "Bloque Inicial", "Longitud", "Color de Fichero"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel2.setText("SD");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel3.setText("Tabla de Asignaciones");

        panelsd.setBackground(new java.awt.Color(208, 223, 239));

        javax.swing.GroupLayout panelsdLayout = new javax.swing.GroupLayout(panelsd);
        panelsd.setLayout(panelsdLayout);
        panelsdLayout.setHorizontalGroup(
            panelsdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        panelsdLayout.setVerticalGroup(
            panelsdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 403, Short.MAX_VALUE)
        );

        btnCrear.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnCrear.setText("Crear Archivo");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnEliminar.setText("Eliminar Archivo");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnCrear1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnCrear1.setText("Crear Directorio");
        btnCrear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrear1ActionPerformed(evt);
            }
        });

        btnModificar1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnModificar1.setText("Modificar ");
        btnModificar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar1ActionPerformed(evt);
            }
        });

        btnEliminar1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnEliminar1.setText("Eliminar Directorio");
        btnEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar1ActionPerformed(evt);
            }
        });

        btnCrearAleatorio.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnCrearAleatorio.setText("Crear Directorios/Archivos Aleatorios");
        btnCrearAleatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearAleatorioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelsd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(297, 297, 297)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(modoAdmi)
                                    .addComponent(modoUsuario)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnCrear1)
                                            .addComponent(btnCrear))
                                        .addGap(17, 17, 17)
                                        .addComponent(btnModificar1)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnEliminar)
                                            .addComponent(btnEliminar1)))
                                    .addComponent(btnCrearAleatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modoAdmi)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(modoUsuario)
                        .addGap(8, 8, 8)
                        .addComponent(btnCrearAleatorio)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnCrear)
                                    .addComponent(btnEliminar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnCrear1)
                                    .addComponent(btnEliminar1)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(btnModificar1))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(panelsd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        modoAdmi.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void modoAdmiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modoAdmiActionPerformed
        btnCrear.setVisible(true);
        btnEliminar.setVisible(true);
        btnCrear1.setVisible(true);
        btnModificar1.setVisible(true);
        btnEliminar1.setVisible(true);
    }//GEN-LAST:event_modoAdmiActionPerformed
        
    private void modoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modoUsuarioActionPerformed
        btnCrear.setVisible(false);
        btnEliminar.setVisible(false);
        btnCrear1.setVisible(false);
        btnModificar1.setVisible(false);
        btnEliminar1.setVisible(false);
    }//GEN-LAST:event_modoUsuarioActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        CrearArchivo creararchivo = new CrearArchivo(this, sistema);
        creararchivo.setVisible(true);
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnCrear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrear1ActionPerformed
        CrearDirectorio creardirec = new CrearDirectorio(this, sistema);
        creardirec.setVisible(true);
    }//GEN-LAST:event_btnCrear1ActionPerformed

    private void btnModificar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar1ActionPerformed
        // Obtener el nodo seleccionado en el JTree
        DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();

        if (nodoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un archivo o directorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener el objeto asociado al nodo seleccionado
        Object objeto = nodoSeleccionado.getUserObject();

        if (objeto instanceof Archivo) {
            Archivo archivo = (Archivo) objeto;
            modificarNombreArchivo(archivo, nodoSeleccionado);
        } else if (objeto instanceof Directorio) {
            Directorio directorio = (Directorio) objeto;
            modificarNombreDirectorio(directorio, nodoSeleccionado);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un archivo o directorio válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificar1ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();

        if (nodoSeleccionado == null) {
            System.out.println("No se ha seleccionado ningún nodo.");
            return;
        }

        Object objeto = nodoSeleccionado.getUserObject();
        System.out.println("Nodo seleccionado: " + objeto); // Verifica si realmente se está seleccionando algo

        if (objeto instanceof Archivo) {
            Archivo archivo = (Archivo) objeto;
            System.out.println("Archivo a eliminar: " + archivo.getNombre());

            DefaultMutableTreeNode nodoPadre = (DefaultMutableTreeNode) nodoSeleccionado.getParent();
            if (nodoPadre != null) {
                Object objetoPadre = nodoPadre.getUserObject();
                if (objetoPadre instanceof Directorio) {
                    Directorio padre = (Directorio) objetoPadre;
                    System.out.println("Eliminando archivo " + archivo.getNombre() + " del directorio " + padre.getNombre());

                    sistema.eliminarArchivo(padre.getNombre(), archivo.getNombre());

                    // Eliminar nodo del árbol
                    DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();
                    model.removeNodeFromParent(nodoSeleccionado);
                    model.reload();

                    System.out.println("Archivo eliminado exitosamente.");
                }
            }
        } else {
            System.out.println("Seleccione un archivo válido.");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar1ActionPerformed
        DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();

    if (nodoSeleccionado != null) {
        Object objeto = nodoSeleccionado.getUserObject();

        if (objeto instanceof Directorio) {
            Directorio dir = (Directorio) objeto;
            DefaultMutableTreeNode nodoPadre = (DefaultMutableTreeNode) nodoSeleccionado.getParent();

            if (nodoPadre != null) {
                Object objetoPadre = nodoPadre.getUserObject();
                if (objetoPadre instanceof Directorio) {
                    Directorio padre = (Directorio) objetoPadre;

                    // Verificar si el directorio se encuentra en el sistema
                    Directorio directorioPadre = sistema.buscarDirectorio(padre, padre.getNombre());

                    if (directorioPadre != null) {
                        // Eliminar el directorio
                        sistema.eliminarDirectorio(padre.getNombre(), dir.getNombre());

                        // Eliminar el nodo en el JTree
                        DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();
                        model.removeNodeFromParent(nodoSeleccionado);
                        model.reload();

                        // Actualizar el JTree
                        actualizarJTree();
                        System.out.println("Directorio '" + dir.getNombre() + "' eliminado correctamente.");
                    }
                }
            } else {
                // Intentando eliminar la raíz
                System.out.println("No se puede eliminar la raíz del sistema.");
            }
        } else {
            System.out.println("Seleccione un directorio válido.");
        }
    } else {
        System.out.println("No se ha seleccionado ningún directorio.");
    }
    }//GEN-LAST:event_btnEliminar1ActionPerformed

    private void btnCrearAleatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearAleatorioActionPerformed
        // Crear 5 directorios y 10 archivos aleatorios
        sistema.crearDirectoriosYArchivosAleatorios();

        // Actualizar el JTree para reflejar los cambios
        actualizarJTree();
    }//GEN-LAST:event_btnCrearAleatorioActionPerformed

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
            java.util.logging.Logger.getLogger(Simulador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Simulador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Simulador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Simulador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Simulador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Modo;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnCrear1;
    private javax.swing.JButton btnCrearAleatorio;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminar1;
    private javax.swing.JButton btnModificar1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    public javax.swing.JTree jTree1;
    private javax.swing.JRadioButton modoAdmi;
    private javax.swing.JRadioButton modoUsuario;
    private javax.swing.JPanel panelsd;
    // End of variables declaration//GEN-END:variables
}
