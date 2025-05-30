import dao.UserDao;
import model.User;

import javax.swing.*;
import java.awt.*;

public class LoginDialog extends JDialog {

    private final JTextField tfUser   = new JTextField(12);
    private final JPasswordField tfPwd= new JPasswordField(12);
    private final UserDao dao = new UserDao();
    private User loggedIn=null;

    public LoginDialog(JFrame owner){
        super(owner,"Login",true);
        buildUI(); pack(); setLocationRelativeTo(owner);
    }
    public User showLogin(){ setVisible(true); return loggedIn; }

    private void buildUI(){
        var p=new JPanel(new GridBagLayout());
        var g=new GridBagConstraints(); g.insets=new Insets(4,4,4,4); g.anchor=13;
        g.gridy=0; p.add(new JLabel("Username:"),g); g.gridx=1; p.add(tfUser,g);
        g.gridx=0; g.gridy=1; p.add(new JLabel("Password:"),g);
        g.gridx=1; p.add(tfPwd,g);

        var btnLogin=new JButton("Login");
        var btnReg  =new JButton("Register…");
        btnLogin.addActionListener(e->doLogin());
        btnReg  .addActionListener(e->openRegister());

        var south=new JPanel(); south.add(btnLogin); south.add(btnReg);
        getContentPane().add(p,BorderLayout.CENTER);
        getContentPane().add(south,BorderLayout.SOUTH);
    }
    private void doLogin(){
        String user=tfUser.getText().trim();
        String pwd =new String(tfPwd.getPassword());
        var u=dao.login(user,pwd);
        if(u.isPresent()){ loggedIn=u.get(); dispose(); }
        else JOptionPane.showMessageDialog(this,"Wrong credentials");
    }
    private void openRegister() {
        RegisterDialog rd = new RegisterDialog(null);   //  <-- no cast, no crash
        rd.setVisible(true);
        if (rd.ok())
            JOptionPane.showMessageDialog(this,
                    "Now log-in with the credentials you just created.");
    }

}
