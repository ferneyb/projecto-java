/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package panel;

import Controlador.ControlTablas;
import Controlador.Controles;
import Modelo.TextPrompt;
import Modelo.connectDB;
import java.awt.Image;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author FERNEY
 */
public class Empleado extends javax.swing.JPanel {

    /**
     * Creates new form Administrador
     */
    private connectDB conDB = new connectDB();
    private Connection con;
    private Statement statement;
    private PreparedStatement pst;
    private ResultSet rs;
    private ControlTablas controlEmpleado;
    private String gender = "";
    private Controles controles;

    public Empleado() {
        initComponents();
        this.setSize(980, 650);
        Icon ico = lb.getIcon();
        ImageIcon icon = (ImageIcon) ico;
        Image image = icon.getImage().getScaledInstance(lb.getWidth(), lb.getHeight(), Image.SCALE_SMOOTH);
        lb.setIcon(new ImageIcon(image));

        txtId.setEnabled(false);
        controles=new Controles();
        controlEmpleado = new ControlTablas();
        controlEmpleado.listEmployee(tb_empleado);

        disableAdmin(false, false, false, false, true, true);
        enableTextfield(false, false, false, false, false, false, false, true, false, false, false, false, true);

//        //placeholder
        TextPrompt txt7 = new TextPrompt("Buscar", txtBuscar);
    }

    public void disablePanelEmployee() {
        btn_eliminar.setVisible(false);

    }

    private void disableAdmin(boolean cancel, boolean delete, boolean save, boolean update, boolean nuevo, boolean search) {
        btn_cancelar.setVisible(cancel);
        btn_eliminar.setVisible(delete);
        btn_Guardar.setVisible(save);
        btn_actualizar.setVisible(update);
        btn_nuevo.setVisible(nuevo);
        btn_buscar.setVisible(search);
    }

    private void setTextField() {
        txtId.setText("");
        txtNombres.setText("");
        txtCedula.setText("");
        txtApellidos.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtBuscar.setText("");
        txtDapartamento.setText("");
        txtPais.setText("");
        txtCuidad.setText("");
        cbx_admin.setSelectedIndex(0);
    }

    private void enableTextfield(boolean id, boolean name, boolean ced, boolean ape, boolean tel, boolean email, boolean dir, boolean search, boolean dpto, boolean city, boolean pais, boolean combo, boolean combo1) {
        txtId.setEnabled(id);
        txtNombres.setEnabled(name);
        txtCedula.setEnabled(ced);
        txtApellidos.setEnabled(ape);
        txtTelefono.setEnabled(tel);
        txtCorreo.setEnabled(email);
        txtDireccion.setEnabled(dir);
        txtBuscar.setEnabled(search);
        txtCuidad.setEnabled(city);
        txtDapartamento.setEnabled(dpto);
        txtPais.setEnabled(pais);
        cbx_admin.setEnabled(combo);
        cbx_admin.setSelectedIndex(0);
        cbxBusqueda.setEnabled(combo1);
        cbxBusqueda.setSelectedIndex(0);
    }

    public void SetterAll() {
        disableAdmin(false, false, false, false, true, true);
        enableTextfield(false, false, false, false, false, false, false, true, false, false, false, false, true);
        setTextField();
    }

