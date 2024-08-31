/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ñox;

import java.awt.Image;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;          // Para manejar archivos.
import java.io.FileWriter;    // Para escribir en archivos.
import java.io.IOException;   // Para manejar excepciones de entrada/salida.
import java.util.HashMap;     // Para usar HashMap.
import java.util.Map;         // Para usar Map.
import javax.swing.JFileChooser; // Para el selector de archivos.
import javax.swing.JOptionPane; // Para mostrar diálogos de mensajes.
import javax.swing.filechooser.FileNameExtensionFilter; // Para filtrar tipos de archivos en el selector.
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.BoundedRangeModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;



public class diseño extends javax.swing.JFrame {
    
    private boolean contenidoGuardado = true;

    private static final Map<String, String> traducciones = new HashMap<>();

    static {
// Tipos de Datos
traducciones.put("entero", "int");
traducciones.put("largo", "long");
traducciones.put("corto", "short");
traducciones.put("caracter", "char");
traducciones.put("flotante", "float");
traducciones.put("doble", "double");
traducciones.put("boolean", "int"); 

// Condicionales
traducciones.put("si", "if");
traducciones.put("sino", "else");
traducciones.put("seleccionar", "switch");
traducciones.put("caso", "case");
traducciones.put("pordefecto", "default");

// Ciclos
traducciones.put("para", "for");
traducciones.put("mientras", "while");
traducciones.put("haz", "do");

// Control de Flujo
traducciones.put("romper", "break");
traducciones.put("continuar", "continue");
traducciones.put("retornar", "return");
traducciones.put("irsea", "goto");
traducciones.put("cadenadecaracteres", "char");
traducciones.put("tamde", "sizeof");

// Clases, Métodos y Variables
traducciones.put("vacio", "void");
traducciones.put("estatico", "static");
traducciones.put("constante", "const");

// Otros
traducciones.put("enumeracion", "enum");
traducciones.put("estructura", "struct");
traducciones.put("tipodefinido", "typedef");
traducciones.put("verdadero", "1"); 
traducciones.put("falso", "0"); 
traducciones.put("nulo", "NULL");
traducciones.put("INICIAR", "#include <stdio.h>\n#include <stdlib.h>\n#include <conio.h>\n#include <math.h>\nint main(int argc, char *argv[]) {");
traducciones.put("FINALIZAR", "return 0;}");
traducciones.put("EQUIPO", "printf(\"INTEGRANTES \\n Díaz Coronado Martin Enrique \\n García Rocha Roberto Samuel\\n Guillen Elizondo Ramón Alfonso\\n Nava Castro Diana Paola \\n Mireles Vázquez Dulce Idalia \\n Villagómez Manrique Angelica \\n Vega Natividad Rosa María \\n Villanueva Morales Oscar \")");
traducciones.put("desplegar", "printf");
traducciones.put("principal", "int main()");

    }
    
    public static String traducirCodigo(String codigoEnEspanol) {
    String[] lineas = codigoEnEspanol.split("\n");
    StringBuilder codigoEnCpp = new StringBuilder();

    for (String linea : lineas) {
        String[] palabras = linea.split(" ");
        StringBuilder lineaTraducida = new StringBuilder();

        for (String palabra : palabras) {
            lineaTraducida.append(traducciones.getOrDefault(palabra, palabra)).append(" ");
        }

        codigoEnCpp.append(lineaTraducida.toString().trim()).append("\n");
    }

    return codigoEnCpp.toString();
}
    
    private void leerArchivoYMostrarEnCampocja(File archivo) {
    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
        String linea;
        StringBuilder contenido = new StringBuilder();
        while ((linea = br.readLine()) != null) {
            contenido.append(linea).append("\n");
        }
        campocja.setText(contenido.toString());
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + e.getMessage());
    }
}
    

    
    private static final Map<String, String> erroresComunes = new HashMap<>();

static {
    erroresComunes.put("entro", "entero");
    erroresComunes.put("flotant", "flotante");
}

