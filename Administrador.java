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

/**
 *
 * @author FERNEY
 */
public class Administrador extends javax.swing.JPanel {

    /**
     * Creates new form Administrador
     */
    private Controles controles;
    private ControlTablas controlTablas;
    private connectDB conDB = new connectDB();
    private Connection con;
    private Statement statement;
    private PreparedStatement pst;
    private ResultSet rs;
    private String gender = "";

    public Administrador() {
        initComponents();
        this.setSize(980, 650);
        Icon ico = lb.getIcon();
        ImageIcon icon = (ImageIcon) ico;
        Image image = icon.getImage().getScaledInstance(lb.getWidth(), lb.getHeight(), Image.SCALE_SMOOTH);
        lb.setIcon(new ImageIcon(image));

        controles = new Controles();
        controlTablas = new ControlTablas();
        tb_admin.getTableHeader().setResizingAllowed(false);
        tb_admin.getTableHeader().setReorderingAllowed(false);
        //placeholder
        TextPrompt txt1 = new TextPrompt("ID", txt_Id);
        TextPrompt txt2 = new TextPrompt("Nombre de usuario", txtUser);
        TextPrompt txt3 = new TextPrompt("Documento", txtCedula);
        TextPrompt txt4 = new TextPrompt("Correo electronico", txtEmail);
        TextPrompt txt5 = new TextPrompt("Contraseña", txtPassword);
        TextPrompt txt6 = new TextPrompt("Nombres", txtNames);
        TextPrompt txt7 = new TextPrompt("Buscar", txtBuscar);
        TextPrompt txt8 = new TextPrompt("Ingrese documento del empleado", txtBuscarEmpleado);

        txt_Id.setEnabled(false);

        controlTablas.listAdministrator(tb_admin);
        disableAdmin(false, false, false, false, true, true);
        enableTextfield(false, false, false, false, false, false, true, true, false, true);

    }

    private void disableAdmin(boolean cancel, boolean delete, boolean save, boolean update, boolean search, boolean searchEmployee) {
        btn_cancelar1.setVisible(cancel);
        btn_eliminar.setVisible(delete);
        btn_Guardar.setVisible(save);
        btn_actualizar1.setVisible(update);
        btn_buscar.setVisible(search);
        btn_BuscarEmpleado.setVisible(searchEmployee);
    }

    private void setTextField() {
        txt_Id.setText("");
        txtUser.setText("");
        txtCedula.setText("");
        txtNames.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        txtBuscarEmpleado.setText("");
        txtBuscar.setText("");
        cbxRol.setSelectedIndex(0);
    }

    public void SetterAll() {
        disableAdmin(false, false, false, false, true, true);
        enableTextfield(false, false, false, false, false, false, true, true, false, true);
        setTextField();
    }

    private void enableTextfield(boolean id, boolean user, boolean ced, boolean names, boolean mail, boolean pass, boolean employee, boolean search, boolean rol, boolean combo1) {
        txt_Id.setEnabled(id);
        txtUser.setEnabled(user);
        txtCedula.setEnabled(ced);
        txtNames.setEnabled(names);
        txtEmail.setEnabled(mail);
        txtPassword.setEnabled(pass);
        txtBuscarEmpleado.setEnabled(employee);
        txtBuscar.setEnabled(search);
        cbxRol.setEnabled(rol);
        cbxRol.setSelectedIndex(0);
        cbxSearch.setEnabled(combo1);
        cbxSearch.setSelectedIndex(0);
    }