    private void ValidateAdmin() {
        if (txtBuscar.getText().equals("")) {
            javax.swing.JOptionPane.showMessageDialog(null, "El campo del busqueda esta vacio \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            txtBuscar.requestFocus();
        } else {
            if (cbxBusqueda.getSelectedItem().toString().equals("ID")) {
                Search("employee_id", txtBuscar);
            } else if (cbxBusqueda.getSelectedItem().toString().equals("Cedula")) {
                Search("document", txtBuscar);
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Seleccion una opción para buscar \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        }
        txtBuscar.setText("");
    }

    private void Search(String st, JTextField buscar) {
        String sql = "SELECT * FROM employees WHERE " + st + "='" + buscar.getText() + "';";

        try {
            con = conDB.getConection();
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            if (rs.next()) {
                if (buscar.getText().equals("100") || buscar.getText().equals("1")) {
                    disableAdmin(true, true, false, true, false, false);
                    enableTextfield(false, false, false, false, true, true, true, false, true, true, true, true, false);

                } else {
                    disableAdmin(true, true, false, true, false, false);
                    enableTextfield(false, true, false, true, true, true, true, false, true, true, true, true, false);
                }
                txtId.setText(rs.getString("employee_id"));
                txtCedula.setText(rs.getString("document"));
                txtNombres.setText(rs.getString("first_name"));
                txtApellidos.setText(rs.getString("last_name"));
//                cbx_admin.setSelectedItem(rs.getString("gender").toString());
                gender = rs.getString("gender").toString();

                txtTelefono.setText(rs.getString("phone_number"));
                txtCorreo.setText(rs.getString("email"));
                txtDireccion.setText(rs.getString("address"));
                txtCuidad.setText(rs.getString("city"));
                txtDapartamento.setText(rs.getString("department"));;
                txtPais.setText(rs.getString("country"));
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Registro no encontrado en la base de datos\n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                txtBuscar.setText("");
                disableAdmin(false, false, false, false, true, true);
                enableTextfield(false, false, false, false, false, false, false, true, false, false, false, false, true);
                setTextField();

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            disableAdmin(false, false, false, false, true, true);
            enableTextfield(false, false, false, false, false, false, false, true, false, false, false, false, true);
            setTextField();
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

        jLabel1 = new javax.swing.JLabel();
        lb_bienvenido = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        cbxBusqueda = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        btn_eliminar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_empleado = new javax.swing.JTable();
        btn_Guardar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btn_actualizar = new javax.swing.JButton();
        btn_nuevo = new javax.swing.JButton();
        btn_buscar = new javax.swing.JButton();
        txtApellidos = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtCuidad = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDapartamento = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtPais = new javax.swing.JTextField();
        txtBuscar = new javax.swing.JTextField();
        cbx_admin = new javax.swing.JComboBox<>();
        lb = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(980, 650));
        setMinimumSize(new java.awt.Dimension(980, 650));
        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel1.setText("ID:");
        add(jLabel1);
        jLabel1.setBounds(25, 74, 40, 20);

        lb_bienvenido.setFont(new java.awt.Font("SimSun-ExtB", 1, 48)); // NOI18N
        lb_bienvenido.setText("Formulario Empleado");
        add(lb_bienvenido);
        lb_bienvenido.setBounds(210, 0, 550, 40);

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel2.setText("Documento:");
        add(jLabel2);
        jLabel2.setBounds(474, 74, 120, 20);

        jLabel3.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel3.setText("Apellido:");
        add(jLabel3);
        jLabel3.setBounds(474, 114, 110, 20);

        jLabel4.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel4.setText("Nombre:");
        add(jLabel4);
        jLabel4.setBounds(25, 114, 80, 20);

        jLabel5.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel5.setText("Sexo:");
        add(jLabel5);
        jLabel5.setBounds(30, 160, 60, 20);

        jLabel6.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel6.setText("Dirección:");
        add(jLabel6);
        jLabel6.setBounds(470, 160, 120, 20);

        txtId.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });
        add(txtId);
        txtId.setBounds(110, 70, 294, 22);

        txtCedula.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });
        add(txtCedula);
        txtCedula.setBounds(610, 70, 280, 22);