private void sincronizarScrollYNumeracion(JScrollPane scrollPanePrincipal, JTextArea textAreaPrincipal, JTextArea gutter) {
    textAreaPrincipal.getDocument().addDocumentListener(new DocumentListener() {
        public void insertUpdate(DocumentEvent e) {
            actualizarNumerosDeLinea();
        }
        public void removeUpdate(DocumentEvent e) {
            actualizarNumerosDeLinea();
        }
        public void changedUpdate(DocumentEvent e) {
            actualizarNumerosDeLinea();
        }

        private void actualizarNumerosDeLinea() {
            String contenidoTexto = textAreaPrincipal.getText();
            String[] lineas = contenidoTexto.split("\n", -1); // Split con límite negativo para conservar líneas vacías al final

            StringBuilder numeros = new StringBuilder();
            for (int i = 0; i < lineas.length; i++) {
                // Agregar número solo si la línea no es la última o si la última línea tiene texto
                if (i < lineas.length - 0 || (i == lineas.length - 1 && !lineas[i].isEmpty())) {
                    numeros.append(i).append("\n");
                }
            }
            gutter.setText(numeros.toString());
        }
    });

    // Sincronizar el scroll de ambos JTextArea
    AdjustmentListener adjustmentListener = new AdjustmentListener() {
        public void adjustmentValueChanged(AdjustmentEvent e) {
            JScrollBar barraGutter = scrollPanePrincipal.getVerticalScrollBar();
            JScrollBar barraPrincipal = gutter.getParent() instanceof JViewport
                                         ? ((JViewport) gutter.getParent()).getViewPosition().y != e.getValue()
                                           ? ((JScrollPane) gutter.getParent().getParent()).getVerticalScrollBar()
                                           : null
                                         : null;

            if (barraPrincipal != null && !e.getValueIsAdjusting()) {
                barraPrincipal.setValue(e.getValue());
            }

            if (barraGutter != null && !e.getValueIsAdjusting()) {
                barraGutter.setValue(e.getValue());
            }
        }
    };

    scrollPanePrincipal.getVerticalScrollBar().addAdjustmentListener(adjustmentListener);
    if (gutter.getParent() instanceof JViewport) {
        ((JScrollPane) gutter.getParent().getParent()).getVerticalScrollBar().addAdjustmentListener(adjustmentListener);
    }
}

private void sincronizarDesplazamiento(JScrollPane scrollPane1, JScrollPane scrollPane2) {
    BoundedRangeModel modelo = scrollPane1.getVerticalScrollBar().getModel();
    scrollPane2.getVerticalScrollBar().setModel(modelo);
}