    private void Search(String st, JTextField buscar) {
        String sql = "SELECT * FROM barber_admin WHERE " + st + "='" + buscar.getText() + "';";

        try {
            con = conDB.getConection();
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            if (rs.next()) {
                if (buscar.getText().equals("100") || buscar.getText().equals("1") || buscar.getText().equals("200") || buscar.getText().equals("2")) {
                    controlTablas.listAdministrator(tb_admin);
                    disableAdmin(true, true, false, true, false, false);
                    enableTextfield(false, false, false, false, false, true, false, false, false, false);
                } else {
                    disableAdmin(true, true, false, true, false, false);
                    enableTextfield(false, false, false, false, false, true, false, false, true, false);
                }
                controlTablas.listAdministrator(tb_admin);

                txt_Id.setText(rs.getString("admin_id"));
                txtCedula.setText(rs.getString("cedula"));
                txtUser.setText(rs.getString("username"));
                txtEmail.setText(rs.getString("email"));
//                cbx_admin.setSelectedItem(rs.getString("gender").toString());
                gender = rs.getString("role").toString();

                txtNames.setText(rs.getString("full_name"));
                txtPassword.setText(rs.getString("password"));

            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Registro no encontrado en la base de datos\n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                txtBuscar.setText("");
                controlTablas.listAdministrator(tb_admin);
                disableAdmin(false, false, false, false, true, true);
                enableTextfield(false, false, false, false, false, false, true, true, false, true);
                setTextField();

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            controlTablas.listAdministrator(tb_admin);
            disableAdmin(false, false, false, false, true, true);
            enableTextfield(false, false, false, false, false, false, true, true, false, true);
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

        lb_bienvenido = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_Id = new javax.swing.JTextField();
        txtBuscarEmpleado = new javax.swing.JTextField();
        txtUser = new javax.swing.JTextField();
        txtBuscar = new javax.swing.JTextField();
        txtNames = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        cbxSearch = new javax.swing.JComboBox<>();
        btn_eliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_admin = new javax.swing.JTable();
        btn_BuscarEmpleado = new javax.swing.JButton();
        btn_Guardar = new javax.swing.JButton();
        cbxRol = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        btn_actualizar1 = new javax.swing.JButton();
        btn_buscar = new javax.swing.JButton();
        txtEmail = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        btn_cancelar1 = new javax.swing.JButton();
        lb = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(980, 650));
        setMinimumSize(new java.awt.Dimension(980, 650));
        setLayout(null);

        lb_bienvenido.setFont(new java.awt.Font("SimSun-ExtB", 1, 36)); // NOI18N
        lb_bienvenido.setText("Formulario Administrador");
        add(lb_bienvenido);
        lb_bienvenido.setBounds(30, 10, 500, 40);

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel1.setText("ID:");
        add(jLabel1);
        jLabel1.setBounds(25, 74, 40, 20);

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel2.setText("Documento:");
        add(jLabel2);
        jLabel2.setBounds(474, 74, 120, 20);

        jLabel3.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel3.setText("Correo:");
        add(jLabel3);
        jLabel3.setBounds(474, 114, 80, 20);

        jLabel4.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel4.setText("Usuario:");
        add(jLabel4);
        jLabel4.setBounds(25, 114, 90, 20);

        jLabel5.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel5.setText("Nombres:");
        add(jLabel5);
        jLabel5.setBounds(25, 154, 100, 20);

        jLabel6.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel6.setText("Contraseña:");
        add(jLabel6);
        jLabel6.setBounds(474, 154, 120, 20);

        jLabel7.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel7.setText("Buscar:");
        add(jLabel7);
        jLabel7.setBounds(470, 200, 70, 20);

        txt_Id.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txt_Id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_IdKeyTyped(evt);
            }
        });
        add(txt_Id);
        txt_Id.setBounds(140, 74, 294, 22);

        txtBuscarEmpleado.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtBuscarEmpleado.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtBuscarEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarEmpleadoKeyTyped(evt);
            }
        });
        add(txtBuscarEmpleado);
        txtBuscarEmpleado.setBounds(600, 30, 210, 30);

        txtUser.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUserKeyTyped(evt);
            }
        });
        add(txtUser);
        txtUser.setBounds(140, 114, 294, 22);

        txtBuscar.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        add(txtBuscar);
        txtBuscar.setBounds(675, 200, 220, 22);

        txtNames.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtNames.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamesKeyTyped(evt);
            }
        });
        add(txtNames);
        txtNames.setBounds(140, 154, 294, 22);

        txtPassword.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPasswordKeyTyped(evt);
            }
        });
        add(txtPassword);
        txtPassword.setBounds(597, 154, 300, 22);

        cbxSearch.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        cbxSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Cedula", "ID" }));
        add(cbxSearch);
        cbxSearch.setBounds(550, 200, 110, 22);

        btn_eliminar.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, new java.awt.Color(153, 153, 153), java.awt.Color.cyan, java.awt.Color.black));
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        add(btn_eliminar);
        btn_eliminar.setBounds(170, 240, 120, 28);

        tb_admin = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        tb_admin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tb_admin);

        add(jScrollPane1);
        jScrollPane1.setBounds(25, 281, 867, 260);

        btn_BuscarEmpleado.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        btn_BuscarEmpleado.setText("Buscar");
        btn_BuscarEmpleado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, new java.awt.Color(153, 153, 153), java.awt.Color.cyan, java.awt.Color.black));
        btn_BuscarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BuscarEmpleadoActionPerformed(evt);
            }
        });
        add(btn_BuscarEmpleado);
        btn_BuscarEmpleado.setBounds(820, 30, 80, 30);

        btn_Guardar.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        btn_Guardar.setText("Agregar");
        btn_Guardar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, new java.awt.Color(153, 153, 153), java.awt.Color.cyan, java.awt.Color.black));
        btn_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarActionPerformed(evt);
            }
        });
        add(btn_Guardar);
        btn_Guardar.setBounds(30, 240, 120, 28);

        cbxRol.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        cbxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Empleado" }));
        add(cbxRol);
        cbxRol.setBounds(140, 195, 294, 22);

        jLabel9.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel9.setText("Rol:");
        add(jLabel9);
        jLabel9.setBounds(25, 195, 50, 20);

        btn_actualizar1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        btn_actualizar1.setText("Actualizar");
        btn_actualizar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, new java.awt.Color(153, 153, 153), java.awt.Color.cyan, java.awt.Color.black));
        btn_actualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizar1ActionPerformed(evt);
            }
        });
        add(btn_actualizar1);
        btn_actualizar1.setBounds(310, 240, 120, 28);

        btn_buscar.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        btn_buscar.setText("Buscar");
        btn_buscar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, new java.awt.Color(153, 153, 153), java.awt.Color.cyan, java.awt.Color.black));
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });
        add(btn_buscar);
        btn_buscar.setBounds(450, 240, 120, 28);

        txtEmail.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailKeyTyped(evt);
            }
        });
        add(txtEmail);
        txtEmail.setBounds(597, 114, 300, 22);

        txtCedula.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });
        add(txtCedula);
        txtCedula.setBounds(597, 74, 300, 22);

        btn_cancelar1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        btn_cancelar1.setText("Cancelar");
        btn_cancelar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, new java.awt.Color(153, 153, 153), java.awt.Color.cyan, java.awt.Color.black));
        btn_cancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar1ActionPerformed(evt);
            }
        });
        add(btn_cancelar1);
        btn_cancelar1.setBounds(590, 240, 120, 28);

        lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/back3.PNG"))); // NOI18N
        add(lb);
        lb.setBounds(0, 0, 920, 570);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        // TODO add your handling code here:
        controles.limitedField(txtCedula, 45, evt);
    }//GEN-LAST:event_txtCedulaKeyTyped

    private void txtUserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyTyped
        // TODO add your handling code here:
        controles.limitedField(txtCedula, 50, evt);
    }//GEN-LAST:event_txtUserKeyTyped

    private void txtEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyTyped
        // TODO add your handling code here:
        controles.limitedField(txtCedula, 50, evt);
    }//GEN-LAST:event_txtEmailKeyTyped

    private void txtPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyTyped
        // TODO add your handling code here:
        controles.limitedField(txtCedula, 32, evt);
    }//GEN-LAST:event_txtPasswordKeyTyped

    private void txtNamesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamesKeyTyped
        // TODO add your handling code here:
        controles.limitedField(txtCedula, 100, evt);
    }//GEN-LAST:event_txtNamesKeyTyped

    private void btn_BuscarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BuscarEmpleadoActionPerformed
        // TODO add your handling code here:
        if (txtBuscarEmpleado.getText().equals("")) {
            javax.swing.JOptionPane.showMessageDialog(null, "El campo del busqueda esta vacio \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            txtBuscar.requestFocus();
        } else {
            String sql = "SELECT * FROM employees WHERE document='" + txtBuscarEmpleado.getText() + "';";
            try {
                con = conDB.getConection();
                statement = con.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    txt_Id.setText("0");
                    txtCedula.setText(rs.getString("document"));
                    txtUser.setText(rs.getString("first_name"));
                    txtNames.setText(rs.getString("first_name") + " " + rs.getString("last_name"));
                    txtEmail.setText(rs.getString("email"));
                    txtBuscarEmpleado.setText("");
                    disableAdmin(true, false, true, false, false, false);
                    enableTextfield(false, false, false, false, false, true, false, false, true, false);

                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Registro no encontrado en la base de datos\nProcede a registrar en el formulario de Empleados", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    txtBuscarEmpleado.setText("");
                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_btn_BuscarEmpleadoActionPerformed

    private void btn_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarActionPerformed
        // TODO add your handling code here:
        String grade = cbxRol.getSelectedItem().toString();
        String pass=new String(txtPassword.getText());
        //int phone = Integer.parseInt(txt_tel.getText());

        con = conDB.getConection();
        String st = "INSERT INTO barber_admin VALUES(0,'" + txtCedula.getText() + "','" + txtUser.getText() + "','" + txtEmail.getText() + "','" + txtNames.getText() + "','" + pass + "','" + grade + "')";

        try {
            pst = conDB.getConection().prepareStatement("SELECT * FROM barber_admin WHERE cedula='" + txtCedula.getText() + "'");
            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "El número de cedula " + txtCedula.getText() + " ya se encuentra registrado\n", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                txtCedula.requestFocus();
                controlTablas.listAdministrator(tb_admin);
                disableAdmin(false, false, false, false, true, true);
                enableTextfield(false, false, false, false, false, false, true, true, false, true);
                setTextField();
            } else {
                statement = (Statement) con.createStatement();
                int comp = (int) statement.executeUpdate(st);
                if (comp == 1) {
                    JOptionPane.showMessageDialog(null, "Registo guardado con exito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    disableAdmin(false, false, false, false, true, true);
                    enableTextfield(false, false, false, false, false, false, true, true, false, true);
                    setTextField();
                    controlTablas.listAdministrator(tb_admin);
                } else {
                    JOptionPane.showMessageDialog(null, "Se presento un error al guardar el registro", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    controlTablas.listAdministrator(tb_admin);
                    disableAdmin(false, false, false, false, true, true);
                    enableTextfield(false, false, false, false, false, false, true, true, false, true);
                    setTextField();
                }
                statement.close();
            }
            con.close();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_GuardarActionPerformed

    private void btn_cancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar1ActionPerformed
        // TODO add your handling code here:
        controlTablas.listAdministrator(tb_admin);
        disableAdmin(false, false, false, false, true, true);
        enableTextfield(false, false, false, false, false, false, true, true, false, true);
        setTextField();
    }//GEN-LAST:event_btn_cancelar1ActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        con = conDB.getConection();
        String st = "DELETE FROM barber_admin WHERE admin_id='" + txt_Id.getText() + "'";
        int p = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar el registro?", "Delete", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            if (txtCedula.getText().equals("100") && txt_Id.getText().equals("1")) {
                JOptionPane.showMessageDialog(null, "Este registro no se puede eliminar", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                controlTablas.listAdministrator(tb_admin);
                disableAdmin(false, false, false, false, true, true);
                enableTextfield(false, false, false, false, false, false, true, true, false, true);
                setTextField();
            } else {
                try {
                    pst = (PreparedStatement) con.prepareStatement(st);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Registo eliminado con exito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    controlTablas.listAdministrator(tb_admin);
                    disableAdmin(false, false, false, false, true, true);
                    enableTextfield(false, false, false, false, false, false, true, true, false, true);
                    setTextField();
                    pst.close();
                    con.close();

                } catch (SQLException ex) {
                    Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
                    controlTablas.listAdministrator(tb_admin);
                    disableAdmin(false, false, false, false, true, true);
                    enableTextfield(false, false, false, false, false, false, true, true, false, true);
                    setTextField();

                }
            }

        } else {
            controlTablas.listAdministrator(tb_admin);
            disableAdmin(false, false, false, false, true, true);
            enableTextfield(false, false, false, false, false, false, true, true, false, true);
            setTextField();
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_actualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizar1ActionPerformed
        // TODO add your handling code here:
        String grade = cbxRol.getSelectedItem().toString();
        //int phone = Integer.parseInt(txt_tel.getText());
        if (txtPassword.getText().equals("")) {
            javax.swing.JOptionPane.showMessageDialog(null, "El campo de la contraseña esta vacio \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            txtPassword.requestFocus();
        } else {
            try {
                con = conDB.getConection();
                String st = "UPDATE barber_admin SET cedula='" + txtCedula.getText() + "',username='" + txtUser.getText() + "',email='" + txtEmail.getText() + "',full_name='" + txtNames.getText() + "',password='" + txtPassword.getText() + "',role='" + grade + "' WHERE admin_id='" + txt_Id.getText() + "';";
                statement = (Statement) con.createStatement();
                statement.execute(st);
                JOptionPane.showMessageDialog(null, "Registo actualizado con exito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                controlTablas.listAdministrator(tb_admin);
                disableAdmin(false, false, false, false, true, true);
                enableTextfield(false, false, false, false, false, false, true, true, false, true);
                setTextField();
                statement.close();
                con.close();

            } catch (Exception e) {
                controlTablas.listAdministrator(tb_admin);
                disableAdmin(false, false, false, false, true, true);
                enableTextfield(false, false, false, false, false, false, true, true, false, true);
                setTextField();
            }
        }
        controlTablas.listAdministrator(tb_admin);
    }//GEN-LAST:event_btn_actualizar1ActionPerformed

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        // TODO add your handling code here:
        if (txtBuscar.getText().equals("")) {
            javax.swing.JOptionPane.showMessageDialog(null, "El campo del busqueda esta vacio \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            txtBuscar.requestFocus();
            controlTablas.listAdministrator(tb_admin);
            disableAdmin(false, false, false, false, true, true);
            enableTextfield(false, false, false, false, false, false, true, true, false, true);
            setTextField();
        } else {
            if (cbxSearch.getSelectedItem().toString().equals("ID")) {
                Search("admin_id", txtBuscar);
                cbxSearch.setSelectedItem(gender);
                gender = "";
                txtBuscar.setText("");
            } else if (cbxSearch.getSelectedItem().toString().equals("Cedula")) {
                Search("cedula", txtBuscar);
                cbxSearch.setSelectedItem(gender);
                gender = "";
                txtBuscar.setText("");
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Seleccion una opción para buscar \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void txt_IdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_IdKeyTyped
        // TODO add your handling code here:
        controles.limitedField(txt_Id, 11, evt);
    }//GEN-LAST:event_txt_IdKeyTyped

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
        controles.limitedField(txtBuscar, 15, evt);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void txtBuscarEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarEmpleadoKeyTyped
        // TODO add your handling code here:
        controles.limitedField(txtBuscarEmpleado, 15, evt);
    }//GEN-LAST:event_txtBuscarEmpleadoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_BuscarEmpleado;
    private javax.swing.JButton btn_Guardar;
    private javax.swing.JButton btn_actualizar1;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_cancelar1;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JComboBox<String> cbxRol;
    private javax.swing.JComboBox<String> cbxSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb_bienvenido;
    private javax.swing.JTable tb_admin;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarEmpleado;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNames;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUser;
    private javax.swing.JTextField txt_Id;
    // End of variables declaration//GEN-END:variables
}