        txtNombres.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombresKeyTyped(evt);
            }
        });
        add(txtNombres);
        txtNombres.setBounds(110, 110, 294, 22);

        txtTelefono.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        add(txtTelefono);
        txtTelefono.setBounds(610, 200, 280, 22);

        cbxBusqueda.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        cbxBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Cedula" }));
        cbxBusqueda.setBorder(null);
        add(cbxBusqueda);
        cbxBusqueda.setBounds(550, 280, 110, 20);

        jLabel7.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel7.setText("Buscar:");
        add(jLabel7);
        jLabel7.setBounds(470, 280, 70, 20);

        btn_eliminar.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, new java.awt.Color(153, 153, 153), java.awt.Color.cyan, java.awt.Color.black));
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        add(btn_eliminar);
        btn_eliminar.setBounds(310, 330, 120, 25);

        btn_cancelar.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, new java.awt.Color(153, 153, 153), java.awt.Color.cyan, java.awt.Color.black));
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        add(btn_cancelar);
        btn_cancelar.setBounds(730, 330, 120, 25);

        tb_empleado = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        tb_empleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tb_empleado);

        add(jScrollPane1);
        jScrollPane1.setBounds(25, 371, 867, 170);

        btn_Guardar.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        btn_Guardar.setText("Agregar");
        btn_Guardar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, new java.awt.Color(153, 153, 153), java.awt.Color.cyan, java.awt.Color.black));
        btn_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarActionPerformed(evt);
            }
        });
        add(btn_Guardar);
        btn_Guardar.setBounds(170, 330, 120, 25);

        jLabel9.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel9.setText("Correo:");
        add(jLabel9);
        jLabel9.setBounds(30, 200, 70, 20);

        btn_actualizar.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        btn_actualizar.setText("Actualizar");
        btn_actualizar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, new java.awt.Color(153, 153, 153), java.awt.Color.cyan, java.awt.Color.black));
        btn_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizarActionPerformed(evt);
            }
        });
        add(btn_actualizar);
        btn_actualizar.setBounds(450, 330, 120, 25);

        btn_nuevo.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        btn_nuevo.setText("Nuevo");
        btn_nuevo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, new java.awt.Color(153, 153, 153), java.awt.Color.cyan, java.awt.Color.black));
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });
        add(btn_nuevo);
        btn_nuevo.setBounds(30, 330, 120, 25);

        btn_buscar.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        btn_buscar.setText("Buscar");
        btn_buscar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, new java.awt.Color(153, 153, 153), java.awt.Color.cyan, java.awt.Color.black));
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });
        add(btn_buscar);
        btn_buscar.setBounds(590, 330, 120, 25);

        txtApellidos.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidosKeyTyped(evt);
            }
        });
        add(txtApellidos);
        txtApellidos.setBounds(607, 114, 280, 22);

        txtCorreo.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoKeyTyped(evt);
            }
        });
        add(txtCorreo);
        txtCorreo.setBounds(110, 200, 294, 22);

        jLabel8.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel8.setText("Teléfono:");
        add(jLabel8);
        jLabel8.setBounds(470, 200, 110, 20);

        txtDireccion.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });
        add(txtDireccion);
        txtDireccion.setBounds(610, 160, 280, 22);

        jLabel10.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel10.setText("Cuidad:");
        add(jLabel10);
        jLabel10.setBounds(30, 240, 90, 20);

        txtCuidad.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtCuidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCuidadActionPerformed(evt);
            }
        });
        txtCuidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuidadKeyTyped(evt);
            }
        });
        add(txtCuidad);
        txtCuidad.setBounds(110, 240, 294, 22);

        jLabel11.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel11.setText("Departamento:");
        add(jLabel11);
        jLabel11.setBounds(470, 240, 140, 20);

        txtDapartamento.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtDapartamento.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtDapartamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDapartamentoKeyTyped(evt);
            }
        });
        add(txtDapartamento);
        txtDapartamento.setBounds(610, 240, 280, 25);

        jLabel12.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel12.setText("Pais:");
        add(jLabel12);
        jLabel12.setBounds(30, 280, 60, 20);

        txtPais.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtPais.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPaisKeyTyped(evt);
            }
        });
        add(txtPais);
        txtPais.setBounds(110, 280, 294, 22);

        txtBuscar.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        add(txtBuscar);
        txtBuscar.setBounds(670, 280, 220, 25);

        cbx_admin.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        cbx_admin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Femenino", "Otro" }));
        cbx_admin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(cbx_admin);
        cbx_admin.setBounds(110, 150, 290, 26);

        lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/back1.png"))); // NOI18N
        lb.setMinimumSize(new java.awt.Dimension(980, 650));
        add(lb);
        lb.setBounds(0, 0, 1000, 620);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCuidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCuidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuidadActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        // TODO add your handling code here:
        txtId.setText("0");
        disableAdmin(true, false, true, false, false, false);
        enableTextfield(false, true, true, true, true, true, true, false, true, true, true, true, false);

    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        // TODO add your handling code here:
        setTextField();
        controlEmpleado.listEmployee(tb_empleado);
        disableAdmin(false, false, false, false, true, true);
        enableTextfield(false, false, false, false, false, false, false, true, false, false, false, false, true);
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        // TODO add your handling code here:

        if (txtBuscar.getText().equals("")) {
            javax.swing.JOptionPane.showMessageDialog(null, "El campo del busqueda esta vacio \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            txtBuscar.requestFocus();
            setTextField();
            controlEmpleado.listEmployee(tb_empleado);
            disableAdmin(false, false, false, false, true, true);
            enableTextfield(false, false, false, false, false, false, false, true, false, false, false, false, true);
        } else {
            if (cbxBusqueda.getSelectedItem().toString().equals("ID")) {
                Search("employee_id", txtBuscar);
                cbx_admin.setSelectedItem(gender);
                gender = "";
                txtBuscar.setText("");
            } else if (cbxBusqueda.getSelectedItem().toString().equals("Cedula")) {
                Search("document", txtBuscar);
                cbx_admin.setSelectedItem(gender);
                gender = "";
                txtBuscar.setText("");
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Seleccion una opción para buscar \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void btn_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarActionPerformed
        // TODO add your handling code here:
        String grade = cbx_admin.getSelectedItem().toString();
        //int phone = Integer.parseInt(txt_tel.getText());
        if (txtNombres.getText().equals("") || txtPais.getText().equals("") || txtCedula.getText().equals("") || txtDapartamento.getText().equals("") || txtCuidad.getText().equals("") || txtApellidos.getText().equals("") || txtDireccion.getText().equals("") || txtTelefono.getText().equals("") || txtCorreo.getText().equals("")) {
            javax.swing.JOptionPane.showMessageDialog(null, "Por favor!\nDebe llenar todos los campos \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            txtNombres.requestFocus();
        } else {
            con = conDB.getConection();
            String st = "INSERT INTO employees VALUES(0,'" + txtCedula.getText() + "','" + txtNombres.getText() + "','" + txtApellidos.getText() + "','" + grade + "','" + txtTelefono.getText() + "','" + txtCorreo.getText() + "','" + txtDireccion.getText() + "','" + txtCuidad.getText() + "','" + txtDapartamento.getText() + "','" + txtPais.getText() + "')";

            try {
                pst = conDB.getConection().prepareStatement("SELECT * FROM employees WHERE document='" + txtCedula.getText() + "'");
                rs = pst.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "El número de cedula " + txtCedula.getText() + " ya se encuentra registrado\n", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    txtCedula.requestFocus();
                } else {
                    statement = (Statement) con.createStatement();
                    int comp = (int) statement.executeUpdate(st);
                    if (comp == 1) {
                        JOptionPane.showMessageDialog(null, "Registo guardado con exito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        disableAdmin(false, false, false, false, true, true);
                        enableTextfield(false, false, false, false, false, false, false, true, false, false, false, false, true);
                        setTextField();
                        controlEmpleado.listEmployee(tb_empleado);
                    } else {
                        JOptionPane.showMessageDialog(null, "Se presento un error al guardar el registro", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        disableAdmin(false, false, false, false, true, true);
                        enableTextfield(false, false, false, false, false, false, false, true, false, false, false, false, true);
                        setTextField();
                        controlEmpleado.listEmployee(tb_empleado);
                    }
                    statement.close();
                }
                con.close();
            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_btn_GuardarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        con = conDB.getConection();
        String st = "DELETE FROM employees WHERE employee_id='" + txtId.getText() + "'";
        int p = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar el registro?", "Delete", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            if (txtCedula.getText().equals("100") && txtId.getText().equals("1")) {
                JOptionPane.showMessageDialog(null, "Este registro no se puede eliminar", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                disableAdmin(false, false, false, false, true, true);
                enableTextfield(false, false, false, false, false, false, false, true, false, false, false, false, true);
                setTextField();
                controlEmpleado.listEmployee(tb_empleado);
            } else {
                try {
                    pst = (PreparedStatement) con.prepareStatement(st);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Registo eliminado con exito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    disableAdmin(false, false, false, false, true, true);
                    enableTextfield(false, false, false, false, false, false, false, true, false, false, false, false, true);
                    setTextField();
                    pst.close();
                    con.close();
                    controlEmpleado.listEmployee(tb_empleado);

                } catch (SQLException ex) {
                    Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
                    disableAdmin(false, false, false, false, true, true);
                    enableTextfield(false, false, false, false, false, false, false, true, false, false, false, false, true);
                    setTextField();
                    controlEmpleado.listEmployee(tb_empleado);

                }
            }

        } else {
            setTextField();
            controlEmpleado.listEmployee(tb_empleado);
            disableAdmin(false, false, false, false, true, true);
            enableTextfield(false, false, false, false, false, false, false, true, false, false, false, false, true);
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizarActionPerformed
        // TODO add your handling code here:
//        disableAdmin(false, false, false, false, true, true);
//        enableTextfield(false, false, false, false, false, false, false, true, false, false, false, false, true);
        String grade = cbx_admin.getSelectedItem().toString();
        //int phone = Integer.parseInt(txt_tel.getText());
        if (txtNombres.getText().equals("") || txtPais.getText().equals("") || txtCedula.getText().equals("") || txtDapartamento.getText().equals("") || txtCuidad.getText().equals("") || txtApellidos.getText().equals("") || txtDireccion.getText().equals("") || txtTelefono.getText().equals("") || txtCorreo.getText().equals("")) {
            javax.swing.JOptionPane.showMessageDialog(null, "Por favor!\nDebe llenar todos los campos \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            txtNombres.requestFocus();
        } else {
            try {
                con = conDB.getConection();
                String st = "UPDATE employees SET document='" + txtCedula.getText() + "',first_name='" + txtNombres.getText() + "',last_name='" + txtApellidos.getText() + "',gender='" + grade + "',phone_number='" + txtTelefono.getText() + "',email='" + txtCorreo.getText() + "',address='" + txtDireccion.getText() + "',city='" + txtCuidad.getText() + "',department='" + txtDapartamento.getText() + "',country='" + txtPais.getText() + "' WHERE employee_id='" + txtId.getText() + "';";
                statement = (Statement) con.createStatement();
                statement.execute(st);
                JOptionPane.showMessageDialog(null, "Registo actualizado con exito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                setTextField();
                //boolean cancel,boolean delete,boolean  save,boolean update,boolean nuevo,boolean search
                disableAdmin(false, false, false, false, true, true);
                enableTextfield(false, false, false, false, false, false, false, true, false, false, false, false, true);
                setTextField();
                statement.close();
                con.close();

            } catch (Exception e) {
                disableAdmin(false, false, false, false, true, true);
                enableTextfield(false, false, false, false, false, false, false, true, false, false, false, false, true);
                setTextField();
            }
        }
        controlEmpleado.listEmployee(tb_empleado);
    }//GEN-LAST:event_btn_actualizarActionPerformed

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        // TODO add your handling code here:
        controles.limitedField(txtId, 11, evt);
    }//GEN-LAST:event_txtIdKeyTyped

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        // TODO add your handling code here:
        controles.limitedField(txtCedula, 45, evt);
    }//GEN-LAST:event_txtCedulaKeyTyped

    private void txtNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyTyped
        // TODO add your handling code here:
        controles.limitedField(txtNombres, 50, evt);
    }//GEN-LAST:event_txtNombresKeyTyped

    private void txtApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosKeyTyped
        // TODO add your handling code here:
        controles.limitedField(txtApellidos, 50, evt);
    }//GEN-LAST:event_txtApellidosKeyTyped

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        // TODO add your handling code here:
        controles.limitedField(txtDireccion, 100, evt);
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyTyped
        // TODO add your handling code here:
        controles.limitedField(txtCorreo, 50, evt);
    }//GEN-LAST:event_txtCorreoKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
        controles.limitedField(txtTelefono, 15, evt);
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtCuidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuidadKeyTyped
        // TODO add your handling code here:
        controles.limitedField(txtCuidad, 100, evt);
    }//GEN-LAST:event_txtCuidadKeyTyped

    private void txtDapartamentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDapartamentoKeyTyped
        // TODO add your handling code here:
        controles.limitedField(txtDapartamento, 100, evt);
    }//GEN-LAST:event_txtDapartamentoKeyTyped

    private void txtPaisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaisKeyTyped
        // TODO add your handling code here:
        controles.limitedField(txtPais, 100, evt);
    }//GEN-LAST:event_txtPaisKeyTyped

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
        controles.limitedField(txtBuscar, 15, evt);
    }//GEN-LAST:event_txtBuscarKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Guardar;
    private javax.swing.JButton btn_actualizar;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JComboBox<String> cbxBusqueda;
    private javax.swing.JComboBox<String> cbx_admin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb_bienvenido;
    private javax.swing.JTable tb_empleado;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtCuidad;
    private javax.swing.JTextField txtDapartamento;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtPais;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