private void actualizarNumerosDeLinea(JTextArea textAreaContenido, JTextArea textAreaNumeros) {
    String contenidoTexto = textAreaContenido.getText();
    String[] lineas = contenidoTexto.split("\n", -1); // Split con límite negativo para conservar líneas vacías al final

    StringBuilder numeros = new StringBuilder();
    for (int i = 0; i < lineas.length; i++) {
        numeros.append(i).append("\n");
    }
    textAreaNumeros.setText(numeros.toString());
}

    
    public diseño() {
        initComponents();
        setTitle("Compilador ÑOX");
        java.net.URL imgURL = getClass().getResource("/ñox/ñoxicon.png");
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image image = icon.getImage();
            this.setIconImage(image);
        } else {
            System.err.println("");
        }
        contenidoGuardado = false;
        compilar.setEnabled(false); // Desactiva el botón compilar al inicio
        sincronizarScrollYNumeracion(barracja, campocja, numcja);
        sincronizarScrollYNumeracion(barrac, campoc, numc);
        sincronizarDesplazamiento(barracja, jScrollPane1); // Asumiendo que jScrollPaneDeNumcja es el JScrollPane que contiene a numcja
        sincronizarDesplazamiento(barrac, jScrollPane2);

        // Para el JScrollPane que contiene numcja
        jScrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        // Para el JScrollPane que contiene numc
        jScrollPane4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        
            actualizarNumerosDeLinea(campocja, numcja);
            actualizarNumerosDeLinea(campoc, numc);
        

    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        numcja = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        numc = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        campocja = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        campoc = new javax.swing.JTextArea();
        guardarcpp = new javax.swing.JButton();
        consola = new javax.swing.JPanel();
        tconsola = new javax.swing.JLabel();
        guardarcja = new javax.swing.JButton();
        compilar = new javax.swing.JButton();
        abrir = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        barrac = new javax.swing.JScrollPane();
        barracja = new javax.swing.JScrollPane();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        palabrasr = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(253, 251, 249));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(32, 34, 41));
        jLabel1.setText("LENGUAJE DE PROGRAMACIÓN \"CJA\"");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 340, -1));

        numcja.setEditable(false);
        numcja.setBackground(new java.awt.Color(255, 255, 255));
        numcja.setColumns(20);
        numcja.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        numcja.setRows(5);
        jScrollPane3.setViewportView(numcja);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 32, 257));

        numc.setEditable(false);
        numc.setBackground(new java.awt.Color(255, 255, 255));
        numc.setColumns(20);
        numc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        numc.setRows(5);
        jScrollPane4.setViewportView(numc);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, 31, 257));

        campocja.setBackground(new java.awt.Color(242, 242, 242));
        campocja.setColumns(20);
        campocja.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campocja.setRows(5);
        jScrollPane1.setViewportView(campocja);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 870, 250));

        campoc.setEditable(false);
        campoc.setColumns(20);
        campoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoc.setRows(5);
        jScrollPane2.setViewportView(campoc);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, 870, 257));

        guardarcpp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        guardarcpp.setForeground(new java.awt.Color(32, 34, 41));
        guardarcpp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ñox/cguardar.png"))); // NOI18N
        guardarcpp.setText("Guardar C");
        guardarcpp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guardarcpp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarcppActionPerformed(evt);
            }
        });
        jPanel1.add(guardarcpp, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 50, -1, 30));

        tconsola.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        javax.swing.GroupLayout consolaLayout = new javax.swing.GroupLayout(consola);
        consola.setLayout(consolaLayout);
        consolaLayout.setHorizontalGroup(
            consolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consolaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tconsola, javax.swing.GroupLayout.PREFERRED_SIZE, 895, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        consolaLayout.setVerticalGroup(
            consolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consolaLayout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(tconsola, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(consola, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 740, 910, 90));

        guardarcja.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        guardarcja.setForeground(new java.awt.Color(32, 34, 41));
        guardarcja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ñox/cjaguardar.png"))); // NOI18N
        guardarcja.setText("Guardar CJA");
        guardarcja.setBorder(null);
        guardarcja.setBorderPainted(false);
        guardarcja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guardarcja.setDefaultCapable(false);
        guardarcja.setFocusPainted(false);
        guardarcja.setFocusable(false);
        guardarcja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarcjaActionPerformed(evt);
            }
        });
        jPanel1.add(guardarcja, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, 140, 30));

        compilar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        compilar.setForeground(new java.awt.Color(32, 34, 41));
        compilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ñox/compilar.png"))); // NOI18N
        compilar.setText("Compilar");
        compilar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        compilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compilarActionPerformed(evt);
            }
        });
        jPanel1.add(compilar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 113, 30));

        abrir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        abrir.setForeground(new java.awt.Color(32, 34, 41));
        abrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ñox/abrir.png"))); // NOI18N
        abrir.setText("Abrir");
        abrir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirActionPerformed(evt);
            }
        });
        jPanel1.add(abrir, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 89, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ñox/ñox.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 170, 70));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(32, 34, 41));
        jLabel2.setText("LENGUAJE DE PROGRAMACIÓN \"C\"");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, 310, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ñox/cja.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ñox/c.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, -1, -1));

        barrac.setBackground(new java.awt.Color(5, 67, 104));
        barrac.setForeground(new java.awt.Color(5, 67, 104));
        barrac.setAutoscrolls(true);
        jPanel1.add(barrac, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 830, 24, 21));

        barracja.setBackground(new java.awt.Color(5, 67, 104));
        barracja.setForeground(new java.awt.Color(5, 67, 104));
        barracja.setAutoscrolls(true);
        jPanel1.add(barracja, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 830, 23, 21));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText(">_ Consola de observaciones");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 720, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 850));

        jPanel2.setBackground(new java.awt.Color(5, 67, 104));
        jPanel2.setForeground(new java.awt.Color(55, 137, 211));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ÑOX - COMPILADOR PARA LENGUAJE CJA");

        palabrasr.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        palabrasr.setForeground(new java.awt.Color(255, 255, 255));
        palabrasr.setText("(?) PALABRAS RESERVADAS");
        palabrasr.setContentAreaFilled(false);
        palabrasr.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        palabrasr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                palabrasrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 502, Short.MAX_VALUE)
                .addComponent(palabrasr)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(palabrasr, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 850, 1020, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarcppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarcppActionPerformed
    try {
        String contenido = campoc.getText();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar como");
        fileChooser.setFileFilter(new FileNameExtensionFilter("C++ Source File (.c)", "c"));
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.endsWith(".c")) {
                filePath += ".c";
            }
            FileWriter fw = new FileWriter(filePath);
            fw.write(contenido);
            fw.close();
            JOptionPane.showMessageDialog(this, "Archivo guardado en: " + filePath);
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + e.getMessage());
    }
    }//GEN-LAST:event_guardarcppActionPerformed

    private void guardarcjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarcjaActionPerformed
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Guardar como");
    fileChooser.setFileFilter(new FileNameExtensionFilter("CJA File (.cja)", "cja"));
    int userSelection = fileChooser.showSaveDialog(this);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        try {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.endsWith(".cja")) {
                filePath += ".cja";
            contenidoGuardado = true; // Marcar el contenido como guardado
            compilar.setEnabled(true); // Habilitar el botón compilar
            }
            try (FileWriter fw = new FileWriter(filePath)) {
                fw.write(campocja.getText());
            }
        } catch (IOException e) {
        }
    }
    }//GEN-LAST:event_guardarcjaActionPerformed

    private void abrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirActionPerformed
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new FileNameExtensionFilter("CJA File (.cja)", "cja"));
    int resultado = fileChooser.showOpenDialog(this);
    if (resultado == JFileChooser.APPROVE_OPTION) {
        File archivoSeleccionado = fileChooser.getSelectedFile();
        leerArchivoYMostrarEnCampocja(archivoSeleccionado);

        contenidoGuardado = true; // Marcar el contenido como guardado
        compilar.setEnabled(true); // Habilitar el botón compilar
    }
    }//GEN-LAST:event_abrirActionPerformed

    private void compilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compilarActionPerformed
    if (!contenidoGuardado) {
        tconsola.setText("<html>Error: Por favor, guarde el contenido antes de compilar.</html>");
        campoc.setText(""); // Limpiar campoc debido a error
        return;
    }

    String codigoEspanol = campocja.getText().trim();
    String[] lineas = codigoEspanol.split("\n");

    if (lineas.length < 2 || !lineas[0].equals("INICIAR") || !lineas[lineas.length - 1].equals("FINALIZAR")) {
        tconsola.setText("<html>Error: El código debe comenzar con 'INICIAR' y terminar con 'FINALIZAR'</html>");
        campoc.setText(""); // Limpiar campoc debido a error
        return;
    }

    for (int i = 0; i < lineas.length; i++) {
        String linea = lineas[i].trim();

        if (!linea.isEmpty() &&
            !linea.equals("INICIAR") && 
            !linea.equals("FINALIZAR") && 
            !linea.endsWith(";") && 
            !linea.endsWith("}") && 
            !linea.endsWith("{") &&
            !linea.endsWith(",") &&                
            !linea.endsWith(":")) {
            tconsola.setText("<html>Error: Falta punto y coma al final de la línea " + i + "</html>");
            campoc.setText(""); // Limpiar campoc debido a error
            return;
        }

        if (linea.contains("desplegar")) {
            if (!linea.matches("desplegar \\(\".*\"(,\\s*([\\w\\[\\]]+))*\\).*;")) {
                tconsola.setText("<html>Error: Uso incorrecto de 'desplegar' en la línea " + i + "</html>");
                campoc.setText(""); // Limpiar campoc debido a error
                return;
            }
        }
    }

    String codigoCpp = traducirCodigo(codigoEspanol);
    campoc.setText(codigoCpp);
    tconsola.setText("<html>Se ha compilado correctamente.</html>");
    }//GEN-LAST:event_compilarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void palabrasrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_palabrasrActionPerformed
    File archivoPalabrasReservadas = new File("src\\ñox\\palabrasreservadas.txt");
    leerArchivoYMostrarEnCampocja(archivoPalabrasReservadas);
}

private void leerArchivoYMostrarEnCampocjaa(File archivo) {
    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
        StringBuilder contenido = new StringBuilder();
        String linea;
        while ((linea = br.readLine()) != null) {
            contenido.append(linea).append("\n");
        }
        campocja.setText(contenido.toString());
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + e.getMessage());
    }
    }//GEN-LAST:event_palabrasrActionPerformed

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
            java.util.logging.Logger.getLogger(diseño.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(diseño.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(diseño.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(diseño.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new diseño().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abrir;
    private javax.swing.JScrollPane barrac;
    private javax.swing.JScrollPane barracja;
    private javax.swing.JTextArea campoc;
    private javax.swing.JTextArea campocja;
    private javax.swing.JButton compilar;
    private javax.swing.JPanel consola;
    private javax.swing.JButton guardarcja;
    private javax.swing.JButton guardarcpp;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea numc;
    private javax.swing.JTextArea numcja;
    private javax.swing.JButton palabrasr;
    private javax.swing.JLabel tconsola;
    // End of variables declaration//GEN-END:variables
}
