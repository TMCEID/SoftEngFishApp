import dao.UserDao;

import javax.swing.*;
import java.awt.*;

public class RegisterDialog extends JDialog {

    private final JTextField tfPhone = new JTextField(12);
    private final JTextField tfUser  = new JTextField(12);
    private final JPasswordField tfPwd = new JPasswordField(12);
    private final JPasswordField tfPwd2= new JPasswordField(12);

    private final JRadioButton male  = new JRadioButton("Male",true);
    private final JRadioButton fem   = new JRadioButton("Female");

    private boolean success = false;
    private final UserDao dao = new UserDao();

    public RegisterDialog(JFrame owner){
        super(owner,"Create account",true);
        buildUI();
        pack(); setLocationRelativeTo(owner);
    }
    public boolean ok(){ return success; }

    private void buildUI(){
        var p = new JPanel(new GridBagLayout());
        var g=new GridBagConstraints(); g.insets=new Insets(4,4,4,4); g.anchor=13;

        int y=0;
        g.gridy=y; p.add(new JLabel("Phone:"),g); g.gridx=1; p.add(tfPhone,g);
        g.gridx=0; g.gridy=++y; p.add(new JLabel("Username:"),g);
        g.gridx=1; p.add(tfUser,g);
        g.gridx=0; g.gridy=++y; p.add(new JLabel("Password:"),g);
        g.gridx=1; p.add(tfPwd,g);
        g.gridx=0; g.gridy=++y; p.add(new JLabel("Repeat:"),g);
        g.gridx=1; p.add(tfPwd2,g);
        g.gridx=0; g.gridy=++y; p.add(new JLabel("Gender:"),g);
        var bg = new ButtonGroup(); bg.add(male); bg.add(fem);
        var gp=new JPanel(); gp.add(male); gp.add(fem);
        g.gridx=1; p.add(gp,g);

        var btnOK=new JButton("Register");
        btnOK.addActionListener(e->doRegister());
        getContentPane().add(p,BorderLayout.CENTER);
        getContentPane().add(btnOK,BorderLayout.SOUTH);
    }
    private void doRegister(){
        String phone=tfPhone.getText().trim();
        String user =tfUser .getText().trim();
        String pwd  =new String(tfPwd.getPassword());
        String pwd2 =new String(tfPwd2.getPassword());
        if(phone.isEmpty()||user.isEmpty()||pwd.isEmpty()){
            msg("Fill all fields"); return;
        }
        if(!pwd.equals(pwd2)){ msg("Passwords differ"); return; }
        int id=dao.register(phone,user, male.isSelected()?'M':'F',pwd);
        if(id==-1){ msg("Username/phone already used"); return; }
        success=true; dispose();
    }
    private void msg(String s){ JOptionPane.showMessageDialog(this,s); }
}
