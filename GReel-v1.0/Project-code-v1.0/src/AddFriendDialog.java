import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;

// Modal dialog that autocompletes phone/username with the DB.
public class AddFriendDialog extends JDialog {

    private final JTextField searchBox = new JTextField(14);
    private final JList<User> suggList  = new JList<>(new DefaultListModel<>());
    private final JPopupMenu popup      = new JPopupMenu();
    private final FriendsController ctrl;
    private final int ownerId;

    public AddFriendDialog(Frame parent, FriendsController ctrl, int ownerId) {
        super(parent, "Add friend", true);   // modal
        this.ctrl    = ctrl;
        this.ownerId = ownerId;
        buildUi();
    }

    //UI
    private void buildUi() {
        suggList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        popup.add(new JScrollPane(suggList));
        //keep focus on the text field
                popup.setFocusable(false);
                suggList.setFocusable(false);

                        popup.setBorder(
                                        javax.swing.BorderFactory.createLineBorder(new Color(160,160,160)));
                suggList.setFixedCellHeight(24);     // taller rows

                        suggList.setCellRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override public java.awt.Component getListCellRendererComponent(
                    javax.swing.JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {

                                                User u = (User) value;
                                        String html = "<html><b>" + u.getUsername() + "</b>&nbsp;"
                                                             + "<span style='color:gray'>" + u.getPhone() + "</span></html>";
                                        return super.getListCellRendererComponent(
                                                        list, html, index, isSelected, cellHasFocus);
                                    }
        });

        // live filtering
        searchBox.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            void update() {
                String p = searchBox.getText().trim();
                if (p.length() < 2) { popup.setVisible(false); return; }

                var m = (DefaultListModel<User>) suggList.getModel();
                m.clear();
                ctrl.suggest(p).forEach(m::addElement);

                if (m.isEmpty()) { popup.setVisible(false); return; }
                if (!popup.isVisible())
                            popup.show(searchBox, 0, searchBox.getHeight()); // under field
                            suggList.setSelectedIndex(0);
                            searchBox.requestFocusInWindow();                    // stay focused
            }
            public void insertUpdate (javax.swing.event.DocumentEvent e){update();}
            public void removeUpdate (javax.swing.event.DocumentEvent e){update();}
            public void changedUpdate(javax.swing.event.DocumentEvent e){}
        });

        /* ↓ / ↑ to navigate – Enter to accept – all while retaining focused */
                searchBox.addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                                if (popup.isVisible()) {
                                       int sel = suggList.getSelectedIndex();
                                        switch (e.getKeyCode()) {
                                                case KeyEvent.VK_DOWN -> {
                                                        suggList.setSelectedIndex(
                                                                        Math.min(sel + 1,
                                                                                        suggList.getModel().getSize() - 1));
                                                        e.consume();
                                                    }
                                                case KeyEvent.VK_UP -> {
                                                        suggList.setSelectedIndex(Math.max(sel - 1, 0));
                                                        e.consume();
                                                    }
                                                case KeyEvent.VK_ENTER -> {
                                                        acceptCurrent();
                                                        e.consume();
                                                   }
                                            }
                                            }
                            }
        });

        suggList.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                                /* single click selects ,fills box, hides popup, keep focus */
                                        acceptCurrent();
                            }
        });

        /* buttons */
        JButton ok     = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        ok.addActionListener(e -> commit());
        cancel.addActionListener(e -> dispose());

        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        south.add(ok); south.add(cancel);

        setLayout(new BorderLayout(5,5));
        add(new JLabel("Phone or username:"), BorderLayout.NORTH);
        add(searchBox, BorderLayout.CENTER);
        add(south,        BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(getParent());
    }

    private void commit() {
                String token = searchBox.getText().trim();
                User sel = suggList.getSelectedValue();
                if (sel != null) token = sel.getUsername();
                if (token.isBlank()) return;

                        switch (ctrl.add(token, ownerId)) {
                                case ADDED -> {
                                        JOptionPane.showMessageDialog(this, "Friend added!");
                                        dispose();
                                    }
                                case ALREADY ->
                                            JOptionPane.showMessageDialog(this, "Already friends");
                                case NOT_FOUND ->
                                            JOptionPane.showMessageDialog(this, "User not found");
                            }
                    }

    // choose suggestion without committing
    private void acceptCurrent() {
        User u = suggList.getSelectedValue();
        if (u == null) return;
        searchBox.setText(u.getUsername());
        popup.setVisible(false);
        searchBox.requestFocusInWindow();
    }

}
